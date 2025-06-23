package org.redquark.extras.solutions.design;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class UnixTailNCommand {

    public List<String> tail(InputStream inputStream, int N) {
        // We don't know how many lines there are beforehand, so we maintain
        // only the last N lines using circular buffer
        final Deque<String> buffer = new ArrayDeque<>(N);
        // Process the input stream
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            // Current line
            String line;
            // Read the input stream line by line
            while ((line = reader.readLine()) != null) {
                if (N == 0) {
                    continue;
                }
                if (buffer.size() == N) {
                    buffer.removeFirst();
                }
                buffer.offerLast(line);
            }
            return new ArrayList<>(buffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        final UnixTailNCommand unixTailNCommand = new UnixTailNCommand();
        String data = String.join("\n",
                "Line1",
                "Line2",
                "Line3",
                "Line4",
                "Line5"
        );
        InputStream input = new ByteArrayInputStream(data.getBytes());
        List<String> result = unixTailNCommand.tail(input, 3);

        for (String line : result) {
            System.out.println(line);
        }
    }
}
