<%@page import="com.movie.model.MovieVO"%>
<%@page import="com.movie.model.MovieService"%>
<%@page import="com.theater.model.TheaterService"%>
<%@page import="com.showing.model.ShowingVO"%>
<%@page import="java.sql.Date"%>
<%@page import="com.member.model.MemberVO"%>
<%@page import="com.member.model.MemberService"%>
<%@page import="com.theater.model.TheaterVO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- ����n�J���ϥΪ� -->
<% Object memberVO = session.getAttribute("memberVO"); %>

<!-- ��X�������v���B�q�v�B��� -->
<%!int theaterId;%>
<%!int movieId;%>
<%!Date showingDate;%>
<%
	if (request.getAttribute("showingVO") != null) {
		List<ShowingVO> list = (List<ShowingVO>) request.getAttribute("showingVO");
		for (ShowingVO showingVO : list) {
			theaterId = showingVO.getTheaterId();
			movieId = showingVO.getMovieId();
			showingDate = showingVO.getShowingDate();
		}
	}
%>

<%
	TheaterService theaterSvc1 = new TheaterService();
	TheaterVO theaterVO = theaterSvc1.getOneTheater(theaterId);

	MovieService movieSvc = new MovieService();
	MovieVO movieVO = movieSvc.getOneMovie(movieId);
%>

<html>
<head>

<title>�����d��</title>
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

	<!-- �ɨ���� -->

	<div class="content">
		<div class="aside">
			<div class="aside_search">
				<a class="aside_btn aside_show"
					href="<%=request.getContextPath()%>/showing/showing_search.jsp">�����d��</a>
				<form method="post"
					action="<%=request.getContextPath()%>/history/history.do">
					<input type="hidden" name="action" value="getHistory"> <a
						class="aside_btn aside_hist"
						href="<%=request.getContextPath()%>/history/history.do">���v����</a>
				</form>
			</div>
		</div>
		
		<div class="main">
			<!--���~��� -->
			<c:if test="${not empty errorMsgs}">
				<ul style="list-style-type: none; position: relative; top: 35px; font-size: 18px;">
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: #FFBE0B">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<div class="select_search">
				<jsp:useBean id="areaSvc" scope="page" class="com.area.model.AreaService" />
				<jsp:useBean id="theaterSvc" scope="page" class="com.theater.model.TheaterService" />
				<form method="post" action="<%=request.getContextPath()%>/showing/showingview.do" name="form">

					��ܦa�� <select name="select_search_area" onchange="selectArea()" onload="selectArea()" id="area_city">
						<option value="">�п��</option>
						<c:forEach var="areaVO" items="${areaSvc.all}">
							<option value="${areaVO.areaNum }">${areaVO.city }</option>
						</c:forEach>
					</select> 
					��ܼv�� <select name="theaterId" id="theater" onchange="selectTheater()">
						
						<%if (request.getAttribute("showingVO") != null) {%>
						<option><%=theaterVO.getTheaterName()%></option>
						<%}%>
					</select> 
					��ܹq�v <select name="movieId" id="movie" onchange="selectMovie()">
						
						<%if (request.getAttribute("showingVO") != null) {%>
						<option><%=movieVO.getMovieName()%></option>
						<%}%>
					</select> 
					��ܤ�� <select name="showingDate" id="showdate">
						
						<%if (request.getAttribute("showingVO") != null) {%>
						<option><%=showingDate%></option>
						<%}%>
					</select> 
					<input type="hidden" name="action" value="getShowingTime">
					<input class="search_btn" type="button" value="�e�X" onclick="check()">
				</form>
			</div>

			<div class="show_time">
				<%if (request.getAttribute("showingVO") != null) {%>
				<jsp:include page="showing_view.jsp" />
				<%}%>
			</div>

			<div class="map_box">
				<div class="show_theater">
					<p id="map_area_title"></p>
					<div class="tab tab1 -on">
						<ul id="map_theater"></ul>
					</div>
				</div>

				<div class="show_map">
					<ul class="map_title">
						<li><i class="fas fa-map-marker-alt"></i></li>
						<li>�v���a��</li>
					</ul>
					<div id="map"></div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="/footer.file"%>

</body>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://use.fontawesome.com/b0a5afcff9.js"></script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD6vGOM-O_H4ezGbv3giRKYe4Gcdtf2eF4&callback=initMap"></script>
<script src="<%=request.getContextPath()%>/js/nav.js"></script>
<script src="<%=request.getContextPath()%>/js/showtime.js"></script>

<script>

	/************************************�a�X�v����**********************************************/
	function selectArea(){
		var areaNum = $("#area_city option:selected").val();
		var str="";
		var map_theater="";
		var arr = [];
		var val = [];
		$("#theater").html("<option value=''>�п��</option>");
		$("#movie").html("<option value=''>�п��</option>");
		$("#showdate").html("<option value=''>�п��</option>");
		$("#map_theater").html("");
		$.ajax({
			url:"<%=request.getContextPath()%>/showing/showingselect.do",
			data: {areaNum},
			type: "POST",
			dataType: "json",
			success: function(response){
				
				//���X�_�ƪ�id�M���ƪ���
				arr = response.filter((item,index) => index % 2 === 0);
				val = response.filter((item,index) => index % 2 !== 0);
				
				for(i in arr){
						str = "<option value ="+ arr[i] +">"+val[i]+"</option>";
						map_theater="<li><a href='#'>" + val[i] + "</a></li>"
						$("#theater").append(str);
						$("#map_theater").append(map_theater);
				}
			}
		})
	};
	
	/************************************�a�X�q�v��**********************************************/

	function selectTheater(){
		var theaterId = $("#theater option:selected").val();
		var str="";
		var arr = [];
		var val = [];
		$("#movie").html("<option value=''>�п��</option>");
		$("#showdate").html("<option value=''>�п��</option>");
		$.ajax({
			url:"<%=request.getContextPath()%>/showing/showingselect.do",
			data: {theaterId},
			type: "POST",
			dataType: "json",
			success: function(response){
				
				//���X�_�ƪ�id�M���ƪ���
				arr = response.filter((item,index) => index % 2 === 0);
				//�R�����Ƥ���
				arr = arr.filter((item, index) => arr.indexOf(item) === index);

				val = response.filter((item,index) => index % 2 !== 0);
				val = val.filter((item, index) => val.indexOf(item) === index);
			
				for(i in arr){
						str = "<option value ="+ arr[i] +">"+val[i]+"</option>";
						$("#movie").append(str);
				}
			}
		})
	};
	
	/************************************�a�X�����**********************************************/
	
	function selectMovie(){
		var theaterId1 = $("#theater option:selected").val();
		var movieId1 = $("#movie option:selected").val();
		var str="";
		var arr = [];
		var val = [];
		$("#showdate").html("<option value=''>�п��</option>");
		
		$.ajax({
			url:"<%=request.getContextPath()%>/showing/showingselect.do",
			data: {theaterId1,movieId1},
			type: "POST",
			dataType: "json",
			success: function(response){
	
				//���X�_�ƪ�id�M���ƪ���
				arr = response.filter((item,index) => index % 2 === 0);
				arr = arr.filter((item, index) => arr.indexOf(item) === index);
				
				for(i in arr){
					str = "<option value ="+ arr[i] +">"+arr[i]+"</option>";
					$("#showdate").append(str);
				}
			}
		})
	};
		
	/************************************���Ҧ��L���**********************************************/
	
	function check(){
		with(document.form){
			if($("#area_city option:selected").val()==""){
				alert("�п�ܦa��");
			}else if($("#theater option:selected").val()==""){
				alert("�п�ܼv��")
			}else if($("#movie option:selected").val()==""){
				alert("�п�ܹq�v");
			}else if($("#showdate option:selected").val()==""){
				alert("�п�ܤ��");
			}else{
				submit();
			}
		}
	};
	
	/**************************************���ܦa�Ϫ��a�ϦW********************************************/

		$("#area_city").change(function(){
			$("#map_area_title").text("");
			if($("#area_city option:selected").val()!=""){
				$("#map_area_title").text($("#area_city option:selected").text());
			}
		});
	
</script>
</html>