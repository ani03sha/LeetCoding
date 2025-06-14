package org.redquark.extras.solutions.utils;

import java.util.HashMap;
import java.util.Map;

public class TrieNodeWithDynamicChildren {
    public String value;
    public final Map<String, TrieNodeWithDynamicChildren> children;

    public TrieNodeWithDynamicChildren() {
        this.value = null;
        this.children = new HashMap<>();
    }
}
