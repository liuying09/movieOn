<%@page import="com.movie.model.MovieVO"%>
<%@page import="com.showing.model.ShowingVO"%>
<%@page import="com.theater.model.TheaterVO"%>
<%@page import="com.area.model.AreaVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% ShowingVO showingVO = (ShowingVO) request.getAttribute("showingVO"); %>

<html>
<head>
<meta charset="BIG5">
<title>修改時刻表</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage_showing_time.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300;400&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/datetimepicker/jquery-ui.css">
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
			<!-- 錯誤顯示 -->
			<c:if test="${not empty errorMsgs}">
				<ul style="list-style-type: none">
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: #FFBE0B">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<c:if test="${successMsg.length()!=0 }">
				<ul style="list-style-type: none">
					<li style="color: #FFBE0B">${successMsg}</li>
				</ul>
			</c:if>

			<h2>修改電影時刻表</h2>

			<form method="post" action="<%=request.getContextPath()%>/showing/showing.do">
				<table>
					<tr>
						<td>時刻表編號:</td>
						<td>${showingVO.showingId }</td>
					</tr>
					
					<jsp:useBean id="theaterSvc" scope="page" class="com.theater.model.TheaterService" />
					
					<tr>
						<td>影城名稱:</td>
						<td><select name="theaterId">
								<c:forEach var="theaterVO" items="${theaterSvc.all }">
									<option value="${theaterVO.theaterId }"
										${(showingVO.theaterId==theaterVO.theaterId)? 'selected' : '' }>${theaterVO.theaterName}
								</c:forEach>
							</select>
						</td>
					</tr>

					<jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />
					<tr>
						<td>電影名稱:</td>
						<td><select name="movieId">
								<c:forEach var="movieVO" items="${movieSvc.all}">
									<option value="${movieVO.movieId }"
										${(showingVO.movieId==movieVO.movieId)? 'selected' : '' }>${movieVO.movieName}
								</c:forEach>
							</select>
						</td>
					</tr>

					<tr>
						<td>日期:</td>
						<td><input name="showingDate" type="text" class="datepicker"
							id="datepicker" value="<%=showingVO.getShowingDate()%>"
							autocomplete="off"></td>
					</tr>

					<tr>
						<td>場次時間:</td>
						<td><input name="showingTime" type="text" class="timepicker"
							value="<%=showingVO.getShowingTime()%>" autocomplete="off"></td>
					</tr>
				</table>

				<input type="hidden" name="action" value="update"> 
				<input type="hidden" name="showingId" value="<%=showingVO.getShowingId()%>"> 
				<input type="submit" value="修改資料" id="showing_time_btn">
			</form>
		</div>
	</div>
</body>

<!-- 日期與時間套件 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/datepicker.js"></script>

</html>