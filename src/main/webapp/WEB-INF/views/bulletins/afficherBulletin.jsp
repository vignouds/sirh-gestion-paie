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
<title>Bulletin de salaire</title>
</head>
<body>
<div class="container">
	<h4 class="text-center">Bulletin de salaire</h4>
</div>
<div class="container">
	<div class="row">
		<div class="col-8">
		<p>Entreprise</p>
		<p></p>
		<p>SIRET :</p>
		</div>
		<div class="col-4">
			<p class="text-right">Période</p>
			<p class="text-right"></p>
			<p class="text-right">Matricule</p>
		</div>
	</div>
</div>
<div class="container">
<p>Salaire</p>
<table class="table table-sm table-striped table-bordered">
	<thead>
		<tr>
			<th>Rubriques</th>
			<th>Base</th>
			<th>Taux Salarial</th>
			<th>Montant Salarial</th>
			<th>Taux Patronal</th>
			<th>Cot. patronales</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>
<div class="container">
<p>Cotisations</p>
<table class="table table-sm table-striped table-bordered">
	<thead>
		<tr>
			<th>Rubriques</th>
			<th>Base</th>
			<th>Taux Salarial</th>
			<th>Montant Salarial</th>
			<th>Taux Patronal</th>
			<th>Cot. patronales</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>
<div class="container">
<p>Net Imposable :</p>
<table class="table table-sm table-striped table-bordered">
	<thead>
		<tr>
			<th>Rubriques</th>
			<th>Base</th>
			<th>Taux Salarial</th>
			<th>Montant Salarial</th>
			<th>Taux Patronal</th>
			<th>Cot. patronales</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>
<div class="container">
<p class="text-right">NET A PAYER :</p>
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