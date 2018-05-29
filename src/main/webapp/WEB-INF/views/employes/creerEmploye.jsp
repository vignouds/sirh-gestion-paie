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
<title>Ajouter un employé</title>
</head>
<body>
<div class="container">
	<h4 class="text-center">Ajouter un employé</h4>
</div>

<div class="container">
	<form:form method="post" modelAttribute="remuneration">
		<div class="form-group row">
			<label for="matricule" class="col-form-label col-4">Matricule</label>
			<div class="col-8 pr-0">
				<form:input path="matricule" id="matricule" class="form-control"/>
			</div>
		</div>
		<div class="form-group row">
			<label for="matricule" class="col-form-label col-4">Entreprise</label>
			<div class="col-8 pr-0">
				<form:select path="entreprise.id" items="${entreprises}" itemValue="id" id="entreprise" class="form-control"></form:select>
			</div>
		</div>
		<div class="form-group row">
			<label for="profilRemuneration" class="col-form-label col-4">Profil de rémuneration</label>
			<div class="col-8 pr-0">
				<form:select path="profilRemuneration.id" items="${profils}" itemValue="id" id="profilRemuneration" class="form-control"></form:select>
			</div>
		</div>
		<div class="form-group row">
			<label for="grade" class="col-form-label col-4">Grade</label>
			<div class="col-8 pr-0">
				<form:select path="grade.id" items="${grades}" itemValue="id" id="grade" class="form-control"></form:select>
			</div>
		</div>
		<div class="row">
			<div class="ml-auto">
				<input type="submit" value="Ajouter" class="btn btn-sm btn-success" />
				</div>
			</div>
	</form:form>

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


