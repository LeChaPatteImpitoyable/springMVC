<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="default.jsp" %>
<style type="text/css">
   .modal-backdrop{
       z-index: 0; 
   }
</style>
<title>导入学生信息</title>
</head>
<body style="background-color: #DFDFDF;">

<div style="background-color: #f8f8f8;padding:5px 0px;">
   <div class="container">
	   <!-- <form class="form-inline" action="../addController/batchimport" method="post"  enctype="multipart/form-data">
	          <div class="col-sm-6">
	           <input id="excel_file" type="file" style="margin-top:5px;" name="filename" accept="xlsx"/>
	          </div> 
	          <div class="col-sm-6 text-right">
	             <button type="submit" class="btn btn-default">导入Execl</button>
	          </div>
	   </form> -->
	 <form action="${pageContext.request.contextPath}/manage/batchimport.do" method="post"  enctype="multipart/form-data">
	   <div class="col-sm-4" style="position:relative">
	      <p style="margin-top:8px;width:80px;color:#5F7293;display:inline-block;">导入Excel</p>
	      <c:if test="${!empty emptyExcel}">
	         <span style="color:red;font-size:10px;">导入的学生信息为空,请重新选择文件</span>
	      </c:if>
	      <input id="excel_file" type="file" style="margin-top:5px;width:80px;position: absolute;top:8px;opacity:0;" name="filename" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" onchange="importExcel()"/>
	   </div>
	   <input type="submit" style="display:none;">
	  </form>  
	   <div class="col-sm-4 text-center">
	       <p style="margin-top:8px;">导入学生信息</p>
	   </div>
  </div>
</div>


<div class="container" style="margin-top:10px;">
    <table class="table table-striped table-hover" style="background-color:#fff;">
        <tr style="background-color:#5F7293;">
           <th>序号</th>
           <th>学生编号</th>
           <th>姓名</th>
           <th>性别</th>
           <th>年龄</th>
           <th>体重(kg)</th>
           <th>身高</th>
           <th>级/班级</th>
        </tr>
        
       <c:if test="${!empty studentDTOs}">
	       <c:forEach var="student" items="${studentDTOs}">
	        <tr>
	           <td>${student.serialId }</td>
	           <td>${student.studentNO }</td>
	           <td>${student.studentName }</td>
	           <td>${student.sex }</td>
	           <td>${student.age }</td>
	           <td>${student.weight }</td>
	           <td>${student.stature }</td>
	           <td>${student.className }</td>
	        </tr>
	        </c:forEach>
        </c:if>
    </table>
</div>
<div class="modal fade bs-example-modal-sm" id="about">
    <div class="modal-dialog  modal-sm">
        <div class="modal-content text-center">
          <div class="modal-body" style="padding:50px;"></div>
        </div>
    </div>
</div>
<script type="text/javascript">
if("${success}" != ""){
	$('#about').modal('show');
	$(".modal-body").text("${success}");
	$(".modal-body").css("color","#5F7293").css("font-size","14px");
	setTimeout(function(){$('#about').modal('hide')},2000);
	<%
	    session.removeAttribute("success");
	%>
}

if("${errorContent}" != ""){
	$('#about').modal('show');
	$(".modal-body").text("${errorContent}");
	$(".modal-body").css("color","red").css("font-size","14px");
	setTimeout(function(){$('#about').modal('hide')},2000);
	<%
	    session.removeAttribute("errorContent");
	%>
}

  function importExcel(){
	    var file = $("#excel_file").val();
	    if(file != ""){
	    	 var reg = /^.*\.(?:xls|xlsx)$/i;//文件名可以带空格
             if (!reg.test(file)) {//校验不通过
                 alert("请上传excel格式的文件!");
                 return false;
             }
	    }
	    $('form').submit();
	  
  }
  
  
</script>
</body>
</html>