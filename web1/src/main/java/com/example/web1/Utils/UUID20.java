package com.example.web1.Utils;

import java.util.UUID;

public class UUID20 {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","").substring(12,32);
    }
}
