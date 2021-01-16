package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;
import com.epam.izh.rd.online.helper.FileReaderService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static java.util.Collections.*;

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
        ArrayList<String> arrayList = (ArrayList<String>) getWords(text);
        int sum = 0;
        for (String s : arrayList) {
            sum += s.length();
        }
        return sum;
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
        return getUniqueWords(text).size();
    }

    /**
     * Необходимо реализовать функционал получения списка слов из текста.
     * Пробелы, запятые, точки, кавычки и другие знаки препинания являются разделителями слов.
     * Например для текста "One, two, three, three - one, tWo, tWo!!" должен вернуться список :
     * {"One", "two", "three", "three", "one", "tWo", "tWo"}
     *
     * @param text текст
     */

    @Override
    public List<String> getWords(String text) {
        String[] wordsStringArray = text.split("\\W");
        ArrayList<String> words = new ArrayList<>(Arrays.asList(wordsStringArray));
        words.removeIf(String::isEmpty);
        return words;
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
        return new TreeSet<>(getWords(text));
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
        Map<String, Integer> hashMapForRepetitionCounting = new HashMap<>();
        ArrayList<String> list = (ArrayList<String>) getWords(text);
        int count;
        for (int i = 0; i < list.size(); i++) {
            count = 0;
            for (String s : list) {
                if (list.get(i).equals(s)) {
                    count++;
                }
            }
            hashMapForRepetitionCounting.put(list.get(i), count);
        }
        return hashMapForRepetitionCounting;
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
        ArrayList<String> arrayList = (ArrayList<String>) getWords(text);
        if (direction == Direction.ASC) {
            arrayList.sort(Comparator.comparingInt(String::length));
        } else {
            arrayList.sort(Comparator.comparingInt(String::length).reversed());
        }
        return arrayList;
    }
}
