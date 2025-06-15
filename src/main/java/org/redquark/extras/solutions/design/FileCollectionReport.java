package org.redquark.extras.solutions.design;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class FileCollectionReport {

    public ReportResult generateReport(List<File> files, int topN) {
        // Map to store collections and their respective sizes
        final ConcurrentHashMap<String, Long> collectionSizeMappings = new ConcurrentHashMap<>();
        final AtomicLong size = new AtomicLong(0);
        // Process all files
        for (File file : files) {
            size.addAndGet(file.size);
            for (String collectionId : file.collectionIds) {
                collectionSizeMappings.merge(collectionId, (long) file.size, Long::sum);
            }
        }
        // Find top collections
        final List<Map.Entry<String, Long>> topCollections = collectionSizeMappings.entrySet()
                .stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(topN)
                .toList();
        return new ReportResult(size.get(), topCollections);
    }

    record File(String fileId, int size, List<String> collectionIds) {
    }

    record ReportResult(long totalSize, List<Map.Entry<String, Long>> topCollections) {
    }

    public static void main(String[] args) {
        final FileCollectionReport fileCollectionReport = new FileCollectionReport();
        List<File> files = List.of(
                new File("f1", 100, List.of("c1")),
                new File("f2", 200, List.of("c1", "c2")),
                new File("f3", 300, List.of("c3")),
                new File("f4", 50, List.of("c2"))
        );
        int topN = 2;
        ReportResult result = fileCollectionReport.generateReport(files, topN);
        System.out.println("Total Size: " + result.totalSize);
        System.out.println("Top " + topN + " Collections:");
        for (Map.Entry<String, Long> entry : result.topCollections) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
