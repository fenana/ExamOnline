<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.text.*" %>
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
              <li class="active"><a href="#">上传考试</a></li>
              <li><a href="<%=basePath%>user/examHistory">历史考试</a></li>
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
		        
		        <!-- 示例文件下载区域 --> 
                <div class="panel-body">                   
                         <a href="exam/downloadFile?filetype=student" >考生文件模板下载</a>
                </div>
                <div class="panel-body">                   
                         <a href="exam/downloadFile?filetype=question" >试题文件模板下载</a>                      
                </div>
		
		        <label for="examName" class="col-md-4 control-label" style="margin-bottom:4px;">考试名</label>
                <div class="input-group col-md-8" style="margin-bottom:4px;">
                     <input id="examName" type="text" class="form-control" name="examName" placeholder="请输入考试名，例如：软件过程管理" value="" required >
                </div>
                
                <label for="examPassword" class="col-md-4 control-label" style="margin-bottom:4px;">考试口令</label>
                <div class="input-group col-md-8" style="margin-bottom:4px;">
                     <input id="examPassword" type="text" class="form-control" name="examPassword" placeholder="123456" value="" required >
                </div>
                
                <label for="examClass" class="col-md-4 control-label"  style="margin-bottom:4px;">选择课程</label>
                <div class="input-group col-md-8" style="margin-bottom:4px;">
                     <select id="examClass" name="examClass" class="form-control">
                     </select>
                </div>
                
                <label for="startTime" class="col-md-4 control-label" style="margin-bottom:4px">考试开始时间</label>
                <div class='input-group date col-md-8'  style="margin-bottom:4px">
                    <input type='text' class="form-control"  id="startTime" readonly="readonly"/>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
                <label for="timeLength" class="col-md-4 control-label" style="margin-bottom:4px;">考试时长（分钟）</label>
                <div class='input-group date col-md-8'   style="margin-bottom:4px;">
                    <input type='text'  placeholder="120(只能输入数字)" class="form-control" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " id="timeLength" />
         </div>
                
		<h3>导入考试试题</h3>
		<form id="questionForm" enctype="multipart/form-data">
		      <input type="file" name="questionFileInput">
		      <input type="button" value="上传" id="questionUploadSubmit">
		</form>
		
		<h3>试题列表</h3>
		<table class="table" id="questionTable">
			<thead>
				<tr>
					<th>序号</th>
					<th>内容</th>
	     			<th>选项</th>
					<th>答案</th>
					<th>分值</th>
				</tr>
			</thead>
			<tbody>
			    
	    	</tbody>
		</table>
		
		</br>
		
		<label for="questionNum" class="col-md-4 control-label">选择试题数</label>
                <div class='input-group date col-md-8' style="margin-bottom:4px;">
                    <select id="questionNum" name="examClass" class="form-control">
                         
                     </select>
                </div>
		
	    <h3>导入考生名单</h3>
		<form id="studentForm" enctype="multipart/form-data">
		      <input type="file" name="studentFileInput">
		      <input type="button" value="上传" id="studentUploadSubmit">
		</form>
		
	    <h3>考生列表</h3>
		<table class="table" id="studentTable">
			<thead>
				<tr>
					<th>序号</th>
					<th>姓名</th>
	     			<th>年级</th>
					<th>班级</th>
					<th>邮箱</th>
				</tr>
			</thead>
			<tbody>
			    
	    	</tbody>
		</table>
			
			
		</br>

                <button id="publishExam" class="col-md-12 control-label" style="margin-top:15px;height:38px;">发布考试</button>
		</div>
	</div>
</div>
      </div>

    </div> <!-- /container -->
    
        <%		
        //获得课程列表，转换为字符串
        String classStr="";
        HttpSession s=request.getSession();
        List<ExamClass> list = (List)s.getAttribute("classList");
        String cstr = "<option value=\\\"0\\\">请选择考试课程</option>";
   		for(int i=0;i<list.size();i++){
   			cstr=cstr+"<option value=\\\""+list.get(i).getId()+"\\\">"+list.get(i).getClassname()+"</option>";
   		}
        %>
        
    <script>
    $(document).ready(function(){
    	$('#startTime').datetimepicker({
        	lang:'ch'
        });
        
        //class set
        document.getElementById("examClass").innerHTML="<%=cstr%>";
        
    });
    
    $("#questionUploadSubmit").click(function(){
    	console.log("questionUploadSubmit");
    	var formData = new FormData(document.getElementById("questionForm"));
        $.ajax({
           url:"<%=basePath%>exam/uploadQuestions",
           type:"post",
           async:true,
           data:formData,
           dataType:'json', 
           processData:false,
           contentType:false,
           success:function(data){
        	   var list = data.questionList;
        	   console.log("q1:"+list[0].content+"; "+list[0].options);
               console.log("试题上传成功");
               
               var numOption = "";
               var qestr = "<thead><tr> <th width=2%>序号</th>"
   		        +"<th width=4%>内容</th><th width=4%>选项</th><th width=2%>答案</th>"
   		        +"<th width=2%>分值</th></tr></thead><tbody>"
   		        console.log(qestr)
   		        var index=0;
   		        for(var i=0;i<list.length;i++){
   		        	index=index+1;
   		        	qestr=qestr+"<tr>"
						+"<td>"+(index)+"</td>"
						+"<td>"+list[i].content+"</td>"
						+"<td>"+list[i].options+"</td>"
						+"<td>"+list[i].answer+"</td>"
						+"<td>"+list[i].point+"</td>"
					    +"</tr>"
   		        }
   		        console.log(qestr);
   		        document.getElementById("questionTable").innerHTML=qestr;
   		        
   		        for(var i = 1;i<=index;i++){
   		        	numOption = numOption + "<option value="+i+">"+i+"</option>";
   		        }
   		        document.getElementById("questionNum").innerHTML=numOption;
           },
           error:function(e){
               alert("试题上传失败");
           }
       });        
    });
    
    
    $("#studentUploadSubmit").click(function(){
    	console.log("studentUploadSubmit");
    	var formData = new FormData(document.getElementById("studentForm"));
        $.ajax({
           url:"<%=basePath%>exam/uploadStudents",
           type:"post",
           async:true,
           data:formData,
           dataType:'json', 
           processData:false,
           contentType:false,
           success:function(data){
        	   var list = data.studentList;
        	   console.log("q1:"+list[0].name+"; "+list[0].options);
               console.log("考生上传成功");
               
               var str = "<thead><tr> <th width=2%>序号</th>"
   		        +"<th width=4%>姓名</th><th width=4%>年级</th><th width=2%>班级</th>"
   		        +"<th width=2%>邮箱</th></tr></thead><tbody>"
   		        console.log(str)
   		        var index=0;
   		        for(var i=0;i<list.length;i++){
   		        	index=index+1;
   		        	str=str+"<tr>"
						+"<td>"+(index)+"</td>"
						+"<td>"+list[i].studentName+"</td>"
						+"<td>"+list[i].studentGrade+"</td>"
						+"<td>"+list[i].studentClass+"</td>"
						+"<td>"+list[i].studentEmail+"</td>"
					    +"</tr>"
   		        }
   		        console.log(str);
   		        document.getElementById("studentTable").innerHTML=str
           },
           error:function(e){
               alert("考生上传失败");
           }
       });        
    });
    
    
    /** 
     * 遍历表格内容返回数组
     * @param  Int   id 表格id
     * @return Array
     */
    function getTableContent(id){
        var mytable = document.getElementById(id);
        var data = [];
        for(var i=0,rows=mytable.rows.length; i<rows; i++){
            for(var j=0,cells=mytable.rows[i].cells.length; j<cells; j++){
                if(!data[i]){
                    data[i] = new Array();
                }
                data[i][j] = mytable.rows[i].cells[j].innerHTML;
            }
        }
        return data;
    }
    
    $("#publishExam").click(function(){
    	console.log("发布考试中...");
    	var questionArray=getTableContent("questionTable");
    	var studentArray=getTableContent("studentTable");
    	var examName=document.getElementById("examName").value;
    	var examPassword=document.getElementById("examPassword").value;
    	var startTime=document.getElementById("startTime").value;
    	var length=document.getElementById("timeLength").value;
    	var classId = document.getElementById("examClass").value;
    	var questionNumber = document.getElementById("questionNum").value;
    	console.log("examName:"+examName+" ;examPassword:"+examPassword+" ;startTime:"+startTime+"; length:"+length+"; classId:"+classId+"");
    	var data={"examName":examName,"examPassword":examPassword,"startTime":startTime,"length":length,
    			"questionList":questionArray, "studentList":studentArray, "classId":classId,
    			"questionNumber":questionNumber};
    	console.log("data:"+data);
    	$.ajax({
    		 url:"<%=basePath%>exam/publish",
    		 type:"post",
    		 async:true,
             data:data,
             success:function(data){
          	   console.log("考试发布成功");
          	   window.location.href = "<%=basePath%>exam/publishSuccess";
             },
             error:function(XMLHttpRequest, textStatus, errorThrown){
                 alert("考试发布失败:"+XMLHttpRequest+"; "+ textStatus+"; "+errorThrown);
             }
    		
    	});
    });
    </script>
</body>
</html>