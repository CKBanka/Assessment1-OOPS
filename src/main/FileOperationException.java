package main;

/**
 * LockedMe.com - File Management Application
 * Main Application Class
 * 
 * @author Chirag
 * @version 1.01
 * @company Company LockedMe.com pvt Ltd..
 */

@SuppressWarnings("serial")
class FileOperationException extends Exception {
    
    private String operation;
    private String fileName;

    public FileOperationException(String message) {
        super(message);
    }
    
    
    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
    public FileOperationException(String message, String operation, String fileName) {
        super(message);
        this.operation = operation;
        this.fileName = fileName;
    }
    
    
    public FileOperationException(String message, Throwable cause, String operation, String fileName) {
        super(message, cause);
        this.operation = operation;
        this.fileName = fileName;
    }
    
    /**
     * Gets the operation that caused the exception
     */
    public String getOperation() {
        return operation;
    }
    
    /**
     * Gets the filename involved in the failed operation
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * Returns detailed error information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FileOperationException: ").append(getMessage());
        
        if (operation != null) {
            sb.append(" [Operation: ").append(operation).append("]");
        }
        
        if (fileName != null) {
            sb.append(" [File: ").append(fileName).append("]");
        }
        
        return sb.toString();
    }
}

/**
 * InvalidInputException - Custom exception for invalid user input
 */
class InvalidInputException extends Exception {
    
    private String inputValue;
    private String expectedFormat;
    
    /**
     * Constructor with message only
     */
    public InvalidInputException(String message) {
        super(message);
    }
    
    /**
     * Constructor with message and cause
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor with input details
     */
    public InvalidInputException(String message, String inputValue, String expectedFormat) {
        super(message);
        this.inputValue = inputValue;
        this.expectedFormat = expectedFormat;
    }
    
    /**
     * Gets the invalid input value
     */
    public String getInputValue() {
        return inputValue;
    }
    
    /**
     * Gets the expected input format
     */
    public String getExpectedFormat() {
        return expectedFormat;
    }
    
    /**
     * Returns detailed error information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InvalidInputException: ").append(getMessage());
        
        if (inputValue != null) {
            sb.append(" [Input: '").append(inputValue).append("']");
        }
        
        if (expectedFormat != null) {
            sb.append(" [Expected: ").append(expectedFormat).append("]");
        }
        
        return sb.toString();
    }
}

/**
 * DirectoryAccessException - Custom exception for directory access errors
 */
class DirectoryAccessException extends Exception {
    
    private String directoryPath;
    private String accessType;
    
    /**
     * Constructor with message only
     */
    public DirectoryAccessException(String message) {
        super(message);
    }
    
    /**
     * Constructor with message and cause
     */
    public DirectoryAccessException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor with directory details
     */
    public DirectoryAccessException(String message, String directoryPath, String accessType) {
        super(message);
        this.directoryPath = directoryPath;
        this.accessType = accessType;
    }
    
    /**
     * Gets the directory path
     */
    public String getDirectoryPath() {
        return directoryPath;
    }
    
    /**
     * Gets the access type that failed
     */
    public String getAccessType() {
        return accessType;
    }
    
    /**
     * Returns detailed error information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DirectoryAccessException: ").append(getMessage());
        
        if (directoryPath != null) {
            sb.append(" [Directory: ").append(directoryPath).append("]");
        }
        
        if (accessType != null) {
            sb.append(" [Access Type: ").append(accessType).append("]");
        }
        
        return sb.toString();
    }
}

/**
 * ApplicationException - Generic application exception
 */
class ApplicationException extends Exception {
    
    private String componentName;
    private int errorCode;
    
    /**
     * Constructor with message only
     */
    public ApplicationException(String message) {
        super(message);
    }
    
    /**
     * Constructor with message and cause
     */
    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructor with component details
     */
    public ApplicationException(String message, String componentName, int errorCode) {
        super(message);
        this.componentName = componentName;
        this.errorCode = errorCode;
    }
    
    /**
     * Gets the component name
     */
    public String getComponentName() {
        return componentName;
    }
    
    /**
     * Gets the error code
     */
    public int getErrorCode() {
        return errorCode;
    }
    
    /**
     * Returns detailed error information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ApplicationException: ").append(getMessage());
        
        if (componentName != null) {
            sb.append(" [Component: ").append(componentName).append("]");
        }
        
        if (errorCode != 0) {
            sb.append(" [Error Code: ").append(errorCode).append("]");
        }
        
        return sb.toString();
    }
}