<%--
  Created by IntelliJ IDEA.
  User: Sourish_lemon
  Date: 2021/6/8
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <script src="./js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="./js/layui/css/layui.css">
    <link rel="stylesheet" href="./css/adminLogin.css">
</head>
<body>
<div class="wrap">
    <img src="images/login.png" class="imgStyle">
    <div class="loginForm">
        <form>
            <div class="logoHead">
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>用户名:</label>
                </div>
                <div class="usernameDiv">
                    <i class="layui-icon layui-icon-username adminIcon"></i>
                    <input id="loginUsername" class="layui-input adminInput" type="text" name="username" placeholder="输入用户名" >
                </div>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>密码:</label>
                </div>
                <div class="passwordDiv">
                    <i class="layui-icon layui-icon-password adminIcon"></i>
                    <input id="loginPassword" class="layui-input adminInput" type="password" name="password" placeholder="输入密码">
                </div>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>验证码:</label>
                </div>
                <div class="cardDiv">
                    <input id="loginCard" class="layui-input cardInput" type="text" name="card" placeholder="输入验证码">
                </div>
                <div class="codeDiv">
                    <input id="loginCode" class="layui-input codeInput"  type="button">
                </div>
            </div>
            <div class="usernameWrapDiv">
                <div class="submitLabel">
                    <label>没有账号？<a href="#" id="loginRegister">点击注册</a></label>
                </div>
                <div class="submitDiv">
                    <input id="loginBtn" type="button" class="submit layui-btn layui-btn-primary" value="登录"></input>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="./js/layui/layui.js" type="text/javascript"></script>
<script>
    layui.use(['layer'],function () {
        var layer = layui.layer;
    })
    $(function () {
        // 页面初始化生成验证码
        window.onload = createCode('#loginCode');
        // 验证码切换
        $('#loginCode').click(function () {
            createCode('#loginCode');
        });
        // 登陆事件
        $('#loginBtn').click(function () {
            login();
        });
        // 注册事件
        $('#loginRegister').click(function () {
            register();
        });
    });
    // 生成验证码
    function createCode(codeID) {
        var code = "";
        // 验证码长度
        var codeLength = 4;
        // 验证码dom元素
        var checkCode = $(codeID);
        // 验证码随机数
        var random = [0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',
            'S','T','U','V','W','X','Y','Z'];
        for (var i = 0;i < codeLength; i++){
            // 随机数索引
            var index = Math.floor(Math.random()*36);
            code += random[index];
        }
        // 将生成的随机验证码赋值
        checkCode.val(code);
    }
    // 校验验证码、用户名、密码
    function validateCode(inputID,codeID) {
        var inputCode = $(inputID).val().toUpperCase();
        var cardCode = $(codeID).val();
        var loginUsername = $('#loginUsername').val();
        var loginPassword = $('#loginPassword').val();
        if ($.trim(loginUsername) == '' || $.trim(loginUsername).length<=0){
            layer.alert("用户名不能为空");
            return false;
        }
        if ($.trim(loginPassword) == '' || $.trim(loginPassword).length<=0){
            layer.alert("密码不能为空");
            return false;
        }
        if (inputCode.length<=0){
            layer.alert("验证码不能为空");
            return false;
        }
        if (inputCode != cardCode){
            layer.alert("请输入正确验证码");
            return false;
        }
        return true;
    }
    // 登录流程
    function login() {
        if (!validateCode('#loginCard','#loginCode')){
            //阻断提示
        }else {
            var loginUsername = $('#loginUsername').val();
            var loginPassword = $('#loginPassword').val();
            var params = {};
            params.username = loginUsername;
            params.password = loginPassword;
            var loginLoadIndex = layer.load(2);
            $('#loginBtn').val("正在登录...");
            $.ajax({
                url:"user/login",
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify(params),
                dataType:'json',
                type:"post",
                success:function (data) {
                    layer.close(loginLoadIndex);
                    window.location.href = 'user/pageJump?str=groupbooking';




                },
                error:function () {
                    layer.close(loginLoadIndex);
                    layer.alert("登录失败！")
                    $('#loginBtn').val("登录");
                }
            });
        }

    }
    // 注册流程
    function register() {
        layer.open({
            type:'1',
            content:$('.registerPage'),
            title:'注册',
            area:['430px','400px'],
            btn:['注册','重置','取消'],
            closeBtn:'1',
            btn1:function (index,layero) {
                //注册回调
                layer.close(index);
                var registerUsername = $('#registerUsername').val();
                var registerPassword = $('#registerPassword').val();
                var registerWellPassword = $('#registerWellPassword').val();
                var params = {};
                params.registerUsername = registerUsername;
                params.registerPassword = registerPassword;
                params.registerWellPassword = registerWellPassword;
                var registerLoadIndex = layer.load(2);
                $.ajax({
                    type:'post',
                    url:'user/register',
                    dataType:'json',
                    data:params,
                    // contentType:'application/json',
                    success:function (data) {
                        layer.close(registerLoadIndex);

                        layer.alert("注册成功！")
                    },
                    error:function () {
                        layer.close(registerLoadIndex);
                        layer.alert("用户已存在！")
                    }
                });
            },
            btn2:function (index,layero) {
                //重置回调
                var registerUsername = $('#registerUsername').val("");
                var registerPassword = $('#registerPassword').val("");
                var registerWellPassword = $('#registerWellPassword').val("");
                // 防止注册页面关闭
                return false;
            },
            btn3:function (index,layero) {
                //取消回调
            }
        })
    }
</script>

</body>
<div class="registerPage">
    <div class="registerDiv">
        <form>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>用户名:</label>
                </div>
                <div class="usernameDiv">
                    <i class="layui-icon layui-icon-username adminIcon"></i>
                    <input id="registerUsername" class="layui-input adminInput" type="text" name="username" placeholder="输入用户名" >
                </div>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>密码:</label>
                </div>
                <div class="passwordDiv">
                    <i class="layui-icon layui-icon-password adminIcon"></i>
                    <input id="registerPassword" class="layui-input adminInput" type="password" name="password" placeholder="输入密码">
                </div>
            </div>
            <div class="usernameWrapDiv">
                <div class="usernameLabel">
                    <label>确认密码:</label>
                </div>
                <div class="passwordDiv">
                    <i class="layui-icon layui-icon-password adminIcon"></i>
                    <input id="registerWellPassword" class="layui-input adminInput" type="password" name="password" placeholder="输入密码">
                </div>
            </div>
        </form>
    </div>
</div>
</html>

