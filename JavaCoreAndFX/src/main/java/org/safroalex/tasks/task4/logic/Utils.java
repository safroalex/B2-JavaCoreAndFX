package org.safroalex.tasks.task4.logic;

import org.safroalex.tasks.task4.logic.exceptions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class Utils {

    public static Map<String, String> readDictionary(String filepath)
            throws FileReadException, InvalidFileFormatException {
        Map<String, String> dictionary = new HashMap<>();
        try (BufferedReader br
                     = new BufferedReader(new FileReader(filepath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 2) {
                    throw new InvalidFileFormatException(
                            "Invalid format in dictionary file."
                    );
                }
                dictionary.put(
                        parts[0].trim().toLowerCase(),
                        parts[1].trim()
                );
                }
            } catch (IOException e) {
            throw new FileReadException(
                    "Failed to read dictionary file: " + e.getMessage()
            );
        }
        return dictionary;
    }

    public static String readInputText(String filepath)
            throws FileReadException {
        StringBuilder text = new StringBuilder();
        try(BufferedReader bf = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = bf.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new FileReadException(
                    "Failed to read dictionary file: " + e.getMessage()
            );
        }
        return text.toString();
    }

    public static Map<String, String> parseManualDictionary(String input)
            throws InvalidFileFormatException {
        Map<String, String> dictionary = new HashMap<>();
        String[] lines = input.split("\\n");
        for (String line : lines) {
            String[] parts = line.split("\\|");
            if (parts.length != 2) {
                throw new InvalidFileFormatException(
                        "Invalid format in manual dictionary input."
                );
            }
            dictionary.put(
                    parts[0].trim().toLowerCase(),
                    parts[1].trim()
            );
        }
        return dictionary;
    }
}
