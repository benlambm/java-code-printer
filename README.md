# JavaCodePrinter :coffee: :page_facing_up:

**JavaCodePrinter** is a Java utility that scans a specified directory and its subdirectories for `.java` files, collating their contents into a single text file. This tool is particularly useful for developers, educators, and students who need to consolidate Java source code for analysis, sharing, or archiving purposes.

This project is open-source and licensed under the MIT License, allowing you to use, modify, and distribute the code as you wish, provided you include the original copyright.

We welcome contributions through pull requests and encourage you to report any issues on the Issues board.

## Table of Contents

- [Usage](#usage-printer-rocket)
- [System Requirements](#system-requirements-coffee)
- [Use Cases](#use-cases-bulb)
- [License](#license-scroll)
- [Contributing](#contributing-handshake)

## Usage :printer: :rocket:

### Prerequisites

- **Java SE 17** or higher installed on your computer.
  - Verify your Java version by running `java --version` in your terminal.

### Compiling the Program

1. **Clone or Download** the repository to your local machine.

2. **Navigate** to the directory containing `JavaCodePrinter.java` in your terminal.

3. **Compile** the Java file using the following command:

   ```bash
   javac JavaCodePrinter.java
   ```

   This will generate a `JavaCodePrinter.class` file in the same directory.

### Running the Program

By default, the program scans the current working directory and its subdirectories, excluding certain directories like `build`, `test`, and `.git`. It collates all `.java` files into a single text file named `code.txt`.

#### Running from the Current Directory

To run the program from the directory containing the `JavaCodePrinter.class` file:

```bash
java JavaCodePrinter
```

To specify a custom output file name, provide it as an argument:

```bash
java JavaCodePrinter output_filename.txt
```

#### Running from Anywhere Using Environment Variables

To run `JavaCodePrinter` from any directory in your terminal, you can add its location to your system's environment variables.

##### Steps:

1. **Add the Directory to CLASSPATH**

   - **On Windows:**

     ```cmd
     set CLASSPATH=%CLASSPATH%;C:\path\to\directory
     ```

   - **On Unix/Linux/MacOS:**

     ```bash
     export CLASSPATH=$CLASSPATH:/path/to/directory
     ```

   Replace `C:\path\to\directory` or `/path/to/directory` with the actual path to the directory containing `JavaCodePrinter.class`.

2. **Move the Class File to a Common Directory (Optional)**

   Alternatively, you can place the `JavaCodePrinter.class` file in a directory that's already included in your `CLASSPATH`.

3. **Run the Program from Any Location**

   Now, you can run `JavaCodePrinter` from any directory:

   ```bash
   java JavaCodePrinter
   ```

   Remember that the program operates on the current working directory from which it is run.

### Notes

- **Excluding Directories:** The program automatically excludes certain common directories to prevent including unwanted files, such as compiled classes or test suites.
- **Charset Specification:** The program uses UTF-8 encoding when reading and writing files to ensure proper handling of special characters.
- **Progress Indicators:** The program displays progress in the console, showing the number of files processed and lines of code.
- **Data Summary:** At the end of processing, the program outputs a summary of the total number of Java files and lines of code.

## System Requirements :coffee:

- **Java SE 17** or higher installed on your computer.
- Works on Windows, macOS, and Linux systems.

## Use Cases :bulb:

1. **Learning & Teaching:** Educators and students can compile all `.java` files into one file for easier reading, sharing, and analysis.

2. **Code Portability for Assistance:** Easily share your entire Java project's source code with a teacher, tutor, or AI coding assistant without managing multiple files.

3. **Easier Code Metrics:** Run metrics on the whole project (e.g., lines of code, number of methods/classes) to get an overview of the code's size and complexity.

4. **Quick Project Overview:** Produce a single file containing all the Java code, making it easier to review the codebase, especially for new team members.

5. **Integration with Other Tools:** Integrate with tools that require a single source file for features like code formatting, highlighting, or converting to different formats (e.g., PDF).

6. **Audit & Compliance:** Simplify the audit process by providing a consolidated view of the code for compliance checks.

7. **Simple Text Backup:** Create a single text file backup of your Java project's source code as a snapshot in time.

## License :scroll:

This project is licensed under the **MIT License**. You are free to use, modify, and distribute the code, provided you include the original license notice in any copy of the software/source.

## Contributing :handshake:

We welcome contributions to this project! If you have a feature request, bug report, or want to improve the code, please feel free to:

- **Open an Issue:** Use the [Issues board](https://github.com/yourusername/java-code-printer/issues) to report bugs or suggest features.
- **Submit a Pull Request:** Fork the repository, make your changes, and submit a pull request for review.

Please ensure your code adheres to the project's coding standards and include appropriate documentation and tests.

---

**Thank you for using JavaCodePrinter!** If you find this tool helpful, please consider starring the repository and sharing it with others.

# Contact

- **Author:** Ben Lamb (benlamb@vt.edu)
- **GitHub Repo:** [https://github.com/benlambm/java-code-printer](https://github.com/benlambm/java-code-printer)

