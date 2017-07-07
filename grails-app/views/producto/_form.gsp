<%@ page import="la.kosmos.app.Producto" %>



<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'nombreDelProducto', 'error')} required">
	<label for="nombreDelProducto">
		<g:message code="producto.nombreDelProducto.label" default="Nombre Del Producto" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombreDelProducto" required="" value="${productoInstance?.nombreDelProducto}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'marca', 'error')} required">
	<label for="marca">
		<g:message code="producto.marca.label" default="Marca" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="marca" name="marca.id" from="${la.kosmos.app.Marca.list()}" optionKey="id" required="" value="${productoInstance?.marca?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'entidadFinanciera', 'error')} required">
	<label for="entidadFinanciera">
		<g:message code="producto.entidadFinanciera.label" default="Entidad Financiera" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="entidadFinanciera" name="entidadFinanciera.id" from="${la.kosmos.app.EntidadFinanciera.list()}" optionKey="id" required="" value="${productoInstance?.entidadFinanciera?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'tipoDeProducto', 'error')} required">
	<label for="tipoDeProducto">
		<g:message code="producto.tipoDeProducto.label" default="Tipo De Producto" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="tipoDeProducto" name="tipoDeProducto.id" from="${la.kosmos.app.TipoDeProducto.list()}" optionKey="id" required="" value="${productoInstance?.tipoDeProducto?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'rutaImagenDefault', 'error')} required">
	<label for="rutaImagenDefault">
		<g:message code="producto.rutaImagenDefault.label" default="Ruta Imagen Default" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rutaImagenDefault" required="" value="${productoInstance?.rutaImagenDefault}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="producto.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${productoInstance?.descripcion}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'tituloEnCotizador', 'error')} required">
	<label for="tituloEnCotizador">
		<g:message code="producto.tituloEnCotizador.label" default="Titulo En Cotizador" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="tituloEnCotizador" required="" value="${productoInstance?.tituloEnCotizador}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'tipoDeTasa', 'error')} ">
	<label for="tipoDeTasa">
		<g:message code="producto.tipoDeTasa.label" default="Tipo De Tasa" />
		
	</label>
	<g:select id="tipoDeTasa" name="tipoDeTasa.id" from="${la.kosmos.app.TipoDeTasaDeInteres.list()}" optionKey="id" value="${productoInstance?.tipoDeTasa?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'claveDeProducto', 'error')} ">
	<label for="claveDeProducto">
		<g:message code="producto.claveDeProducto.label" default="Clave De Producto" />
		
	</label>
	<g:textField name="claveDeProducto" value="${productoInstance?.claveDeProducto}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'esquema', 'error')} ">
	<label for="esquema">
		<g:message code="producto.esquema.label" default="Esquema" />
		
	</label>
	<g:select id="esquema" name="esquema.id" from="${la.kosmos.app.Esquema.list()}" optionKey="id" value="${productoInstance?.esquema?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'claseIconoPaso', 'error')} ">
	<label for="claseIconoPaso">
		<g:message code="producto.claseIconoPaso.label" default="Clase Icono Paso" />
		
	</label>
	<g:textField name="claseIconoPaso" value="${productoInstance?.claseIconoPaso}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'activo', 'error')} ">
	<label for="activo">
		<g:message code="producto.activo.label" default="Activo" />
		
	</label>
	<g:checkBox name="activo" value="${productoInstance?.activo}" />

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'cat', 'error')} required">
	<label for="cat">
		<g:message code="producto.cat.label" default="Cat" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="cat" value="${fieldValue(bean: productoInstance, field: 'cat')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'montoMaximo', 'error')} required">
	<label for="montoMaximo">
		<g:message code="producto.montoMaximo.label" default="Monto Maximo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="montoMaximo" value="${fieldValue(bean: productoInstance, field: 'montoMaximo')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'montoMinimo', 'error')} required">
	<label for="montoMinimo">
		<g:message code="producto.montoMinimo.label" default="Monto Minimo" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="montoMinimo" value="${fieldValue(bean: productoInstance, field: 'montoMinimo')}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: productoInstance, field: 'tasaDeInteres', 'error')} required">
	<label for="tasaDeInteres">
		<g:message code="producto.tasaDeInteres.label" default="Tasa De Interes" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tasaDeInteres" value="${fieldValue(bean: productoInstance, field: 'tasaDeInteres')}" required=""/>

</div>

