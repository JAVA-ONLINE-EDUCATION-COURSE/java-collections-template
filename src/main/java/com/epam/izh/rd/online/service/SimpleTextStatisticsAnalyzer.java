package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.io.File;
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

        String str = "";

        for (String x : getWords(text)){
            str = str + x;
        }

        return str.length();
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

        List array = getWords(text);
        int count = 1;

        for (int i = 0; i < array.size() - 1; i++ ) {
            count++;
            for (int y = i + 1; y < array.size(); y++ ) {
                if (array.get(i).equals(array.get(y))) {
                    count--;
                    break;
                }
            }
        }

        return count;
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
    ArrayList<String> array = new ArrayList<>();

        StringTokenizer path_array = new StringTokenizer(text, " .,\"-!;:\r\n");
        while(path_array.hasMoreElements()) {
        array.add(path_array.nextToken());

            }
        return array;
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

        Set<String> str = new HashSet<>();

        for (String x : getWords(text)) {
            str.add(x);
        }

        return str;
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
        List array = getWords(text);
        Set<String> str = getUniqueWords(text);

        for (String x : str) {
            int count = 0;
            for (int i = 0; i < array.size(); i++) {
                if (x.equals(array.get(i))) {
                count++;
                }
            }
            map.put(x, count);
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

        List<String> array = getWords(text);

        if (direction.equals("ASC")) {

            // сортируем по длинне слов
            for (int i = 0; i < array.size(); i++) {

                for (int j = 0; j < array.size(); j++) {

                    if (array.get(i).length() > array.get(j).length()) {

                        String x = array.get(j);
                        array.set(j, array.get(i));
                        array.set(i, x);

                    }
                }
            }

            // сортируем слова одинаковой длинны в алфавитном порядке
            for (int i = 0; i < array.size(); i++) {

                for (int j = 0; j < array.size(); j++) {

                    if ((array.get(i).length() == array.get(j).length()) && (array.get(i).compareTo(array.get(j)) > 0)) {

                        String x = array.get(j);
                        array.set(j, array.get(i));
                        array.set(i, x);


                    }
                }
            }
            return array;
        } else {
            if (direction.equals("DESC")) {


                // сортируем по длинне слов
                for (int i = 0; i < array.size(); i++) {

                    for (int j = 0; j < array.size(); j++) {

                        if (array.get(i).length() < array.get(j).length()) {

                            String x = array.get(j);
                            array.set(j, array.get(i));
                            array.set(i, x);

                        }
                    }
                }


                // сортируем слова одинаковой длинны в алфавитном порядке
                for (int i = 0; i < array.size(); i++) {

                    for (int j = 0; j < array.size(); j++) {

                        if ((array.get(i).length() == array.get(j).length()) && (array.get(i).compareTo(array.get(j)) < 0)) {

                            String x = array.get(j);
                            array.set(j, array.get(i));
                            array.set(i, x);


                        }
                    }
                }

                return array;
            } else {throw new IllegalArgumentException();}
        }

    }
    }

