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

</head>

<body>
	<div class="container">

		<jsp:include page="header.jsp" />

		<section id="mainsection">
			<c:if test="${empty expenses}">
				<div class="row top-buffer-20 italic-green">
					<div class="col-md-offset-5 col-md-7">
						You have not added any expense in last 2 days !!!					
					</div>
				</div>
			</c:if>
			<c:if test="${not empty expenses}">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th>Creation Date</th>
								<th>Name</th>
								<th>Amount (in Rs)</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${expenses}" var="expense">
								<tr>
									<td>${expense.creationDate}</td>
									<td>${expense.name}</td>
									<td>${expense.amount}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:if>
		</section>

		<jsp:include page="footer.jsp" />

	</div>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>

