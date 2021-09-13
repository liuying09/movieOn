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
<title>�W�[�v����</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/backstage_showing_time.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@300;400&display=swap"
	rel="stylesheet">
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

		<!-- �D�n��ܰ� -->
		<div class="main">
			<!-- ���~��� -->
			<c:if test="${not empty errorMsgs }">
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

			<h2>�s�W�q�v�|��T</h2>

			<form method="post" action="<%=request.getContextPath()%>/area/area.do">
				<table>
					<tr>
						<td>�s�W�a��:</td>
						<td><input type="TEXT" autocomplete="off"
							placeholder="�п�J�����W�Aex.XX��(��)(���)" name="city" size="10"
							value="<%=(areaVO == null) ? "" : areaVO.getCity()%>" /></td>
					</tr>

					<jsp:useBean id="areaSvc" scope="page" class="com.area.model.AreaService" />
					
					<tr>
						<td>��ܦa��:</td>
						<td><select size="1" name="area">
								<c:forEach var="areaVO" items="${areaSvc.all}">
									<option value="${areaVO.areaNum}"
										${(theaterVO.areaNum==areaVO.areaNum) ? 'selected' : '' }>${areaVO.city}
								</c:forEach>
							</select>
						</td>
					</tr>

					<tr>
						<td>�v���W��:</td>
						<td><input type="TEXT" autocomplete="off"
							placeholder="�п�J�v���W��(����)" name="theaterName" size="45"
							value="<%=(theaterVO == null) ? "" : theaterVO.getTheaterName()%>">
						</td>
					</tr>
				</table>
				<input type="hidden" name="action" value="insert"> 
				<input type="submit" value="�e�X�s�W" id="showing_time_btn">
			</form>
		</div>
	</div>

</body>
</html>