<div id="listaDeProductos"> </div>
    <section class="container">
        <div class="width480 autoMargin solicitudBox marginBottom84">
            <div class="autoMargin">
                <input type="hidden" id="currentPageProductos"/>
                <ul class="clearFix" id="paginationProductos">
                </ul>
            </div>
        </div>
    </section>
<g:render template="configuracion/producto/altaProducto"/>
<div id="detalleProducto"></div>

<g:urlContextAware value="/dashboard/getProductos" var="urlProductos"/>
<script>
$.getProductos = "${urlProductos}";
 
$(document).ready(function () {
    var idPaginacion = "paginationProductos";
    
	$('#'+idPaginacion).on('click', 'a.page', function (event) {
		event.preventDefault();
		var page = $(this).data('page');
		getProductos(page,idPaginacion);
	    });
	getProductos(1,idPaginacion);
    console.log(idPaginacion);
});

function getProductos(page,idPaginacion) {
    $("#currentPageProductos").val(page);
    var filter = new Object();
    filter.page = page;
    $.ajax({
        type: "POST",
        dataType: "json",
        url: $.getProductos,
        data: JSON.stringify(filter),
        contentType: "application/json",
        success: function (response) {
            var page = response.page;
            var totalPages = response.totalPages;
            $('#'+idPaginacion).empty();
            if (response.productos.length > 0) {
            pagination(totalPages, page,idPaginacion);
            mostrarProductosPaginados(response.productos);
            } else {
                var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="5" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay usuarios registrados</span>';
                row += '</td>';
                row += '</tr>';
                $("#usuarios-tb tbody:last").append(row);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            
        }
    });
}




</script>
