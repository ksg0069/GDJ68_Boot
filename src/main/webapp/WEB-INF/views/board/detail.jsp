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
					<h1>detail page</h1>
					
					<div class="card shadow mb-4">
						<div class="card-body">
							<div class="table-responsive">
								<div id="dataTable_wrapper"
									class="dataTables_wrapper dt-bootstrap4">
									<div class="row">
										<div class="col-sm-12">
											<table class="table table-bordered" id="dataTable"
												width="100%" cellspacing="0">
												<thead>
													<tr>
														<th>No</th>
														<th>Title</th>
														<th>Writer</th>
														<th>Date</th>
														<th>Hit</th>
													</tr>
												</thead>
												<tbody>
													
														<tr>
															<td>${vo.boardNo}</td>
															<td>${vo.boardTitle}</td>
															<td>${vo.boardWriter}</td>
															<td>${vo.boardDate}</td>
															<td>${vo.boardHit}</td>
														</tr>
													
												</tbody>
												<tfoot>
														<td colspan="5">${vo.boardContents}</td>
													
												
												</tfoot>
												
											</table>
											<div>
											
												<c:forEach items="${vo.fileVOs}" var="f">
													<img alt="" src="../files/${board}/${f.fileName}">
													<a href="./fileDown?fileNo=${f.fileNo}">${f.oriName}</a>
												</c:forEach>
											</div>
											
											
											
										</div>
									</div>
									
								</div>
										<a class="btn btn-dark" href="./update?boardNo=${vo.boardNo}"> 수정 </a>
										<a class="btn btn-dark" href="./delete?boardNo=${vo.boardNo}"> 삭제 </a>
										
							</div>
							
						</div>

					</div>
					 
		
					
					
					
					
				</div>

			</div>
			
			<c:import url="/WEB-INF/views/layout/footer.jsp"></c:import>
		</div>

	</div>




	<c:import url="/WEB-INF/views/layout/foot.jsp"></c:import>
</body>
</html>