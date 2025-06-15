package test;
/**
 * LockedMe.com - File Management Application
 * Main Application Class
 * 
 * @author Chirag
 * @version 1.01
 * @company Company LockedMe.com pvt Ltd..
 */
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import main.FileManager;
import main.InputValidator;

public class FileManagerTest {
    
    private FileManager fileManager;
    private InputValidator inputValidator;
    private String testDirectory;
    
    // Test files for testing
    private static final String[] TEST_FILES = {
        "test1.txt", "test2.txt", "testA.txt", "testB.txt", "sample.doc"
    };

    public void setUp() {
        testDirectory = System.getProperty("user.dir") + File.separator + "test_files";
        File testDir = new File(testDirectory);
        if (!testDir.exists()) {
            testDir.mkdir();
        }
        
        fileManager = new FileManager(testDirectory);
        inputValidator = new InputValidator();
        
        System.out.println("Test setup completed. Test directory: " + testDirectory);
    }

    public void tearDown() {
        File testDir = new File(testDirectory);
        if (testDir.exists()) {
            File[] files = testDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        file.delete();
                    }
                }
            }
            testDir.delete();
        }
        
        System.out.println("Test cleanup completed.");
    }
    
    /**
     * Tests file addition functionality
     */
    public void testAddFile() {
        System.out.println("\n=== Testing File Addition ===");
        
        boolean result1 = fileManager.addFile("testAdd1.txt");
        assert result1 : "Failed to add valid file";
        System.out.println("✓ Successfully added testAdd1.txt");
        
        boolean result2 = fileManager.addFile("testAdd1.txt");
        assert !result2 : "Should not add duplicate file";
        System.out.println("✓ Correctly rejected duplicate file");
        
        boolean result3 = fileManager.addFile("");
        assert !result3 : "Should not add empty filename";
        System.out.println("✓ Correctly rejected empty filename");
        
        boolean result4 = fileManager.addFile("test<>.txt");
        assert !result4 : "Should not add file with invalid characters";
        System.out.println("✓ Correctly rejected file with invalid characters");
        
        System.out.println("File addition tests completed successfully!");
    }

    public void testDeleteFile() {
        System.out.println("\n=== Testing File Deletion ===");
        
        // First add a file to delete
        fileManager.addFile("testDelete.txt");
        
        boolean result1 = fileManager.deleteFile("testDelete.txt");
        assert result1 : "Failed to delete existing file";
        System.out.println("✓ Successfully deleted existing file");
        
        boolean result2 = fileManager.deleteFile("nonExistentFile.txt");
        assert !result2 : "Should not delete non-existent file";
        System.out.println("✓ Correctly rejected deletion of non-existent file");
        
        boolean result3 = fileManager.deleteFile("");
        assert !result3 : "Should not delete with empty filename";
        System.out.println("✓ Correctly rejected empty filename for deletion");
        
        boolean result4 = fileManager.deleteFile(null);
        assert !result4 : "Should not delete with null filename";
        System.out.println("✓ Correctly rejected null filename for deletion");
        
        System.out.println("File deletion tests completed successfully!");
    }
    

    public void testSearchFile() {
        System.out.println("\n=== Testing File Search ===");
        
        fileManager.addFile("testSearch.txt");
        
        boolean result1 = fileManager.searchFile("testSearch.txt");
        assert result1 : "Failed to find existing file";
        System.out.println("✓ Successfully found existing file");
        
        boolean result2 = fileManager.searchFile("nonExistentFile.txt");
        assert !result2 : "Should not find non-existent file";
        System.out.println("✓ Correctly reported non-existent file as not found");
        
        boolean result3 = fileManager.searchFile("");
        assert !result3 : "Should not search with empty filename";
        System.out.println("✓ Correctly rejected empty filename for search");
        
        boolean result4 = fileManager.searchFile(null);
        assert !result4 : "Should not search with null filename";
        System.out.println("✓ Correctly rejected null filename for search");
        
        System.out.println("File search tests completed successfully!");
    }
    

    public void testDisplayFilesAscending() {
        System.out.println("\n=== Testing File Display (Sorting) ===");
        
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        try {
            fileManager.addFile("zebra.txt");
            fileManager.addFile("apple.txt");
            fileManager.addFile("banana.txt");
            fileManager.addFile("cherry.txt");
            
            fileManager.displayFilesAscending();
            
            String output = outputStream.toString();
            
            // Check if files appear in correct order
            int appleIndex = output.indexOf("apple.txt");
            int bananaIndex = output.indexOf("banana.txt");
            int cherryIndex = output.indexOf("cherry.txt");
            int zebraIndex = output.indexOf("zebra.txt");
            
            assert appleIndex < bananaIndex : "Files not sorted correctly";
            assert bananaIndex < cherryIndex : "Files not sorted correctly";
            assert cherryIndex < zebraIndex : "Files not sorted correctly";
            
        } finally {
            System.setOut(originalOut);
        }
        
        System.out.println("✓ Files displayed in correct ascending order");
        System.out.println("File display tests completed successfully!");
    }
    

    public void testInputValidator() {
        System.out.println("\n=== Testing Input Validator ===");
        
        assert inputValidator.isValidFileName("test.txt") : "Valid filename rejected";
        assert inputValidator.isValidFileName("document.doc") : "Valid filename rejected";
        assert inputValidator.isValidFileName("file123.pdf") : "Valid filename rejected";
        System.out.println("✓ Valid filenames accepted correctly");
        
        assert !inputValidator.isValidFileName("") : "Empty filename accepted";
        assert !inputValidator.isValidFileName(null) : "Null filename accepted";
        assert !inputValidator.isValidFileName("file<>.txt") : "Filename with invalid characters accepted";
        assert !inputValidator.isValidFileName("CON") : "Reserved filename accepted";
        System.out.println("✓ Invalid filenames rejected correctly");
        
        String sanitized1 = inputValidator.sanitizeFileName("test<>file.txt");
        assert !sanitized1.contains("<") && !sanitized1.contains(">") : "Sanitization failed";
        System.out.println("✓ Filename sanitization working correctly");
        
        assert inputValidator.isValidLength("test", 1, 10) : "Valid length rejected";
        assert !inputValidator.isValidLength("verylongfilename", 1, 5) : "Invalid length accepted";
        System.out.println("✓ Length validation working correctly");
        
        System.out.println("Input validator tests completed successfully!");
    }
    

    public void testFileCount() {
        System.out.println("\n=== Testing File Count ===");
        
        int initialCount = fileManager.getFileCount();
        System.out.println("Initial file count: " + initialCount);
        
        fileManager.addFile("count1.txt");
        fileManager.addFile("count2.txt");
        fileManager.addFile("count3.txt");
        
        int newCount = fileManager.getFileCount();
        assert newCount == initialCount + 3 : "File count not updated correctly after addition";
        System.out.println("✓ File count correct after adding files: " + newCount);
        
        fileManager.deleteFile("count1.txt");
        int afterDeleteCount = fileManager.getFileCount();
        assert afterDeleteCount == newCount - 1 : "File count not updated correctly after deletion";
        System.out.println("✓ File count correct after deleting file: " + afterDeleteCount);
        
        System.out.println("File count tests completed successfully!");
    }
    

    public void testDirectoryEmptyCheck() {
        System.out.println("\n=== Testing Directory Empty Check ===");
        
        tearDown();
        setUp();
        
        boolean isEmpty1 = fileManager.isDirectoryEmpty();
        assert isEmpty1 : "Empty directory not detected as empty";
        System.out.println("✓ Empty directory correctly identified");
        
        fileManager.addFile("emptyTest.txt");
        boolean isEmpty2 = fileManager.isDirectoryEmpty();
        assert !isEmpty2 : "Non-empty directory detected as empty";
        System.out.println("✓ Non-empty directory correctly identified");
        
        System.out.println("Directory empty check tests completed successfully!");
    }

    public void testSearchFilesByPattern() {
        System.out.println("\n=== Testing Pattern Search ===");
        
        // Add test files with different patterns
        fileManager.addFile("test_document.txt");
        fileManager.addFile("test_image.jpg");
        fileManager.addFile("document.doc");
        fileManager.addFile("image.png");
        
        // Search for files containing "test"
        var testFiles = fileManager.searchFilesByPattern("test");
        assert testFiles.size() == 2 : "Pattern search not working correctly";
        System.out.println("✓ Pattern search found correct number of files: " + testFiles.size());
        
        // Search for files containing "doc"
        var docFiles = fileManager.searchFilesByPattern("doc");
        assert docFiles.size() >= 2 : "Pattern search not finding all matches";
        System.out.println("✓ Pattern search working correctly for 'doc' pattern");
        
        // Search for non-existent pattern
        var noFiles = fileManager.searchFilesByPattern("xyz123");
        assert noFiles.size() == 0 : "Pattern search finding files that don't match";
        System.out.println("✓ Pattern search correctly returns empty for non-matching pattern");
        
        System.out.println("Pattern search tests completed successfully!");
    }
    
    /**
     * Tests edge cases and error handling
     */
    public void testEdgeCases() {
        System.out.println("\n=== Testing Edge Cases ===");
        
        // Test with very long filename
        StringBuilder longName = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            longName.append("a");
        }
        longName.append(".txt");
        
        boolean longResult = fileManager.addFile(longName.toString());
        assert !longResult : "Very long filename should be rejected";
        System.out.println("✓ Very long filename correctly rejected");
        
        boolean spaceResult = fileManager.addFile("   ");
        assert !spaceResult : "Filename with only spaces should be rejected";
        System.out.println("✓ Filename with only spaces correctly rejected");
        
        // Tet case sensitivity
        fileManager.addFile("CaseSensitive.txt");
        boolean searchLower = fileManager.searchFile("casesensitive.txt");
        boolean searchCorrect = fileManager.searchFile("CaseSensitive.txt");
        assert !searchLower : "Search should be case sensitive";
        assert searchCorrect : "Search should find exact case match";
        System.out.println("✓ Case sensitivity working correctly");
        
        System.out.println("Edge case tests completed successfully!");
    }
    

    public void runAllTests() {
        System.out.println("========================================");
        System.out.println("    FileManager Unit Tests Starting");
        System.out.println("========================================");
        
        try {
            setUp();
            
            testAddFile();
            testDeleteFile();
            testSearchFile();
            testDisplayFilesAscending();
            testInputValidator();
            testFileCount();
            testDirectoryEmptyCheck();
            testSearchFilesByPattern();
            testEdgeCases();
            
            System.out.println("\n========================================");
            System.out.println("    ALL TESTS PASSED SUCCESSFULLY! ✓");
            System.out.println("========================================");
            
        } catch (AssertionError e) {
            System.err.println("\n========================================");
            System.err.println("    TEST FAILED: " + e.getMessage());
            System.err.println("========================================");
        } catch (Exception e) {
            System.err.println("\n========================================");
            System.err.println("    UNEXPECTED ERROR: " + e.getMessage());
            System.err.println("========================================");
            e.printStackTrace();
        } finally {
            tearDown();
        }
    }
    

    public static void main(String[] args) {
        // Enable assertions
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
        
        FileManagerTest tester = new FileManagerTest();
        tester.runAllTests();
    }
    

    private void printTestResult(String testName, boolean passed) {
        System.out.println(testName + ": " + (passed ? "✓ PASSED" : "✗ FAILED"));
    }

    private void timeTest(String testName, Runnable test) {
        long startTime = System.currentTimeMillis();
        test.run();
        long endTime = System.currentTimeMillis();
        System.out.println("Test '" + testName + "' completed in " + (endTime - startTime) + "ms");
    }
}