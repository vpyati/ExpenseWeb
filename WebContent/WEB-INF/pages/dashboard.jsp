<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="utf-8">
<!--[if lte IE 8]>
    <script>
      document.createElement("nav");
      document.createElement("header");
      document.createElement("footer");
      document.createElement("section");
      document.createElement("aside");
      document.createElement("article");
    </script>
    <![endif]-->
<title>Expense Tracker</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/styles.css" rel="stylesheet">
 
<link rel="stylesheet" type="text/css" href="/css/autocomplete.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript" src="/js/autocomplete.js"></script>
  


</head>

<body>
	<div class="container">
		
		<jsp:include page="header.jsp" />
		
		<section id="mainsection">
			
			<div class="container-fluid">
	<form action="/newexpense" method="post">
	
      <div class="row top-buffer-20 italic-green">
        <div class="col-md-offset-5 col-md-7">
        	<c:if test="${not empty expenseAdded}">
				Expense Added Successfully !!				
			</c:if>       	
        </div>
      </div>	
	
      <!-- row 2 -->
      <div class="row top-buffer-20 bold-underline">
        <div class="col-md-offset-5 col-md-7">Add a New Expense</div>
      </div>

      <!-- row 3 -->
      <div class="row top-buffer-20">
        <div class="col-md-offset-4 col-md-1">Name</div>
        <div class="col-md-1"><input name="name" type="text"></div>
      </div>
   
      <div class="row top-buffer-20">
        <div class="col-md-offset-4 col-md-1">Category</div>
        <div class="col-md-1"><input name="categoryPath" type="text" id="cat_title"></div>
      </div>

      <div class="row top-buffer-20">
        <div class="col-md-offset-4 col-md-1">Date</div>
        <div class="col-md-1"><input name="creationDate" type="date" value="${current_date}"></div>
      </div>
      
      <div class="row top-buffer-20">
        <div class="col-md-offset-4 col-md-1">Description (Optional)</div>
        <div class="col-md-1"><input name="description" type="text"></div>
      </div>
      
      <input type="hidden" name="category" id="cat_id">     
      <div class="row top-buffer-20">
        <div class="col-md-offset-5 col-md-7"><input id="submit" type="submit"></div>
      </div>

      <div class="row top-buffer-40">
        <div class="col-md-offset-5 col-md-7"></div>
      </div>

	</form>

     </div>	
			
		
		</section>

		<jsp:include page="footer.jsp" />

	</div>
	<!-- <script src="http://code.jquery.com/jquery.js"></script>-->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>

