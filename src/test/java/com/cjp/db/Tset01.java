package com.cjp.db;

import java.util.HashMap;
import java.util.Map;

public class Tset01 {

    enum Sex{
        Man,
        Woman
    }

    public static void main(String[] args) {
        System.out.println(Sex.Man.toString());
        System.out.println(Sex.Man.toString().toUpperCase());
        Map<String, Object> map = new HashMap<>();
        map.put("age", 18);
        map.put("sex", Sex.Man);
        Sex sex = (Sex)map.get("sex");
        System.out.println(sex);
        Sex sex2 = Sex.Man;
        System.out.println(sex2.equals(sex));
    }
}
