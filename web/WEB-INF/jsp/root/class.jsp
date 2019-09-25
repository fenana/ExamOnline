<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.exam.model.ExamClass" %>
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
   <link href="https://cdn.bootcss.com/remodal/1.1.1/remodal.css" rel="stylesheet">
   <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/jquery-datetimepicker/2.4.1/jquery.datetimepicker.css">
   <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
   <script src="https://cdn.bootcss.com/remodal/1.1.1/remodal.min.js"></script>
   <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
   <script src="https://cdn.bootcss.com/jquery-datetimepicker/2.4.1/jquery.datetimepicker.js"></script>
   
    <%		
        //获得课程列表，转换为字符串
        String classStr="";
        HttpSession s=request.getSession();
        List<ExamClass> list = (List)s.getAttribute("classList");
        String name = "";
        String cstr = "<thead><tr><th>序号</th><th>课程名称</th><th>创建时间</th><th>操作</th></tr>";
   		for(int i=0;i<list.size();i++){
   			name = list.get(i).getClassname();
   			cstr=cstr+"<tr><td>"+(i+1)+"</td>"
  			         +"<td>"+list.get(i).getClassname()+"</td>"
  			         +"<td>"+list.get(i).getCreateTime().toLocaleString()+"</td>"
  			         +"<td><button onclick=\\\"editClass("+list.get(i).getId()+")\\\" data-remodal-target=\\\"editModal\\\">编辑</button><button onclick=\\\"deleteClass("+list.get(i).getId()+")\\\" data-remodal-target=\\\"deleteModal\\\">删除</button></td></tr>";
   		}
        %>
        
    <script>
    $().ready(function(){
        //class set
        document.getElementById("examClassTable").innerHTML="<%=cstr%>";
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
              <li><a href="<%=basePath%>user/examHistory">历史考试</a></li>
              <li class="active"><a href="<%=basePath%>class/">课程管理</a></li>
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
      				<button id="newClass" data-remodal-target="modal" class="col-md-2" style="float:right;margin-bottom:10px;">新建</button>
      				<table class="table"  id="examClassTable">
      					<thead>
      						<tr>
      							<th width="10%" style="vertical-align: middle;text-align: center;">序号</th>
      							<th width="30%" style="vertical-align: middle;text-align: center;">课程名称</th>
      							<th width="30%" style="vertical-align: middle;text-align: center;">创建时间</th>
      							<th width="30%" style="vertical-align: middle;text-align: center;">操作</th>
      						</tr>
      					</thead>
      					<tbody style="text-align:center">
      											
      					</tbody>
      				</table>			
      			</div>
      		</div>
      	</div>
      </div>



</div> <!-- /container -->
<!-- /remodal 弹出层   新建课程-->
<div class="remodal" data-remodal-id="modal" style="background:#EEEEEE;position:absolute;top:0;right:200px;width:1000px;height:300px;filter:alpha(Opacity=90);-moz-opacity:0.9;opacity: 0.9;">
  <div class="container-fluid">
  <div class="row-fluid">
  <div class="span12">
  <button data-remodal-action="close" class="remodal-close" style="height:30px;width:45px;float:right;">x</button>
  <h4>创建新课程</h4>
  <label for="className" class="col-md-4 control-label" style="margin-bottom:20px;margin-top:10px;">课程名称</label>
  <div class="input-group col-md-8" style="margin-bottom:20px;margin-top:10px;">
      <input id="className" type="text" class="form-control" style="margin-top:10px;margin-bottom:20px" name="className" value="" required >
  </div>
  <br>
  <button data-remodal-action="cancel" class="remodal-cancel">取消</button>
  <button data-remodal-action="confirm" class="remodal-confirm" id="newClassSub">确认</button>
  </div>
  </div>
  </div>
</div>

<!-- /remodal 弹出层   编辑课程-->
<div class="remodal" data-remodal-id="editModal" style="background:#EEEEEE;position:absolute;top:0;right:200px;width:1000px;height:300px;filter:alpha(Opacity=90);-moz-opacity:0.9;opacity: 0.9;">
  <div class="container-fluid">
  <div class="row-fluid">
  <div class="span12">
  <button data-remodal-action="close" class="remodal-close" style="height:30px;width:45px;float:right;">x</button>
  <h4>编辑课程信息</h4>
  <label for="editClassName" class="col-md-4 control-label" style="margin-bottom:20px;margin-top:10px;">课程名称</label>
  <div class="input-group col-md-8" style="margin-bottom:20px;margin-top:10px;">
      <input id="editClassName" type="text" class="form-control" style="margin-top:10px;margin-bottom:20px" name="className" required >
  </div>
  <br>
  <button data-remodal-action="cancel" class="remodal-cancel">取消</button>
  <button data-remodal-action="confirm" class="remodal-confirm" id="editClassSub">确认</button>
  </div>
  </div>
  </div>
</div>

<!-- /remodal 弹出层   编辑课程-->
<div class="remodal" data-remodal-id="deleteModal" style="background:#EEEEEE;position:absolute;top:0;right:200px;width:1000px;height:300px;filter:alpha(Opacity=90);-moz-opacity:0.9;opacity: 0.9;">
  <div class="container-fluid">
  <div class="row-fluid">
  <div class="span12">
  <button data-remodal-action="close" class="remodal-close" style="height:30px;width:45px;float:right;">x</button>
  <h4>确认删除此课程？</h4>
  <br>
  <button data-remodal-action="cancel" class="remodal-cancel">取消</button>
  <button data-remodal-action="confirm" class="remodal-confirm" id="deleteClassSub">确认</button>
  </div>
  </div>
  </div>
</div>

<script type="text/javascript">
	$("#createTime").datetimepicker({
		lang:'ch'
	});
	$("#createTime").attr("z-index",999);
	document.getElementById("editClassName").innerHTML="<%=name%>";
	$("#newClassSub").click(function(){
		var className = document.getElementById("className").value;
		console.log("className:"+className);
		var data = {"className":className};
		$.ajax({
			url:"<%=basePath%>class/addClass",
   		    type:"post",
   		    async:true,
            data:data,
            dataType:'json', 
            success:function(data){
         	   console.log("创建课程成功！");
         	   var list = data.classList;
         	   var str = "<thead><tr><th>序号</th><th>课程名称</th><th>创建时间</th><th>操作</th></tr>";
          	   for(var i=0;i<list.length;i++){
          		    var id = list[i].id;
          		    var name = list[i].classname;
          		    console.log("id="+id);
           			str=str+"<tr><td>"+(i+1)+"</td>"
          			         +"<td>"+list[i].classname+"</td>"
          			         +"<td>"+dateTransfer(list[i].createTime)+"</td>"
          			       +"<td><button onclick=\"editClass("+id+")\" data-remodal-target=\"editModal\">编辑</button><button onclick=\"deleteClass("+id+")\" data-remodal-target=\"deleteModal\">删除</button></td></tr>";
           	   }
          	   console.log(str);
          	   document.getElementById("examClassTable").innerHTML=str;
          	   
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
                alert("创建课程失败:"+XMLHttpRequest+"; "+ textStatus+"; "+errorThrown);
            }
		});
	});
	
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
    function editClass(classId){
    	$("#editClassSub").click(function(){
    		var className = document.getElementById("editClassName").value;
        	var data = {"classId":classId,"className":className};
        	console.log("classId:"+classId+", className:"+className);
    		$.ajax({
    			url:"<%=basePath%>/class/editClass",
       		    type:"post",
       		    async:true,
                data:data,
                dataType:'json', 
                success:function(data){
             	   console.log("更新课程成功！");
             	   var list = data.classList;
             	   var str = "<thead><tr><th>序号</th><th>课程名称</th><th>创建时间</th><th>操作</th></tr>";
              	   for(var i=0;i<list.length;i++){
              		    var id = list[i].id;
              		    var name = list[i].classname;
              		    console.log("id="+id);
               			str=str+"<tr><td>"+(i+1)+"</td>"
              			         +"<td>"+list[i].classname+"</td>"
              			         +"<td>"+dateTransfer(list[i].createTime)+"</td>"
              			         +"<td><button onclick=\"editClass("+id+")\" data-remodal-target=\"editModal\">编辑</button><button onclick=\"deleteClass("+id+")\" data-remodal-target=\"deleteModal\">删除</button></td></tr>";
               	   }
              	   console.log(str);
              	   document.getElementById("examClassTable").innerHTML=str;
              	   
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert("更新课程失败:"+XMLHttpRequest+"; "+ textStatus+"; "+errorThrown);
                }
    		});
    	});
    	
    }
	
    function deleteClass(classId){
    	var data = {"classId":classId};
    	$("#deleteClassSub").click(function(){
    		$.ajax({
    			url:"<%=basePath%>/class/deleteClass",
       		    type:"post",
       		    async:true,
                data:data,
                dataType:'json', 
                success:function(data){
             	   console.log("删除课程成功！");
             	   var list = data.classList;
             	   var str = "<thead><tr><th>序号</th><th>课程名称</th><th>创建时间</th><th>操作</th></tr>";
              	   for(var i=0;i<list.length;i++){
              		    var id = list[i].id;
              		    var name = list[i].classname;
              		    console.log("id="+id);
               			str=str+"<tr><td>"+(i+1)+"</td>"
              			         +"<td>"+list[i].classname+"</td>"
              			         +"<td>"+dateTransfer(list[i].createTime)+"</td>"
              			         +"<td><button onclick=\"editClass("+id+")\" data-remodal-target=\"editModal\">编辑</button><button onclick=\"deleteClass("+id+")\" data-remodal-target=\"deleteModal\">删除</button></td></tr>";
               	   }
              	   console.log(str);
              	   document.getElementById("examClassTable").innerHTML=str;
              	   
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    alert("删除课程失败:"+XMLHttpRequest+"; "+ textStatus+"; "+errorThrown);
                }
    		});
    	});
    	
    }
	
</script>       
</body>
</html>