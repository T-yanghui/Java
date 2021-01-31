import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
//A->B
public class Copy {
    public static void main(String[] args)throws IOException{
      String a = args[0];
      String b = args[1];
      try(FileInputStream Fa = new FileInputStream(a);
      FileOutputStream Fb = new FileOutputStream(b)){
          Fa.transferTo(Fb);
      }
    }
}
