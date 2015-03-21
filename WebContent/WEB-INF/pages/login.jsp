
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

		<section id="mainsection-login">

			<div class="row">
				<div class="col-md-12 hidden-xs">
					<div class="eighty-px"></div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-4 hidden-xs">
					<div class="eighty-px"></div>
				</div>
				<div class="col-md-4 col-xs-12">
					<div class="eighty-px" align="center">

						<button class="btn" type="submit" formmethod="get" form="login">Login
							With Google</button>

					</div>
				</div>
				<div class="col-md-4 hidden-xs">
					<div class="eighty-px"></div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-12 hidden-xs">
					<div class="eighty-px"></div>
				</div>
			</div>

		</section>

		<form id="login" action="/googlelogin"></form>
		
		<jsp:include page="footer.jsp" />

	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>

