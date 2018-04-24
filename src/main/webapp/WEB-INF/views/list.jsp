<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>
</head>
<body>
	<form action="/insert" method="post">
	<table border=1 width=500>
		<tr>
			<td>이름</td><td><input type="text" name="name"></td>
			<td>비밀번호</td><td><input type="password" name="password"></td>
		</tr>
		<tr>
			<td colspan=4><textarea name="content" cols=60 rows=5></textarea></td>
		</tr>
		<tr>
			<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
		</tr>
	</table>
	</form>
	<br/>

	<table width=510 border=1>
		<c:forEach items="${list}" var="vo">
		<tr>
			<td>[${vo.no}]</td>
			<td>${vo.name}</td>
			<td>${vo.date}</td>
			<td><a href="/deleteform/${vo.no}">삭제</a></td>
		</tr>
		<tr>
			<td colspan=4>${vo.content}</td>
		</tr>
		</c:forEach>
	</table>
        <br/>
</body>
</html>