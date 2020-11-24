<%--
  Created by IntelliJ IDEA.
  User: yangxueming
  Date: 2019/7/8
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8" />
    <title>详细资料</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-touch-fullscreen" content="yes">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <link rel="stylesheet" href="static/assets/css/reset.css" />
    <link rel="stylesheet" href="static/assets/css/animate.css" />
    <link rel="stylesheet" href="static/assets/css/swiper-3.4.1.min.css" />
    <link rel="stylesheet" href="static/assets/css/layout.css" />

    <script src="static/assets/js/jquery-1.9.1.min.js"></script>
    <script src="static/assets/js/zepto.min.js"></script>
    <script src="static/assets/js/fontSize.js"></script>
    <script src="static/assets/js/swiper-3.4.1.min.js"></script>
    <script src="static/assets/js/wcPop/wcPop.js"></script>
    <script type='text/javascript' src='static/assets/js/huanxin/webim.config.js'></script>
    <script type='text/javascript' src='static/assets/js/huanxin/strophe-1.2.8.js'></script>
    <script type='text/javascript' src='static/assets/js/huanxin/websdk-1.4.13.js'></script>

</head>
<!-- <>微聊主容器 -->
<div class="wechat__panel clearfix">
    <div class="wc__home-wrapper flexbox flex__direction-column">
        <!-- //顶部 -->
        <div class="wc__headerBar fixed">
            <div class="inner flexbox">
                <a class="back splitline" href="javascript:;" onclick="history.back(-1);"></a>
                <h2 class="barTit flex1">个人信息</h2>
            </div>
        </div>

        <!-- //个人信息页 -->
        <div class="wc__ucinfoPanel">
            <div class="wc__ucinfo-personal">
                <ul class="clearfix">
                    <li>
                        <div class="item flexbox flex-alignc wc__material-cell">
                            <label class="lbl flex1">头像</label>
                            <img class="uimg" src="static/assets/img/uimg/${user.headPortrait}" />
                            <input class="chooseImg" type="file" accept="image/*" />
                        </div>
                        <div class="item flexbox flex-alignc wc__material-cell">
                            <label class="lbl flex1">昵称</label>
                            <div class="val">${user.nickname}</div>
                        </div>
                        <div class="item flexbox flex-alignc wc__material-cell">
                            <label class="lbl flex1">微聊号</label>
                            <div class="val">${user.userNum}</div>
                        </div>
                        <div class="item flexbox flex-alignc wc__material-cell">
                            <label class="lbl flex1">更多</label>
                        </div>
                    </li>
                    <li>
                        <div class="item flexbox flex-alignc wc__material-cell">
                            <label class="lbl flex1">我的地址</label>
                            <div class="val">${user.address}</div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

    </div>
</div>


<script type="text/javascript">
    /** __公共函数 */
    $(function(){
        // ...
    });

    /** __自定函数 */
    $(function(){
        // ...
    });
</script>

</body>
</html>

