package Scrap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;

public class Login {
    public static void httpClientLogin(String loginUrl,String mainPageURL) throws Exception{
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpUriRequest login = RequestBuilder.post()
            .setUri(new URI(loginUrl))// 登陆url
            .setHeader("Accept","*/*")
            .setHeader("Accept-Encoding","gzip, deflate, br")
            .setHeader("Accept-Language","en-US,en;q=0.9,zh-CN;q=0.8,zh;q=0.7")
            .setHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
            // 设置账号信息
            .addParameter("auth_type","local")
            .addParameter("username","2020141072")
            .addParameter("password","208056")
            .addParameter("needCaptcha","false")
            .build();
    // 模拟登陆
    CloseableHttpResponse response = httpclient.execute(login);
        System.out.println(response.toString());
    if (response.getStatusLine().getStatusCode() == 200 || response.getStatusLine().getStatusCode()==302){
        //构造主页请求
        HttpGet httpGet = new HttpGet(mainPageURL);
        CloseableHttpResponse user_response = httpclient.execute(httpGet);
        HttpEntity entity = user_response.getEntity();
        //
        String body = EntityUtils.toString(entity, "utf-8");
        BufferedWriter out = new BufferedWriter(new FileWriter("/home/yang/桌面/login"));
        out.write(body);
        out.close();

//        HttpGet Loginout = new HttpGet("https://webvpn.bupt.edu.cn/logout");
//        CloseableHttpResponse loginoutResponse = httpclient.execute(Loginout);
//        System.out.println(loginoutResponse.toString());
        }else {
        System.out.println("httpclient 模拟登录VPN失败了!!!!");
        }
    }

    public static void main(String[] args) throws Exception {
        String loginURL = "https://webvpn.bupt.edu.cn/do-login";
        String mainPageURL = "https://webvpn.bupt.edu.cn/";
        httpClientLogin(loginURL,mainPageURL);
    }
}
