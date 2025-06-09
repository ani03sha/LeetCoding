package org.redquark.leetcoding.arrays;

public class CrawlerLogFolder {

    public int minOperations(String[] logs) {
        // Depth from the main folder
        int depth = 0;
        // Process logs
        for (String log : logs) {
            if (log.equals("./")) {
                continue;
            }
            if (log.equals("../")) {
                if (depth > 0) {
                    depth--;
                }
            } else {
                depth++;
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        final CrawlerLogFolder crawlerLogFolder = new CrawlerLogFolder();

        String[] logs = new String[]{"d1/", "d2/", "../", "d21/", "./"};
        System.out.println(crawlerLogFolder.minOperations(logs)); // 2

        logs = new String[]{"d1/", "d2/", "./", "d3/", "../", "d31/"};
        System.out.println(crawlerLogFolder.minOperations(logs)); // 3

        logs = new String[]{"d1/", "../", "../", "../"};
        System.out.println(crawlerLogFolder.minOperations(logs)); // 0
    }
}
