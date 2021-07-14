package com.parallel.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String uuid(){
        String id = UUID.randomUUID().toString().replace("-","");

        return id;
    }


}
