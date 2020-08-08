package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;

import static java.util.Collections.*;

public class SimpleTextStatisticsAnalyzer implements TextStatisticsAnalyzer {

    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return new HashSet<>(getWords(text)).size();
    }

    @Override
    public List<String> getWords(String text) {
        List<String> list = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (char symbol: text.toCharArray()){
            if(((symbol >= 'а' && symbol <= 'я') ||
                    (symbol >= 'А' && symbol <= 'Я')) ||
                        ((symbol >= 'a' && symbol <= 'z') ||
                            (symbol >= 'A' && symbol <= 'Z'))) {
                stringBuilder.append(symbol);
            }
            else {
                if(stringBuilder.length() != 0) {
                    list.add(String.valueOf(stringBuilder));
                    stringBuilder = new StringBuilder();
                }
            }
        }
        char symbol = text.toCharArray()[text.toCharArray().length - 1];
        if(((symbol >= 'а' && symbol <= 'я') ||
                (symbol >= 'А' && symbol <= 'Я')) ||
                ((symbol >= 'a' && symbol <= 'z') ||
                        (symbol >= 'A' && symbol <= 'Z')))
            list.add(String.valueOf(stringBuilder));
        return list;
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return new HashSet<>(getWords(text));
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        List<String> list = getWords(text);
        Map<String, Integer> map = new HashMap<>();
        list.forEach(word -> map.put(word, (map.get(word) == null ? 0 : map.get(word)) + 1));
        return map;
    }

    /**
     * Необходимо реализовать функционал вывода слов из текста в отсортированном виде (по длине) в зависимости от параметра direction.
     * Например для текста "Hello, Hi, mother, father - good, cat, c!!" должны вернуться результаты :
     * ASC : {"mother", "father", "Hello", "good", "cat", "Hi", "c"}
     * DESC : {"c", "Hi", "cat", "good", "Hello", "father", "mother"}
     *
     * @param text текст
     */
    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        List<String> list = getWords(text);
        list.sort(!direction.equals(Direction.ASC) ? Comparator.comparing(String::length).reversed() : Comparator.comparing(String::length));
        return list;
    }
}
