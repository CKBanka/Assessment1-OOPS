package main;

/**
 * LockedMe.com - File Management Application
 * Main Application Class
 * 
 * @author Chirag
 * @version 1.01
 * @company Company LockedMe.com pvt Ltd..
 */

import java.util.Scanner;

public class LockedMeApp {
    
    private static final String APP_NAME = "LockedMe.com";
    private static final String DEVELOPER_NAME = "Chirag";
    private static final String COMPANY_NAME = "Company LockedMe.com pvt. Ltd.";
    private static final String VERSION = "1.01";
    
    private static Scanner scanner = new Scanner(System.in);
    private static FileManager fileManager = new FileManager();
    private static MenuHandler menuHandler = new MenuHandler();
    private static InputValidator inputValidator = new InputValidator();
    
    public static void main(String[] args) {
        try {
            displayWelcomeScreen();
            runApplication();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Displays the welcome screen with application and developer details
     */
    private static void displayWelcomeScreen() {
        System.out.println("=========================================");
        System.out.println("    Welcome to " + APP_NAME);
        System.out.println("=========================================");
        System.out.println("Developer: " + DEVELOPER_NAME);
        System.out.println("Company: " + COMPANY_NAME);
        System.out.println("Version: " + VERSION);
        System.out.println("=========================================");
        System.out.println();
    }
    
    /**
     * Main application loop - handles user interaction
     */
    private static void runApplication() {
        boolean continueRunning = true;
        
        while (continueRunning) {
            try {
                menuHandler.displayMainMenu();
                int choice = inputValidator.getValidMenuChoice(scanner, 1, 3);
                
                switch (choice) {
                    case 1:
                        handleDisplayFiles();
                        break;
                    case 2:
                        handleFileOperations();
                        break;
                    case 3:
                        continueRunning = false;
                        displayExitMessage();
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                
                if (continueRunning) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                    clearScreen();
                }
                
            } catch (Exception e) {
                System.err.println("Error in main menu: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
    
    /**
     * Handles displaying files in ascending order
     */
    private static void handleDisplayFiles() {
        System.out.println("\n=== Files in Current Directory (Ascending Order) ===");
        try {
            fileManager.displayFilesAscending();
        } catch (Exception e) {
            System.err.println("Error displaying files: " + e.getMessage());
        }
    }
    
    /**
     * Handles file operations sub-menu
     */
    private static void handleFileOperations() {
        boolean backToMain = false;
        
        while (!backToMain) {
            try {
                menuHandler.displayFileOperationsMenu();
                int choice = inputValidator.getValidMenuChoice(scanner, 1, 4);
                
                switch (choice) {
                    case 1:
                        handleAddFile();
                        break;
                    case 2:
                        handleDeleteFile();
                        break;
                    case 3:
                        handleSearchFile();
                        break;
                    case 4:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                
                if (!backToMain) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
                
            } catch (Exception e) {
                System.err.println("Error in file operations menu: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }
    
    /**
     * Handles adding a new file
     */
    private static void handleAddFile() {
        System.out.println("\n=== Add File ===");
        try {
            System.out.print("Enter file name to add: ");
            String fileName = scanner.nextLine().trim();
            
            if (inputValidator.isValidFileName(fileName)) {
                boolean success = fileManager.addFile(fileName);
                if (success) {
                    System.out.println("File '" + fileName + "' added successfully!");
                } else {
                    System.out.println("Failed to add file '" + fileName + "'. File may already exist.");
                }
            } else {
                System.out.println("Invalid file name. Please enter a valid file name.");
            }
        } catch (Exception e) {
            System.err.println("Error adding file: " + e.getMessage());
        }
    }
    
    /**
     * Handles deleting a file
     */
    private static void handleDeleteFile() {
        System.out.println("\n=== Delete File ===");
        try {
            System.out.print("Enter file name to delete (case-sensitive): ");
            String fileName = scanner.nextLine().trim();
            
            if (inputValidator.isValidFileName(fileName)) {
                boolean success = fileManager.deleteFile(fileName);
                if (success) {
                    System.out.println("File '" + fileName + "' deleted successfully!");
                } else {
                    System.out.println("File Not Found (FNF): '" + fileName + "' does not exist.");
                }
            } else {
                System.out.println("Invalid file name. Please enter a valid file name.");
            }
        } catch (Exception e) {
            System.err.println("Error deleting file: " + e.getMessage());
        }
    }
    
    /**
     * Handles searching for a file
     */
    private static void handleSearchFile() {
        System.out.println("\n=== Search File ===");
        try {
            System.out.print("Enter file name to search (case-sensitive): ");
            String fileName = scanner.nextLine().trim();
            
            if (inputValidator.isValidFileName(fileName)) {
                boolean found = fileManager.searchFile(fileName);
                if (found) {
                    System.out.println("Search Result: File '" + fileName + "' found successfully!");
                } else {
                    System.out.println("Search Result: File '" + fileName + "' not found in the directory.");
                }
            } else {
                System.out.println("Invalid file name. Please enter a valid file name.");
            }
        } catch (Exception e) {
            System.err.println("Error searching file: " + e.getMessage());
        }
    }
    
    /**
     * Displays exit message
     */
    private static void displayExitMessage() {
        System.out.println("\n=========================================");
        System.out.println("Thank you for using " + APP_NAME + "!");
        System.out.println("Application closed successfully.");
        System.out.println("=========================================");
    }
    
    /**
     * Clears the console screen (platform independent)
     */
    private static void clearScreen() {
        try {
            // For Windows
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // For Unix/Linux/Mac
                System.out.print("\033[2J\033[H");
            }
        } catch (Exception e) {
            // If screen clearing fails, just print some newlines
            for (int i = 0; i < 3; i++) {
                System.out.println();
            }
        }
    }
}