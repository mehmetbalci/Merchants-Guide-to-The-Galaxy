package com.mehmetbalci.thoughtworks.assignment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecimalFromRoman {

    //Mapping Roman Numbers to their decimal equivalent
    static final Map<String, Integer> romanMapping = new HashMap<>();

    static {
        romanMapping.put("I", 1);
        romanMapping.put("V", 5);
        romanMapping.put("X", 10);
        romanMapping.put("L", 50);
        romanMapping.put("C", 100);
        romanMapping.put("D", 500);
        romanMapping.put("M", 1000);
    }

}
