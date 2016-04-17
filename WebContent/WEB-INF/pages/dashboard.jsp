
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

</head>

<body>
	<div class="container">
		
		<jsp:include page="header.jsp" />
		
		<section id="mainsection">
			
			<div class="container-fluid">
	<form action="/newexpense" method="post">
      <!-- row 2 -->
      <div class="row top-buffer-20-20">
        <div class="col-md-offset-5 col-md-7">Add a New Expense</div>
      </div>

      <!-- row 3 -->
      <div class="row top-buffer-20">
        <div class="col-md-offset-4 col-md-1">Name</div>
        <div class="col-md-1"><input name="name" type="text"></div>
      </div>
      
      <div class="row top-buffer-20">
        <div class="col-md-offset-4 col-md-1">Description</div>
        <div class="col-md-1"><input name="description" type="text"></div>
      </div>
      
      <div class="row top-buffer-20">
        <div class="col-md-offset-4 col-md-1">Date</div>
        <div class="col-md-1"><input name="creationDate" type="date"></div>
      </div>
     
      <div class="row top-buffer-20">
        <div class="col-md-offset-4 col-md-1">Category</div>
        <div class="col-md-1"><input name="category" type="text"></div>
      </div>
      
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
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>

