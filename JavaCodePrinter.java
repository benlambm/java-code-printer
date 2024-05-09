package algorithms;


import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This program uses Recursion to scan a Java Project's directory tree,
 * appending all Java source code files to a single text file called "code.txt"
 */
public class JavaCodePrinter {

    private List<String> codeLines = new ArrayList<>();
    {
    	codeLines.add("Thank you for using JavaCodePrinter - Version 0.0.2");
    	codeLines.add("Author: benlambm - benlamb@vt.edu");
    	codeLines.add("GitHub repo: https://github.com/benlambm/java-code-printer");
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
                	// skip this JavaCodePrinter.java file
                	if (filePath.toString().endsWith("JavaCodePrinter.java")) {
                		continue;
                	}
                	
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
        JavaCodePrinter jcp = new JavaCodePrinter();
        List<String> output = jcp.scanProjectFiles(Paths.get(System.getProperty("user.dir"))); 
        jcp.writeFileContents(output);
    }
    
}
