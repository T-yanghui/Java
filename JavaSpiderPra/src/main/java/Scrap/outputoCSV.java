package Scrap;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName outputocsv
 * Description
 * Author yang
 * @Date 2021/6/6 下午10:03
 * Version 1.0
 **/
public class outputoCSV {
    protected BufferedWriter bufferedWriter;
/*
 *flage is true,append to the file,otherwise override
 * @Param [outputPath, flage]
**/
    public outputoCSV(String outputPath,Boolean flage) throws IOException {
        BufferedWriter bw =new BufferedWriter(new FileWriter(outputPath,flage));
        this.bufferedWriter = bw;
    }
    /*
     *output in comma intervals
     * @Param [strs]
    **/
    public void write(String ...strs) throws IOException {
        int lenth = strs.length;
        if(lenth == 0) {
            bufferedWriter.write("\n");
            return;
        }
        for(int i=0;i<lenth-1;i++){
            bufferedWriter.write(strs[i]);
            bufferedWriter.write(',');
        }
        bufferedWriter.write(strs[lenth-1]);
        bufferedWriter.write("\n");
    }
    public void writeByStrings(String[] strs) throws IOException {
       int lenth = strs.length;
        if(lenth == 0) {
            bufferedWriter.write("\n");
            return;
        }
        for(int i=0;i<lenth-1;i++){
            bufferedWriter.write(strs[i]);
            bufferedWriter.write(',');
        }
        bufferedWriter.write(strs[lenth-1]);
        bufferedWriter.write("\n");
    }
    public void close() throws IOException {
        bufferedWriter.close();
    }

}
