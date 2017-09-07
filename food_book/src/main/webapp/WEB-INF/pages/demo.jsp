<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://pic.lvmama.com/min/index.php?f=/js/new_v/jquery-1.7.2.min.js"></script>
<%-- <script src="<%=request.getContextPath()%>/js/jquery-1.4.4.min.js"></script> --%>
<script src="<%=request.getContextPath()%>/js/dialog.js"></script>
</head>
<body>
	<input name="right_button08Submit" type="button" value="确定" class="right-button09" id="modifySubmit"/>
	<input name="right_button08Submit" type="button" value="测试缓存" class="right-button09" id="memSubmit"/>
</body>
<script type="text/javascript">
	$("#modifySubmit").click(function(){
		var result="{result:'当前工作IP=10.200.1.203'}" ;
		var ip=eval(result);
		var r = confirm(ip);
		if(r==true){
			$.ajax({
				type:"POST", 
				url:'http://super.lvmama.com/gateway/payment_gateway_configure!updateMemcached.do' + '?random=' + Math.random(), 
				//data:$("#addForm").serialize(), 
				async: false, 
				dataType: 'JSONP',
				success:function (result) {
					var message=eval(result);
					alert(message);
				}
			});
		}
	});
	$("#memSubmit").click(function(){
			$.ajax({
				type:"POST", 
				url:'http://127.0.0.1:8080/springmvc-mybatis-demo/index/memtest.do', 
				async: false, 
				//dataType: 'JSON',
				success:function (result) {
					console.log(result);
					alert(result);
				}
			});
	});
</script>
</html>