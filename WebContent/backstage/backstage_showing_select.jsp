<%@page import="com.showing.model.ShowingVO"%>
<%@page import="java.util.List"%>
<%@page import="com.showing.model.ShowingService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />
<jsp:useBean id="theaterSvc" scope="page" class="com.theater.model.TheaterService" />


<html>
<head>
<meta charset="BIG5">
<title>已上架時刻表管理</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage_showing_time.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300;400&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
	integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p"
	crossorigin="anonymous" />
</head>
<body>

	<!-- header部分include -->
	<%@ include file="header.file"%>

	<div class="content">
		<!-- 側邊欄 -->
		<div class="aside">
			<div class="aside_search">
				<a class="aside_btn"
					href="<%=request.getContextPath()%>/Backstage/backstage_add_theater.jsp">上架影城表</a>

				<a class="aside_btn aside_addtime"
					href="<%=request.getContextPath()%>/Backstage/backstage_add_time.jsp">新增電影時刻表</a>

				<a class="aside_btn"
					href="<%=request.getContextPath()%>/Backstage/backstage_showing_select.jsp">已上架管理</a>
			</div>
		</div>

		<div class="main">
			<div class="show_view_main">
			
				<!-- 錯誤顯示 -->
				<div class="view_show">
					<c:if test="${not empty errorMsgs}">
						<ul style="list-style-type: none; position: relative; top: -50px;">
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: #FFBE0B">${message}</li>
							</c:forEach>
						</ul>
					</c:if>

					<div class="select_search">
						<form method="post" action="<%=request.getContextPath()%>/showing/showing.do">

							選擇影城 <select name="theaterId">
								<c:forEach var="theaterVO" items="${theaterSvc.all}">
									<option value="${theaterVO.theaterId }">${theaterVO.theaterName}
								</c:forEach>
							</select> 
							
							選擇電影 <select name="movieId">
								<c:forEach var="movieVO" items="${movieSvc.all }">
									<option value="${movieVO.movieId }">${movieVO.movieName}
								</c:forEach>
							</select>

							<button class="search_btn">
								<input type="hidden" name="action" value="getBy_Movie_Theater">
								搜尋
							</button>
						</form>
						
						<form method="post" action="<%=request.getContextPath()%>/showing/showing.do">
							<button class="search_btn"
								style="background-color: #FFBE0B; width: 80px; border-color: #FFBE0B;">
								<input type="hidden" name="action" value="delete"> 一鍵刪除
							</button>
						</form>
					</div>

					<% if (request.getAttribute("showingVO") != null) {%>
					<jsp:include page="backstage_showing_view.jsp" />
					<%}%>
				</div>
			</div>
		</div>
	</div>

	<script src="https://use.fontawesome.com/b0a5afcff9.js"></script>
</body>
</html>