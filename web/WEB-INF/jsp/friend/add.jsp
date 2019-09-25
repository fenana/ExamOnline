<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2019/9/23
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@include file="../utils.jsp"%>
<html>
<head>
    <title>资讯添加页</title>
    <script src="${basePath}/scripts/formvalidator4.1.3/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="${basePath}/scripts/formvalidator4.1.3/formValidator-4.1.3.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${basePath}/scripts/formvalidator4.1.3/formValidatorRegex.js" type="text/javascript" charset="UTF-8"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $.formValidator.initConfig({formID:"form1",theme:"ArrowSolidBox",submitOnce:true});

            $("#title").formValidator({onShowText:"请输入标题",onShow:"请输入标题",onFocus:"至少5个字符,最多50个字符",onCorrect:"输入正确"}).inputValidator({min:5,max:50,onError:"你输入的用户名非法,请确认"});
            $("#content").formValidator({onShowText:"这家伙很懒，什么都没有留下。",onShow:"请输入你的描述",onFocus:"描述至少要输入10个汉字或20个字符",onCorrect:"恭喜你,你输对了",defaultValue:"这家伙很懒，什么都没有留下。"}).inputValidator({min:20,onError:"你输入的描述长度不正确,请确认"});
        });

    </script>
    <style type="text/css">
        body,div,span{font-size:12px;}
    </style>

    <script type="text/javascript" src="${basePath}/scripts/wangEditor-3.1.1/wangEditor.js"></script>


</head>
<body>

<h1>资讯添加页</h1>
<form id="form1" action="" method="post">
    <table border="1" width="800" align="center">
        <tr>
            <td width="120">姓名：</td>
            <td ><input type="text" id="title" name="name"/></td>
            <td width="350"><div id="titleTip"></div></td>
        </tr>
        <tr>
            <td>座右铭：</td>
            <td>
                <div id="div1">
                    <input type="text" id="title1" name="motto"/>
                </div>

                <textarea id="content" name="content" style="display: none; width:350px;"></textarea>
                <script type="text/javascript">
                    var E = window.wangEditor
                    var editor = new E('#div1')
                    var $text1 = $('#content')
                    editor.customConfig.onchange = function (html) {
                        // 监控变化，同步更新到 textarea
                        $text1.val(html)
                    }
                    editor.create()
                    // 初始化 textarea 的值
                    $text1.val(editor.txt.html())
                </script>

            </td>
            <td><span id="contentTip"></span></td>
        </tr>
        <tr>
            <td colspan="3">
                ${info}
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="提交"/>
                <input type="button" onclick="window.location.href='index';" value="返回"></input>
            </td>
        </tr>


    </table>
</form>

</body>
</html>
