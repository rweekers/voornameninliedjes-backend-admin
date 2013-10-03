<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Songs</h1>

<c:url var="addUrl" value="/quooc/main/songs/add" />
<table style="border: 1px solid; width: 500px; text-align:center">
	<thead style="background:#fcf">
		<tr>
			<th>Artist</th>
			<th>Title</th>
			<th>Firstname</th>
			<th colspan="3"></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${songs}" var="song">
			<c:url var="editUrl" value="/quooc/main/songs/edit?id=${song.id}" />
			<c:url var="deleteUrl" value="/quooc/main/songs/delete?id=${song.id}" />
		<tr>
			<td><c:out value="${song.artist}" /></td>
			<td><c:out value="${song.title}" /></td>
			<td><c:out value="${song.firstName}" /></td>
			<td><a href="${editUrl}">Edit</a></td>
			<td><a href="${deleteUrl}">Delete</a></td>
			<td><a href="${addUrl}">Add</a></td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<c:if test="${empty songs}">
	There are currently no songs in the list. <a href="${addUrl}">Add</a> a song.
</c:if>

</body>
</html>