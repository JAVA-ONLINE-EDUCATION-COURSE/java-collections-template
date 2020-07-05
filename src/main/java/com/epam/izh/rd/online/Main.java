package com.epam.izh.rd.online;

import com.epam.izh.rd.online.service.SimpleTextStatisticsAnalyzer;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
       SimpleTextStatisticsAnalyzer t = new SimpleTextStatisticsAnalyzer();
        t.countNumberOfWordsRepetitions("One, two, three, three - one, tWo, tWo!!");
        //t.countSumLengthOfWords("One, I - tWo!!");
    }
}
