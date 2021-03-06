<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<style type="text/css">
.contenedor {
	display: flex;
	align-items: stretch;
	background-color: #f1f1f1;
}

,
.contenedor>div {
	background-color: DodgerBlue;
	padding: 10px;
	color: white;
	margin: 30px;
	text-align: center;
	line-height: 75px;
	font-size: 30px;
	justify-content: center;
	color: white;
}

,
.pad {
	padding: 30px;
}
</style>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>

<body style="background-color: #66C7FF">
	<div id="DIV0" style="background-color: #66C7FF">
		<div id="DIV1" style="background-color: #0B4B70">
			<div class="row p-3 border">
				<div class="col-2"></div>
				<div class="col-8 text-center align-middle">

					<IMG class="rounded mx-auto d-block" style="width: 15%"
						src="imagenes/img2.png" alt="" />
					<p style="color: #66C7FF">CINE DISTRITO</p>
				</div>
				<div class="col-2">
					<LABEL style="color: #FFFFFF">Usuario:</LABEL> <label
						style="color: #FFFFFF">Diego Guerra</label>
					<FORM name="formCerrar" method="get"
						action="ControladorCompraBoleta">
						<INPUT type="hidden" name="instruccion" value="CerrarSesion">
						<INPUT style="width: 119px" class="btn btn-outline-info"
							type="button" name="buttonCerrar" value="Ver Perfil"> <INPUT
							class="btn btn-outline-danger" type="submit" name="buttonCerrar"
							value="Cerrar Sesion">
					</FORM>
					<INPUT type="hidden" name="idUsuario" value="${idUsuario}">
				</div>
			</div>
		</div>

		<div class="p-3">
			<span style="text-align: left"></span>
			<p
				style="font-family: Constantia, 'Lucida Bright', 'DejaVu Serif', Georgia, serif; text-align: center;">GESTION
				SNACKS LOCAL</p>
			<hr>

			<div class="row row-cols-1 row-cols-md-2">

				<div class="col mb-6">
					<div class="card text-white bg-secondary"
						style="max-width: 30rem; flex-grow: 1; margin: 0 auto; /* Added */ float: none; /* Added */ margin-bottom: 10px;">

						<div class="card-header" style="BACKGROUND-COLOR: #000000;">Insertar
							Snack Local</div>
						<div class="card-body" style="BACKGROUND-COLOR: #0B4B70;">
							<form>
								<table width="355" border="0" cellspacing="0">
									<tbody>
										<tr>
											<td width="168">Establecimiento ID</td>
											<td width="171"><input type="text"
												name="Establecimiento ID"></td>
										</tr>
										<tr>
											<td>Stock Establecimiento:</td>
											<td><input type="number" name="Stock Establecimiento"></td>
										</tr>
										<tr>
											<td>Inventario General ID:</td>
											<td><input type="text" name="cargo"></td>
										</tr>
									</tbody>
								</table>
								<p>
									&nbsp;&nbsp; &nbsp;&nbsp; <input type="button" name="button6"
										value="Insertar">
								</p>
							</form>
						</div>
					</div>
				</div>
				<div class="card text-white bg-secondary"
					style="max-width: 30rem; flex-grow: 1; margin: 0 auto; /* Added */ float: none; /* Added */ margin-bottom: 10px;">

					<div class="card-header" style="BACKGROUND-COLOR: #000000;">Consultar
						Snack Local</div>
					<div class="card-body" style="BACKGROUND-COLOR: #0B4B70;">
						<form>
							<table width="355" border="0" cellspacing="0">
								<tbody>
									<tr>
										<td width="168">ID Snacks:</td>
										<td width="171"><input type="text" name="nombre"></td>
									</tr>
								</tbody>
							</table>
							Establecimiento ID<br> Stock Estbablecimiento:<br>
							Inventario General ID:<br>
							<p style="text-align: left">
								&nbsp;&nbsp; &nbsp;&nbsp; <input type="button" name="button7"
									value="Consultar">
							</p>
						</form>
					</div>
				</div>
			</div>
			<div class="row row-cols-1 row-cols-md-2">
				<div class="col mb-6">
					<div class="card text-white bg-secondary"
						style="max-width: 30rem; flex-grow: 1; margin: 0 auto; /* Added */ float: none; /* Added */ margin-bottom: 10px;">
						<div class="card-header" style="BACKGROUND-COLOR: #000000;">Actualizar
							Snack Local</div>
						<div class="card-body" style="BACKGROUND-COLOR: #0B4B70;">
							<form>
								<table width="355" border="0" cellspacing="0">
									<tbody>
										<tr>
											<td width="168">Establecimiento ID</td>
											<td width="171"><input type="text"
												name="Establecimiento ID"></td>
										</tr>
										<tr>
											<td>Stock Establecimiento:</td>
											<td><input type="number" name="Stock Establecimiento"></td>
										</tr>
										<tr>
											<td>Inventario General ID:</td>
											<td><input type="text" name="cargo"></td>
										</tr>
									</tbody>
								</table>
								<p>
									&nbsp;&nbsp; &nbsp;&nbsp; <input type="button" name="button7"
										value="Actualizar">
								</p>
							</form>
						</div>
					</div>
				</div>
				<div class="col mb-6">

					<div class="card text-white bg-secondary"
						style="max-width: 30rem; flex-grow: 1; margin: 0 auto; /* Added */ float: none; /* Added */ margin-bottom: 10px;">

						<div class="card-header" style="BACKGROUND-COLOR: #000000;">Eliminar
							Snack Local</div>
						<div class="card-body" style="BACKGROUND-COLOR: #0B4B70;">
							<form>
								<table width="355" border="0" cellspacing="0">
									<tbody>
										<tr>
											<td width="168">ID Snacks:</td>
											<td width="171"><input type="text" name="nombre"></td>
										</tr>
									</tbody>
								</table>
								<br>
								<p>
									&nbsp;&nbsp; &nbsp;&nbsp; <input type="button" name="button7"
										value="Eliminar">
								</p>
							</form>
						</div>
					</div>



				</div>

			</div>
		</div>
	</div>
</body>
<footer style="background-color: #0B4B70" align="center">
	<p style="color: #FFFFFF">Autores</p>

	<label style="color: #FFFFFF">Oscar Hernandez</label> - <label
		style="color: #FFFFFF">Diego Guerra</label> - <label
		style="color: #FFFFFF">Natalia Angarita</label>

	<p>
		<a href="https://www.udistrital.edu.co/inicio">Universidad
			Distrital Francisco Jose de Caldas</a><br> <label
			style="color: #FFFFFF">Seminario de Ingeneria de Software</label>
	</p>

</footer>
</html>