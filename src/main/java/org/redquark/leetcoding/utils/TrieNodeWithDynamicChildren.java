package org.redquark.leetcoding.utils;

import java.util.HashMap;
import java.util.Map;

public class TrieNodeWithDynamicChildren {

    public int value;
    public final Map<String, TrieNodeWithDynamicChildren> children;

    public TrieNodeWithDynamicChildren() {
        this.value = -1;
        this.children = new HashMap<>();
    }
}
