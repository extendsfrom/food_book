<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>厨神</title>
	<link rel="stylesheet" media="screen" href="css/style.css">
	<style type="text/css">
		.register {
			width: 400px;
			height: 500px;
			/* background-color: red; */
			position: absolute;
			left: 50%;
			top: 50%;
			margin-left: -200px;
			margin-top: -250px;	
		}
	</style>
</head>
<body>
	<div class="register">
		<div style="width: 100%;height: 100px;text-align: center;">
			<font style="font-family: cursive;font-size: 60px;font-weight: bolder;color: #0F88EB;">厨神</font><br>
			<font style="font-family: STFangsong;font-size: 20px;color: #0F88EB;">与世界分享你的知识、经验和见解</font>
		</div>
		<div style="width: 100%;height: 40px;">
			<div style="width: 50%;height: 100%; float: left;text-align: right;">
				<a style="line-height: 40px;font-family: STFangsong;margin-right: 20px;font-size: 18px;color: #d5d5d5;">注册</a>
			</div>
			<div style="width: 50%;height: 100%; float: left;text-align: letter-spacing;">
				<a style="line-height: 40px;font-family: STFangsong;margin-left: 20px;font-size: 18px;color: #d5d5d5;">登录</a>
			</div>
		</div>
		<div style="width: 90%;height: 160px;margin: 0 auto;border: solid #d5d5d5 1px; background-color:  white;border-radius:3px;">
			<form action="<%=request.getContextPath()%>/user/userRegister.do" method="post">
				<div style="width: 100%;height: 38px;border-bottom: solid #d5d5d5 1px;">
					<input name="userName" type="text" placeholder="昵称" style="width: 80%;height: 90%;border: 0;outline:medium;margin-left: 10%;"/>
				</div>
				<div style="width: 100%;height: 38px;border-bottom: solid #d5d5d5 1px;">
					<input name="userPhone" type="text" placeholder="手机号" style="width: 80%;height: 90%;border: 0;outline:medium;border: none;margin-left: 10%;"/>
				</div>
				<div style="width: 100%;height: 40px;">
					<input name="userPassword" type="text" placeholder="密码（不少于6位）" style="width: 80%;height: 90%;border: 0;outline:medium;border: none;margin-left: 10%;"/>
				</div>
				<div style="width: 100%;height: 40px;margin-top: 20px;">
					<button style="width: 360px;height: 100%; background-color: #0F88EB;border: solid 1px #0F88EB;border-radius:3px;font-family: STFangsong;font-size: 15px;color: white;" onclick="doRegister();">注册厨神</button>
				</div>
			</form>
		</div>
		<div style="width: 90%;height: 200px;margin: 0 auto;">
			<div style="width: 100%;height: 40px;margin-top: 20px;">
				<button style="width: 360px;height: 100%; border: solid 1px #d5d5d5;border-radius:3px;font-family: STFangsong;font-size: 14px;">
					点击「注册」按钮，即代表你同意《厨神协议》
				</button>
			</div>
			<div style="width: 100%;height: 40px;margin-top: 60px;">
				<button style="width: 360px;height: 100%; border: solid 1px #d5d5d5;border-radius:3px;font-family: STFangsong;font-size: 14px;color: #0F88EB;">
					下载厨神 App
				</button>
			</div>
		</div>
	</div>
	<div id="particles-js"></div>
</body>
<script src="js/particles.min.js"></script>
<script src="js/app.js"></script>
</html>