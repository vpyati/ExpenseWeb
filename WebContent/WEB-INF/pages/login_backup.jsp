
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
		<section id="header">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span> 
							<span class="icon-bar"></span>
						</button>
						<div class="navbar-brand"><a href="#"><img alt="" src="/img/logo.png" id="logoimg"></a>TRAx</div>
						
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul class="nav navbar-nav">
							<li class="active"><a href="#">Home</a></li>
							<li><a href="#">New Expense</a></li>
							<li><a href="#">Dashboard</a></li>
							<li><a href="#">Contact</a></li>
						</ul>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="#">Welcome Vikram!</a></li>
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
				<!--/.container-fluid -->
			</nav>
		</section>

		<section id="mainsection">
		
			<button class="btn" type="submit" formmethod="get" form="login">Login
				With Google</button>
		
		</section>

		<form id="login" action="/googlelogin"></form>


		<footer id="page_footer">
			<p>Copyright © 2014 TRAx</p>
			<nav>
				<ul>
					<li><a href="#">Home</a></li>
					<li><a href="#">About</a></li>
					<li><a href="#">Terms of Service</a></li>
					<li><a href="#">Privacy</a></li>
				</ul>
			</nav>
		</footer>
	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>

