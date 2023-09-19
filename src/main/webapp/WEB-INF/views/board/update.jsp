<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<div class="row">

						<form action="./update" method="post">
						
							<input hidden="hidden" name="boardNo" value="${vo.boardNo}">
							<div class="mb-3">
								<label for="boardTitle">제목</label> 
								<input type="text" class="form-control" name="boardTitle" id="boardTitle" value="${vo.boardTitle}">
							</div>
							
							<div class="mb-3">
								<label for="boardWriter">작성자</label> 
								<input type="text" class="form-control" readonly="readonly" name="boardWriter" id="boardWriter" value="${vo.boardWriter}">
							</div>
							
							<div class="mb-3">
								<label for="boardContents">내용</label> 
								<textarea rows="" cols="" class="form-control" id="boardContents" name="boardContents" >${vo.boardContents}</textarea>
							</div>
							
							
							<button type="submit" class="btn btn-danger">수정하기</button>
						</form>

					</div>


				</div>

			</div>

			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
		</div>

	</div>




	<c:import url="/WEB-INF/views/layout/foot.jsp"></c:import>
</body>
</html>