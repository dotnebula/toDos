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
	<title>User's Profile</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/userprofilestyle.css">
	<style>
	    body {
	      background-image: url("/images/fall.jpg");
	    }
  	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/userapp.js"></script>
	
</head>
<body >
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
	    	<p><form:errors path="userForm.*"/></p>
	    </div>
		
		<div class="row">
			<div class="col-1"></div>
			<div class="col">
				<h3>Your Name: <span><c:out value="${user.firstName}"/></span> <span><c:out value="${user.lastName}"/></span></h3>
				<hr>
	            <h3>Your eMail: <span><c:out value="${user.email}"/></span></h3>
	            <hr>
            	<div class="row">
					<div class="col-2"></div>
					<div  id="center" class="col">
						<h3>Update Your Profile</h3>
						<br>
						<form:form class="forma" action="/users/profile/${user.id}/update" method="POST" modelAttribute="userForm">
		 				   	<p>
						        <form:label path="firstName">First Name</form:label>
						        <form:input path="firstName" value="${ user.firstName }"/>
						    </p>
						    <p>
						        <form:label path="lastName">Last Name</form:label>
						        <form:input path="lastName" value="${ user.lastName }"/>
						    </p>
						    <p>
						        <form:label path="email">eMail</form:label>
						        <form:input path="email" value="${ user.email }"/>
						    </p>
						    <p>
					            <form:label path="password">Password:</form:label>
					            <form:password path="password"/>
					        </p>
					        <p>
					            <form:label path="passwordConfirmation">Password:</form:label>
					            <form:password path="passwordConfirmation"/>
					        </p>
						    <input class="sub" type="submit" value="Submit"/>
						</form:form>
						<div>
							<form  action="/users/${user.id}/delete" method="post">
			    				<input type="hidden" name="_method" value="delete">
								<button id="toDelete" type="submit">Delete Your Profile</button>
							</form>
						</div>
					</div>
					<div class="col-2"></div>
				</div>
			</div>
			<div class="col-1"></div>	
		</div>	
		
	</div>		
</body>
</html>