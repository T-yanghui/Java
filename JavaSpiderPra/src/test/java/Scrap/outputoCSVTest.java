package Scrap;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class outputoCSVTest {

    @Test
    void write() {
        try {
            outputoCSV outputocsv = new outputoCSV("/home/yang/桌面/test.csv",false);
            outputocsv.write("湖人","32");
            outputocsv.write("勇士","45");
            outputocsv.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void close() {
    }
}