package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.StreamApiTextStatisticsAnalyzer;

public class StreamApiTextStatisticsAnalyzerTest {

    public static void main(String[] args) {
        StreamApiTextStatisticsAnalyzer streamApiTextStatisticsAnalyzer = new StreamApiTextStatisticsAnalyzer();
        System.out.println(streamApiTextStatisticsAnalyzer.getWords("One, two, three, three - one, tWo, tWo!!"));
        System.out.println(streamApiTextStatisticsAnalyzer.getUniqueWords("One, two, three, three - one, tWo, tWo!!"));
    }
}
