package edu.vwcc.recursion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * This program creates a text file print out of all code files from an entire
 * Java Project, recursively scanning all subdirectories too for source code,
 * writing/appending the contents of all .java files to a single text file.
 */
public class JavaProjectPrinter {

    private List<String> codeLines = new ArrayList<>();
    {
    	codeLines.add("Thank you for using JavaProjectPrinter - Version 0.0.1");
    	codeLines.add("Author: benlamb@vt.edu ");
    	codeLines.add("GitHub repo: "  );
    }


    /**
     * Scans and collects the content of all the .java files 
     * in the provided directory path and its subdirectories.
     * 
     * @param dir The directory path to start the scan.
     * @return A list of strings containing the content of all .java files.
     */
    public List<String> scanProjectFiles(Path dir) {
        
    	// Use try-with-resources to automatically manage the DirectoryStream's lifecycle
    	try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            
            // Iterate through each file or directory in the current directory
            for (Path filePath: stream) {
                
                // Handle Recursive Case: file is another Directory
                if (Files.isDirectory(filePath)) {
                    scanProjectFiles(filePath); // this is the recursive call
                } 
                // Base Case: 
                else if (filePath.toString().endsWith(".java")) {
                    
                    // Get the size of the file for informational purposes
                    long size = Files.size(filePath);
                    String fileInfo = filePath.toAbsolutePath() + ": " + size + " bytes\n";
                    
                    // Add file to outputs
                    codeLines.add("\n\nNEXT FILENAME: " + fileInfo);
                    for (String line : Files.readAllLines(filePath)) {
                    	codeLines.add(line);
                    }
                    System.out.println("SCANNING:" + fileInfo);
                }
            }
        } 
        catch (DirectoryIteratorException e) {
            System.err.println("Error iterating over directory contents: " + e.getMessage());
            e.getCause().printStackTrace();
        }
        catch (IOException e) {
            System.err.println("I/O error occurred: " + e.getMessage());
            e.printStackTrace();
        }
		
		return codeLines;
    }



    /**
     * Writes the content to a file named "code.txt" in the current working directory.
     * 
     * @param output The content to write to the file.
     */
    private void writeFileContents(List<String> output) {

    	String currentWorkingDir = System.getProperty("user.dir");
    	Path filePath = Paths.get(currentWorkingDir, "code.txt");

        // this will create or over-write an existing file
        try {
            Files.write(filePath, output);
        } catch (IOException e) {
            System.out.println("Error writing to text file: ");
            e.printStackTrace();
        }
	
    }
    
    
    public static void main(String[] args) throws IOException {
        JavaProjectPrinter jpp = new JavaProjectPrinter();
        List<String> output = jpp.scanProjectFiles(Paths.get(System.getProperty("user.dir"))); 
        jpp.writeFileContents(output);
    }
    
}
