<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h1>staff정보 등록 화면</h1>
<form action="<c:url value='/staff/InsertStaffAction'/>" method="post">
	<table border="1" >
		<tr>
			<th>이름</th>
			<th>주민번호</th>
			<th>종교</th>
			<th>학력</th>
			<th>기술</th>
			<th>졸업일</th>
		</tr>
		<tr>
			<td>
				<c:forEach var="staff" items="${staffList}">
				
				</c:forEach>
			</td>
		</tr>
	</table>
</form>
</body>
</html>