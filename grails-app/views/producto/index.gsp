
<%@ page import="la.kosmos.app.Producto" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'producto.label', default: 'Producto')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-producto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-producto" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nombreDelProducto" title="${message(code: 'producto.nombreDelProducto.label', default: 'Nombre Del Producto')}" />
					
						<th><g:message code="producto.marca.label" default="Marca" /></th>
					
						<th><g:message code="producto.entidadFinanciera.label" default="Entidad Financiera" /></th>
					
						<th><g:message code="producto.tipoDeProducto.label" default="Tipo De Producto" /></th>
					
						<g:sortableColumn property="rutaImagenDefault" title="${message(code: 'producto.rutaImagenDefault.label', default: 'Ruta Imagen Default')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'producto.descripcion.label', default: 'Descripcion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${productoInstanceList}" status="i" var="productoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${productoInstance.id}">${fieldValue(bean: productoInstance, field: "nombreDelProducto")}</g:link></td>
					
						<td>${fieldValue(bean: productoInstance, field: "marca")}</td>
					
						<td>${fieldValue(bean: productoInstance, field: "entidadFinanciera")}</td>
					
						<td>${fieldValue(bean: productoInstance, field: "tipoDeProducto")}</td>
					
						<td>${fieldValue(bean: productoInstance, field: "rutaImagenDefault")}</td>
					
						<td>${fieldValue(bean: productoInstance, field: "descripcion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${productoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
