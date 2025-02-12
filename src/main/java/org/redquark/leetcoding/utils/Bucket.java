package org.redquark.leetcoding.utils;

import java.util.HashSet;
import java.util.Set;

public class Bucket {

    public int frequency;
    public Set<String> keys;
    public Bucket next;
    public Bucket previous;

    public Bucket(int frequency) {
        this.frequency = frequency;
        this.keys = new HashSet<>();
    }
}
