<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>ToDos Created by ${todosCreator.firstName}</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">

	<style>
	    body {
	      background-image: url("/images/fall.jpg");
	    }
  	</style>
  	<link rel="stylesheet" type="text/css" href="/css/dashboardstyle.css">
</head>
<body>
	<div class="container">
		
		<nav class="navbar navbar-light bg-light" >
			<h1 class="nav">Welcome <c:out value="${user.firstName}"/></h1>
			<ul class="nav justify-content-end">
			  	<li class="nav-item">
			    	<a class="nav-link active" href="/todos">Home</a>
			  	</li>
			  	<li class="nav-item">
			    	<a class="nav-link" href="/todos/new">New ToDo</a>
			  	</li>
			  	<li class="nav-item">
			    	<a class="nav-link" href="/users/todos">My ToDos</a>
			  	</li>
			  	<li class="nav-item">
			    	<a class="nav-link" href="/logout">LogOut</a>
			  	</li>
			</ul>
		</nav>
		
		<br>
		
		<h2>ToDos Created By:&nbsp;&nbsp;&nbsp;<span><c:out value="${todosCreator.firstName}"/></span></h2>
		
		<div  class="scrollTable">
			<table>
			    <thead>
			        <tr>
			            <th class="dateColumn">DATE</th>
			            <th>FOR</th>
			            <th>WHAT NEEDS TO BE DONE</th>
			            <th class="uColumn">URGENCY</th>
			            <th class="dateColumn">DO BY</th>
			        </tr>
			    </thead>
			    <tbody>
		        	<c:forEach items="${todos}" var="todo">
						<c:choose>
							<c:when  test="${ todo.userCreator.id.equals(todosCreator.id) }">
								<tr>
						            <td class="dateColumn">
						            	<fmt:formatDate pattern="MM-dd-YYYY" value="${todo.createdAt}"/>
						            </td>
						            <td class="side"><a href="/users/${todo.userAssignee.id}/assignedtodos"><c:out value="${todo.userAssignee.firstName}"/></a></td>
						            <td class="side"><a href="/todos/${todo.id}"><c:out value="${todo.description}"/></a></td>
						            <td class="uColumn"><c:out value="${todo.priority}"/></td>
						            <td class="dateColumn">
						            	<c:out value="${todo.byDate}"/>
						            </td>
						        </tr>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
			        </c:forEach>
			    </tbody>
			</table>
		</div>
		
	</div>		
</body>
</html>