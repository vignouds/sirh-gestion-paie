<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<title>Liste des bulletins</title>
</head>
<body>
<div class="container">
	<h4 class="text-center">Liste des bulletins</h4>
</div>
<div class="container text-right">
	<button ><a href="<c:url value='/mvc/bulletins/creer' />" class="text-dark">Créer un nouveau bulletin</a></button>
</div>
<div class="container">
<table class="table table-sm table-striped table-bordered">
	<thead>
		<tr>
			<th>Date/heure création</th>
			<th>Période</th>
			<th>Matricule</th>
			<th>Salaire brut</th>
			<th>Net Imposable</th>
			<th>Net A Payer</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>

	<c:forEach var="bulletin" items="${bulletins}">
		<tr>
			<td>${bulletin.key.heureCreationFormat}</td>
			<td>${bulletin.key.periode}</td>
 			<td>${bulletin.key.remunerationEmploye.matricule}</td>
 			<td>${bulletin.value.salaireBrut}</td>
 			<td>${bulletin.value.netImposable}</td>
 			<td>${bulletin.value.netAPayer}</td>
 			<td>Visualiser</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
		integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
		crossorigin="anonymous"></script>
</body>
</html>