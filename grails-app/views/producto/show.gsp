
<%@ page import="la.kosmos.app.Producto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producto.label', default: 'Producto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-producto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-producto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list producto">
			
				<g:if test="${productoInstance?.nombreDelProducto}">
				<li class="fieldcontain">
					<span id="nombreDelProducto-label" class="property-label"><g:message code="producto.nombreDelProducto.label" default="Nombre Del Producto" /></span>
					
						<span class="property-value" aria-labelledby="nombreDelProducto-label"><g:fieldValue bean="${productoInstance}" field="nombreDelProducto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.marca}">
				<li class="fieldcontain">
					<span id="marca-label" class="property-label"><g:message code="producto.marca.label" default="Marca" /></span>
					
						<span class="property-value" aria-labelledby="marca-label"><g:link controller="marca" action="show" id="${productoInstance?.marca?.id}">${productoInstance?.marca?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.entidadFinanciera}">
				<li class="fieldcontain">
					<span id="entidadFinanciera-label" class="property-label"><g:message code="producto.entidadFinanciera.label" default="Entidad Financiera" /></span>
					
						<span class="property-value" aria-labelledby="entidadFinanciera-label"><g:link controller="entidadFinanciera" action="show" id="${productoInstance?.entidadFinanciera?.id}">${productoInstance?.entidadFinanciera?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.tipoDeProducto}">
				<li class="fieldcontain">
					<span id="tipoDeProducto-label" class="property-label"><g:message code="producto.tipoDeProducto.label" default="Tipo De Producto" /></span>
					
						<span class="property-value" aria-labelledby="tipoDeProducto-label"><g:link controller="tipoDeProducto" action="show" id="${productoInstance?.tipoDeProducto?.id}">${productoInstance?.tipoDeProducto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.rutaImagenDefault}">
				<li class="fieldcontain">
					<span id="rutaImagenDefault-label" class="property-label"><g:message code="producto.rutaImagenDefault.label" default="Ruta Imagen Default" /></span>
					
						<span class="property-value" aria-labelledby="rutaImagenDefault-label"><g:fieldValue bean="${productoInstance}" field="rutaImagenDefault"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="producto.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${productoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.tituloEnCotizador}">
				<li class="fieldcontain">
					<span id="tituloEnCotizador-label" class="property-label"><g:message code="producto.tituloEnCotizador.label" default="Titulo En Cotizador" /></span>
					
						<span class="property-value" aria-labelledby="tituloEnCotizador-label"><g:fieldValue bean="${productoInstance}" field="tituloEnCotizador"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.tipoDeTasa}">
				<li class="fieldcontain">
					<span id="tipoDeTasa-label" class="property-label"><g:message code="producto.tipoDeTasa.label" default="Tipo De Tasa" /></span>
					
						<span class="property-value" aria-labelledby="tipoDeTasa-label"><g:link controller="tipoDeTasaDeInteres" action="show" id="${productoInstance?.tipoDeTasa?.id}">${productoInstance?.tipoDeTasa?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.claveDeProducto}">
				<li class="fieldcontain">
					<span id="claveDeProducto-label" class="property-label"><g:message code="producto.claveDeProducto.label" default="Clave De Producto" /></span>
					
						<span class="property-value" aria-labelledby="claveDeProducto-label"><g:fieldValue bean="${productoInstance}" field="claveDeProducto"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.esquema}">
				<li class="fieldcontain">
					<span id="esquema-label" class="property-label"><g:message code="producto.esquema.label" default="Esquema" /></span>
					
						<span class="property-value" aria-labelledby="esquema-label"><g:link controller="esquema" action="show" id="${productoInstance?.esquema?.id}">${productoInstance?.esquema?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.claseIconoPaso}">
				<li class="fieldcontain">
					<span id="claseIconoPaso-label" class="property-label"><g:message code="producto.claseIconoPaso.label" default="Clase Icono Paso" /></span>
					
						<span class="property-value" aria-labelledby="claseIconoPaso-label"><g:fieldValue bean="${productoInstance}" field="claseIconoPaso"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.activo}">
				<li class="fieldcontain">
					<span id="activo-label" class="property-label"><g:message code="producto.activo.label" default="Activo" /></span>
					
						<span class="property-value" aria-labelledby="activo-label"><g:formatBoolean boolean="${productoInstance?.activo}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.cat}">
				<li class="fieldcontain">
					<span id="cat-label" class="property-label"><g:message code="producto.cat.label" default="Cat" /></span>
					
						<span class="property-value" aria-labelledby="cat-label"><g:fieldValue bean="${productoInstance}" field="cat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.montoMaximo}">
				<li class="fieldcontain">
					<span id="montoMaximo-label" class="property-label"><g:message code="producto.montoMaximo.label" default="Monto Maximo" /></span>
					
						<span class="property-value" aria-labelledby="montoMaximo-label"><g:fieldValue bean="${productoInstance}" field="montoMaximo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.montoMinimo}">
				<li class="fieldcontain">
					<span id="montoMinimo-label" class="property-label"><g:message code="producto.montoMinimo.label" default="Monto Minimo" /></span>
					
						<span class="property-value" aria-labelledby="montoMinimo-label"><g:fieldValue bean="${productoInstance}" field="montoMinimo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${productoInstance?.tasaDeInteres}">
				<li class="fieldcontain">
					<span id="tasaDeInteres-label" class="property-label"><g:message code="producto.tasaDeInteres.label" default="Tasa De Interes" /></span>
					
						<span class="property-value" aria-labelledby="tasaDeInteres-label"><g:fieldValue bean="${productoInstance}" field="tasaDeInteres"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:productoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${productoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
