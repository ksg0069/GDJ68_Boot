<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="/WEB-INF/views/layout/headcss.jsp"></c:import>
</head>
<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">
		<!-- Sidebar -->
		<c:import url="/WEB-INF/views/layout/sidebar.jsp"></c:import>

		 <!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">
		
			<!-- Main Content -->
			<div id="content">
			
				<!-- Topbar -->
				<c:import url="/WEB-INF/views/layout/topbar.jsp"></c:import>
				
				<!-- Begin Page Content -->
				<div class="container-fluid">
				
					<!-- page 실제 내용 -->
					<sec:authentication property="principal" var="vo"></sec:authentication> 
					<div>${vo.username}</div>
					<div>${vo.password}</div>
					<div>${vo.email}</div>
					<div>${vo.birth}</div>
					
					
				</div>

			</div>
			
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
		</div>

	</div>




	<c:import url="/WEB-INF/views/layout/foot.jsp"></c:import>
</body>
</html>