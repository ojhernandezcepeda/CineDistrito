<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<HTML>
<HEAD>
<META charset="utf-8">
<TITLE>Home</TITLE>
<STYLE type="text/css">
.contenedor {
	display: flex;
	align-items: stretch;
}

,
.contenedor>div {
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
#text-color {
	color: #66C7FF;
}

,
.pad {
	padding: 30px;
}
</STYLE>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<!-- <LINK href="css/bootstrap-4.4.1.css" rel="stylesheet" type="text/css"> -->
</HEAD>

<BODY style="background-color: #66C7FF">
	<DIV id="DIV0" style="background-color: #66C7FF">
		<DIV id="DIV1" style="background-color: #0B4B70">
			<div class="row p-3 border">
				<div class="col-2"></div>
				<div class="col-8 text-center align-middle">

					<IMG class="rounded mx-auto d-block" style="width: 15%"
						src="imagenes/img2.png" alt="" />
					<p style="color: #66C7FF">CINE DISTRITO</p>
				</div>
				<div class="col-2">
					<FORM name="form0" method="get" action="ControladorHome">
						<INPUT type="hidden" name="instruccion" value="inicioSesion">	
							
							<LABEL style="color: #66C7FF">Usuario:</LABEL> <INPUT
							required="required" name="usuario"> <LABEL
							style="color: #66C7FF">Contraseña:</LABEL> <INPUT name="pass"
							type="password" required="required"> <INPUT name="rol"
							required="required" type="radio" value="1"><label
							style="color: #66C7FF">empleado</label> <INPUT name="rol"
							required="required" type="radio" value="2"><label
							style="color: #66C7FF">cliente</label> <INPUT name="inicio"
							type="submit" id="button" value="Iniciar Sesión">
						<INPUT type="hidden" name="idUsuario" value="${idUsuario}">
					</FORM>
				</div>

			</div>
			<div class="p-3">
				<div class="row" class="">
					<div class="col">
						<FORM name="form1" method="get" action="ControladorHome">

							<div class="input-group">

								<INPUT type="hidden" name="instruccion"
									value="consultaPeliculas"> <SELECT
									class="custom-select" name="establecimiento"
									id="establecimientos" title="Establecimientos"
									<c:if test="${banderaEstablecimiento == true}"> disabled </c:if>>
									<c:forEach var="tempEst" items="${listaEstablecimientos}">
										<OPTION value="${tempEst.identificador}">${tempEst.nombre}</OPTION>
									</c:forEach>
								</SELECT>
								<div class="input-group-append">
									<INPUT type="submit" name="button2" id="button2"
										value="Consultar"
										<c:if test="${banderaEstablecimiento == true}"> disabled </c:if>>
								</div>

							</div>
						</FORM>

					</div>
					<div class="col">


						<FORM name="form2" method="get" action="ControladorHome">
							<div class="input-group">

								<INPUT type="hidden" name="instruccion"
									value="consultaPeliculaEspecifica"> <SELECT
									class="custom-select" name="pelicula" id="peliculas"
									title="pelicula"
									<c:if test="${banderaPelicula == true}"> disabled </c:if>>
									<c:forEach var="tempPelicula" items="${listaPeliculas}">
										<OPTION value="${tempPelicula.nombre}">${tempPelicula.nombre}</OPTION>
									</c:forEach>
								</SELECT> <INPUT type="submit" name="button3" id="button3"
									value="Consultar Pelicula"
									<c:if test="${banderaPelicula == true}"> disabled </c:if>>

							</div>
						</FORM>

					</div>
					<div class="col">
						<FORM name="form3" method="get" action="ControladorHome">
							<div class="input-group">

								<INPUT type="hidden" name="instruccion" value="consultaDetalle">
								<SELECT class="custom-select" name="fecha" id="fechas"
									title="fecha"
									<c:if test="${banderaFecha == true}"> disabled </c:if>>
									<c:forEach var="tempFecha" items="${listaPeliculas}">
										<OPTION value="${tempFecha.identificador}">${tempFecha.fechaInicio}
											&nbsp ${tempFecha.horaInicio}:00</OPTION>
									</c:forEach>
								</SELECT> <INPUT type="submit" name="button4" id="button4"
									value="Ver Detalle"
									<c:if test="${banderaFecha == true}"> disabled </c:if>>
							</div>
						</FORM>

					</div>
					<div class="col">
						<LABEL for="date" style="font-size: 12px">
							<FORM name="formNuevaConsulta" method="get"
								action="ControladorHome">
								<INPUT type="hidden" name="instruccion" value="NuevaConsulta">
								<INPUT class="btn btn-outline-warning" type="submit"
									name="button5" value="NuevaConsulta">
							</FORM>
					</div>
				</div>
			</div>
		</DIV>
		<DIV class="contenedor p-3">
			<div class="card text-white bg-secondary mb-3"
				style="max-width: 18rem; flex-grow: 1;">
				<div class="card-header" style="BACKGROUND-COLOR: #000000;">Detalle
					de la película</div>
				<div class="card-body" style="BACKGROUND-COLOR: #0B4B70;">
					<h5 class="card-title">${peliculaDeseada.nombre}</h5>
					Id: ${peliculaDeseada.identificador} <BR> Duracion en minutos:
					${peliculaDeseada.duracion}<BR> Estado:
					${peliculaDeseada.estado}<BR> Genero:
					${peliculaDeseada.genero}<BR> Fecha de inicio:
					${peliculaDeseada.fechaInicio}<BR> Hora de inicio:
					${peliculaDeseada.horaInicio}<BR> Sala
					#${peliculaDeseada.idSala}<BR>
				</div>
			</div>
			<!-- 
			<DIV style="flex-grow: 1" class="text-center">
				<BR> AA:<BR> Id:
				${peliculaDeseada.identificador} <BR> Nombre:
				${peliculaDeseada.nombre}<BR> Duracion en minutos:
				${peliculaDeseada.duracion}<BR> Estado:
				${peliculaDeseada.estado}<BR> Genero: ${peliculaDeseada.genero}<BR>
				Fecha de inicio: ${peliculaDeseada.fechaInicio}<BR> Hora de
				inicio: ${peliculaDeseada.horaInicio}<BR> Sala
				#${peliculaDeseada.idSala}<BR>
			</DIV>
			 -->
			<DIV style="flex-grow: 1">
				<DIV style="margin-right: 30px; margin-left: 30px;">
					<INPUT type="hidden" name="instruccion" value="comprar">
					<TABLE width=100% border="0" cellspacing="1" cellpadding="1">
						<TBODY>
							<TR>
								<TD height="47" colspan="11" align="center" valign="middle"
									bgcolor="#000000" style="color: #FFFFFF"></SPAN>Pantalla</TD>
							</TR>
							<TR align="center">
								<TH scope="col"></TH>
								<TH scope="col">1</TH>
								<TH scope="col">2</TH>
								<TH scope="col">3</TH>
								<TH scope="col">4</TH>
								<TH scope="col">5</TH>
								<TH scope="col">6</TH>
								<TH scope="col">7</TH>
								<TH scope="col">8</TH>
								<TH scope="col">9</TH>
								<TH scope="col">10</TH>
							</TR>
							<TR>
								<TH align="center" scope="row">A</TH>
								<c:forEach var="sillas" items="${sillasPelicula}" begin="0"
									end="9">
									<TD style="text-align: center"
										<c:choose>  
    									<c:when test="${sillas.disponible == 1}">
    									bgcolor= "#f06e6e"    										
    									</c:when>  
    									<c:when test="${sillas.disponible == 2}">
    									bgcolor= "#eff587"   									        										
    									</c:when>  
    									<c:otherwise>
    									bgcolor="#72eb65"      											  
   	 									</c:otherwise>  
									</c:choose>>
										<INPUT type="checkbox" id="silla_${sillas.identificador}"
										name="sillaSeleccion" value="${sillas.identificador}"
										<c:if test="${sillas.disponible != 3}"> disabled </c:if>>
									</TD>
								</c:forEach>
							</TR>
							<TR>
								<TH align="center" scope="row">B</TH>
								<c:forEach var="sillas" items="${sillasPelicula}" begin="10"
									end="19">
									<TD style="text-align: center"
										<c:choose>  
    									<c:when test="${sillas.disponible == 1}">
    									bgcolor= "#f06e6e"    										
    									</c:when>  
    									<c:when test="${sillas.disponible == 2}">
    									bgcolor= "#eff587"   									        										
    									</c:when>  
    									<c:otherwise>
    									bgcolor="#72eb65"      											  
   	 									</c:otherwise>  
									</c:choose>>
										<INPUT type="checkbox" id="silla_${sillas.identificador}"
										name="sillaSeleccion" value="${sillas.identificador}"
										<c:if test="${sillas.disponible != 3}"> disabled </c:if>>
									</TD>
								</c:forEach>
							</TR>
							<TR>
								<TH align="center" scope="row">C</TH>
								<c:forEach var="sillas" items="${sillasPelicula}" begin="20"
									end="29">
									<TD style="text-align: center"
										<c:choose>  
    									<c:when test="${sillas.disponible == 1}">
    									bgcolor= "#f06e6e"    										
    									</c:when>  
    									<c:when test="${sillas.disponible == 2}">
    									bgcolor= "#eff587"   									        										
    									</c:when>  
    									<c:otherwise>
    									bgcolor="#72eb65"      											  
   	 									</c:otherwise>  
									</c:choose>>
										<INPUT type="checkbox" id="silla_${sillas.identificador}"
										name="sillaSeleccion" value="${sillas.identificador}"
										<c:if test="${sillas.disponible != 3}"> disabled </c:if>>
									</TD>
								</c:forEach>
							</TR>
							<TR>
								<TH align="center" scope="row">D</TH>
								<c:forEach var="sillas" items="${sillasPelicula}" begin="30"
									end="39">
									<TD style="text-align: center"
										<c:choose>  
    									<c:when test="${sillas.disponible == 1}">
    									bgcolor= "#f06e6e"    										
    									</c:when>  
    									<c:when test="${sillas.disponible == 2}">
    									bgcolor= "#eff587"   									        										
    									</c:when>  
    									<c:otherwise>
    									bgcolor="#72eb65"      											  
   	 									</c:otherwise>  
									</c:choose>>
										<INPUT type="checkbox" id="silla_${sillas.identificador}"
										name="sillaSeleccion" value="${sillas.identificador}"
										<c:if test="${sillas.disponible != 3}"> disabled </c:if>>
									</TD>
								</c:forEach>
							</TR>
							<TR>
								<TD height="10" colspan="11" align="center" valign="middle">Preferencial</TD>
							</TR>
							<TR>
								<TH align="center" scope="row">E</TH>
								<c:forEach var="sillas" items="${sillasPelicula}" begin="40"
									end="49">
									<TD style="text-align: center"
										<c:choose>  
    									<c:when test="${sillas.disponible == 1}">
    									bgcolor= "#f06e6e"    										
    									</c:when>  
    									<c:when test="${sillas.disponible == 2}">
    									bgcolor= "#eff587"   									        										
    									</c:when>  
    									<c:otherwise>
    									bgcolor="#72eb65"      											  
   	 									</c:otherwise>  
									</c:choose>>
										<INPUT type="checkbox" id="silla_${sillas.identificador}"
										name="sillaSeleccion" value="${sillas.identificador}"
										<c:if test="${sillas.disponible != 3}"> disabled </c:if>>
									</TD>
								</c:forEach>
							</TR>
							<TR>
								<TH align="center" scope="row">F</TH>
								<c:forEach var="sillas" items="${sillasPelicula}" begin="50"
									end="59">
									<TD style="text-align: center"
										<c:choose>  
    									<c:when test="${sillas.disponible == 1}">
    									bgcolor= "#f06e6e"    										
    									</c:when>  
    									<c:when test="${sillas.disponible == 2}">
    									bgcolor= "#eff587"   									        										
    									</c:when>  
    									<c:otherwise>
    									bgcolor="#72eb65"      											  
   	 									</c:otherwise>  
									</c:choose>>
										<INPUT type="checkbox" id="silla_${sillas.identificador}"
										name="sillaSeleccion" value="${sillas.identificador}"
										<c:if test="${sillas.disponible != 3}"> disabled </c:if>>
									</TD>
								</c:forEach>
							</TR>
						</TBODY>
					</TABLE>
				</DIV>
			</DIV>
		</DIV>
	</DIV>
</BODY>
<footer style="background-color: #0B4B70" align="center">
	<p style="color: #FFFFFF">Autores</p>

	<label style="color: #FFFFFF" >Oscar Hernandez</label> - 
	<label style="color: #FFFFFF">Diego Guerra</label> - <label
		style="color: #FFFFFF">Natalia Angarita</label>
		
	<p>
		<a href="https://www.udistrital.edu.co/inicio">Universidad Distrital Francisco Jose de Caldas</a><br>
		<label style="color: #FFFFFF">Seminario de Ingeneria de Software</label>
	</p>
	
</footer>
</HTML>