package main;

/**
 * FileManager.java - Core File Operations Class
 * Handles all file-related operations including CRUD operations,
 * sorting, and searching functionality
 * 
 * @author [Your Name]
 * @version 1.0
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {
    
    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private File workingDirectory;
    
    /**
     * Constructor - initializes the working directory
     */
    public FileManager() {
        this.workingDirectory = new File(CURRENT_DIRECTORY);
    }
    
    /**
     * Constructor with custom directory path
     * @param directoryPath Path to the working directory
     */
    public FileManager(String directoryPath) {
        this.workingDirectory = new File(directoryPath);
        if (!workingDirectory.exists() || !workingDirectory.isDirectory()) {
            System.err.println("Warning: Directory does not exist, using current directory");
            this.workingDirectory = new File(CURRENT_DIRECTORY);
        }
    }
    
    /**
     * Displays all files in the current directory in ascending order
     * Uses Collections.sort() for efficient sorting
     */
    public void displayFilesAscending() {
        try {
            List<String> fileNames = getFileList();
            
            if (fileNames.isEmpty()) {
                System.out.println("The directory is empty. No files found.");
                return;
            }
            
            // Sort files in ascending order using Collections.sort()
            Collections.sort(fileNames);
            
            System.out.println("Files in directory (" + workingDirectory.getAbsolutePath() + "):");
            System.out.println("Total files found: " + fileNames.size());
            System.out.println("----------------------------------------");
            
            for (int i = 0; i < fileNames.size(); i++) {
                System.out.println((i + 1) + ". " + fileNames.get(i));
            }
            
            System.out.println("----------------------------------------");
            
        } catch (Exception e) {
            System.err.println("Error retrieving file list: " + e.getMessage());
            throw new RuntimeException("Failed to display files", e);
        }
    }
    
    /**
     * Adds a new file to the current directory
     * @param fileName Name of the file to create
     * @return true if file was created successfully, false otherwise
     */
    public boolean addFile(String fileName) {
        try {
            if (fileName == null || fileName.trim().isEmpty()) {
                System.err.println("Error: File name cannot be empty");
                return false;
            }
            
            File newFile = new File(workingDirectory, fileName.trim());
            
            // Check if file already exists
            if (newFile.exists()) {
                System.out.println("File '" + fileName + "' already exists in the directory.");
                return false;
            }
            
            // Create the new file
            boolean created = newFile.createNewFile();
            
            if (created) {
                System.out.println("File created at: " + newFile.getAbsolutePath());
                return true;
            } else {
                System.err.println("Failed to create file: " + fileName);
                return false;
            }
            
        } catch (IOException e) {
            System.err.println("IO Error while creating file '" + fileName + "': " + e.getMessage());
            return false;
        } catch (SecurityException e) {
            System.err.println("Security Error: Permission denied to create file '" + fileName + "': " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Unexpected error while creating file '" + fileName + "': " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Deletes a file from the current directory (case-sensitive)
     * @param fileName Name of the file to delete
     * @return true if file was deleted successfully, false otherwise
     */
    public boolean deleteFile(String fileName) {
        try {
            if (fileName == null || fileName.trim().isEmpty()) {
                System.err.println("Error: File name cannot be empty");
                return false;
            }
            
            File fileToDelete = new File(workingDirectory, fileName);
            
            // Check if file exists (case-sensitive)
            if (!fileToDelete.exists()) {
                return false; // File not found
            }
            
            // Check if it's actually a file (not a directory)
            if (!fileToDelete.isFile()) {
                System.err.println("Error: '" + fileName + "' is not a file");
                return false;
            }
            
            // Delete the file
            boolean deleted = fileToDelete.delete();
            
            if (deleted) {
                System.out.println("File deleted from: " + fileToDelete.getAbsolutePath());
                return true;
            } else {
                System.err.println("Failed to delete file: " + fileName);
                return false;
            }
            
        } catch (SecurityException e) {
            System.err.println("Security Error: Permission denied to delete file '" + fileName + "': " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Unexpected error while deleting file '" + fileName + "': " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Searches for a file in the current directory (case-sensitive)
     * @param fileName Name of the file to search for
     * @return true if file is found, false otherwise
     */
    public boolean searchFile(String fileName) {
        try {
            if (fileName == null || fileName.trim().isEmpty()) {
                System.err.println("Error: File name cannot be empty");
                return false;
            }
            
            File fileToSearch = new File(workingDirectory, fileName);
            
            // Check if file exists and is actually a file
            if (fileToSearch.exists() && fileToSearch.isFile()) {
                // Display file details
                displayFileDetails(fileToSearch);
                return true;
            } else {
                return false; // File not found
            }
            
        } catch (Exception e) {
            System.err.println("Error while searching for file '" + fileName + "': " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Gets a list of all files in the current directory
     * @return List of file names
     */
    private List<String> getFileList() {
        List<String> fileNames = new ArrayList<>();
        
        try {
            File[] files = workingDirectory.listFiles();
            
            if (files != null) {
                for (File file : files) {
                    // Only include files, not directories
                    if (file.isFile()) {
                        fileNames.add(file.getName());
                    }
                }
            }
        } catch (SecurityException e) {
            System.err.println("Security Error: Permission denied to access directory: " + e.getMessage());
            throw new RuntimeException("Cannot access directory", e);
        }
        
        return fileNames;
    }
    
    /**
     * Displays detailed information about a file
     * @param file File object to display details for
     */
    private void displayFileDetails(File file) {
        try {
            System.out.println("File Details:");
            System.out.println("  Name: " + file.getName());
            System.out.println("  Path: " + file.getAbsolutePath());
            System.out.println("  Size: " + formatFileSize(file.length()));
            System.out.println("  Last Modified: " + new java.util.Date(file.lastModified()));
            System.out.println("  Readable: " + file.canRead());
            System.out.println("  Writable: " + file.canWrite());
            System.out.println("  Executable: " + file.canExecute());
        } catch (Exception e) {
            System.err.println("Error retrieving file details: " + e.getMessage());
        }
    }
    
    /**
     * Formats file size in human-readable format
     * @param size File size in bytes
     * @return Formatted size string
     */
    private String formatFileSize(long size) {
        if (size < 1024) {
            return size + " bytes";
        } else if (size < 1024 * 1024) {
            return String.format("%.2f KB", size / 1024.0);
        } else if (size < 1024 * 1024 * 1024) {
            return String.format("%.2f MB", size / (1024.0 * 1024.0));
        } else {
            return String.format("%.2f GB", size / (1024.0 * 1024.0 * 1024.0));
        }
    }
    
    /**
     * Gets the current working directory
     * @return Current working directory path
     */
    public String getCurrentDirectory() {
        return workingDirectory.getAbsolutePath();
    }
    
    /**
     * Gets the count of files in the current directory
     * @return Number of files
     */
    public int getFileCount() {
        return getFileList().size();
    }
    
    /**
     * Checks if the directory is empty
     * @return true if directory is empty, false otherwise
     */
    public boolean isDirectoryEmpty() {
        return getFileList().isEmpty();
    }
    
    /**
     * Gets file names that match a pattern (for future enhancement)
     * @param pattern Pattern to match
     * @return List of matching file names
     */
    public List<String> searchFilesByPattern(String pattern) {
        List<String> matchingFiles = new ArrayList<>();
        List<String> allFiles = getFileList();
        
        for (String fileName : allFiles) {
            if (fileName.toLowerCase().contains(pattern.toLowerCase())) {
                matchingFiles.add(fileName);
            }
        }
        
        return matchingFiles;
    }
}