package org.redquark.leetcoding.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class SnapshotArray {

    // Map to store snapIds and elements in that snap
    private final Map<Integer, TreeMap<Integer, Integer>> mappings;
    // Snap Id;
    private int snapId;

    public SnapshotArray(int length) {
        this.mappings = new HashMap<>();
        this.snapId = 0;
    }

    public void set(int index, int val) {
        this.mappings.computeIfAbsent(index, _ -> new TreeMap<>()).put(this.snapId, val);
    }

    public int snap() {
        return this.snapId++;
    }

    public int get(int index, int snap_id) {
        if (!this.mappings.containsKey(index)) {
            return 0;
        }
        final Map.Entry<Integer, Integer> entry = this.mappings.get(index).floorEntry(snap_id);
        return entry == null ? 0 : entry.getValue();
    }

    public static void main(String[] args) {
        final SnapshotArray snapshotArray = new SnapshotArray(3);

        snapshotArray.set(0, 5);
        System.out.println(snapshotArray.snap()); // 0
        snapshotArray.set(0, 6);
        System.out.println(snapshotArray.get(0, 0)); // 5
    }
}
