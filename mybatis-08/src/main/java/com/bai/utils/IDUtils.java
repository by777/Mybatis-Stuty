package com.bai.utils;

import org.junit.Test;

import java.util.UUID;

public class IDUtils {
    public static String genID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    @Test
    public void testUUID() {
        System.out.println(genID());
    }
}
