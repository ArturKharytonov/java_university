package save;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FileHandler {
    private static final PrintWriter printWriter;
    private static final String FILE_NAME = "Saved_Battle.txt";
    private static final StringBuilder history;
    private static final Object lock = new Object();

    static {
        try {
            printWriter = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, false)));
            history = new StringBuilder();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CompletableFuture<Void> writeToFileAsync() {
        return CompletableFuture.runAsync(() -> {
            synchronized (lock) {
                try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(FILE_NAME, true)))) {
                    printWriter.println(history);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        });
    }

    public static CompletableFuture<List<String>> readFromFileAsync() {
        return CompletableFuture.supplyAsync(() -> {
            synchronized (lock) {
                try {
                    List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
                    return lines;
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        });
    }

    public static void addToHistory(String text) { history.append(text); }
    public static void closeFile() { printWriter.close(); }
}

