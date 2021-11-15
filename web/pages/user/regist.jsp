<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>W会员注册页面</title>
    <%--  静态包含 base 标签  ，css 样式  ，jQuery文件--%>
    <%@include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        //页面加载完成之后
        $(function () {
            //给注册绑定单击事件
            $("#sub_btn").click(function () {
                let $spanErrorMsg = $("span.errorMsg");//span.errorMsg 错误提示

                // 验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                //1、获取用户名输入框里的内容
                let usernameText = $("#username").val();
                //2、创建正则表达式对象
                var usernamePatt = /^\w{5,12}$/;/* \w--> 字母数字下划线  {长度}  */
                //使用 test方法 验证
                if (!usernamePatt.test(usernameText)) {
                    //4、提示用户结果
                    $spanErrorMsg.text("用户名不合法！");

                    return false;
                }


                // 验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
                //1、获取密码输入框里的内容
                let passwordText = $("#password").val();
                //2、创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;/* \w--> 字母数字下划线  {长度}  */
                //使用 test方法 验证
                if (!passwordPatt.test(passwordText)) {
                    //4、提示用户结果
                    $spanErrorMsg.text("密码不合法！");

                    return false;
                }

                // 验证确认密码：和密码相同
                //1、获取确认密码内容
                var repwdText = $("#repwd").val();

                //2、和密码相比较
                if (repwdText !== passwordText) {
                    //3、提示用户
                    $spanErrorMsg.text("确认密码和密码不一致");

                    return false;
                }


                // 邮箱验证：xxxxx@xxx.com
                //1、获取邮箱里的内容
                let emailText = $("#email").val();
                //2、创建正则表达式对象
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3、使用 test 方法验证是否合法
                if (!emailPatt.test(emailText)) {
                    //提示用户
                    $spanErrorMsg.text("邮箱格式不合法");

                    return false;
                }
                // 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。

                //获取验证码里的内容
                let codeText = $("#code").val();
                //去掉验证码前后空格
                codeText = $.trim(codeText);

                if (codeText == null || codeText === "") {
                    //提示用户
                    $spanErrorMsg.text("请输入验证码");

                    return false;
                }


                //4、合法 无提示
                $spanErrorMsg.text("");
            })

            //给验证码的图片绑定单击事件
            $("#code_img").click(function () {
                this.src = "${basePath}kaptcha.jpg?d+" + Math.random();
            })

            $("#username").blur(function () {
                //获取用户名
                var username = this.value;
                $.getJSON(
                    "http://localhost:8989/book/userServlet",
                    "action=ajaxExistsUsername&username=" + username,
                    function (data) {
                        //true 说明用户名已存在，不可用
                        if (data.existsUsername) {
                            //4、提示用户结果
                            $("span.errorMsg").text("用户名已存在 ！");

                        } else {
                            //false ，说明用户名不存在 ， 可用
                            $("span.errorMsg").text("用户名可用 ！");
                        }
                    }
                )


            })


        })
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册W会员</h1>
                    <span class="errorMsg">
                        ${requestScope.msg}
                        <%--<%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>--%>
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="regist">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                               value="${requestScope.username}"/>
                        <%--<%=request.getAttribute("username") == null ? "" : request.getAttribute("username")%>--%>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1"
                               name="repwd" id="repwd"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1"
                               name="email" id="email"
                               value="${requestScope.email}"/>
                        <%--<%=request.getAttribute("email") == null ? "" : request.getAttribute("email")%>--%>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" placeholder="请输入验证码" style="width: 100px; margin-left:14px ;"
                               name="code" id="code"/>
                        <img id="code_img" alt="验证码加载失败！" src="kaptcha.jpg"
                             style="float: right; margin-right: 50px ;margin-top:1px ;width: 100px ;height: 40px;">
                        <br/>
                        <br/>
                        <input type="submit" value="注册" id="sub_btn"/>

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%-- 静态包含 页尾 --%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>