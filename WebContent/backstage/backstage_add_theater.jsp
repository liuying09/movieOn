<%@page import="com.theater.model.TheaterVO"%>
<%@page import="com.area.model.AreaVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	AreaVO areaVO = (AreaVO) request.getAttribute("areaVO");
	TheaterVO theaterVO = (TheaterVO) request.getAttribute("theaterVO");
%>

<html>
<head>
<meta charset="BIG5">
<title>上架影城表</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage_showing_time.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300;400&display=swap"
	rel="stylesheet">
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

		<!-- 主要顯示區 -->
		<div class="main">
			<!-- 錯誤顯示 -->
			<c:if test="${not empty errorMsgs }">
				<ul style="list-style-type: none">
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: #FFBE0B">${message}</li>
					</c:forEach>
				</ul>
			</c:if>

			<!-- 成功提示 -->
			<c:if test="${successMsg.length()!=0 }">
				<ul style="list-style-type: none">
					<li style="color: #FFBE0B">${successMsg}</li>
				</ul>
			</c:if>

			<h2>新增電影院資訊</h2>

			<form method="post" action="<%=request.getContextPath()%>/area/area.do">
				<table>
					<tr>
						<td>新增地區:</td>
						<td><input type="TEXT" autocomplete="off"
							placeholder="請輸入縣市名，ex.XX市(縣)(選填)" name="city" size="10"
							value="<%=(areaVO == null) ? "" : areaVO.getCity()%>" /></td>
					</tr>

					<jsp:useBean id="areaSvc" scope="page" class="com.area.model.AreaService" />
					
					<tr>
						<td>選擇地區:</td>
						<td><select size="1" name="area">
								<c:forEach var="areaVO" items="${areaSvc.all}">
									<option value="${areaVO.areaNum}"
										${(theaterVO.areaNum==areaVO.areaNum) ? 'selected' : '' }>${areaVO.city}
								</c:forEach>
							</select>
						</td>
					</tr>

					<tr>
						<td>影城名稱:</td>
						<td><input type="TEXT" autocomplete="off"
							placeholder="請輸入影城名稱(必填)" name="theaterName" size="45"
							value="<%=(theaterVO == null) ? "" : theaterVO.getTheaterName()%>">
						</td>
					</tr>
				</table>
				<input type="hidden" name="action" value="insert"> 
				<input type="submit" value="送出新增" id="showing_time_btn">
			</form>
		</div>
	</div>

</body>
</html>