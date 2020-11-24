<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8" />
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <link rel="stylesheet" href="static/assets/css/reset.css" />
    <link rel="stylesheet" href="static/assets/css/animate.css" />
    <link rel="stylesheet" href="static/assets/css/layout.css" />

    <script src="static/assets/js/jquery-1.9.1.min.js"></script>
    <script src="static/assets/js/zepto.min.js"></script>
    <script src="static/assets/js/fontSize.js"></script>
    <script src="static/assets/js/wcPop/wcPop.js"></script>
    <script type='text/javascript' src='static/assets/js/huanxin/webim.config.js'></script>
    <script type='text/javascript' src='static/assets/js/huanxin/strophe-1.2.8.js'></script>
    <script type='text/javascript' src='static/assets/js/huanxin/websdk-1.4.13.js'></script>

</head>
<body>

<!-- <>微聊主容器 -->
<div class="wechat__panel clearfix">
    <div class="wc__home-wrapper flexbox flex__direction-column">
        <!-- //登录页面 -->
        <div class="wc__lgregPanel flex1">
            <br/>
            <br/>
            <a href="javascript:history.back(-1)"><sapn style="font-size: 0.3rem ;color: #0060a0">&nbsp;&nbsp;&nbsp;返回登陆</sapn></a>
            <h2 class="hdtips">微聊号注册</h2>
            <div class="forms">
                <form action="register" method="post" id="lgregForms" >
                    <ul class="clearfix">
                        <li>
                            <label class="lbl flexbox">
                                <em>账号:</em>
                                <input class="iptxt flex1" type="text" id="userNum" name="userNum" placeholder="请填写微聊号" onblur="ajaxValidate()"/>
                            </label>
                        </li>
                        <li><label class="lbl flexbox"><em>昵称:</em><input class="iptxt flex1" type="text" id="nickname" name="nickname" placeholder="请填写昵称" /></label></li>
                        <li><label class="lbl flexbox"><em>密码:</em><input class="iptxt flex1" type="password" id="password" name="password" placeholder="请设置密码" /></label></li>
                    </ul>
                    <div class="lgway"><a href="javascript:;"></a></div>
                    <div class="btns"><a id="regist" class="wc__btn-primary btn__login" href="javascript:;">注册</a></div>
                </form>
            </div>
        </div>
        <!-- //底部 -->
        <div class="wc__lgregFoot">
            <ul class="clearfix">
                <li><a href="#">找回密码</a></li>
                <li><a href="#">更多</a></li>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript">
    function ajaxValidate() {
            //3.获取用户输入的值
        let username =$("#userNum").val();
        $.ajax({
            url: "findUser",
            data: {"userNum":username},
            type:"post",
            success: function (data) {
                if ("no" == data) {//后端传来的msg的值是no,不能注册。反之。
                    wcPop({content: '该账号已被使用！', time: 2000});
                }
                else if ("yes" == data) {
                }else{
                }
            }
        });
    };
    $(function(){
        var conn = null;
        //创建连接：开启IM连接
        conn = new WebIM.connection({
            isMultiLoginSessions: WebIM.config.isMultiLoginSessions,
            url: WebIM.config.xmppURL,
            heartBeatWait: WebIM.config.heartBeatWait,
            autoReconnectNumMax: WebIM.config.autoReconnectNumMax,
            autoReconnectInterval: WebIM.config.autoReconnectInterval,
            apiUrl: WebIM.config.apiURL,
            isAutoLogin: true
        });
        // 登录验证
        $("#regist").on("click", function(){
            /** 环信初始化 */
            var myusername = $("#userNum").val();
            var mypassword = $("#password").val();
            var mynickname = $("#nickname").val();
            if(myusername == ''){
                wcPop({content: '用户名不能为空！', time: 2000});
            }else if(mypassword == ''){
                wcPop({ content: '密码不能为空！', time: 2000 });
            }else if (mynickname=='') {
                wcPop({ content: '昵称不能为空！', time: 2000 });
            }else{
                /**环信:注册*/
                /** 环信初始化 */
                var options = {
                    username: myusername, //环信用户名
                    password: mypassword, //环信密码
                    nickname: mynickname, //环信昵称
                    appKey: WebIM.config.appkey,
                    success: function(msg) {
                        $("#lgregForms").submit();
                    },
                    error: function(msg) {
                        alert('###注册失败###',msg);
                    },
                    apiUrl: WebIM.config.apiURL
                }//optaions
                conn.registerUser(options);
               conn.isAutoLogin;
            }//else
        });// $("#regist").on
    })


</script>

</body>
</html>
