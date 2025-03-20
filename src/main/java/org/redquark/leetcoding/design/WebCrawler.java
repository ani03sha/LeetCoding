package org.redquark.leetcoding.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WebCrawler {

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        // Set to store all the parsed URLs
        final Set<String> result = new HashSet<>();
        // Domain of the startUrl
        final String domain = getDomain(startUrl);
        // Queue to perform BFS
        final Queue<String> urls = new LinkedList<>();
        urls.offer(startUrl);
        // Process until we have URLs to parse in the queue
        while (!urls.isEmpty()) {
            // Get the current URL
            final String currentUrl = urls.remove();
            // Add this to the final result
            result.add(currentUrl);
            // Check its neighbors
            for (String nextUrl : htmlParser.getUrls(currentUrl)) {
                // Check if the nextUrl is from the same domain
                if (!result.contains(nextUrl) && getDomain(nextUrl).equals(domain)) {
                    urls.add(nextUrl);
                }
            }
        }
        return new ArrayList<>(result);
    }

    private String getDomain(String url) {
        final String domain = url.substring(7);
        return domain.split("/")[0];
    }

    interface HtmlParser {
        List<String> getUrls(String url);
    }

    static class HtmlParserImpl implements HtmlParser {

        private final Map<String, List<String>> webGraph;

        HtmlParserImpl(Map<String, List<String>> webGraph) {
            this.webGraph = webGraph;
        }

        @Override
        public List<String> getUrls(String url) {
            return webGraph.getOrDefault(url, new ArrayList<>());
        }
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
        WebCrawler webCrawler = new WebCrawler();
        List<String> result = webCrawler.crawl(startUrl, parser);
        System.out.println(result);
    }
}
