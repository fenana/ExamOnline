<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="utils.jsp"%>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>基于SSM和Mysql集群的在线考试系统的设计与实现...</title>
   
   <link rel="stylesheet" href="${basePath}/scripts/bootstrap-3.3.7-dist/css/bootstrap.min.css">
   <script src="${basePath}/scripts/jquery/2.1.1/jquery.min.js"></script>
   <script src="${basePath}/scripts/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
   
   <%		
     //获取cookie内保存的用户信息
	Cookie[] cookies = request.getCookies();
	String cookieEmail="";
	String cookiePassword="";
	String cookieType="";
	if(cookies!=null){
		for(int i=0;i<cookies.length;i++) {
			if(cookies[i].getName().equals("email")) {
				cookieEmail=cookies[i].getValue();
			}
			if(cookies[i].getName().equals("password")) {
				cookiePassword=cookies[i].getValue();
			}
			if(cookies[i].getName().equals("type")) {
				cookieType=cookies[i].getValue();
			}
		} 
	}%>
	
	
 	<script type="text/javascript">
	    $().ready(function() {
	    	//实现选择框根据cookie数据自动选定
	    	if("<%=cookieEmail%>" != ""){
	    		$("#email").val("<%=cookieEmail%>");
	    	}
	    	if("<%=cookiePassword%>" != ""){
	    		$("#password").val("<%=cookiePassword%>");
	    	}
	    });
	</script>
   
</head>
<body>
  <div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h2>基于SSM和Mysql集群的基于SSM和Mysql集群的在线考试系统的设计与实现...的设计与实现...</h2>
                </div>
                <div class="panel-body" style="padding-top: 40px;">
                    <form id="loginForm" class="form-horizontal" role="form" method="post" action="?operate=login">
                        <input type="hidden" name="_token" value="QFe0UARw5ThQWOQTtEetutiShrDpBZbCQBd87ypf">
                        <div class="form-group">
                            <label for="email" class="col-md-4 control-label">邮箱</label>
                            <div class="col-md-6">
                                <input id="email" type="email" class="form-control" name="email" value="" required autofocus>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="col-md-4 control-label">密码</label>

                            <div class="col-md-6">
                                <input id="password" type="password" class="form-control" name="password" value="" required >
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="userType" class="col-md-4 control-label">身份</label>

                            <div class="col-md-6">
                                <select class="form-control" id="userType" name="userType" required>
							         <option value="1">学生</option>
							         <option value="0">管理员</option>
						        </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="remember" > Remember Me
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <button type="submit" class="btn btn-primary">
                                                                                                登录
                                </button>
                                <a class="btn btn-link" href="user/register">
                                    注册新帐号
                                </a>
                                 <a class="btn btn-link" href="user/backpassword">
                                    找回密码
                                </a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>


