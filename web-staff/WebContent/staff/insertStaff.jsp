<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h1>staff���� ��� ȭ��</h1>
<form action="<c:url value='/staff/InsertStaffAction'/>" method="post">
	<table border="1">
		<tr>
			<td>�̸�</td>
			<td colspan="3">
				<input type="text" name="Name" size="15"/>
			</td>
			<td>�ֹι�ȣ</td>
			<td colspan="5">
				<input type="text" name="Sn01" size="10"/>-
				<input type="text" name="Sn02" size="10"/>
			</td>
			<td>����</td>
			<td><select name="ReligionNo">
					<option value="">::��������::</option>
					<c:forEach var="r" items="${religionList}">
					<option value="${r.getNo()}">${r.getName()}</option>
					</c:forEach>									
				</select>
			</td>
		</tr>
		<tr>
			<td>�з�</td>
			<td colspan="3">
				<c:forEach var="school" items="${schoolList}">
					<input type="radio" name="SchoolNo" value="${school.getNo()}"/>${school.getGraduate()} 
	
				</c:forEach>
			
			</td>
			<td>���</td>
			<td colspan="7">
				<c:forEach var="skill" items="${skillList}">
					<input type="checkbox" name="skillcheck" value="${skill.getNo()}"/>${skill.getName()}
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>������</td>
			<td colspan="11">
				<input type="date" name="Graduateday"/>
			</td>
		</tr>
		<tr>
			<td colspan="12" align="center" >
			<input type="submit" value="���"/> |
			<input type="reset" value="�ʱ�ȭ"/>
			</td>
		</tr>	
	</table>
</form>
</body>
</html>