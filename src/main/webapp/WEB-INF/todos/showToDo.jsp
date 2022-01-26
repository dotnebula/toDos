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
	<title>ToDo Details</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/showtodostyle.css">
	<style>
	    body {
	      background-image: url("../images/fall.jpg");
	    }
  	</style>
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
		
		<div class="row">
			<div class="col-1"></div>
			<div class="col">
				<%-- <h3>Complete by Date: <span><fmt:formatDate pattern="MM-dd-YYYY" value="${todo.byDate}"/></span></h3> --%>
				<h3>Complete by: <span><c:out value="${todo.byDate}"/></span></h3>
				<hr>
	            <h3><c:out value="${todo.description}"/></h3>
	            <hr>
	            <h4>Added by&nbsp;&nbsp;&nbsp;<a href="/users/${todo.userCreator.id}/createdtodos"><span><c:out value="${todo.userCreator.firstName}"/></span></a>&nbsp;&nbsp;&nbsp;for&nbsp;&nbsp;&nbsp;<a href="/users/${todo.userAssignee.id}/assignedtodos"><span><c:out value="${todo.userAssignee.firstName}"/></span></a></h4>
				<hr>
				<h3>Urgency&nbsp;&nbsp;&nbsp;<span><c:out value="${todo.priority}"/></span></h3>
				<br>
				<div id="center">
 					<div>
						<c:if test="${ todo.userCreator.id == user.id }">
			            	<div><a class="editlink" href="/todos/${todo.id}/edit">Edit</a><form class="forma" action="/todos/${todo.id}/delete" method="post">
			    				<input type="hidden" name="_method" value="delete">
			    				<input id="to_delete" type="submit" value="Delete"></form></div>
			    		</c:if>
					</div>
					<div>
						<c:if test="${ todo.userAssignee.id == user.id }">
							<form id="to_delete" action="/todos/${todo.id}/delete" method="post">
			    				<input type="hidden" name="_method" value="delete">
								<!-- <button type="submit">Do it Again</button> -->
							</form>
							<c:if test="${ todo.status.equals(false) }">
								<a href="/todos/${todo.id}/statusShow"><button>Mark As Done</button></a>
							</c:if>
							<c:if test="${ !todo.status.equals(false) }">
								<a href="/todos/${todo.id}/statusShow"><button>Do It Again?</button></a>
							</c:if>
						</c:if>
					</div>
	            </div>
			</div>
			<div class="col-1"></div>	
		</div>	
		
	</div>		
</body>
</html>