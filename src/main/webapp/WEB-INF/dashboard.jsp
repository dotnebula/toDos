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
	<title>Home</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/dashboardstyle.css">
	<style>
	    body {
	      background-image: url("/images/fall.jpg");
	    }
  	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
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

		<br>
		<br>
		
		<div class="scrollTable">		
		<table>
		    <thead>
		        <tr>
		            <th class="dateColumn">
		            	DATE
		            	<a href="/todos/LowToHigh"> ↓ </a>
						<a href="/todos/HighToLow"> ↑ </a>
		            	</th>
		            <th>BY</th>
		            <th>FOR</th>
		            <th>WHAT NEEDS TO BE DONE</th>
		            <th class="dateColumn">
		            	DO BY
		            	<a href="/todos/dateLowToHigh"> ↓ </a>
						<a href="/todos/dateHighToLow"> ↑ </a>
		            </th>
		            <th class="uColumn">URGENCY</th>
		            <th class="center">ACTIONS</th>
		        </tr>
		    </thead>
		    <tbody>
	        	<c:forEach items="${todosTop}" var="todo">
			        <tr>
			            <td class="dateColumn">
			            	<fmt:formatDate pattern="MM-dd-YYYY" value="${todo.createdAt}"/>
			            </td>
			            <td class="side"><a class="ellipsisName" href="/users/${todo.userCreator.id}/createdtodos"><c:out value="${todo.userCreator.firstName}"/></a></td>
			            <td class="side"><a class="ellipsisName" href="/users/${todo.userAssignee.id}/assignedtodos"><c:out value="${todo.userAssignee.firstName}"/></a></td>
			            <td class="side"><a class="ellipsis" href="/todos/${todo.id}"><c:out value="${todo.description}"/></a></td>
			            <td class="dateColumn"><c:out value="${todo.byDate}"/></td>
			            <td class="uColumn"><c:out value="${todo.priority}"/></td>
			            <td class="center">
		 					<div>
								<c:if test="${ todo.userCreator.id == user.id }">
					            	<div>
					            		<a class="editlink" href="/todos/${todo.id}/edit">Edit</a>
					            		<form class="forma" action="/todos/${todo.id}/delete" method="post">
					    					<input type="hidden" name="_method" value="delete">
					    					<input id="to_delete" type="submit" value="Delete">
					    				</form>
					    			</div>
					    		</c:if>
							</div>
							<div>
				    			<a href="/todos/${todo.id}/markAsDone"><button>Mark As Done</button></a>
				    		</div>
			            </td>
			        </tr>
		        </c:forEach>
		    </tbody>
		</table>
		</div>
		
		<br>
		<br>
		
		<div class="scrollTable">
			<table>
			    <thead>
			        <tr>
			            <th class="dateColumn">
			            	DATE
			            	<a href="/todos/doneLowToHigh"> ↓ </a>
							<a href="/todos/doneHighToLow"> ↑ </a>
			            </th>
			            <th>BY</th>
			            <th>FOR</th>
			            <th>FINSHED ASSIGNMENTS</th>
			            <th class="dateColumn">
			            	DO BY
			            	<a href="/todos/dateDoneLowToHigh"> ↓ </a>
							<a href="/todos/dateDoneHighToLow"> ↑ </a>
			            </th>
			            <th class="uColumn">URGENCY</th>
			            <th class="center">ACTIONS</th>
			        </tr>
			    </thead>
			    <tbody>
		        	<c:forEach items="${todosBottom}" var="todo">
				        <tr>
				            <td class="dateColumn">
				            	<fmt:formatDate pattern="MM-dd-YYYY" value="${todo.createdAt}"/>
				            </td>
				            <td class="side"><a class="ellipsisName" href="/users/${todo.userCreator.id}/createdtodos"><c:out value="${todo.userCreator.firstName}"/></a></td>
				            <td class="side"><a class="ellipsisName" href="/users/${todo.userAssignee.id}/assignedtodos"><c:out value="${todo.userAssignee.firstName}"/></a></td>
				            <td class="side"><a class="ellipsis" href="/todos/${todo.id}"><c:out value="${todo.description}"/></a></td>
				            <td class="dateColumn"><c:out value="${todo.byDate}"/></td>
				            <td class="uColumn"><c:out value="${todo.priority}"/></td>
				            <td class="center">
			 					<div>
									<c:if test="${ todo.userCreator.id == user.id }">
						            	<div>
						            		<form class="forma" action="/todos/${todo.id}/delete" method="post">
							    				<input type="hidden" name="_method" value="delete">
							    				<input id="to_delete" type="submit" value="Delete">
						    				</form>
						    			</div>
						    		</c:if>
								</div>
								<div>
						    		<a href="/todos/${todo.id}/edit"><button>Do It Again?</button></a>
					    		</div>
				            </td>
				        </tr>
			        </c:forEach>
			    </tbody>
			</table>
		</div>
		
		<br>
		<br>
		
		<footer>
			<div class="text-center p-3 bg-light">
		    	Maria's Project 2022
		  	</div>
		</footer>
		
	</div>
</body>
</html>