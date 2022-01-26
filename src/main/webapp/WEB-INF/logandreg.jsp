<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/logandregstyle.css">
    <style>
	    body {
	      background-image: url("../images/fall.jpg");
	    }
  	</style>
</head>
<body>
	<div class="container">
	
		<nav><h1>Welcome to ToDos!</h1></nav>
	    
	    <div class="row">
			<div class="col-1"></div>
		    <div class="col">
		    	<h3>Register</h3>
		    	<p><form:errors path="user.*"/></p>
			    <form:form method="POST" action="/register" modelAttribute="user">
			    	<div class="form-group">
			            <form:label path="firstName">First Name:</form:label>
			            <form:input type="text" path="firstName"/>
			        </div>
			        <div class="form-group">
			            <form:label path="lastName">Last Name:</form:label>
			            <form:input type="text" path="lastName"/>
			        </div>
			        
			        <div class="form-group">
			            <form:label path="email">Email:</form:label>
			            <form:input type="email" path="email"/>
			        </div>
			        <div class="form-group">
			            <form:label path="password">Password:</form:label>
			            <form:password path="password"/>
			        </div>
			        <div class="form-group">
			            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
			            <form:password path="passwordConfirmation"/>
			        </div>
			        <input type="submit" value="Register!"/>
			    </form:form>
		    </div>

 		    <div class="col">
		    	<h3>LogIn</h3>
		    	<p><c:out value="${error}" /></p>
			    <form method="post" action="/login">
			        <div class="form-group">
			            <label type="email" for="email">Email</label>
			            <input type="text" id="email" name="email"/>
			        </div>
			        <div class="form-group">
			            <label for="password">Password</label>
			            <input type="password" id="password" name="password"/>
			        </div>
			        <input type="submit" value="Login!"/>
			    </form>
		    </div>
		    <div class="col-1"></div>

	    </div>
    </div>
</body>
</html>