<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
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
      
      
      $().ready(function(){   	   
      	   var questionIdArray = new Array();
      	   var questionContentArray = new Array(); 
      	 var questionOptionsArray = new Array(); 
      	 var questionAnswerArray = new Array(); 
      	   var questionScoreArray = new Array();
         console.log("1ghghbjn");
      	   
      	   <c:forEach items="${examHistory}" var="t">  
      	 questionIdArray.push("${t.questionId}"); //js中可以使用此标签，将EL表达式中的值push到数组中 
      	      //此语句的意思是通过el表达式，将获取到的examHistory对象定义为t，之后即可获取到此对象的值
   	   </c:forEach>
      	    
   	 //内容
      	   <c:forEach items="${contentList}" var="con">  
   	      questionContentArray.push("${con}"); 
   	   </c:forEach>
   	   
  	 //选项
  	   <c:forEach items="${optionsList}" var="opt">  
	      questionOptionsArray.push("${opt}"); 
	   </c:forEach>
	   
		 //答案
  	   <c:forEach items="${answerList}" var="ans">  
	      questionAnswerArray.push("${ans}"); 
	   </c:forEach>
   	  
      	   
   	   <c:forEach items="${examHistory}" var="t">  
   	questionScoreArray.push("${t.score}"); 
   	   </c:forEach>
      	
      	   var number = ${examHistory.size()}; 
      	   console.log(number);
      	   var questionStr = "<thead><tr><th width=5%>题目序号</th><th width=12%>题目内容<th width=12%>题目选项<th width=12%>正确答案</th><th width=3%>得分</th></tr></thead> <tbody>";
      	   for(var i=0;i<number;i++){
      		   var questionId = questionIdArray[i];
      		   var content = questionContentArray[i];
      		 var options = questionOptionsArray[i];
      		var answer = questionAnswerArray[i];
      		   var score = questionScoreArray[i];
      		   
      		   questionStr =  questionStr  + "<tr>"+"<td>"+questionId+"</td>"
      		                             +"<td>"+content+"</td>"
      		                           +"<td>"+options+"</td>"
      		                         +"<td>"+answer+"</td>"
      		                             +"<td>"+score+"</td></tr>";
      	   }
      	   questionStr = questionStr + "</tbody>";
      	   document.getElementById("paperDetailTable").innerHTML=questionStr;
         });
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
             <li><a href="<%=basePath%>user/main">上传考试</a></li>
              <li class="active"><a href="<%=basePath%>user/examHistory">历史考试</a></li>
              <li><a href="<%=basePath%>class/">课程管理</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${user.name} <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="<%=basePath%>user/userInfo">个人信息</a></li>
                  <li><a href="<%=basePath%>user/logout">退出登录</a></li>
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
			<a href="javascript:;" onClick="javascript:history.back(-1);" style=\"color:blue;float:right;margin-bottom:10px;\">返回</a>
			<table class="table" id="paperDetailTable">
				<thead>
					<tr>
						<th>序号</th>
						<th>题目内容</th>
						<th>选项</th>
						<th>答案</th>
						<th>得分</th>
						
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
			
		</div>
	</div>
</div>
      </div>

    </div> <!-- /container -->
</body>
</html>