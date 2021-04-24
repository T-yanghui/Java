/*!
 * author:T-yang
 *
 * Date: 2021/4/24
 */
//时间
    function time() {
        //获取容器
        t_div = document.getElementById('showtime');
        //获取date
        var now = new Date();
	var utc8DiffMinutes = now.getTimezoneOffset() + 480;
   	now.setMinutes(now.getMinutes() + utc8DiffMinutes)
        //div内容
	    t_div.innerHTML = checkTime(now.getHours()) + ":" + checkTime(now.getMinutes())+"<br/>";
        //等待一秒钟后调用time方法，由于settimeout在time方法内，所以可以无限调用
        setTimeout(time, 1000);
    }
    //日期
    function date(){
       t_div = document.getElementById('showdate');
        //获取date,kindle上时间少8个小时
        var now = new Date();
	var utc8DiffMinutes = now.getTimezoneOffset() + 480
    	now.setMinutes(now.getMinutes() + utc8DiffMinutes)
        var week = getWeek(now);
        //div内容
        t_div.innerHTML= checkTime(now.getMonth()+1) + "/" +checkTime(now.getDate())+"    "+week;
        setTimeout(time, 1000);
    }
    function checkTime(i)
    {
        if (i<10)
        {
            i="0" + i;
        }
        return i;
    }
    function getWeek (now) {
						var weekday = new Array(7)
						weekday[0] = "SUN"
						weekday[1] = "Mon"
						weekday[2] = "Tues"
						weekday[3] = "WED"
						weekday[4] = "Thur"
						weekday[5] = "Fri"
						weekday[6] = "SAT"

						return weekday[now.getDay()];
					}
	

	function getreq(){
         	  var apiurl = "/kindle/api/v2/quote/quotes/today/"
          	 $.ajax({
         	      type: "get",
               	url: apiurl,
               dataType: "",

               success: function (d) {
		       t_div = document.getElementById('todayEnglish');
		       t_div.innerHTML="<br/>"+d.data.content+"<br/>"+d.data.translation; 
                   },
               error: function () {
			t_div = document.getElementById('todayEnglish');
		       t_div.innerHTML="If I know what love is,it is because of you.<br/>因为你，我懂得了爱"; 

               }
           });
       }
