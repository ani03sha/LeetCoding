package org.redquark.leetcoding.utils;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {

    private Integer value;
    private List<NestedInteger> list;

    // Constructor initializes an empty nested list.
    public NestedInteger() {
        this.list = new ArrayList<>();
    }

    // Constructor initializes a single integer.
    public NestedInteger(int value) {
        this.value = value;
        this.list = null; // Indicates that this is a single integer
    }

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return value != null;
    }

    // @return the single integer that this NestedInteger holds if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return value;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        this.value = value;
        this.list = null; // Clear the list since it's now an integer
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        if (this.list == null) {
            this.list = new ArrayList<>();
            this.value = null; // Clear the integer since it's now a list
        }
        this.list.add(ni);
    }

    // @return the nested list that this NestedInteger holds if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list != null ? list : new ArrayList<>();
    }
}

