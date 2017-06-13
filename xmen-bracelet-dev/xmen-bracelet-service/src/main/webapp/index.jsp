<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="default.jsp" %>
<title>登陆</title>
</head>
<body class="shadow-bg">
    <div class="container text-center" style="padding-top:10%;padding-left:23%;padding-right:23%;">
         <div class="login">
            <div class="login-top"><strong>手环后台管理</strong></div>
            <form action="${pageContext.request.contextPath}/chooseImportInfo.jsp" style="margin-top:30px;" method="post">
               <div class="padding15">
	                <div class="form-group">
					    <input type="text" class="form-control" name="userName" id="userName" placeholder="请输入用户名">
					</div>
					<div class="form-group" style="margin-top:20px;">
					    <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
					</div>
					<div class="text-left" id="error" style="font-size:10px;color:red;display:none;"></div>
				</div>
				<button class="btn btn-primary" onclick="return checkParam()" style="width:100px;margin-top: 30px;">登陆</button>
            </form>
         </div>
    </div>
</body>
<script type="text/javascript">
     function checkParam(){
    	 var userName = $("#userName").val();
    	 var password = $("#password").val();
    	 if((userName != "admin") || (password != "123456")){
    			$("#error").text("*账号或密码错误");
    			$("#error").show();
    			$("#userName").val("");
    			$("#password").val("");
    			$(".padding15").addClass('has-error');
    			setTimeout(function(){
	    				$('#error').text("");
	    				$('#error').hide();
	    				$(".padding15").removeClass('has-error');
    				},2000);
    		 return false;
    	 }
     }
</script>
</html>