package com.epam.izh.rd.online;

import com.epam.izh.rd.online.helper.Direction;
import com.epam.izh.rd.online.service.SimpleTextStatisticsAnalyzer;
import com.epam.izh.rd.online.service.StreamApiTextStatisticsAnalyzer;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        SimpleTextStatisticsAnalyzer s1 = new SimpleTextStatisticsAnalyzer();
        StreamApiTextStatisticsAnalyzer s2 = new StreamApiTextStatisticsAnalyzer();
        for(String str : s1.sortWordsByLength("Hello, Hi, mother, father - good, cat, c!!", Direction.ASC))
            System.out.println(str);
        System.out.println();
        for(String str : s1.sortWordsByLength("Hello, Hi, mother, father - good, cat, c!!", Direction.DESC))
            System.out.println(str);

    }
}
