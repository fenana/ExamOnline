<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>Register</title>
   
   <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">  
   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   	<!-- insert jquery validate -->
	<script src="https://cdn.bootcss.com/jquery-validate/1.17.0/jquery.validate.js"></script>

    <script>
    $().ready(function() {
    	// 在键盘按下并释放及提交后验证提交表单
    	// jquery表单验证
    	  $("#registerForm").validate({
    	    rules: {
    	      password: {
    	        required: true,
    	        minlength: 4
    	      },
    	      password_confirm: {
    	        required: true,
    	        minlength: 4,
    	        equalTo: "#password"
    	        //equalTo定义了验证前后密码是否一致
    	      }
    	    }
    	    
    	   });  
    	});
    </script>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="panel panel-default">
                <div class="panel-heading text-center">
                    <h2>注册新帐号</h2>
                </div>
                <div class="panel-body">
                    <form id="registerForm" class="form-horizontal" role="form" method="POST" action="register?operate=register">
                        <input type="hidden" name="_token" value="QFe0UARw5ThQWOQTtEetutiShrDpBZbCQBd87ypf">

                        <div class="form-group">
                            <label for="email" class="col-md-4 control-label">邮箱</label>
                            <div class="col-md-6">
                                <input id="email" type="text" class="form-control" name="email" value="" autofocus>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label for="name" class="col-md-4 control-label">姓名</label>
                            <div class="col-md-6">
                                <input id="name" type="text" class="form-control" name="name" value="" autofocus>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="col-md-4 control-label">密码</label>
                            <div class="col-md-6">
                                <input id="password" type="password" class="form-control" name="password" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password-confirm" class="col-md-4 control-label">确认密码</label>

                            <div class="col-md-6">
                                <input id="password-confirm" type="password" class="form-control" name="password_confirm" required> 
                            </div>
                        </div>

                        <div class="form-group">

                            <label for="password-confirm" class="col-md-4 control-label"></label>
                            <div class="col-md-6">
                                <div class="g-recaptcha" data-sitekey="6Lc--SkUAAAAAFpxJNUtEhde4ACQlQySrjtL4rO9"></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-6 col-md-offset-4">
                                <a href="/OnlineExam/" class="btn btn-primary">
                                    &nbsp;&nbsp;&nbsp;&nbsp;取消&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                                </a>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button type="submit" class="btn btn-primary">
                                    &nbsp;&nbsp;&nbsp;&nbsp;注册&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </button>
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