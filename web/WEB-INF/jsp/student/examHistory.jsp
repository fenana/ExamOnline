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
   	   var examIdArray = new Array();
   	   var nameArray = new Array(); 
   	   var startTimeArray = new Array();
       var endTimeArray = new Array(); 
   	   var lengthArray = new Array(); 
   	   var totalPointArray = new Array(); 
   	   var totalScoreArray = new Array();
   	   var examHistoryList = new Array();
   	   
   	   <c:forEach items="${examHistoryList}" var="t">  
   	      examIdArray.push("${t.examId}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	   </c:forEach>
   	      
   	   <c:forEach items="${examHistoryList}" var="t">  
	      nameArray.push("${t.name}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	   </c:forEach>
   	   
	   <c:forEach items="${examHistoryList}" var="t">  
	      startTimeArray.push("${t.startTime}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	   </c:forEach>
   	   
	   <c:forEach items="${examHistoryList}" var="t">  
   	      endTimeArray.push("${t.endTime}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	   </c:forEach>
   	  
       <c:forEach items="${examHistoryList}" var="t">  
          lengthArray.push("${t.length}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
   	   </c:forEach>
          
       <c:forEach items="${examHistoryList}" var="t">  
          totalPointArray.push("${t.totalPoint}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
	   </c:forEach>
       
	   <c:forEach items="${examHistoryList}" var="t">  
	      totalScoreArray.push("${t.totalScore}"); //js中可以使用此标签，将EL表达式中的值push到数组中  
   	   </c:forEach>
   	   
   	   var number = ${examHistoryList.size()}; 
   	   
   	   var examStr = "<thead><tr><th width=5%>考试名称</th><th width=3%>开始时间</th><th width=3%>结束时间</th><th width=2%>时长</th>"
   	              +"<th width=2%>总分</th><th width=2%>得分</th><th width=2%>查看</th></tr></thead><tbody>";
   	   if(number>0){
   	   	   for(var i=0;i<number;i++){
   	   		   var examId = examIdArray[i];
   	   		   var name = nameArray[i];
   	   		   var examId = examIdArray[i];
   	   		   var startTime = startTimeArray[i];
   	   		   var endTime = endTimeArray[i];
   	   	       var length = lengthArray[i];
   	   		   var totalPoint = totalPointArray[i];
   	   		   var totalScore = totalScoreArray[i];
   	     	   
   	   		   examStr = examStr + "<tr>"+"<td>"+name+"</td>"
   	   		                             +"<td>"+dateTransfer(startTime)+"</td>"
   	   		                             +"<td>"+dateTransfer(endTime)+"</td>"
   	   		                             +"<td>"+length+"</td>"
   	   	 	                             +"<td>"+totalPoint+"</td>"
   	   		                             +"<td>"+totalScore+"</td>"
   	   		                      	+"<td><button style=\"border:none;color:blue;\" onclick=\"queryExamDetail("+examIdArray[i]+")\">查看试卷</button></td>"
   	   		                            +"</tr>";
   	   		                            
   	   	   }
   	   	   examStr = examStr + "</tbody>";
   	   }else{
   		   examStr = "无数据";
   	   }
   	   document.getElementById("examHistoryTable").innerHTML=examStr;
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
              <li><a href="<%=basePath %>user/main">考试</a></li>
              <li class="active"><a href="<%=basePath %>exam/studentExamHistory">历史记录</a></li>
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
			<h3>个人考试历史记录</h3>
			<table class="table" id="examHistoryTable">
				<thead>
					<tr>
						<th>考试名称</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>时长</th>
						<th>总分</th>
						<th>得分</th>
						<th>操作</th>
						<th>查看</th>
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
    <script>
 	function queryExamDetail(id,studentEmail){
		window.location.href = "<%=basePath%>exam/paperDetail?examId="+id+"&studentEmail=${user.email}";
	}
    </script>
</body>
</html>