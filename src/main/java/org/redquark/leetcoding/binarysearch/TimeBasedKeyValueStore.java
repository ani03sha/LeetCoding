package org.redquark.leetcoding.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeBasedKeyValueStore {

    static class TimeMap {

        private final Map<String, List<ValueTimeStore>> entries;

        public TimeMap() {
            this.entries = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            final ValueTimeStore valueTimeStore = new ValueTimeStore(value, timestamp);
            if (!this.entries.containsKey(key)) {
                this.entries.put(key, new ArrayList<>());
            }
            this.entries.get(key).add(valueTimeStore);
        }

        public String get(String key, int timestamp) {
            if (!this.entries.containsKey(key) || timestamp < this.entries.get(key).getFirst().timestamp) {
                return "";
            }
            final List<ValueTimeStore> valueTimeStores = this.entries.get(key);
            // Left and right pointers
            int left = 0;
            int right = valueTimeStores.size();
            // Perform binary search
            while (left < right) {
                final int middle = left + (right - left) / 2;
                if (timestamp >= valueTimeStores.get(middle).timestamp) {
                    left = middle + 1;
                } else {
                    right = middle;
                }
            }
            return right == 0 ? "" : valueTimeStores.get(right - 1).value;
        }

        static class ValueTimeStore {
            String value;
            int timestamp;

            ValueTimeStore(String value, int timestamp) {
                this.value = value;
                this.timestamp = timestamp;
            }
        }
    }

    public static void main(String[] args) {
        final TimeMap timeMap = new TimeMap();

        timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
        System.out.println(timeMap.get("foo", 1));         // return "bar"
        System.out.println(timeMap.get("foo", 3));         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
        timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
        System.out.println(timeMap.get("foo", 4));         // return "bar2"
        System.out.println(timeMap.get("foo", 5));         // return "bar2"
    }

}
