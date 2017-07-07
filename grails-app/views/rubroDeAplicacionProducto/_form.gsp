<%@ page import="la.kosmos.app.RubroDeAplicacionProducto" %>



<div class="fieldcontain ${hasErrors(bean: rubroDeAplicacionProductoInstance, field: 'rubro', 'error')} required">
	<label for="rubro">
		<g:message code="rubroDeAplicacionProducto.rubro.label" default="Rubro" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="rubro" name="rubro.id" from="${la.kosmos.app.RubroDeAplicacionDeCredito.list()}" optionKey="id" required="" value="${rubroDeAplicacionProductoInstance?.rubro?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: rubroDeAplicacionProductoInstance, field: 'producto', 'error')} required">
	<label for="producto">
		<g:message code="rubroDeAplicacionProducto.producto.label" default="Producto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="producto" name="producto.id" from="${la.kosmos.app.Producto.list()}" optionKey="id" required="" value="${rubroDeAplicacionProductoInstance?.producto?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: rubroDeAplicacionProductoInstance, field: 'tipoDeIngresos', 'error')} required">
	<label for="tipoDeIngresos">
		<g:message code="rubroDeAplicacionProducto.tipoDeIngresos.label" default="Tipo De Ingresos" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoDeIngresos" name="tipoDeIngresos.id" from="${la.kosmos.app.TipoDeIngresos.list()}" optionKey="id" required="" value="${rubroDeAplicacionProductoInstance?.tipoDeIngresos?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: rubroDeAplicacionProductoInstance, field: 'haTenidoAtrasos', 'error')} ">
	<label for="haTenidoAtrasos">
		<g:message code="rubroDeAplicacionProducto.haTenidoAtrasos.label" default="Ha Tenido Atrasos" />
		
	</label>
	<g:checkBox name="haTenidoAtrasos" value="${rubroDeAplicacionProductoInstance?.haTenidoAtrasos}" />

</div>

