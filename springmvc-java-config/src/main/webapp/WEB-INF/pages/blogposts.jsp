<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Blog Posts</title>

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/css/starter-template.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>


</head>
<body>

 <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="${pageContext.request.contextPath}"><b>Udemy</b></a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
              <li><a href="${pageContext.request.contextPath}/promo.html">Promo</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
    
    
    
   <div class="container">
    
      <h1>Blog Posts</h1>
    
      <c:if test="${empty blogposts}">
      		 No Blog post is found<br/>      	
      </c:if>	
     	
	  <a href="${pageContext.request.contextPath}">Back</a>

	  <br/>
	
	  <a href="${pageContext.request.contextPath}/newblogpost.html">Enter a new  blog Post</a>

	  <br/>
	  
	  <form action="${pageContext.request.contextPath}/searchByTitle" method="post">
	  			<table>
	  				<tr><td>Blog title:</td><td><input name="title" type="text" /></td></tr>
	  				<tr><td colspan="2"><input type="submit" value="Search"></td></tr>  	  			
	  			</table>
	  </form>
	  
	  <br/>
	  
	  <a href="${pageContext.request.contextPath}/draftblogposts">Find Draft Blog Posts</a>

	  <br/>
	  
	  <a href="${pageContext.request.contextPath}/getBlogPostById/2">getBlogPostById/2 @PathVariable</a>

	  <br/>
	  
	  <c:if test="${not empty blogposts}">      		      		
      		 <table style="width: 650px" class="table table-striped">
      		 		<c:forEach var="blogpost" items="${blogposts}">
      		 				<tr><td>Blog post id</td><td><c:out value="${blogpost.id}"/></td></tr> 
      		 				<tr><td>Title</td><td><c:out value="${blogpost.title}"/></td></tr> 
      		 				<tr><td>Content</td><td><div style="white-space: pre"><c:out value="${blogpost.content}"/></div></td></tr> 
      		 				<tr><td>Draft</td><td><c:out value="${blogpost.draft}"/></td></tr> 
      		 				<tr><td colspan="2"></td></tr>
      		 				<tr><td colspan="2"></td></tr>
      		 		</c:forEach>		
      		</table>        		
      </c:if>	

    </div> <!-- /container -->
   
     
   <footer class="footer">
      	<div class="container">
        	<p class="text-muted"><b>Tuna Tore 2016 Please feel free to ask me questions by sending email to tunatore@gmail.com</b></p>
		    <a href="<%=request.getContextPath()%>?language=en">EN</a>
 			<a href="<%=request.getContextPath()%>?language=de">DE</a> 			
 			<spring:message code="language"/> 			
 			<font color="green"><b><spring:message code="welcome.text"/></b></font>
        </div>
    </footer>
    
    
</body>
</html>