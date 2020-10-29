//按行读取指定路径的文件，打印

import java.io.*;

public class ReadFile {
    public static void readFile(String FilePath) {
        try {
            File myFile = new File(FilePath);
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}