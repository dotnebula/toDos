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
	<title>${user.firstName}'s ToDos</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="/css/dashboardstyle.css">
	<style>
	    body {
	      background-image: url("../images/fall.jpg");
	    }
  	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script type="text/javascript" src="/js/userapp.js"></script>
	
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
			    	<a class="nav-link" href="/users/${user.id}/profile">Profile</a>
			  	</li>
			  	<li class="nav-item">
			    	<a class="nav-link" href="/logout">LogOut</a>
			  	</li>
			</ul>
		</nav>

		<br>
		
		<h2>Open ToDos For You</h2>
		
		<div  class="scrollTable">
			<table>
			    <thead>
			        <tr>
			            <th class="dateColumn">DATE</th>
			            <th>WHAT NEED TO BE DONE</th>
			            <th class="uColumn">URGENCY</th>
			        </tr>
			    </thead>
			    <tbody>
		        	<c:forEach items="${todos}" var="todo">
		        		<c:if test="${ todo.status.equals(false) }">
							<c:choose>
								<c:when  test="${ todo.userAssignee.id.equals(userId) }">
									<tr>
							            <td class="dateColumn">
							            	<fmt:formatDate pattern="MM-dd-YYYY" value="${todo.createdAt}"/>
							            </td>
							            <td class="side">
							            	<a class="ellipsis pupup"><c:out value="${todo.description}"/></a>
											<div class="org border border-primary">
												<h3>Added by&nbsp;&nbsp;&nbsp;<a href="/users/${todo.userCreator.id}/createdtodos"><span><c:out value="${todo.userCreator.firstName}"/></span></a>&nbsp;&nbsp;&nbsp;for&nbsp;&nbsp;&nbsp;<a href="/users/${todo.userAssignee.id}/assignedtodos"><span><c:out value="${todo.userAssignee.firstName}"/></span></a></h3>
												<hr>
												<h3 id="description"><c:out value="${todo.description}"/></h3>
												<hr>
												<h3><span><c:out value="${ todo.priority }"/></span></h3>
												<hr>
												<div class="eventinfo">
								 					<div>
														<c:if test="${ todo.userCreator.id == user.id }">
											            	<div><form id="to_delete" action="/todos/${todo.id}/delete" method="post">
											    				<input type="hidden" name="_method" value="delete">
											    				<input id="to_delete" type="submit" value="Delete"></form>| <a href="/todos/${todo.id}/edit">Edit</a></div>
											    		</c:if>
													</div>
													<div>
														<c:if test="${ task.userAssignee.id == user.id }">
															<a href="/todos/${todo.id}/markAsDone"><button>Mark As Done</button></a>
														<%-- <form id="to_delete" action="/todos/${todo.id}/delete" method="post">
										    				<input type="hidden" name="_method" value="delete">
															<button type="submit">Mark As Done</button>
														</form> --%>
														</c:if>
													</div>
													<div>
														<c:if test="${ todo.status.equals(false) }">
															<a href="/todos/${todo.id}/markAsDoneUser"><button>Mark As Done</button></a>
														</c:if>
														<%-- <c:if test="${ !todo.status.equals(false) }">
															<a href="/todos/${todo.id}/unmarkAsDoneUser"><button>Do It Again?</button></a>
														</c:if> --%>
													</div>
												</div>	
											</div>
							            </td>
							            <td class="uColumn"><c:out value="${todo.priority}"/></td>
							        </tr>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:if>
		        	</c:forEach>
			    </tbody>
			</table>
		</div>
		
		<br>

		<h2>Open ToDos for Others</h2>
		
		<div  class="scrollTable">
			<table>
			    <thead>
			        <tr>
			            <th class="dateColumn">DATE</th>
			            <th>WHAT NEEDED TO BE DONE</th>
			            <th class="uColumn">URGENCY</th>
			        </tr>
			    </thead>
			    <tbody>
		        	<c:forEach items="${todos}" var="todo">
		        		<c:if test="${ todo.status.equals(false) }">
							<c:choose>
								<c:when  test="${ todo.userCreator.id.equals(user.id) && !todo.userAssignee.id.equals(user.id) }">
									<tr>
							            <td  class="dateColumn">
							            	<fmt:formatDate pattern="MM-dd-YYYY" value="${ todo.createdAt }"/>
							            </td>
							            <td class="side">
							            	<a class="ellipsis pupup"><c:out value="${todo.description}"/></a>
											<div class="org border border-primary">
												<h3>Added by&nbsp;&nbsp;&nbsp;<a href="/users/${todo.userCreator.id}/createdtodos"><span><c:out value="${todo.userCreator.firstName}"/></span></a>&nbsp;&nbsp;&nbsp;for&nbsp;&nbsp;&nbsp;<a href="/users/${todo.userAssignee.id}/assignedtodos"><span><c:out value="${todo.userAssignee.firstName}"/></span></a></span></h3>
												<hr>
												<h3 id="description"><c:out value="${todo.description}"/></h3>
												<hr>
												<h3><span><c:out value="${ todo.priority }"/></span></h3>
												<hr>
												<div class="eventinfo">
								 					<div>
														<c:if test="${ todo.userCreator.id == user.id }">
											            	<div><form id="to_delete" action="/todos/${todo.id}/delete" method="post">
											    				<input type="hidden" name="_method" value="delete">
											    				<input id="to_delete" type="submit" value="Delete"></form>| <a href="/todos/${todo.id}/edit">Edit</a></div>
											    		</c:if>
													</div>
													<div>
														<c:if test="${ task.userAssignee.id == user.id }">
															<form id="to_delete" action="/todos/${todo.id}/delete" method="post">
											    				<input type="hidden" name="_method" value="delete">
																<button type="submit">Completed</button>
															</form>
														</c:if>
													</div>
													<div>
														<c:if test="${ todo.status.equals(false) }">
															<a href="/todos/${todo.id}/markAsDoneUser"><button>Mark As Done</button></a>
														</c:if>
													</div>
												</div>	
											</div>

							            </td>
							            <td class="uColumn"><c:out value="${ todo.priority }"/></td>
							        </tr>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:if>
			        </c:forEach>
			    </tbody>
			</table>
		</div>
		
	</div>		
</body>
</html>