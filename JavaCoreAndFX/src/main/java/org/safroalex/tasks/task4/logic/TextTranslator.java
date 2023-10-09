package org.safroalex.tasks.task4.logic;

import java.util.Map;

public class TextTranslator {
    public static String translateText(Map<String, String> dictionary, String inputText) {
        String[] words = inputText.split("\\s+");
        StringBuilder translatedText = new StringBuilder();
        int i = 0;

        // Translation algorithm
        while (i < words.length) {
            String bestMatch = "";
            String bestTranslation = "";
            StringBuilder currentPhrase = new StringBuilder(words[i]
                    .toLowerCase().replaceAll("[^a-zA-Z0-9]", ""));

            for (int j = i + 1; j <= words.length; j++) {
                String translation = dictionary.get(currentPhrase.toString());
                if (translation != null && currentPhrase.length() > bestMatch.length()) {
                    bestMatch = currentPhrase.toString();
                    bestTranslation = translation;
                }

                if (j < words.length) {
                    currentPhrase.append(" ").append(words[j].toLowerCase()
                            .replaceAll("[^a-zA-Z0-9]", ""));
                }
            }

            if (!bestMatch.isEmpty()) {
                translatedText.append(bestTranslation).append(" ");
                i += bestMatch.split("\\s").length;
            } else {
                translatedText.append(words[i]
                        .replaceAll("[^a-zA-Z0-9]", "")).append(" ");
                i++;
            }
        }

        return translatedText.toString().trim();
    }
}


