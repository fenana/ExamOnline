<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>基于SSM和Mysql集群的在线考试系统的设计与实现...</title>
   
   <link rel="stylesheet" href="http://localhost:8080/ssmweb/bootstrap/3.3.7/css/bootstrap.min.css">
   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   
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
                    <h2>基于SSM和Mysql集群的的设计与实现...</h2>
                    <h3>请输入新密码</h3>
                </div>
                <div class="panel-body" style="padding-top: 40px;">
                    <form id="loginForm" class="form-horizontal" role="form" method="post" action="backpassword">
                        <input type="hidden" name="_token" value="QFe0UARw5ThQWOQTtEetutiShrDpBZbCQBd87ypf">
                        <div class="form-group">
                            <label for="email" class="col-md-4 control-label">用户名</label>
                            <div class="col-md-6">
                                ${userModel.name }
                                <input id="userid" type="hidden" class="form-control" name="userid" value="${userModel.id }" required >
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="newpwd" class="col-md-4 control-label">新密码</label>

                            <div class="col-md-6">
                                <input id="newpwd" type="password" class="form-control" name="newpwd" value="" required >
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="renewpwd" class="col-md-4 control-label">新密码确认</label>

                            <div class="col-md-6">
                                <input id="renewpwd" type="password" class="form-control" name=""renewpwd"" value="" required >
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <button type="submit" class="btn btn-primary">
                                重置密码
                                </button>
                                <span>
                                ${errorinfo }
                                </span>
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