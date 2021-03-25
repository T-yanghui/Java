var httpReq;
function checkUsername(){
    //IE6以上版本及其他内核
    if(window.XMLHttpRequest){
        httpReq = new XMLHttpRequest();
    }
    else if(window.ActiveXObject){
        httpReq = new ActiveXObject();
    }

    //初始化HTTP请求
    httpReq.open("post","servlet1",true);
    //设置消息头
    httpReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
    console.log("引用成功");
    //指定回调函数
    httpReq.onreadystatechange = response1;

    //获取文本框值
    var name = document.getElementById("username").value;
    //发送http请求
    httpReq.send("username="+name);
}
function response1(){
    if(httpReq.readyState == 4){
        if(httpReq.status == 200){
            //获取服务器返回数据
            var text = httpReq.responseText;
            //把结果写在页面上
            var div = document.getElementById("result");
            div.innerText = text;
            console.log(text);
            console.log(httpReq.responseURL);
        }
    }
}