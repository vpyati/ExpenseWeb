<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section id="header">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="navbar-brand">
					<a href="#"><img alt="" src="/img/logo.png" id="logoimg"></a>TRAx
				</div>
			</div>
			<c:if test="${not empty useremail}">
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">New Expense</a></li>
						<li><a href="#">Dashboard</a></li>
						<li><a href="#">Contact</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">Welcome ${useremail}!</a></li>
					</ul>
				</div>
			</c:if>
			<!--/.nav-collapse -->
		</div>
		<!--/.container-fluid -->
	</nav>
</section>


