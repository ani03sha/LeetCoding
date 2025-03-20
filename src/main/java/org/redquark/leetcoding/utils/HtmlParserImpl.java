package org.redquark.leetcoding.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HtmlParserImpl implements HtmlParser {

    private final Map<String, List<String>> webGraph;

    public HtmlParserImpl(Map<String, List<String>> webGraph) {
        this.webGraph = webGraph;
    }

    @Override
    public List<String> getUrls(String url) {
        return webGraph.getOrDefault(url, new ArrayList<>());
    }
}
