package com.example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

// UC2: performing all file operations
public class FileOperations {

    // method to write data to the file
    public static void writeToFile(String filePath, String data) {
        if (checkIfExists(filePath)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(data);
                writer.newLine();
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
            }
        } else {
            System.out.println("File " + filePath + " does not exist.");
        }
    }

    // method to read and store data in LinkedList
    public static ArrayList<Employee> readFromFile(String filePath) {
        if (checkIfExists(filePath)) {
            ArrayList<Employee> data = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        double salary = Double.parseDouble(parts[2]);
                        data.add(new Employee(id, name, salary));
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return data;
        }
        System.out.println("File " + filePath + " does not exist");
        return null;
    }

    // method to count number of lines in a file
    public static int countLines(String filePath) {
        if (checkIfExists(filePath)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                int count = 0;
                while (reader.readLine() != null) {
                    count++;
                }
                return count;
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
                return -1;
            }
        }
        System.out.println("File " + filePath + " does not exist");
        return -1;
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
