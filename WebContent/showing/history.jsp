<%@page import="com.movie.model.MovieVO"%>
<%@page import="com.movie.model.MovieService"%>
<%@page import="com.theater.model.TheaterVO"%>
<%@page import="com.theater.model.TheaterService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.showing.model.ShowingService"%>
<%@page import="com.showing.model.ShowingVO"%>
<%@page import="com.history.model.HistoryVO"%>
<%@page import="java.util.List"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% List<ShowingVO> list = (List<ShowingVO>) request.getAttribute("showingVO1"); %>

<!-- 抓取登入的使用者 -->
<% Object memberVO = session.getAttribute("memberVO"); %>

<%
	MovieService movieSvc = new MovieService();
	TheaterService theaterSvc = new TheaterService();
	ShowingService showingSvc = new ShowingService();
%>


<html>
<head>
<meta charset="BIG5">
<title>歷史紀錄</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/showing_time.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/history.css">
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

	<!-- 歷史紀錄頁面 -->

	<div class="content">
		<div class="aside">
			<div class="aside_search">
				<a class="aside_btn aside_show"
					href="<%=request.getContextPath()%>/showing/showing_search.jsp">場次查詢</a>
				<form method="post" action="<%=request.getContextPath()%>/history/history.do">
					<input type="hidden" name="action" value="getHistory"> 
					<a class="aside_btn aside_hist" href="<%=request.getContextPath()%>/history/history.do">歷史紀錄</a>
				</form>
			</div>
		</div>

		<div class="main">
			<div class="history_main">
				<div class="history_show">
					<p>歷史搜尋紀錄</p>
					<div class="none_his">尚無紀錄</div>
					<table class="time">
						<thead>
							<tr>
								<th>電影名稱</th>
								<th>影城</th>
								<th>時刻表日期</th>
								<th>查看</th>
								<th>刪除</th>
							</tr>
						</thead>
						<tbody>
							<%for (ShowingVO showingVO : list) {%>
							<tr>
								<td><%=movieSvc.getOneMovie(showingSvc.getOneShowing(showingVO.getShowingId()).getMovieId()).getMovieName()%></td>
								<td><%=theaterSvc.getOneTheater(showingSvc.getOneShowing(showingVO.getShowingId()).getTheaterId()).getTheaterName()%></td>
								<td><%=showingSvc.getOneShowing(showingVO.getShowingId()).getShowingDate()%></td>
								<td>
									<form class="history_search_btn" method="post" action="<%=request.getContextPath()%>/history/historyview.do">
										<button>
											<input type="hidden" name="movieId"
												value="<%=showingSvc.getOneShowing(showingVO.getShowingId()).getMovieId()%>">
											<input type="hidden" name="theaterId"
												value="<%=showingSvc.getOneShowing(showingVO.getShowingId()).getTheaterId()%>">
											<input type="hidden" name="showingDate"
												value="<%=showingSvc.getOneShowing(showingVO.getShowingId()).getShowingDate()%>">
											<input type="hidden" name="action" value="getOne_For_View">
											<i class="fas fa-search"></i>
										</button>
									</form>
								</td>
								<td>
									<form class="history_search_btn" method="post" action="<%=request.getContextPath()%>/history/historyview.do">
										<button>
											<input type="hidden" name="movieId"
												value="<%=showingSvc.getOneShowing(showingVO.getShowingId()).getMovieId()%>">
											<input type="hidden" name="theaterId"
												value="<%=showingSvc.getOneShowing(showingVO.getShowingId()).getTheaterId()%>">
											<input type="hidden" name="showingDate"
												value="<%=showingSvc.getOneShowing(showingVO.getShowingId()).getShowingDate()%>">
											<input type="hidden" name="action" value="getOne_For_Delete">
											<i class="fas fa-trash-alt"></i>
										</button>
									</form>
								</td>
							</tr>
							<%}%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="/footer.file"%>
	
</body>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://use.fontawesome.com/b0a5afcff9.js"></script>

<%
	Object message = request.getAttribute("message");
	if (message != null) {
%>

<script>
	$(".none_his").css("display", "block");
</script>

<%}%>

</html>