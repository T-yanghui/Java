package Scrap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaSouppra {
    public static int count=0;
    public static void getByJoup(String URl,String Selector){
        try{
            Document document = Jsoup.connect(URl).get();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/home/yang/桌面/test.txt"));
            bufferedWriter.write(document.toString());
            bufferedWriter.close();
            Elements elements = document.select(Selector);
            System.out.println(elements.first().ownText());
//            for(Element element : elements){
//                System.out.println(element.ownText());
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
     *获取页面链接
     * @Param [URL]
    **/
    public static void getByHttpClient(String URL) throws IOException {
        outputoCSV outputo = new outputoCSV("/home/yang/桌面/CrawledUrls",false);
        CloseableHttpResponse response = null;
        try{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(URL);
            response =httpClient.execute(httpGet);
        }catch (Exception e){
            System.out.println("网页获取失败...");
            e.printStackTrace();
        }
        if(response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity,"utf-8");
            if(body == null) {
                System.out.println("Body为空...");
            }
            body.replaceAll("\t|\r|\n","");
//            body = "<a href=\"https://bbs.hupu.com/43348291.html\" target=\"_blank\" class=\"list-item-title\"><div class=\"item-title-conent\">重炮出膛！浙江岳鑫这脚禁区外重炮轰门可以打几分？</div></a>";

            String regex = "<a href=\"https://bbs.hupu.com/([0-9]{1,})\\.html\" "
                    +"target=\"_blank\" class=\"list-item-title\"><div class=\"item-title-conent\">"
                    +"(.*?)</div></a>";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(body);
            while(matcher.find()) {
                System.out.println(++count+"标题:"+matcher.group(2)+" 链接:https://bbs.hupu.com/"+matcher.group(1)+".html");
                outputo.write("https://bbs.hupu.com/"+matcher.group(1)+".html");
            }
              outputo.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String URL = "https://www.hupu.com";
        String selector = "#tpc > div > div.floor_box > table.case > tbody > tr > td > div.subhead > span";

//        for(int i = 0;i<60;i++) {
//            String selector = "#newpcnews-" + i + " > div > div.list-item-info";
//            JavaSouppra.getByJoup(URL, selector);
//        }
        getByHttpClient(URL);
//        getByJoup(URL,selector);
    }
}
