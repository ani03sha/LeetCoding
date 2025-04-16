package org.redquark.leetcoding.utils;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeNAry {

    public int val;
    public List<TreeNodeNAry> children;

    public TreeNodeNAry(int _val) {
        val = _val;
        children = new ArrayList<TreeNodeNAry>();
    }

}
