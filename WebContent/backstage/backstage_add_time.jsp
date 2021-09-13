<%@page import="java.sql.Date"%>
<%@page import="com.movie.model.MovieVO"%>
<%@page import="com.showing.model.ShowingVO"%>
<%@page import="com.theater.model.TheaterVO"%>
<%@page import="com.area.model.AreaVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% ShowingVO showingVO = (ShowingVO) request.getAttribute("showingVO"); %>
<%
	if (showingVO != null) {
		int theaterId = showingVO.getTheaterId();
		int movieId = showingVO.getMovieId();
		request.setAttribute("theaterId", theaterId);
		request.setAttribute("movieId", movieId);
	}
%>

<!-- ����ɶ��B�z -->
<%
	Date showingDate = null;
	java.sql.Time showingTime = null;
	try {
		showingDate = showingVO.getShowingDate();
		showingTime = showingVO.getShowingTime();
	} catch (Exception e) {
		showingDate = new java.sql.Date(System.currentTimeMillis());
		showingTime = new java.sql.Time(1000 * 60 * 60 * 24);
	}
%>



<html>
<head>
<meta charset="BIG5">
<title>�W�[�ɨ��</title>
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
	<!-- header����include -->
	<%@ include file="header.file"%>

	<div class="content">
		<!-- ������ -->
		<div class="aside">
			<div class="aside_search">
				<a class="aside_btn"
					href="<%=request.getContextPath()%>/Backstage/backstage_add_theater.jsp">�W�[�v����</a>

				<a class="aside_btn aside_addtime"
					href="<%=request.getContextPath()%>/Backstage/backstage_add_time.jsp">�s�W�q�v�ɨ��</a>

				<a class="aside_btn"
					href="<%=request.getContextPath()%>/Backstage/backstage_showing_select.jsp">�w�W�[�޲z</a>
			</div>
		</div>

		<div class="main">
			<!-- ���~��� -->
			<c:if test="${not empty errorMsgs}">
				<ul style="list-style-type: none">
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: #FFBE0B">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<!-- ���\���� -->
			<c:if test="${successMsg.length()!=0 }">
				<ul style="list-style-type: none">
					<li style="color: #FFBE0B">${successMsg}</li>
				</ul>
			</c:if>

			<h2>�s�W�q�v�ɨ��</h2>

			<form method="post" action="<%=request.getContextPath()%>/showing/showing.do">
				
				<table>
					<jsp:useBean id="theaterSvc" scope="page" class="com.theater.model.TheaterService" />
					<tr>
						<td>�v���W��:</td>
						<td><select name="theaterId">
								<c:forEach var="theaterVO" items="${theaterSvc.all }">
									<option value="${theaterVO.theaterId}"
										${(theaterId==theaterVO.theaterId) ? 'selected' : '' }>${theaterVO.theaterName}
								</c:forEach>
							</select>
						</td>
					</tr>

					<jsp:useBean id="movieSvc" scope="page" class="com.movie.model.MovieService" />
					<tr>
						<td>�q�v�W��:</td>
						<td><select name="movieId">
								<c:forEach var="movieVO" items="${movieSvc.all}">
									<option value="${movieVO.movieId}"
										${(movieId==movieVO.movieId) ? 'selected' : '' }>${movieVO.movieName}
								</c:forEach>
							</select>
						</td>
					</tr>

					<tr>
						<td>���:</td>
						<td><input name="showingDate" type="text" class="datepicker"
							id="datepicker" value="<%=showingDate%>" autocomplete="off"
							placeholder="����"></td>
					</tr>

					<tr>
						<td>�����ɶ�:</td>
						<td><input name="showingTime" type="text" class="timepicker"
							value="<%=showingTime%>" autocomplete="off" placeholder="����"></td>
					</tr>

					<tr>
						<td>�s�W�ɶ�:</td>
						<td><input name="showingTime2" type="text" class="timepicker"
							autocomplete="off" placeholder="���"></td>
					</tr>

					<tr>
						<td>�s�W�ɶ�:</td>
						<td><input name="showingTime3" type="text" class="timepicker"
							autocomplete="off" placeholder="���"></td>
					</tr>

					<tr>
						<td>�s�W�ɶ�:</td>
						<td><input name="showingTime4" type="text" class="timepicker"
							autocomplete="off" placeholder="���"></td>
					</tr>
				</table>

				<input type="hidden" name="action" value="insert"> 
				<input type="submit" value="�s�W���" id="showing_time_btn">
			</form>
		</div>
	</div>
</body>

<!-- ����P�ɶ��M�� -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/datepicker.js"></script>

</html>