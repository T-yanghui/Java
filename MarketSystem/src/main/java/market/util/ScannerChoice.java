package market.util;

import market.page.MainPage;

import java.util.Scanner;

public class ScannerChoice {
    //获取输入的字符串
    public static String scanner_string(){
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    //获取输入double
    public static double scanner_double(){
        return Double.parseDouble(ScannerChoice.scanner_string());
    }

    //获取输入的ID,正确返回ID,否则返回-1
    public static int scanner_int(){
        int num = 3;
        do {
            num--;
            String input = ScannerChoice.scanner_string();
            String reg = "[0-9]{10}";
            if (input.matches(reg)) return Integer.parseInt(input);
            System.err.println("输入有误！");
            System.out.println("请继续输入：");
        }while(num>0);
        return -1;
    }
}
