package Scrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ConcurrentCrawl
 * Description  采集https://www.hupu.com/
 * a.单线程采集首页链接
 * b.多线程采集新闻页面
 * Author yang
 * @Date 2021/6/6 下午8:30
 * Version 1.0
 **/
public class ConcurrentCrawl {
    //采集页面数
    private AtomicInteger pageCount = new AtomicInteger(0);
    //默认线程数5
    private int ThreadNum = 5;
    //线程池
    private myThreadPool ThreadPool;
    //待采集页面
    private LinkedBlockingQueue<String> CrawlURLs;
    //采集失败页面
    private HashSet<String> failedPage = new HashSet<>();
    //采集需要的selector
    private String[] selectors;
    //输出采集结果
    private outputoCSV output = new outputoCSV("/home/yang/桌面/test.csv",false);

    public ConcurrentCrawl() throws IOException {}
    public ConcurrentCrawl(int ThreadNum,LinkedBlockingQueue<String> CrawlURLS,String[] selectors) throws IOException {
        this.ThreadNum = ThreadNum;
        this.CrawlURLs = CrawlURLS;
        this.selectors = selectors;
        this.ThreadPool = new myThreadPool(ThreadNum);
    }

    /*
     *采集页面
     * @Param
    **/
    public void Crawl() throws IOException {
        while(!Thread.currentThread().isInterrupted()){
            final String request = CrawlURLs.poll();
            //If request is null,and no thread is alive in ThreadPool,break the loop;
            if(request == null){
                if(ThreadPool.getThreadAlive()==0){
                    break;
                }
            }else {
                //执行采集任务
                ThreadPool.execute(new Runnable() {
                                       @Override
                                       public void run() {
                                           try {
                                               String[] temp = Task(request,selectors);
                                               String[] res = Arrays.copyOf(temp,temp.length+1);
                                               res[res.length-1] = request;
                                               output.writeByStrings(res);
                                           } catch (Exception e) {
                                               System.out.println(request+"页面获取失败...");
                                               failedPage.add(request);
                                           }
                                       }
                                   }

                );
            }
        }
        ThreadPool.shutdown();
        output.close();
    }
    /*
     *Deal with crawled page;extract information based on selectors.
     * @Param Url selectors
    **/
    public String[] Task(String URl, String[] Selectors)throws Exception{
            final int length = Selectors.length;
            Document document = Jsoup.connect(URl).get();
            String[] results = new String[length];
            for(int i=0;i<length;i++){
                Elements elements = document.select(Selectors[i]);
                results[i] = elements.size()==0?"null":elements.first().ownText();
            }
            return results;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/yang/桌面/CrawledUrls"));
        String Line = null;
        LinkedBlockingQueue<String> Queue = new LinkedBlockingQueue<>();
        while((Line=reader.readLine())!=null){
            Queue.add(Line);
        }
        reader.close();
        String[] selectors = new String[2];
        selectors[0]="#tpc > div > div.floor_box > table.case > tbody > tr > td > div.subhead > span";
        selectors[1]="#Recers > a";
        System.out.println(args[0]);
        int ThreadNum = Integer.valueOf(args[0]);
        outputoCSV outputo = new outputoCSV("/home/yang/桌面/res.csv",true);
            long startTime = System.currentTimeMillis();
            try {
                ConcurrentCrawl spider = new ConcurrentCrawl(ThreadNum, Queue,selectors);
                spider.Crawl();
            }catch (Exception e){
                e.printStackTrace();
            }
            outputo.write(String.valueOf(ThreadNum),String.valueOf(System.currentTimeMillis()-startTime));
            outputo.close();
    }
}
