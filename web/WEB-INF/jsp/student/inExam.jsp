<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.exam.model.Question" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <title>基于SSM和Mysql集群的在线考试系统的设计与实现...</title>
   <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">  
   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   
   <script>
      function dateTransfer(dateTime){
    	  var date = new Date(dateTime);
    	  return date.getFullYear() + "/" + getzf(date.getMonth() + 1) + "/" + getzf(date.getDate()) + "/ " 
    	      + getzf(date.getHours()) + ":" + getzf(date.getMinutes()) + ":" + getzf(date.getSeconds());
      }
      
      //补0操作  
      function getzf(num){  
          if(parseInt(num) < 10){  
              num = '0'+num;  
          }  
          return num;  
      }  
      
   </script>
</head>
<body>
  <div class="container">
      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">基于SSM和Mysql集群的基于SSM和Mysql集群的在线考试系统的设计与实现...的设计与实现...</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="active"><a href="<%=basePath %>user/main">考试</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${user.name} <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="<%=basePath %>user/userInfo">个人信息</a></li>
                  <li><a href="<%=basePath %>user/logout">退出登录</a></li>
                </ul>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>

      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <div class="container-fluid">
	    	<div class="row-fluid">
				<div class="span12">
					<div style="float:right;">
					<label style="" for="timer" class="control-label">剩余时间：</label>
					<div class="input-group">
						<label id="timer" class="form-control" style="background:#EEEEEE;"></label>
					</div>
					</div>
				   <form id="examPaperForm" method="POST" action="<%=basePath %>exam/submit?examId=${exam.id}&&studentEmail=${examStudent.studentEmail}">
				   
				    <div class="page-header">
						<h1>
							<small><span><strong>${exam.name}</strong></span></small>
						</h1>
						<h3>
							<label>时间：${exam.startTime.toLocaleString()}--${exam.endTime.toLocaleString()};   时长：${exam.length}</label>
						</h3>
					</div>
				     
				    <div id="questionDiv"></div>
				     
				    <button id="commit" type="submit">提交</button>
				     
				   </form>
				</div>
	    	</div>
        </div>
      </div>

</div> <!-- /container -->

<script>
   $().ready(function(){
	   var number = ${questionList.size()}; 
	   var qstr = "";
	   var arrayContent = new Array();  
	   <c:forEach items="${questionList}" var="t">  
	     arrayContent.push("${t.content}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	   </c:forEach>
	   
	   var arrayOptions = new Array();  
	   <c:forEach items="${questionList}" var="t">  
	     arrayOptions.push("${t.options}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	   </c:forEach> 
	     
	   var arrayQuestionId = new Array();  
	   <c:forEach items="${questionList}" var="t">  
	     arrayQuestionId.push("${t.id}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	   </c:forEach> 
	   
	   for(var i=0;i<number;i++){
/* 		   var content = '${questionList[""+i+""].content}';//中间双重引号，原因暂时不是很清楚
		   var options = '${questionList[""+i+""].options}';
		   var questionId = '${questionList[""+i+""].id}'; */
		   var content = arrayContent[i];
		   var options = arrayOptions[i];
		   var questionId = arrayQuestionId[i]; 
		   console.log("questionId:"+questionId+"; content:"+content);
		   var optionArray = new Array();
		   optionArray = options.split(";  ");
		   
  		   qstr = qstr + "<table><thead><tr><th>"+(i+1)+":"+content+"</th></tr></thead><tbody><tr><td><ul>"
                       + " <li><label><input type=\"checkbox\"  name=\""+questionId+"\" value=\"A\">"+optionArray[0]+"</label></li>"
                       + " <li><label><input type=\"checkbox\"  name=\""+questionId+"\" value=\"B\">"+optionArray[1]+"</label></li>"
                       + " <li><label><input type=\"checkbox\"  name=\""+questionId+"\" value=\"C\">"+optionArray[2]+"</label></li>"
                       + " <li><label><input type=\"checkbox\"  name=\""+questionId+"\" value=\"D\">"+optionArray[3]+"</label></li>"                   
                       + "</ul></td></tr></tbody></table></br>";  
           //注意，上面复选框的name=questionId
	   }
	   
	   document.getElementById("questionDiv").innerHTML=qstr;
	   
	   tick();
	   window.onload = tick();
	   
   });
   
   function tick(){
	   var time = "${exam.endTime.toLocaleString()}";
	   var myDate = new Date(time);
	   var nowDate = new Date();
	   console.log(time);
	   var year = myDate.getFullYear();
	   var month = myDate.getMonth()+1;
	   var day = myDate.getDate();
	   var hour = myDate.getHours();
	   var minutes = myDate.getMinutes();
	   var seconds = myDate.getSeconds();
	   day = checkTime(day);
	   hour = checkTime(hour);
	   minutes = checkTime(minutes);
	   seconds = checkTime(seconds);
	   console.log(year,month,day,hour,minutes,seconds);
	   var nowYear = nowDate.getFullYear();
	   var nowMonth = nowDate.getMonth()+1;
	   var nowDay = nowDate.getDate();
	   var nowHour = nowDate.getHours();
	   var nowMinutes = nowDate.getMinutes();
	   var nowSeconds = nowDate.getSeconds();
	   
	   leftTimer(year,month,day,hour,minutes,seconds);
	   if(year==nowYear&&month==nowMonth&&day==nowDay&&minutes==nowMinutes&&seconds==nowSeconds){
		   alert("考试时间已到，请提交！");
		   document.getElementById("examPaperForm").submit();
	   }
	   
	   window.setTimeout("tick()",1000);
   }
   
   function leftTimer(year,month,day,hour,minute,second){ 
	 	var leftTime = (new Date(year,month-1,day,hour,minute,second)) - (new Date()); //计算剩余的毫秒数 
	 	var days = parseInt(leftTime/1000/60/60/24 , 10); //计算剩余的天数 
	 	var hours = parseInt(leftTime/1000/60/60%24 , 10); //计算剩余的小时 
	 	var minutes = parseInt(leftTime/1000/60%60, 10);//计算剩余的分钟 
	 	var seconds = parseInt(leftTime/1000%60, 10);//计算剩余的秒数 
	 	days = checkTime(days); 
	 	hours = checkTime(hours); 
	 	minutes = checkTime(minutes); 
	 	seconds = checkTime(seconds);
	 	setInterval("leftTimer(year,month,day,hour,minute,second)",1000); 
	 	document.getElementById("timer").innerHTML = days+"天" + hours+"小时" + minutes+"分"+seconds+"秒";
	 	window.setTimeout("leftTimer(year,month,day,hour,minute,second)",1000);
	} 
	function checkTime(i){ //将0-9的数字前面加上0，例1变为01 
	 if(i<10) 
	 { 
	  i = "0" + i; 
	 } 
	 return i; 
	} 
</script>
</body>
</html>