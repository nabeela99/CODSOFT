package com.codesoft.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Counter {

    public static Map<String, Integer> countWords(String stringData) {
        Map <String,Integer> map = new HashMap<>();
        stringData = stringData.trim().toLowerCase();
        int totalWords = 0;
        String [] lines = stringData.split("\n");
        for (String line : lines) {
            String[] words = line.split("[^\\w']|\s");
            for (String word : words) {
                if (word.trim().isEmpty()) continue;
                map.put(word, map.getOrDefault(word, 0) + 1);
                totalWords = totalWords + 1;
            }
        }
        int uniqueWords = map.size();
        var wordCountResult = new LinkedHashMap<String, Integer>();
        wordCountResult.put("TOTAL_WORDS", totalWords);
        wordCountResult.put("UNIQUE_WORDS", uniqueWords);
        wordCountResult.putAll(map);
        return wordCountResult;
    }

    public static String getCountedWordsForDisplay(String data) {
        var countedData = countWords(data);
        var stringToDisplay = new StringBuilder();
        stringToDisplay
                .append("Total Words: ")
                .append(countedData.get("TOTAL_WORDS"))
                .append(System.getProperty("line.separator"))
                .append("Unique Words: ")
                .append(countedData.get("UNIQUE_WORDS"))
                .append(System.getProperty("line.separator"))
                .append("Frequency of Each Words are below:\n");
        countedData.remove("TOTAL_WORDS");
        countedData.remove("UNIQUE_WORDS");
        stringToDisplay.append(countedData.entrySet().stream().map(e -> e.getKey() + ": " +e.getValue())
                .collect(Collectors.joining(System.getProperty("line.separator"))));
        return stringToDisplay.toString();
    }

}
