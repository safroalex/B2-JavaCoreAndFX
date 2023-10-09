package org.safroalex.tasks.task4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.safroalex.tasks.task4.logic.TextTranslator;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextTranslatorTest {

    private Map<String, String> dictionary;

    @BeforeEach
    public void setUp() {
        dictionary = new HashMap<>();
        dictionary.put("hello", "здравствуйте");
        dictionary.put("hi", "привет");
        dictionary.put("how", "как");
        dictionary.put("are you", "ты");
        dictionary.put("you", "вы");
        dictionary.put("world", "мир");
        dictionary.put("how are you", "как дела");
    }

    @Test
    public void testTranslateText() {
        String inputText = "Hello world How are you Hi";
        String expected = "здравствуйте мир как дела привет";
        String result = TextTranslator.translateText(dictionary, inputText);
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateTextWithUnknownWords() {
        String inputText = "Hello galaxy How are you";
        String expected = "здравствуйте galaxy как дела";
        String result = TextTranslator.translateText(dictionary, inputText);
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateTextWithDifferentCase() {
        String inputText = "hELlo WoRLd hOw aRE YoU hI";
        String expected = "здравствуйте мир как дела привет";
        String result = TextTranslator.translateText(dictionary, inputText);
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateTextWithLongestMatch() {
        String inputText = "How you";
        String expected = "как вы";
        String result = TextTranslator.translateText(dictionary, inputText);
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateEmptyText() {
        String inputText = "";
        String expected = "";
        String result = TextTranslator.translateText(dictionary, inputText);
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateTextWithOnlyUnknownWords() {
        String inputText = "galaxy universe";
        String expected = "galaxy universe";
        String result = TextTranslator.translateText(dictionary, inputText);
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateTextWithAllUpperCase() {
        String inputText = "HELLO WORLD";
        String expected = "здравствуйте мир";
        String result = TextTranslator.translateText(dictionary, inputText);
        assertEquals(expected, result);
    }

    @Test
    public void testTranslateTextWithNumbersAndSpecialCharacters() {
        String inputText = "hello 123";
        String expected = "здравствуйте 123";
        String result = TextTranslator.translateText(dictionary, inputText);
        assertEquals(expected, result);
    }

}
