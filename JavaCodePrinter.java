import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This program scans a Java project's directory tree,
 * appending all Java source code files to a single text file.
 */
public class JavaCodePrinter {

    private static final String DEFAULT_OUTPUT_FILE_NAME = "code.txt";
    private static final String CURRENT_FILE_NAME = "JavaCodePrinter.java";

    private int totalFilesProcessed = 0;
    private long totalLinesOfCode = 0;

    // Set of directory names to exclude
    private static final Set<String> EXCLUDED_DIRS = new HashSet<>();

    static {
        EXCLUDED_DIRS.add("build");
        EXCLUDED_DIRS.add("out");
        EXCLUDED_DIRS.add("bin");
        EXCLUDED_DIRS.add("test");
        EXCLUDED_DIRS.add(".git");
        EXCLUDED_DIRS.add(".idea");
        EXCLUDED_DIRS.add("target");
        EXCLUDED_DIRS.add("node_modules");
    }

    public static void main(String[] args) {
        JavaCodePrinter jcp = new JavaCodePrinter();
        jcp.run(args);
    }

    public void run(String[] args) {
        Path startDir = Paths.get(System.getProperty("user.dir"));

        // Determine output file name
        String outputFileName = args.length > 0 ? args[0] : DEFAULT_OUTPUT_FILE_NAME;
        Path outputFilePath = startDir.resolve(outputFileName);

        System.out.println("Starting JavaCodePrinter...");

        try (BufferedWriter writer = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8)) {
            // Write header information
            writer.write("Thank you for using JavaCodePrinter - Version 0.1.0\n");
            writer.write("Author: benlambm - benlamb@vt.edu\n");
            writer.write("GitHub repo: https://github.com/benlambm/java-code-printer\n");

            // Walk the file tree and process .java files, excluding certain directories
            Files.walk(startDir)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().endsWith(".java"))
                    .filter(p -> !p.getFileName().toString().equals(CURRENT_FILE_NAME))
                    .filter(p -> !isInExcludedDir(p))
                    .forEach(p -> processJavaFile(p, writer));

            // Write data summary to the output file
            writer.write("\n\n=== DATA SUMMARY ===\n");
            writer.write("Total Java files processed: " + totalFilesProcessed + "\n");
            writer.write("Total lines of code: " + totalLinesOfCode + "\n");

            System.out.println("\nAll Java files have been scanned and written to " + outputFileName);
            System.out.println("Total Java files processed: " + totalFilesProcessed);
            System.out.println("Total lines of code: " + totalLinesOfCode);
            System.out.println("JavaCodePrinter completed successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred during processing: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void processJavaFile(Path javaFilePath, BufferedWriter writer) {
        try {
            totalFilesProcessed++;
            long size = Files.size(javaFilePath);
            String fileInfo = javaFilePath.toAbsolutePath() + ": " + size + " bytes";
            writer.write("\n\n=== NEXT FILENAME: " + fileInfo + " ===\n");

            // Read lines with specified charset
            long lineCount = 0;
            for (String line : Files.readAllLines(javaFilePath, StandardCharsets.UTF_8)) {
                writer.write(line);
                writer.newLine();
                totalLinesOfCode++;
                lineCount++;
            }
            System.out.println("Processed file " + totalFilesProcessed + ": " + javaFilePath.getFileName() + " (" + lineCount + " lines)");
        } catch (IOException e) {
            System.err.println("Error processing file " + javaFilePath + ": " + e.getMessage());
        }
    }

    private static boolean isInExcludedDir(Path path) {
        Path parent = path.getParent();
        while (parent != null) {
            Path fileName = parent.getFileName();
            if (fileName != null && EXCLUDED_DIRS.contains(fileName.toString())) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }
}
