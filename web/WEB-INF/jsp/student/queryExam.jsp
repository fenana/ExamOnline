<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.exam.model.Exam" %>
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
				    <a href="javascript:;" onClick="javascript:history.back(-1);" style=\"color:blue;float:right;margin-bottom:10px;\">返回</a>
				    </br>
					<label>欢迎参加${exam.name}，考试开始时间为${exam.startTime.toLocaleString()},时长为${exam.length}分钟,请按提示输入验证信息</label>
					
					<form method="POST" action="<%=basePath %>exam/startExam?examId=${exam.id}">
					   <label for="studentEmail" class="col-md-4 control-label" style="margin-bottom:4px;">考生邮箱</label>
                	   <div class="input-group col-md-8" style="margin-bottom:4px;">
                            <input id="studentEmail" type="text" class="form-control" name="studentEmail" placeholder="请输入邮箱" value="" required >
                	   </div>
                	
                	   <label for="examPasswd" class="col-md-4 control-label" style="margin-bottom:4px;">考试密码</label>
                	   <div class="input-group col-md-8" style="margin-bottom:4px;">
                     	    <input id="examPassword" type="text" class="form-control" name="examPassword" placeholder="请输入密码" value="" required >
                	   </div>
                	   </br>

                       <button id="verifyExam" type="submit" class="col-md-12 control-label" style="margin-top:15px;height:38px;">提交</button>
                    </form>
				</div>
	    	</div>
        </div>
      </div>

</div> <!-- /container -->
    
<script>



</script>
</body>
</html>