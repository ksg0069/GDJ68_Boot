<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
				
					<form:form modelAttribute="memberInfoVO" method="post" enctype="multipart/form-data">
					
					
					  <div class="form-group">
						<form:label path="name">name</form:label>
						<form:input id="name" path="name" cssClass="form-control"/>  
						<form:errors path="name"></form:errors>
					  </div>
					  
					   <div class="form-group">
						<form:label path="email">email</form:label>
						<form:input id="email" path="email" cssClass="form-control"/>
						<form:errors path="email"></form:errors>
					  </div>
					  
					   <div class="form-group">
						<form:label path="birth">birth</form:label>
						<form:input id="birth" path="birth" cssClass="form-control"/>  
						<form:errors path="birth"></form:errors>
					  </div>
					  
					    <div class="form-group">
					    <label for="photo">Photo</label>
					    <input type="file" class="form-control" name="photo" id="photo" aria-describedby="photoHelp">
					    <small id="photoHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
					  </div>
					  
					  <button type="submit" class="btn btn-primary">Submit</button>
					  
					</form:form>
					
					
					
				</div>

			</div>
			
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
		</div>

	</div>




	<c:import url="/WEB-INF/views/layout/foot.jsp"></c:import>
</body>
</html>