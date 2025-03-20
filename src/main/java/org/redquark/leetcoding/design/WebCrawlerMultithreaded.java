package org.redquark.leetcoding.design;

import org.redquark.leetcoding.utils.HtmlParser;
import org.redquark.leetcoding.utils.HtmlParserImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class WebCrawlerMultithreaded {

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        // Set to store the result
        final Set<String> result = new HashSet<>();
        // Hostname
        final String hostname = getDomain(startUrl);
        final ExecutorService executorService = Executors.newFixedThreadPool(1 << 6);
        result.add(startUrl);
        crawl(startUrl, hostname, executorService, htmlParser, result);
        executorService.shutdown();
        try {
            boolean isTerminated = executorService.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println("Is executor service terminated: " + isTerminated);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>(result);
    }

    @SuppressWarnings("rawtypes")
    private void crawl(String url, String hostname, ExecutorService executorService, HtmlParser htmlParser, Set<String> result) {
        final List<Future> futures = new ArrayList<>();
        for (String nextUrl : htmlParser.getUrls(url)) {
            if (!result.contains(nextUrl) && getDomain(nextUrl).equals(hostname)) {
                result.add(nextUrl);
                futures.add(executorService.submit(() -> crawl(nextUrl, hostname, executorService, htmlParser, result)));
            }
        }
        for (Future future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getDomain(String url) {
        final String domain = url.substring(7);
        return domain.split("/")[0];
    }

    public static void main(String[] args) {
        // Given input data
        List<String> urls = Arrays.asList(
                "http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.google.com",
                "http://news.yahoo.com/us"
        );
        int[][] edges = {
                {2, 0}, {2, 1}, {3, 2}, {3, 1}, {0, 4}
        };
        String startUrl = "http://news.yahoo.com/news/topics/";
        // Construct web graph
        Map<String, List<String>> webGraph = new HashMap<>();
        for (String url : urls) {
            webGraph.put(url, new ArrayList<>());
        }
        for (int[] edge : edges) {
            String from = urls.get(edge[0]);
            String to = urls.get(edge[1]);
            webGraph.get(from).add(to);
        }
        // Create an HtmlParser instance
        HtmlParser parser = new HtmlParserImpl(webGraph);
        // Crawl and print the result
        WebCrawlerMultithreaded webCrawler = new WebCrawlerMultithreaded();
        List<String> result = webCrawler.crawl(startUrl, parser);
        System.out.println(result);
    }
}
