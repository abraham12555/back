
<%@ page import="la.kosmos.app.RubroDeAplicacionProducto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rubroDeAplicacionProducto.label', default: 'RubroDeAplicacionProducto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-rubroDeAplicacionProducto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-rubroDeAplicacionProducto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="rubroDeAplicacionProducto.rubro.label" default="Rubro" /></th>
					
						<th><g:message code="rubroDeAplicacionProducto.producto.label" default="Producto" /></th>
					
						<th><g:message code="rubroDeAplicacionProducto.tipoDeIngresos.label" default="Tipo De Ingresos" /></th>
					
						<g:sortableColumn property="haTenidoAtrasos" title="${message(code: 'rubroDeAplicacionProducto.haTenidoAtrasos.label', default: 'Ha Tenido Atrasos')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rubroDeAplicacionProductoInstanceList}" status="i" var="rubroDeAplicacionProductoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rubroDeAplicacionProductoInstance.id}">${fieldValue(bean: rubroDeAplicacionProductoInstance, field: "rubro")}</g:link></td>
					
						<td>${fieldValue(bean: rubroDeAplicacionProductoInstance, field: "producto")}</td>
					
						<td>${fieldValue(bean: rubroDeAplicacionProductoInstance, field: "tipoDeIngresos")}</td>
					
						<td><g:formatBoolean boolean="${rubroDeAplicacionProductoInstance.haTenidoAtrasos}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rubroDeAplicacionProductoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
