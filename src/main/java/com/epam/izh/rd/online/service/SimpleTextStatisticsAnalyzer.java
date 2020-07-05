package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;

/**
 * Совет:
 * Начните с реализации метода {@link SimpleTextStatisticsAnalyzer#getWords(String)}.
 * Затем переиспользуйте данный метод при реализации других.
 * <p>
 * При необходимости, можно создать внутри данного класса дополнительные вспомогательные приватные методы.
 */
public class SimpleTextStatisticsAnalyzer implements TextStatisticsAnalyzer {

    /**
     * Необходимо реализовать функционал подсчета суммарной длины всех слов (пробелы, знаким препинания итд не считаются).
     * Например для текста "One, I - tWo!!" - данный метод должен вернуть 7.
     *
     * @param text текст
     */
    @Override
    public int countSumLengthOfWords(String text) {
        String[] splitWords = text.split("\\s*\\W\\s*");
        int count = 0;
        for (String tempWord : splitWords) {
            count += tempWord.length();
        }
        return count;
    }

    /**
     * Необходимо реализовать функционал подсчета количества слов в тексте.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" - данный метод должен вернуть 7.
     *
     * @param text текст
     */
    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).size();
    }

    /**
     * Необходимо реализовать функционал подсчета количества уникальных слов в тексте (с учетом регистра).
     * Например для текста "One, two, three, three - one, tWo, tWo!!" - данный метод должен вернуть 5.
     * param text текст
     */
    @Override
    public int countNumberOfUniqueWords(String text) {
        return new HashSet<>(getWords(text)).size();
    }

    /**
     * Необходимо реализовать функционал получения списка слов из текста..
     * Пробелы, запятые, точки, кавычки и другие знаки препинания являются разделителями слов............3
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должен вернуться список :
     * {"One", "two", "three", "three", "one", "tWo", "tWo"}
     *...........................
     * @param text текст
     */
    @Override
    public List<String> getWords(String text) {
        String[] splitWords = text.split("\\s*\\W\\s*");

        List<String> wordsList = new ArrayList<>();
        for (String splitWord : splitWords) {
            if (!splitWord.equals("")) {
                wordsList.add(splitWord);
            }
        }
        return wordsList;
    }

    /**
     * Необходимо реализовать функционал получения списка уникальных слов из текста.
     * Пробелы, запятые, точки, кавычки и другие знаки препинания являются разделителями слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должен вернуться список :
     * {"One", "two", "three", "one", "tWo"}
     *
     * @param text текст
     */
    @Override
    public Set<String> getUniqueWords(String text) {
        return new HashSet<>(getWords(text));
    }

    /**
     * Необходимо реализовать функционал подсчета количества повторений слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должны вернуться результаты :
     * {"One" : 1, "two" : 1, "three" : 2, "one" : 1, "tWo" : 2}
     *
     * @param text текст
     */
    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getMapWithNumberOfWordsRepetitions(getWords(text));
    }

    private Map<String, Integer> getMapWithNumberOfWordsRepetitions(List<String> splitWords) {
        Map<String, Integer> map = new HashMap<>();
        for (String splitWord : splitWords) {
                if (map.containsKey(splitWord)) {
                    int count = map.get(splitWord);
                    map.put(splitWord, ++count);
                } else {
                    map.put(splitWord, 1);
                }
        }
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
        return getListWithSortAsDirection(getWords(text), direction);
    }

    private List<String> getListWithSortAsDirection(List<String> wordsList, Direction direction) {
        if (direction.name().equals("ASC")) {
            wordsList.sort(Comparator.comparing(String::length));
        } else {
            wordsList.sort(Comparator.comparing(String::length).reversed());
        }
        return wordsList;
    }
}
