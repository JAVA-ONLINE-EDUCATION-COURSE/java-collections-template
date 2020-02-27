package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        int sum=0;
        List<String> listText = getWords(text);

        for(String list: listText){
            sum +=list.length();
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
        List<String> listText = getWords(text);
        return listText.size();
    }

    /**
     * Необходимо реализовать функционал подсчета количества уникальных слов в тексте (с учетом регистра).
     * Например для текста "One, two, three, three - one, tWo, tWo!!" - данный метод должен вернуть 5.
     * param text текст
     */
    @Override
    public int countNumberOfUniqueWords(String text) {

        Set<String> setString = getUniqueWords(text);
        return setString.size();
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
        Pattern pattern = Pattern.compile("[^A-Z a-z\n]");
        Matcher matcher = pattern.matcher(text);
        String s = matcher.replaceAll("");
        List<String> list  = new ArrayList<>();
        String strings[];
        strings=s.split("[ \n]+");
        for (int i=0; i<strings.length; i++){
            list.add(strings[i]);
        }
        return list;
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
        Pattern pattern = Pattern.compile("[^A-Z a-z\n]");
        Matcher matcher = pattern.matcher(text);
        String s = matcher.replaceAll("");
        Set<String> set = new HashSet<>();
        String strings[];
        strings=s.split("[ \n]+");
        for (int i=0; i<strings.length; i++){
            set.add(strings[i]);
        }

        return set;
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
        Map<String, Integer> map = new HashMap<>();
        List<String> listText = getWords(text);
        int numRepeat=0;
        for(String s : listText) {
            for (String s2 : listText) {
                if (s.compareTo(s2) == 0) {
                    numRepeat++;
                }
            }
            map.put(s, numRepeat);
            numRepeat=0;
        }
        System.out.print(map);
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

        List<String> listText= getWords(text);


        if (direction==Direction.ASC) {
            Collections.sort(listText, (o1, o2) -> o1.length() - o2.length());
        }
        if (direction==Direction.DESC){
            Collections.sort(listText, (o1, o2) -> -(o1.length() - o2.length()));
        }

        return listText;
    }
}
