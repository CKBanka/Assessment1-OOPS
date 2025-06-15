package main;

/**
 * LockedMe.com - File Management Application
 * Main Application Class
 * 
 * @author Chirag
 * @version 1.01
 * @company Company LockedMe.com pvt Ltd..
 */

public class MenuHandler {
    
    private static final String MENU_SEPARATOR = "========================================";
    private static final String SUB_SEPARATOR = "----------------------------------------";

    public void displayMainMenu() {
        System.out.println("\n" + MENU_SEPARATOR);
        System.out.println("           MAIN MENU");
        System.out.println(MENU_SEPARATOR);
        System.out.println("1. Display all files (Ascending Order)");
        System.out.println("2. File Operations");
        System.out.println("3. Exit Application");
        System.out.println(MENU_SEPARATOR);
        System.out.print("Please select an option (1-3): ");
    }
    

    public void displayFileOperationsMenu() {
        System.out.println("\n" + SUB_SEPARATOR);
        System.out.println("        FILE OPERATIONS MENU");
        System.out.println(SUB_SEPARATOR);
        System.out.println("1. Add a file");
        System.out.println("2. Delete a file");
        System.out.println("3. Search for a file");
        System.out.println("4. Return to Main Menu");
        System.out.println(SUB_SEPARATOR);
        System.out.print("Please select an option (1-4): ");
    }
    

    public void displayMainMenuHelp() {
        System.out.println("\n" + SUB_SEPARATOR);
        System.out.println("           HELP - MAIN MENU");
        System.out.println(SUB_SEPARATOR);
        System.out.println("Option 1: Display Files");
        System.out.println("  - Shows all files in the current directory");
        System.out.println("  - Files are sorted in ascending alphabetical order");
        System.out.println("  - Displays total file count");
        System.out.println();
        System.out.println("Option 2: File Operations");
        System.out.println("  - Opens the file operations sub-menu");
        System.out.println("  - Allows you to add, delete, or search files");
        System.out.println();
        System.out.println("Option 3: Exit");
        System.out.println("  - Safely closes the application");
        System.out.println(SUB_SEPARATOR);
    }
    
    /**
     * Displays help information for file operations menu
     */
    public void displayFileOperationsHelp() {
        System.out.println("\n" + SUB_SEPARATOR);
        System.out.println("       HELP - FILE OPERATIONS");
        System.out.println(SUB_SEPARATOR);
        System.out.println("Option 1: Add a file");
        System.out.println("  - Creates a new empty file in the current directory");
        System.out.println("  - File names are case-insensitive for creation");
        System.out.println("  - Will not overwrite existing files");
        System.out.println();
        System.out.println("Option 2: Delete a file");
        System.out.println("  - Removes a file from the current directory");
        System.out.println("  - File names are CASE-SENSITIVE for deletion");
        System.out.println("  - Shows 'File Not Found' if file doesn't exist");
        System.out.println();
        System.out.println("Option 3: Search for a file");
        System.out.println("  - Searches for a file in the current directory");
        System.out.println("  - File names are CASE-SENSITIVE for searching");
        System.out.println("  - Displays file details if found");
        System.out.println();
        System.out.println("Option 4: Return to Main Menu");
        System.out.println("  - Goes back to the main application menu");
        System.out.println(SUB_SEPARATOR);
    }
    
    /**
     * Displays application information
     */
    public void displayApplicationInfo() {
        System.out.println("\n" + MENU_SEPARATOR);
        System.out.println("        APPLICATION INFORMATION");
        System.out.println(MENU_SEPARATOR);
        System.out.println("Application: LockedMe.com File Manager");
        System.out.println("Version: 1.0");
        System.out.println("Developer: [Your Name]");
        System.out.println("Company: Company Lockers Pvt. Ltd.");
        System.out.println();
        System.out.println("Description:");
        System.out.println("A command-line file management application that allows");
        System.out.println("users to perform basic file operations including:");
        System.out.println("- Viewing files in sorted order");
        System.out.println("- Adding new files");
        System.out.println("- Deleting existing files");
        System.out.println("- Searching for specific files");
        System.out.println();
        System.out.println("Features:");
        System.out.println("- Efficient sorting algorithms");
        System.out.println("- Case-sensitive file operations");
        System.out.println("- Comprehensive error handling");
        System.out.println("- User-friendly interface");
        System.out.println("- Cross-platform compatibility");
        System.out.println(MENU_SEPARATOR);
    }
    
    /**
     * Displays current directory information
     */
    public void displayDirectoryInfo(String directoryPath, int fileCount) {
        System.out.println("\n" + SUB_SEPARATOR);
        System.out.println("       CURRENT DIRECTORY INFO");
        System.out.println(SUB_SEPARATOR);
        System.out.println("Working Directory: " + directoryPath);
        System.out.println("Total Files: " + fileCount);
        System.out.println("Status: " + (fileCount == 0 ? "Empty" : "Contains files"));
        System.out.println(SUB_SEPARATOR);
    }
    
    /**
     * Displays error message in a formatted way
     */
    public void displayError(String errorMessage) {
        System.out.println("\n" + SUB_SEPARATOR);
        System.out.println("ERROR: " + errorMessage);
        System.out.println(SUB_SEPARATOR);
    }
    

    public void displaySuccess(String successMessage) {
        System.out.println("\n" + SUB_SEPARATOR);
        System.out.println("SUCCESS: " + successMessage);
        System.out.println(SUB_SEPARATOR);
    }
    
    /**
     * Displays warning message in a formatted way
     */
    public void displayWarning(String warningMessage) {
        System.out.println("\n" + SUB_SEPARATOR);
        System.out.println("WARNING: " + warningMessage);
        System.out.println(SUB_SEPARATOR);
    }
    

    public void displayLoading(String operation) {
        System.out.print("\n" + operation + "...");
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(300);
                System.out.print(".");
            }
            System.out.println(" Done!");
        } catch (InterruptedException e) {
            System.out.println(" Done!");
            Thread.currentThread().interrupt();
        }
    }
    

    public String getConfirmationPrompt(String message) {
        return "\n" + SUB_SEPARATOR + "\n" + 
               "CONFIRMATION: " + message + " (y/n): ";
    }
    

    public void displayOperationResult(String operation, String fileName, boolean success) {
        System.out.println("\n" + SUB_SEPARATOR);
        System.out.println("OPERATION: " + operation.toUpperCase());
        System.out.println("FILE: " + fileName);
        System.out.println("RESULT: " + (success ? "SUCCESS" : "FAILED"));
        System.out.println(SUB_SEPARATOR);
    }

    public void displayInputPrompt(String promptMessage) {
        System.out.print("\n" + promptMessage + ": ");
    }
    

    public void displayHeader(String title) {
        System.out.println("\n" + MENU_SEPARATOR);
        System.out.println("  " + title.toUpperCase());
        System.out.println(MENU_SEPARATOR);
    }
    

    public void displaySubHeader(String title) {
        System.out.println("\n" + SUB_SEPARATOR);
        System.out.println("  " + title);
        System.out.println(SUB_SEPARATOR);
    }
    

    public void refreshScreen() {
        for (int i = 0; i < 2; i++) {
            System.out.println();
        }
        
        System.out.println(MENU_SEPARATOR);
        System.out.println("      LockedMe.com File Manager");
        System.out.println(MENU_SEPARATOR);
    }
}
