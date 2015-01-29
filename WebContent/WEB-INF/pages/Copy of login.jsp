
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
<link rel="stylesheet" href="/css/styles.css">
<title>Expense Tracker</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

</head>

<body>
	<section id="mainsection">
		<article id="header">
			<section id="logsection">
				<img id="logo" alt="" src="/images/logo.png">
			</section>
			<section id="spacesection">&nbsp;</section>
			<section id="namesection">TRAx</section>

		</article>

		<article id="mainarticle">
			<button class="btn" type="submit" formmethod="get" form="login">Login
				With Google</button>
		</article>
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
	<script src="http://code.jquery.com/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>

