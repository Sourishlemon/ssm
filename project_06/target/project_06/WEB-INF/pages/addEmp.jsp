<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加员工信息</title>
</head>
<body>
<!--隐藏的div-->
<div id="saveEmpDiv" style="display:none;margin-top: 20px;">
    <form class="layui-form" action="" id="saveEmpForm">
        <div class="layui-form-item">
            <label class="layui-form-label">编号</label>
            <div class="layui-input-inline">
                <input type="text" name="empno" lay-verify="required" placeholder="请输入活动编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">活动名</label>
            <div class="layui-input-inline">
                <input type="text" name="ename" lay-verify="required|ename" placeholder="请输入活动名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地点</label>
            <div class="layui-input-inline">
                <input type="text" name="job" lay-verify="required" placeholder="请输入活动地点" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">报名人数</label>
            <div class="layui-input-inline">
                <input type="text" name="mgr" lay-verify="" placeholder="请输入报名人数" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">当前人数</label>
            <div class="layui-input-inline">
                <input type="text" name="sal" lay-verify="required|number|sal" placeholder="请输入当前人数" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">活动日期</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="hiredate" id="hiredate" lay-verify="required" placeholder="yyyy-MM-dd" autocomplete="off">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">团费</label>
            <div class="layui-input-inline">
                <input type="text" name="comm" lay-verify="required|number|comm" placeholder="团费" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">部门</label>
            <div class="layui-input-inline">
                <select name="deptno" id="saveDept" lay-verify="required"></select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="formAdd">添加</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>

</div>
</body>
</html>
