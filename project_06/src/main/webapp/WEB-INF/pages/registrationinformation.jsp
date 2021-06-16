<%--
  Created by IntelliJ IDEA.
  User: Sourish_lemon
  Date: 2021/6/9
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看活动</title>
    <link rel="stylesheet" type="text/css" href="../js/layui/css/layui.css" />
    <script src="../js/jquery-3.4.1.min.js"></script>
    <script src="../js/layui/layui.js"></script>
</head>
<body>
<h1 class="layui-font-red">上方山烧烤活动报名成功！请交团费</h1>
<div class="layui-input-inline">
    <button type="button" onclick="selectRole()" class="layui-btn layui-btn-normal">交纳团费</button>

</div>

<script>
    //选择角色弹层
    function selectRole(){
        layer.alert("缴费成功")
    }
</script>
</body>

</html>
