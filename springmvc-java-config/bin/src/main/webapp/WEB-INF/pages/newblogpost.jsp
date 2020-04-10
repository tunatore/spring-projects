<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Blog Post</title>

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
          <a class="brand" href="https://www.udemy.com/u/tunatore/"><b>Udemy</b></a>
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
    
      <h1>New Blog Post</h1>
    
      <c:if test="${not empty message}">
      		${message} <br/>       	
	
			<a href="${pageContext.request.contextPath}/blogposts">Blog Posts</a>	
	     	
      </c:if>	
    
      <form action="${pageContext.request.contextPath}/saveBlogPost" method="post">
      		<table style="width: 650px" class="table table-striped">
      				<tr><td>Title</td><td><input type="text" name="title" required autofocus/></td></tr>
					<tr><td>Content</td><td>
					<textarea style="width: 400px" name="content" rows="10" maxlength="4000" required></textarea>
					
					</td></tr>
					<tr><td>Draft</td><td><input type="checkbox" name="draft"/></td></tr>
					<tr><td colspan="2"><input type="submit" value="Save"></td></tr>      		
      		</table>      
      </form>	    
	
	  <a href="${pageContext.request.contextPath}">Back</a>

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