<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Create ToDo</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/css/createtodostyle.css">
	<style>
	    body {
	      background-image: url("../images/fall.jpg");
	    }
  	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/app.js"></script>
	
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
		
		<div class="row">
			<div class="col-1"></div>
			<div class="col">
				<br>
	    		<p><form:errors path="todo.*"/></p>
	    	</div>
	    </div>
		
		<div class="row">
			<div class="col-1"></div>
			<div class="col">
				<h3>New ToDo:</h3>
				<form:form action="/todos/create" method="post" modelAttribute="todo">
 				   	<p>
				        <form:label path="description"></form:label>
				        <%-- <form:errors path="description"/> --%>
				        <form:textarea path="description" placeholder="Describe Assignment"/>
				    </p>
				    <p>
				    	<form:label path="userAssignee">This toDos is for: </form:label>
				        <%-- <form:errors path="userAssignee"/> --%>
				        <form:select path="userAssignee">
				        	<c:forEach items="${ users }" var="user">
								<option value="${ user.id }">${ user.firstName } ${ user.lastName }</option>
							</c:forEach>
				        </form:select>
				    </p>
				    <p>
				    	<form:label path="priority">Importance of this ToDo: </form:label>
				        <form:errors path="priority"/>
				        <form:select path="priority">
				        	<option value="Urgent">Urgent</option>
				        	<option value="Very High">Very High</option>
				        	<option value="High">High</option>
				        	<option value="Medium">Medium</option>
				        	<option value="Take Your Time">Take Your Time</option>
				        </form:select>
					</p>
					<p>
				        <form:label path="byDate">Complete By Date:</form:label>
				        <%-- <form:errors path="byDate"/> --%>
				        <form:input type="text" id="datepicker" path="byDate"/>
				   </p>
					<input class="sub" type="submit" value="Submit"/>
				</form:form>
			</div>
			<div class="col-1"></div>
		</div>
		
	</div>
</body>
</html>	 