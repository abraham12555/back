
<%@ page import="la.kosmos.app.RubroDeAplicacionProducto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rubroDeAplicacionProducto.label', default: 'RubroDeAplicacionProducto')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-rubroDeAplicacionProducto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-rubroDeAplicacionProducto" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list rubroDeAplicacionProducto">
			
				<g:if test="${rubroDeAplicacionProductoInstance?.rubro}">
				<li class="fieldcontain">
					<span id="rubro-label" class="property-label"><g:message code="rubroDeAplicacionProducto.rubro.label" default="Rubro" /></span>
					
						<span class="property-value" aria-labelledby="rubro-label"><g:link controller="rubroDeAplicacionDeCredito" action="show" id="${rubroDeAplicacionProductoInstance?.rubro?.id}">${rubroDeAplicacionProductoInstance?.rubro?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rubroDeAplicacionProductoInstance?.producto}">
				<li class="fieldcontain">
					<span id="producto-label" class="property-label"><g:message code="rubroDeAplicacionProducto.producto.label" default="Producto" /></span>
					
						<span class="property-value" aria-labelledby="producto-label"><g:link controller="producto" action="show" id="${rubroDeAplicacionProductoInstance?.producto?.id}">${rubroDeAplicacionProductoInstance?.producto?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rubroDeAplicacionProductoInstance?.tipoDeIngresos}">
				<li class="fieldcontain">
					<span id="tipoDeIngresos-label" class="property-label"><g:message code="rubroDeAplicacionProducto.tipoDeIngresos.label" default="Tipo De Ingresos" /></span>
					
						<span class="property-value" aria-labelledby="tipoDeIngresos-label"><g:link controller="tipoDeIngresos" action="show" id="${rubroDeAplicacionProductoInstance?.tipoDeIngresos?.id}">${rubroDeAplicacionProductoInstance?.tipoDeIngresos?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${rubroDeAplicacionProductoInstance?.haTenidoAtrasos}">
				<li class="fieldcontain">
					<span id="haTenidoAtrasos-label" class="property-label"><g:message code="rubroDeAplicacionProducto.haTenidoAtrasos.label" default="Ha Tenido Atrasos" /></span>
					
						<span class="property-value" aria-labelledby="haTenidoAtrasos-label"><g:formatBoolean boolean="${rubroDeAplicacionProductoInstance?.haTenidoAtrasos}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:rubroDeAplicacionProductoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${rubroDeAplicacionProductoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
