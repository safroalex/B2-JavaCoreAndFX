package org.safroalex.tasks.task4;

import org.safroalex.tasks.task4.logic.Utils;
import org.safroalex.tasks.task4.logic.TextTranslator;
import org.safroalex.tasks.task4.logic.exceptions.FileReadException;
import org.safroalex.tasks.task4.logic.exceptions.InvalidFileFormatException;

import java.util.Map;

public class Task4Main {
    private static final String DICTONARY_PATH
            = "src/main/java/org/safroalex/tasks/task4/logic/files/dictionary.txt";
    private static final String INPUT_TEXT_PATH
            = "src/main/java/org/safroalex/tasks/task4/logic/files/input.txt";

    public static void main(String[] args) {
        try {
            Map<String, String> dictionary = Utils.readDictionary(DICTONARY_PATH);
            String inputText = Utils.readInputText(INPUT_TEXT_PATH);

            String translatedText = TextTranslator.translateText(dictionary, inputText);

            System.out.println("Translated text: ");
            System.out.println(translatedText);

        } catch (FileReadException | InvalidFileFormatException e) {
            e.printStackTrace();
        }
    }
}


