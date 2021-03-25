package com.example.web1.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UUID20Test {

    @Test
    void getUUID() {
       String res = UUID20.getUUID();
        System.out.println(res);
        System.out.println(res.substring(12,32));
    }
}