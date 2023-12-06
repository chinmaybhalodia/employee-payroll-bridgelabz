package com.example;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;

// UC2: performing all file operations
public class FileOperations {

    public static void main(String[] args) {
        String DIR_PATH = "dir";
        String BASE_FILE_PATH = "dir/file";

        // creating a new directory
        createDirectory(DIR_PATH);

        // creating some files in directory
        for (int i = 1; i <= 10; i++) {
            createFile(BASE_FILE_PATH + i + ".txt");
        }

        // deleting some files from directory
        for (int i = 6; i <= 10; i++) {
            deleteFile(BASE_FILE_PATH + i + ".txt");
        }

        // checking if files exists or not
        for (int i = 1; i <= 10; i++) {
            System.out.println("File" + i + ".txt: " + checkIfExists(BASE_FILE_PATH + i + ".txt"));
        }

        // listing all files in a directory
        listFiles(DIR_PATH);

        // creating dummy files in dir folder
        for (int i = 1; i <= 5; i++) {
            createFile(DIR_PATH + "/temp" + i);
        }

        // listing all files with .txt extension
        listFilesWithExtension(DIR_PATH, ".txt");

    }

    // method to check if file exists or not
    public static boolean checkIfExists(String filePath) {
        Path path = Path.of(filePath);
        return Files.exists(path);
    }

    // method to delete a file
    public static void deleteFile(String filePath) {
        if (checkIfExists(filePath)) {
            Path path = Path.of(filePath);
            try {
                Files.delete(path);
                System.out.println("Deleted file: " + filePath);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
        } else {
            System.out.println("File " + filePath + " does not exist.");
        }
    }

    // method to create a file
    public static void createFile(String filePath) {
        if (!checkIfExists(filePath)) {
            Path path = Path.of(filePath);
            try {
                Files.createFile(path);
                System.out.println("Created file: " + filePath);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
        } else {
            System.out.println("File " + filePath + " already exists.");
        }
    }

    // method to create a directory
    public static void createDirectory(String dirPath) {
        if (!checkIfExists(dirPath)) {
            Path path = Path.of(dirPath);
            try {
                Files.createDirectory(path);
                System.out.println("Created directory: " + dirPath);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }

        } else {
            System.out.println("Directory " + dirPath + " already exists.");
        }
    }

    // method to list all files from a directory
    public static void listFiles(String dirPath) {
        Path path = Path.of(dirPath);
        if (checkIfExists(dirPath)) {
            if (Files.isDirectory(path)) {
                try {
                    Files.list(path).forEach(System.out::println);
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
                }
            } else {
                System.out.println(dirPath + " is not a directory.");
            }
        } else {
            System.out.println("Directory " + dirPath + " does not exist.");
        }
    }

    // method to list all files with same extension
    public static void listFilesWithExtension(String dirPath, String extention) {
        Path path = Path.of(dirPath);
        if (checkIfExists(dirPath)) {
            if (Files.isDirectory(path)) {
                try {
                    Files.newDirectoryStream(path,
                            filepath -> filepath.toFile().isFile() && filepath.toString().endsWith(extention))
                            .forEach(System.out::println);
                } catch (IOException exception) {
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
                }
            } else {
                System.out.println(dirPath + " is not a directory.");
            }
        } else {
            System.out.println("Directory " + dirPath + " does not exist.");
        }
    }
}
