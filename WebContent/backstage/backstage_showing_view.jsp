<%@page import="com.theater.model.TheaterVO"%>
<%@page import="com.movie.model.MovieVO"%>
<%@page import="com.movie.model.MovieService"%>
<%@page import="com.theater.model.TheaterService"%>
<%@page import="java.util.LinkedList"%>
<%@page import="com.showing.model.ShowingVO"%>
<%@page import="java.util.List"%>
<%@page import="com.showing.model.ShowingService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<% List<ShowingVO> list = (List<ShowingVO>) request.getAttribute("showingVO"); %>

<!-- ����������v���P�q�v�W�� -->
<%!int movieId;%>
<%!int theaterId;%>

<%
	for (ShowingVO showingVO : list) {
		movieId = showingVO.getMovieId();
		theaterId = showingVO.getTheaterId();
	}
%>

<%
	ShowingService showingSvc = new ShowingService();
	MovieService movieSvc = new MovieService();
	TheaterService theaterSvc = new TheaterService();

	MovieVO movieVO = movieSvc.getOneMovie(movieId);
	TheaterVO theaterVO = theaterSvc.getOneTheater(theaterId);
%>


<html>
<head>
<meta charset="BIG5">
<title>�w�W�[�ɨ��޲z</title>
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

	<div class="view_show_main">
		<p style="display: inline-block;">�w�W�[�ɨ��</p>
		<p style="display: inline-block; position: relative; left: 25px; color: #FFBE0B; font-size: 18px;">
			�� �X �d �� �� �� �p �U �� ��: �@<%=list.size()%>��
		</p>

		<table class="view">
			<thead>
				<tr>
					<th>�q�v�W��</th>
					<th>�v��</th>
					<th>���</th>
					<th>�ɶ�</th>
					<th>�ק�</th>
				</tr>
			</thead>
			
			<tbody>
				<%for (ShowingVO showingVO : list) {%>
				<tr>
					<td><%=movieVO.getMovieName()%></td>
					<td><%=theaterVO.getTheaterName()%></td>
					<td><%=showingVO.getShowingDate()%></td>
					<td><%=showingVO.getShowingTime()%></td>

					<td>
						<form method="post" action="<%=request.getContextPath()%>/showing/showing.do">
							<button>
								<input type="hidden" name="showingId" value="<%=showingVO.getShowingId()%>"> 
									<input type="hidden" name="action" value="getOne_For_Update">
								<i class="fas fa-pencil-alt"></i>
							</button>
						</form>
					</td>
				</tr>
				<%}%>
			</tbody>
		</table>
	</div>

	<script src="https://use.fontawesome.com/b0a5afcff9.js"></script>
</body>
</html>