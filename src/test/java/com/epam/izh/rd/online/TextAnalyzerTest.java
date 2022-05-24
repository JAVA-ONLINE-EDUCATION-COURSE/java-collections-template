package com.epam.izh.rd.online;

import com.epam.izh.rd.online.helper.Direction;
import com.epam.izh.rd.online.helper.FileReaderService;
import com.epam.izh.rd.online.helper.IFileReaderService;
import com.epam.izh.rd.online.service.SimpleTextStatisticsAnalyzer;
import com.epam.izh.rd.online.service.StreamApiTextStatisticsAnalyzer;
import com.epam.izh.rd.online.service.TextStatisticsAnalyzer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TextAnalyzerTest {

    private static final int SUM_LENGTH_OF_WORDS = 2119;
    private static final int COUNT_OF_WORDS = 503;
    private static final int COUNT_OF_UNIQUE_WORDS = 265;

    private static List<String> wordsList;

    private static TextStatisticsAnalyzer simpleTextStatisticsAnalyzer;
    private static TextStatisticsAnalyzer streamApiTextStatisticsAnalyzer;

    private static String text;
    private static Properties properties;

    @BeforeAll
    static void setup() {
        IFileReaderService fileReaderService = new FileReaderService();
        simpleTextStatisticsAnalyzer = new SimpleTextStatisticsAnalyzer();
        streamApiTextStatisticsAnalyzer = new StreamApiTextStatisticsAnalyzer();

        try {
            text = fileReaderService.readFromFileToString("sample-text.txt");
            properties = fileReaderService.loadProperties("words-statistics.properties");
            wordsList = readWordsFromProperties();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    @Test
    @DisplayName("Тест метода TextStatisticsAnalyzer.countSumLengthOfWords(String text)")
    void testCountSumLengthOfWords() {
        assertEquals(SUM_LENGTH_OF_WORDS, simpleTextStatisticsAnalyzer.countSumLengthOfWords(text));
    }

    @Test
    @DisplayName("Тест метода TextStatisticsAnalyzer.countNumberOfWords(String text)")
    void testCountNumberOfWords() {
        assertEquals(COUNT_OF_WORDS, simpleTextStatisticsAnalyzer.countNumberOfWords(text));
    }

    @Test
    @DisplayName("Тест метода TextStatisticsAnalyzer.countNumberOfUniqueWords(String text)")
    void testCountNumberOfUniqueWords() {
        assertEquals(COUNT_OF_UNIQUE_WORDS, simpleTextStatisticsAnalyzer.countNumberOfUniqueWords(text));
    }

    @Test
    @DisplayName("Тест метода TextStatisticsAnalyzer.sortWordsByLength(String text, ASC)")
    void testSortWordsByLength() {
        assertIsListSorted(simpleTextStatisticsAnalyzer.sortWordsByLength(text, Direction.ASC),Direction.ASC);
    }

    @Test
    @DisplayName("Тест метода TextStatisticsAnalyzer.sortWordsByLength(String text, DESC)")
    void testSortWordsByLengthDesc() {
        assertIsListSorted(simpleTextStatisticsAnalyzer.sortWordsByLength(text, Direction.DESC),Direction.DESC);
    }

    @Test
    @DisplayName("Тест метода TextStatisticsAnalyzer.getWords(String text)")
    void testGetWords() {
        assertListsContainSameElements(wordsList, simpleTextStatisticsAnalyzer.getWords(text));
    }

    @Test
    @DisplayName("Тест метода TextStatisticsAnalyzer.getUniqueWords(String text)")
    void testGetUniqueWords() {
        assertListsContainSameElements(wordsList.stream().distinct().collect(Collectors.toList()), simpleTextStatisticsAnalyzer.getUniqueWords(text));
    }

    @Test
    @DisplayName("Тест метода TextStatisticsAnalyzer.countNumberOfWordsRepetitions(String text)")
    void testCountNumberOfWordsRepetitions() {
        assertRepetitions(simpleTextStatisticsAnalyzer.countNumberOfWordsRepetitions(text));
    }

    @Test
    @DisplayName("Тест метода StreamApiTextStatisticsAnalyzer.countSumLengthOfWords(String text)")
    void testCountSumLengthOfWordsStream() {
        assertEquals(SUM_LENGTH_OF_WORDS, streamApiTextStatisticsAnalyzer.countSumLengthOfWords(text));
    }

    @Test
    @DisplayName("Тест метода StreamApiTextStatisticsAnalyzer.countNumberOfWords(String text)")
    void testCountNumberOfWordsStream() {
        assertEquals(COUNT_OF_WORDS, streamApiTextStatisticsAnalyzer.countNumberOfWords(text));
    }

    @Test
    @DisplayName("Тест метода StreamApiTextStatisticsAnalyzer.countNumberOfUniqueWords(String text)")
    void testCountNumberOfUniqueWordsStream() {
        assertEquals(COUNT_OF_UNIQUE_WORDS, streamApiTextStatisticsAnalyzer.countNumberOfUniqueWords(text));
    }

    @Test
    @DisplayName("Тест метода StreamApiTextStatisticsAnalyzer.sortWordsByLength(String text, ASC)")
    void testSortWordsByLengthStream() {
        assertIsListSorted(streamApiTextStatisticsAnalyzer.sortWordsByLength(text, Direction.ASC),Direction.ASC);
    }

    @Test
    @DisplayName("Тест метода StreamApiTextStatisticsAnalyzer.sortWordsByLength(String text, DESC)")
    void testSortWordsByLengthDescStream() {
        assertIsListSorted(streamApiTextStatisticsAnalyzer.sortWordsByLength(text, Direction.DESC),Direction.DESC);
    }

    @Test
    @DisplayName("Тест метода StreamApiTextStatisticsAnalyzer.getWords(String text)")
    void testGetWordsStream() {
        assertListsContainSameElements(wordsList, streamApiTextStatisticsAnalyzer.getWords(text));
    }

    @Test
    @DisplayName("Тест метода StreamApiTextStatisticsAnalyzer.getUniqueWords(String text)")
    void testGetUniqueWordsStream() {
        assertListsContainSameElements(wordsList.stream().distinct().collect(Collectors.toList()), streamApiTextStatisticsAnalyzer.getUniqueWords(text));
    }

    @Test
    @DisplayName("Тест метода StreamApiTextStatisticsAnalyzer.countNumberOfWordsRepetitions(String text)")
    void testCountNumberOfWordsRepetitionsStream() {
        assertRepetitions(streamApiTextStatisticsAnalyzer.countNumberOfWordsRepetitions(text));
    }

    private static List<String> readWordsFromProperties() {
        List<String> result = new ArrayList<>();

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            int wordRepetition = Integer.parseInt(entry.getValue().toString());
            String word = entry.getKey().toString();

            if (wordRepetition == 1) {
                result.add(word);
            } else {
                IntStream.rangeClosed(1, wordRepetition).forEach(a -> result.add(word));
            }
        }

        return result;
    }

    private void assertListsContainSameElements(Collection<?> expected, Collection<?> actual) {
        if (expected.size() != actual.size() || !actual.containsAll(expected)) {
            fail();
        }
    }

    private void assertRepetitions(Map<String, Integer> result) {
        if (result.isEmpty()) {
            fail();
        }

        for (Map.Entry<String, Integer> e : result.entrySet()) {
            boolean wordMatchWithProperty = Integer.valueOf(properties.getProperty(e.getKey())).equals(e.getValue());

            if (!wordMatchWithProperty) {
                fail();
            }
        }
    }

    private void assertIsListSorted(List<String> list, Direction direction) {
        Comparator<String> stringComparator = getStringComparator(direction);

        if (list.isEmpty()) {
            fail();
        }

        for(int i = 0; i < list.size() - 1; i++) {
            String cur = list.get(i);
            String next = list.get(i+1);

            if (stringComparator.compare(cur, next) > 0) {
                fail();
            }
        }
    }

    private Comparator<String> getStringComparator(Direction direction) {
        return direction.equals(Direction.ASC) ?
                Comparator.comparing(String::length) :
                Comparator.comparing(String::length).reversed();
    }
}
