<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="default.jsp" %>
<title>选择导入信息</title>
</head>
<body style="background-color: #DFDFDF;">
    <div style="background-color: #f8f8f8;padding:10px 0px;">
       <div class="container text-center" style="font-size:20px;"><strong>选择导入信息</strong></div>
    </div>
    <div style="padding:100px 0px;">
    <div class="text-center" style="font-size:16px;"><a href="${pageContext.request.contextPath}/batchimport.jsp">导入学生信息</a></div>
    <div class="text-center" style="font-size:16px;margin-top: 20px;"><a href="${pageContext.request.contextPath}/deviceimport.jsp">导入设备信息</a></div>
    </div>
</body>
</html>