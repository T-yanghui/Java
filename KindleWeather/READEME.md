- ## 1.效果

  ![IMG_20210418_131338.jpg](https://www.qiuming.top/upload/2021/04/IMG_20210418_131338-1a8fa0929e114f1ebd31ba44d56934b3.jpg)![IMG_20210418_140315.jpg](https://www.qiuming.top/upload/2021/04/IMG_20210418_140315-77ce3bb042d04e02be2374d4af112b4e.jpg)
  ## 2.技术框架

  - HTML

  - CSS

  - JS

  ## 3.文件说明

  - 主要文件index.html，没有单独把JS，CSS提取出来

  - 字体引用 https://fonts.google.com

  - 时间日期显示，均用JS实现；英文和翻译使用扇贝每日一句API(由后端代理，解决跨域问题)；

    ```nginx
    #nginx部分配置
    server{
    	listen 8080;
        root /var/www;
        index tour.html index.html;
        server_name _;
        location / {
        #禁止缓存，每次都从服务器请求，方便调试
            add_header Cache-Control no-store;
        }
        #转发api请求
        location /api/{
            proxy_pass http://rest.shanbay.com;
            add_header 'Access-Control-Allow-Origin' 'rest.shanbay.com'
        }   
    }
    
    
    ```

    


  - 天气预报使用和风天气
  - Github地址：https://github.com/T-yanghui/Java/tree/master/KindleWeather

  ## 4.使用方法
  1) 让kindle保持常亮(主页搜索框输入 ～ds)
  2) kindle浏览器输入url(like: http://192.168.4.1:8080/kindle/index.html)
  3) end