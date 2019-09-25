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
   <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
   <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/jquery-datetimepicker/2.4.1/jquery.datetimepicker.css">
   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script src="https://cdn.bootcss.com/jquery-datetimepicker/2.4.1/jquery.datetimepicker.js"></script>
   
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
           var studentEmailArray = new Array();
      	   var totalPointArray = new Array(); 
      	   var totalScoreArray = new Array(); 
      	   var examIdArray = new Array();
  	     
      	   <c:forEach items="${examSocreList}" var="t">  
      	      studentEmailArray.push("${t.studentEmail}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
   	       </c:forEach>
      	      
      	   <c:forEach items="${examSocreList}" var="t">  
      	      totalPointArray.push("${t.totalPoint}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
   	       </c:forEach>
      	   
   	       <c:forEach items="${examSocreList}" var="t">  
   	          totalScoreArray.push("${t.totalScore}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
   	       </c:forEach>
   	          
   	       <c:forEach items="${examSocreList}" var="t">  
   	          examIdArray.push("${t.examId}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	       </c:forEach>
      	   
      	   var number = ${examSocreList.size()};
      	   var examStr = "<thead><tr><th>学生邮箱</th>"
	              +"<th>总分</th><th>得分</th><th>操作</th></tr></thead> <tbody>";
      	   if(number>0){
          	   for(var i=0;i<number;i++){
          		   var studentEmail = studentEmailArray[i];
          		   var totalPoint = totalPointArray[i];
          		   var totalScore = totalScoreArray[i];
            	   var examId = examIdArray[i];
          		   examStr = examStr + "<tr>"+"<td>"+studentEmail+"</td>"
          	 	                             +"<td>"+totalPoint+"</td>"
          		                             +"<td>"+totalScore+"</td>"
          		                             +"<td><a href=\"/OnlineExam/exam/paperDetail.html?examId="+examId+"&studentEmail=" +studentEmail + "\">查看</a></td></tr>";
          	   }
      	   }else{
      		   examStr = "无数据";
      	   }
      	   
      	   examStr = examStr + "</tbody>"; 
      	   document.getElementById("examScoreTable").innerHTML = examStr;
         
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
      				             <div class="page-header">
      				               <a href="javascript:;" onClick="javascript:history.back(-1);" style=\"color:blue;float:right;margin-bottom:10px;\">返回</a>
						           <h1>
							          <small><span><strong>${exam.name}</strong></span></small>
						           </h1>
						           <h3>
							          <label>时间：${exam.startTime.toLocaleString()}--${exam.endTime.toLocaleString()};   时长：${exam.length}</label>
						           </h3>
					        </div>
					                <h3>考试结果如下：</h3>
      								<table class="table"  id="examScoreTable">
      								</table>
      						</div>
      				</div>
      		</div>
      </div>
    
    <script>
   
    </script>
</body>
</html>