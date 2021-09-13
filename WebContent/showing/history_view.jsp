<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.theater.model.TheaterVO"%>
<%@page import="com.movie.model.MovieVO"%>
<%@page import="com.theater.model.TheaterService"%>
<%@page import="com.movie.model.MovieService"%>
<%@page import="com.showing.model.ShowingService"%>
<%@page import="com.showing.model.ShowingVO"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<% List<ShowingVO> list = (List<ShowingVO>) request.getAttribute("showingVO"); %>

<!-- 抓取登入的使用者 -->
<% Object memberVO = session.getAttribute("memberVO"); %>

<!-- 抓出對應的影城、電影、日期 -->
<%!int theaterId;%>
<%!int movieId;%>
<%!Date showingDate;%>
<%
	for (ShowingVO showingVO : list) {
		theaterId = showingVO.getTheaterId();
		movieId = showingVO.getMovieId();
		showingDate = showingVO.getShowingDate();
	}
%>

<%
	TheaterService theaterSvc = new TheaterService();
	TheaterVO theaterVO = theaterSvc.getOneTheater(theaterId);

	MovieService movieSvc = new MovieService();
	MovieVO movieVO = movieSvc.getOneMovie(movieId);
%>


<html>
<head>
<meta charset="BIG5">
<title>歷史紀錄</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/showing_time.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/showing.css">
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

	<%@include file="/header.file"%>

	<div class="content">
		<div class="aside">
			<div class="aside_search">
				<a class="aside_btn aside_hist"
					href="<%=request.getContextPath()%>/showing/showing_search.jsp">場次查詢</a>
				<form method="post" action="<%=request.getContextPath()%>/history/history.do">
					<input type="hidden" name="action" value="getHistory"> 
					<a class="aside_btn aside_show" href="<%=request.getContextPath()%>/history/history.do">歷史紀錄</a>
				</form>
			</div>
		</div>

		<div class="main">
			<div class="show_time history_view">
				<div class="show_time_poster">
					<div class="poster_title">
						<span><%=movieVO.getMovieName()%></span> <span>/</span> 
						<span class="poster_theater"><%=theaterVO.getTheaterName()%></span>
					</div>
					<img class="poster"
						src="<%=request.getContextPath()%>/DBGifReaderMovie?movieId=<%=movieVO.getMovieId()%>">
					<button class="intro_btn"
						onclick="location.href='<%=request.getContextPath()%>/Links_Controller?movieId=<%=movieVO.getMovieId()%>&action=getOneMovie_From_Home'">
						電影介紹</button>
				</div>

				<div class="show_time_right">
					<div class="timesave">
						<button class="timesave_btn">
							<a href="<%=request.getContextPath()%>/history/history.do">返回紀錄</a>
						</button>
					</div>

					<div class="time_title">
						<p><%=showingDate%></p>
						<table class="time">
							<tbody>
								<%for (ShowingVO showingVO : list) {%>
								<tr>
									<td><%=showingVO.getShowingTime()%></td>
								</tr>
								<%}%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/footer.file"%>

</body>
</html>