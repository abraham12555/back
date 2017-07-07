<div id="listaPasosCotizador"></div>
<g:render template="configuracion/pasoCotizadorEntidadFinanciera/altaPasoCotizadorEntidadFinanciera"/>
<section class="container">
    <div class="width480 autoMargin solicitudBox marginBottom84">
        <div class="autoMargin">
            <input type="hidden" id="currentPagePasosCotizador"/>
            <ul class="clearFix" id="paginationPasosCotizador">
            </ul>
        </div>
    </div>
</section>



<div id="detallePasoCotizador"></div>


<%@ page import="la.kosmos.app.TipoDeDocumento" %>
<%@ page import="la.kosmos.app.TipoDeIngresos" %>

<g:form  autocomplete="on" method="post" enctype="multipart/form-data">

    <div class="solicitudLightbox hide" style="overflow: scroll;" id="modalRubros">

        <div class="overlay"></div>
        <div class="visitaContainer">
            <div class="dashBordBox">
            <div id="vistaPrev">
                <div class="loginForm gray font14" class="tabcontent" id="primerPasoModal">
                    <input type="hidden" name="solicitudId" value="4">
                    <p  class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">RUBROS</p>
                    <div class="solicitudTitle autoMargin" style="width: 100%;">
                        <p class="center font14 lightGray">Ingresa un Rubro Nuevo</p>
                    </div>
                    <div class="formContainer">

                        <div class='col12' style='display:inline-block; position:relative;'>

                            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Nombre del Rubro)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Posición)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Descripción)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Icono)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(tooltip)</p>
                                    </div>
                                </div>
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                                    </div>
                                    <div class="paddingTop15">
                                        <input class="block cameraBox width150 height30" type="text" id="nombre" name="nombre"  value="">
                                    </div>
                                    <div class="paddingTop15">
                                        <input class="block cameraBox width150 height30" type="number" id="posicion" name="posicion"  value="">
                                    </div>
                                    <div class="paddingTop15">
                                        <input class="block cameraBox width150 height30" type="text"  id="descripcionrubro" name="descripcion">
                                    </div>
                                    <div class="paddingTop15">
                                        <input onfocus="verListaIconos('detalleIconos','claseIconoPasoRubro');" class="block cameraBox width150 height30" type="text" id="claseIconoPasoRubro" name="claseIconoPaso">

                                    </div>                   
                                    <div class="col2 floatLeft">
                                        <div class="paddingTop15">
                                            <label>Si</label>
                                            <input class="block cameraBox width150 height30 " onclick="mostrarCampo('activo');" type="radio"  name="tooltip"  value="true" checked>

                                        </div>

                                    </div>
                                    <div class="col2 floatLeft">
                                        <div class="paddingTop15">
                                            <label>No</label>
                                            <input class="block cameraBox width150 height30 " onclick="ocultarCampo('activo');" type="radio"  name="tooltip"  value="false">

                                        </div>

                                    </div>





                                </div>
                            </div>
                            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                                    </div>
                                    <div class="paddingTop20 activo" >
                                        <p class=" gray font14 fontWeight500 latterspacing1 center">(Texto Tooltip)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Imagen)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Activo)</p>
                                    </div>
                                </div>
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                                    </div>
                                    <div class="paddingTop15 activo">
                                        <input class="block cameraBox width150 height30" type="text" id="textoTooltip" name="textoTooltip" value="">
                                    </div>
                                    <div class="paddingTop15">
                                        <input class="block cameraBox width150 height30" type="file" id="urlImagen" name="urlImagen" value="">
                                    </div>
                                    <div class="col2 floatLeft">
                                        <div class="paddingTop15">
                                            <label>Si</label>
                                            <input class="block cameraBox width150 height30 "  type="radio"  name="activoRubro"  value="true" checked>

                                        </div>

                                    </div>
                                    <div class="col2 floatLeft">
                                        <div class="paddingTop15">
                                            <label>No</label>
                                            <input class="block cameraBox width150 height30 " type="radio"  name="activoRubro"  value="false">

                                        </div>

                                    </div>
                                </div>
                            </div>



                            <button type="button" id="segundoPaso" onclick="agregarRubro();" class="loginButton blueButton letterspacing2 font14 pointer" style="width: 15%; position:absolute; bottom:10px; right:10px;">Agregar</button>
                        </div>
                        <div>
                            <table id='rubrosAgregados' style="width: 100%; margin-bottom: 20px; border-bottom: 5px #369;">
                                <thead>
                                    <tr>
                                        <th colspan="5" class="navyBg"><h1 class="graphHeading colorWhite letterspacing2 textUpper center">Rubros Añadidos</h1></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2" class="tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom">No se han agregado rubros</td>
                                    </tr>
                                </tbody>
                            </table>
                            <input type="hidden" id="cantidadDeRubros">
                        </div>
                        <div class="col12" style="text-align: center;">
                            <div class="col4" style="display: inline-block;">
                                <button type="button" id="btnVerificar" onclick="verificar();" class="loginButton letterspacing2 font14 pointer blueButton" >REGISTRAR CONFIGURACIÓN</button>
                            </div>
                            <div class="col4" style="display: inline-block;">
                                <button type="button" id="btnVistaPrevia" onclick="vistaPrevia();" class="loginButton letterspacing2 font14 pointer blueButton" >VISTA PREVIA DE CONFIGURACIÓN</button>
                            </div>
                            <div class="col4" style="display: inline-block;">
                                <button type="button" onclick="cerrarModal('modalRubros');" class="loginButton letterspacing2 font14 pointer " style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        </div>
    </div>


    <div class="solicitudLightbox hide" id="modalDocumentos">
        <div class="overlay"></div>
        <div class="visitaContainer">
            <div class="dashBordBox">
                <div class="loginForm gray font14">
                    <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">Documentos</p>
                    <div class="solicitudTitle autoMargin" style="width: 100%;">
                        <p class="center font14 lightGray nombreRubro">Asocia un Documento a este Rubro</p>
                        <input type="hidden" id='idDiv' value=""/>

                    </div>

                    <div class="formContainer">
                        <div class="blueButton caracteristicasRubro">
                        </div>
                        <div class='col12' style='display:inline-block; position:relative;'>

                            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                                <p class="gray font14 fontWeight500 center paddingBottom20">SELECCIONA EL DOCUMENTO REQUERIDO PARA VISUALIZARLO EN EL COTIZADOR</p>
                                <div class="col12 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1 center">DOCUMENTOS</p>
                                    </div>
                                    <div class="paddingTop20">

                                        <select class="js-example-basic-multiple" id="tipoDeDocumentoId" multiple="multiple" name="tipoDeDocumentoId" required>
                                            <g:each var="documentos" in="${la.kosmos.app.TipoDeDocumento.findAllWhere(usoEnCotizador:true)}">

                                                <option value=${documentos.id}>${documentos.nombre}</option>
                                            </g:each>

                                        </select>
                                    </div>

                                </div>

                            </div>
                            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25">

                                <div class="col12 floatLeft">

                                    <div class="col12" style="text-align: center;">
                                        <div class="col4" style="display: inline-block;">
                                            <button type="button" id="tercerPaso" onclick="agregarDocumento();" class="loginButton blueButton letterspacing2 font14 pointer" style="width: 15%; position:absolute; bottom:-10px; right:10px;">Agregar</button>

                                        </div>
                                    </div>
                                </div>

                            </div>

                        </div>
                        <div>
                            <table id='documentosAgregados' style="width: 100%; margin-bottom: 20px; border-bottom: 5px #369;">
                                <thead>
                                    <tr>
                                        <th colspan="2" class="navyBg"><h1 class="graphHeading colorWhite letterspacing2 textUpper center">Documentos Vinculados</h1></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2" class="tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom">No se han agregado Documentos</td>
                                    </tr>
                                </tbody>
                            </table>
                            <input type="hidden" id="cantidadDeDocumentos">
                            <input type="hidden" class="idRubroTemp" id="idRubroTemp">

                        </div>
                        <div class="col12" style="text-align: center;">

                            <div class="col4" style="display: inline-block;">
                                <button type="button" onclick="cerrarModal('modalDocumentos');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">ATRAS</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <div class="solicitudLightbox hide" id="modalProductos">
        <div class="overlay"></div>
        <div class="visitaContainer">
            <div class="dashBordBox">
                <div class="loginForm gray font14">
                    <input type="hidden" name="solicitudId" value="4">
                    <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">Productos</p>
                    <div class="solicitudTitle autoMargin" style="width: 100%;">
                        <p class="center font14 lightGray">Asocia un Producto a este Rubro</p>
                        <input type="hidden" id='idDivProductos' value=""/>

                    </div>
                    <div class="formContainer">
                        <div class="blueButton caracteristicasRubro">

                        </div>
                        <div class='col12' style='display:inline-block; position:relative;'>


                            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                                    </div>
                                    <div class="paddingTop10">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Nombre del Producto)</p>
                                    </div>
                                    <div class="paddingTop25">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Imagen)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Descripción)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Titulo En Cotizador)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Clave del Producto)</p>
                                    </div>
                                    <div class="paddingTop30">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Ha tenido Atrasos?)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Icono)</p>
                                    </div>

                                </div>
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                                    </div>
                                    <div class="paddingTop15">
                                        <input class="block cameraBox width150 height30" type="text"  id="nombreDelProducto2" name="nombreDelProducto" value="">
                                    </div>
                                    <div class="paddingTop10">
                                        <input class="block cameraBox width150 height30" type="file" id="rutaImagenDefault2" name="rutaImagenDefault" value="">
                                    </div>
                                    <div class="paddingTop10">
                                        <input class="block cameraBox width150 height30" type="text" id="descripcionProducto2" name="descripcionProducto" value="">
                                    </div>
                                    <div class="paddingTop10">
                                        <input class="block cameraBox width150 height30" type="text" id="tituloEnCotizador2" name="tituloEnCotizador" value="">
                                    </div>
                                    <div class="paddingTop10">
                                        <input class="block cameraBox width150 height30" type="text" id="claveDeProducto2" name="claveDeProducto" value="">
                                    </div>
                                    <div class="paddingTop10">
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>Si</label>
                                                <input class="block cameraBox width150 height30 " type="radio"  name="haTenidoAtrasos"  value="true">

                                            </div>

                                        </div>
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>No</label>
                                                <input class="block cameraBox width150 height30 " type="radio"  name="haTenidoAtrasos"  value="false" checked>

                                            </div>

                                        </div>

                                    </div>
                                    <div class="paddingTop15">
                                        <input onfocus="verListaIconos('detalleIconos','claseIconoProductos22');" class="block cameraBox width150 height30" type="text"  id="claseIconoProductos22" name="claseIconoPasoProducto" value="">
                                    </div>
                                </div>
                            </div>


                            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                                    </div>

                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Activo)</p>
                                    </div>
                                    <div class="paddingTop25">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Cat)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Monto Maximo)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Monto Minimo)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Tasa de Interes)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1 center">(Tipo de Ingreso asociado)</p>
                                    </div>
                                    <div class="paddingTop20">
                                    </div>
                                </div>
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                                    </div>
                                    <div class="paddingTop5">
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>Si</label>
                                                <input class="block cameraBox width150 height30 " type="radio"  name="activoProducto"  value="true" checked>
                                            </div>
                                        </div>
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>No</label>
                                                <input class="block cameraBox width150 height30 "type="radio"  name="activoProducto"  value="false">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="paddingTop10">
                                        <input class="block cameraBox width150 height30" type="text"  id="cat2" name="cat" value="">
                                    </div>
                                    <div class="paddingTop10">
                                        <input class="block cameraBox width150 height30" type="text"  id="montoMaximo2" name="montoMaximo" value="">
                                    </div>
                                    <div class="paddingTop10">
                                        <input class="block cameraBox width150 height30" type="text"  id="montoMinimo2" name="montoMinimo" value="">
                                    </div>
                                    <div class="paddingTop10">
                                        <input class="block cameraBox width150 height30" type="text"  id="tasaDeInteres2" name="tasaDeInteres" value="">
                                    </div>

                                    <div class="paddingTop10">

                                        <select class="js-example-basic-multiple" multiple="multiple"  id="tipoDeIngresosId" name="tipoDeIngresosId">
                                            <g:each var="ingresos" in="${la.kosmos.app.TipoDeIngresos.list()}">

                                                <option value=${ingresos.id}>${ingresos.nombre}</option>
                                            </g:each>

                                        </select>
                                    </div>
                                    <div class="paddingTop10">
                                        <button type="button" id="cuartoPaso" onclick="agregarProducto();" class="loginButton blueButton letterspacing2 font14 pointer" style="width: 100%;  bottom:10px; right:10px;">Agregar</button>

                                    </div>
                                </div>
                            </div>


                        </div>
                        <div>
                            <table id='productosAgregados' style="width: 100%; margin-bottom: 20px; border-bottom: 5px #369;">
                                <thead>
                                    <tr>
                                        <th colspan="4" class="navyBg"><h1 class="graphHeading colorWhite letterspacing2 textUpper center">Productos Vinculados</h1></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="3" class="tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom">No se han agregado Productos</td>
                                    </tr>
                                </tbody>
                            </table>
                            <input type="hidden" id="cantidadDeProductos">
                            <input type="hidden" class="idRubroTemp">
                            <input type="hidden" class="idRubroTemp3">

                        </div>
                        <div class="col12" style="text-align: center;">

                            <div class="col4" style="display: inline-block;">
                                <button type="button" onclick="cerrarModal('modalProductos');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">ATRAS</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="solicitudLightbox hide " style="overflow: scroll;" id="modalPlazos">
        <div class="overlay"></div>
        <div class="visitaContainer">
            <div class="dashBordBox">
                <div class="loginForm gray font14">
                    <input type="hidden" name="solicitudId" value="4">
                    <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">PLAZOS</p>
                    <div class="solicitudTitle autoMargin" style="width: 100%;">
                        <p class="center font14 lightGray">PlAZOS PARA</p>
                    </div>
                    <div class="formContainer">
                        <div class="blueButton caracteristicasProducto">

                        </div>
                        <div class='col12' style='display:inline-block; position:relative;'>

                            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Usar Lista de Plazos)</p>
                                    </div>
                                    <div class="paddingTop30">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Importe Maximo)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Importe Minimo)</p>
                                    </div>
                                    <div class="paddingTop20 plazoMaximo hide">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Plazo Maximo)</p>
                                    </div>
                                    <div class="paddingTop20 plazoMinimo hide">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Plazo Minimo)</p>
                                    </div>
                                    <div class="paddingTop20 plazosPermitidos">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Plazos Permitidos)</p>
                                    </div>
                                    <div class="paddingTop20 hide saltoSlider">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Salto Slider)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Periodicidad)</p>
                                    </div>

                                </div>
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                                    </div>
                                    <div class="paddingTop5">
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>Si</label>
                                                <input class="block cameraBox width150 height30 "  onclick="ocultarCampo('saltoSlider','mostrar');" type="radio"  name="usarListaDePlazos"  value="true" checked>
                                            </div>
                                        </div>
                                        <div class="col2 floatLeft">
                                            <div class="paddingTop15">
                                                <label>No</label>
                                                <input class="block cameraBox width150 height30 " onclick="mostrarCampo('saltoSlider','ocultar');" type="radio"  name="usarListaDePlazos"  value="false" >
                                            </div>
                                        </div>
                                    </div>
                                    <div class="paddingTop15">
                                        <input class="block cameraBox width150 height30" type="text"  id="importeMaximo" name="importeMaximo" value="">
                                    </div>
                                    <div class="paddingTop5">
                                        <input class="block cameraBox width150 height30" type="text" id="importeMinimo" name="importeMinimo" value="">
                                    </div>
                                    <div class="paddingTop10 plazoMaximo hide">
                                        <input class="block cameraBox width150 height30" type="text" id="plazoMaximo"  name="plazoMaximo" value="">
                                    </div>
                                    <div class="paddingTop10 plazoMinimo hide">
                                        <input class="block cameraBox width150 height30" type="text" id="plazoMinimo" name="plazoMinimo" value="">
                                    </div>
                                    <div class="paddingTop10 plazosPermitidos">
                                        <input class="block cameraBox width150 height30" type="text" id="plazosPermitidos22" name="plazosPermitidos22" value="">
                                    </div>
                                    <div class="paddingTop10 hide saltoSlider">
                                        <input class="block cameraBox width150 height30" type="text" id="saltoSlider" name="saltoSlider" value="">
                                    </div>
                                    <div class="paddingTop10">
                                        <select class="block cameraBox width150 height30" id="periodicidadId" name="periodicidadId" value="">

                                            <g:each var="periodicidad" in="${la.kosmos.app.Periodicidad.findAll()}">
                                                <option value=${periodicidad.id}> ${periodicidad.nombre}</option>
                                            </g:each>
                                        </select>
                                    </div>
                                </div>
                            </div>    


                            <button type="button" id="quintoPaso" onclick="agregarPlazo();" class="loginButton blueButton letterspacing2 font14 pointer" style="width: 15%; position:absolute; bottom:10px; right:10px;">Agregar</button>
                        </div>
                        <div>
                            <table id='plazosAgregados' style="width: 100%; margin-bottom: 20px; border-bottom: 5px #369;">
                                <thead>
                                    <tr>
                                        <th colspan="4" class="navyBg"><h1 class="graphHeading colorWhite letterspacing2 textUpper center">Plazos Añadidos</h1></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2" class="tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom">No se han agregado plazos</td>
                                    </tr>
                                </tbody>
                            </table>
                            <input type="hidden" id="cantidadDePlazos">
                            <input type="hidden" class="idRubroTemp3">
                            <input type="hidden" class="idProductoTemp">

                        </div>
                        <div class="col12" style="text-align: center;">

                            <div class="col4" style="display: inline-block;">
                                <button type="button" onclick="cerrarModal('modalPlazos');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">ATRAS</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="solicitudLightbox hide" style="overflow: scroll;" id="modalGarantias">
        <div class="overlay"></div>
        <div class="visitaContainer">
            <div class="dashBordBox">
                <div class="loginForm gray font14">
                    <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">GARANTÍAS</p>
                    <div class="solicitudTitle autoMargin" style="width: 100%;">
                        <p class="center font14 lightGray">Garantías Para</p>
                    </div>
                    <div class="formContainer">
                        <div class="blueButton caracteristicasProducto">
                        </div>
                        <div class='col12' style='display:inline-block; position:relative;'>
                            <div class="col6 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1 center">CAMPOS</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Cantidad Maxima)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Cantidad Minima)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Descripción)</p>
                                    </div>
                                    <div class="paddingTop20">
                                        <p class="gray font14 fontWeight500 latterspacing1 center">(Tipo de Garantía)</p>
                                    </div>


                                </div>
                                <div class="col6 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1">TEXTO</p>
                                    </div>
                                    <div class="paddingTop15">
                                        <input class="block cameraBox width150 height30" type="text"  id="cantidadMaxima" name="cantidadMaxima" value="">
                                    </div>
                                    <div class="paddingTop5">
                                        <input class="block cameraBox width150 height30" type="text" id="cantidadMinima" name="cantidadMinima" value="">
                                    </div>
                                    <div class="paddingTop10">
                                        <textArea class="block cameraBox width150 height30" type="text" id="descripcionGarantia"  name="descripcionGarantia" value=""></textarea>
                    </div>
                    <div class="paddingTop10">
                        <select class="block cameraBox height30" style="width: 80%;"  id="tipoDeGarantiaId" name="tipoDeGarantiaId" value="">
                                            <g:each var="periodicidad" in="${la.kosmos.app.TipoDeGarantia.findAllWhere(activo:true)}">
                                <option value=${periodicidad.id}> ${periodicidad.nombre}</option>
                                            </g:each>
                        </select>
                    </div>
                </div>
            </div>    


                        <button type="button" id="sextoPaso" onclick="agregarGarantias();" class="loginButton blueButton letterspacing2 font14 pointer" style="width: 15%; position:absolute; bottom:10px; right:10px;">Agregar</button>
                    </div>
                    <div>
                        <table id='garantiasAgregadas' style="width: 100%; margin-bottom: 20px; border-bottom: 5px #369;">
                            <thead>
                                <tr>
                                    <th colspan="4" class="navyBg"><h1 class="graphHeading colorWhite letterspacing2 textUpper center">Garantías Agregadas</h1></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td colspan="2" class="tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom">No se han agregado Garantías</td>
                                </tr>
                            </tbody>
                        </table>
                        <input type="hidden" id="cantidadDeGarantias">
                        <input type="hidden" class="idRubroTemp3">
                        <input type="hidden" class="idProductoTemp">

                    </div>
                    <div class="col12" style="text-align: center;">
                        <div class="col4" style="display: inline-block;">
                            <button type="button" onclick="cerrarModal('modalGarantias');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">ATRAS</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>




<div class="solicitudLightbox hide" id="modalVistas">
        <div class="overlay"></div>
        <div class="visitaContainer">
            <div class="dashBordBox">
                <div class="loginForm gray font14">
                    <input type="hidden" name="solicitudId" value="4">
                    <p class="loginTitle font25 fontWeight500 darkBluetitle textUpper letterspacing1 center paddingAside15 marginBottom25">Documentos</p>
                    <div class="solicitudTitle autoMargin" style="width: 100%;">
                        <p class="center font14 lightGray nombreRubro">Asocia vistas a este Rubro</p>
                         <input type="text" id='idDivVistas' value=""/>
                    </div>
                    <div class="formContainer">
                        <div class="blueButton caracteristicasRubro">

                        </div>
                        <div class='col12' style='display:inline-block; position:relative;'>

                            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                                <p class="gray font14 fontWeight500 center paddingBottom20">SELECCIONA LAS VISTAS REQUERIDAS PARA ESTE RUBRO</p>
                                <div class="col12 floatLeft">
                                    <div class="paddingTop20">
                                        <p class="gray font12 fontWeight500 latterspacing1 center">VISTAS</p>
                                    </div>
                                    <div class="paddingTop20">

                                        <select class="js-example-basic-multiple" id="pasoCotizadorId" multiple="multiple" name="pasoCotizadorId" required>
                                            <g:each var="paso" in="${listaPasoCotizador}">

                                                <option value=${paso.id}>PASO ${paso.numeroDePaso} ${paso.tituloResumen} </option>
                                            </g:each>
                                            </select>
                                        </div>

                                </div>

                            </div>
                            <div class="col12 col12-mob floatLeft clearFix marginTop25 marginBottom25">
                                <div class="col12 floatLeft">

                                    <div class="col12" style="text-align: center;">
                                        <div class="col4" style="display: inline-block;">
                                            <button id="septimoPaso" type="button" onclick="agregarVistas();" class="loginButton blueButton letterspacing2 font14 pointer" style="width: 15%; position:absolute; bottom:-10px; right:10px;">Agregar</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <table id='vistasAgregadas' style="width: 100%; margin-bottom: 20px; border-bottom: 5px #369;">
                                <thead>
                                    <tr>
                                        <th colspan="2" class="navyBg"><h1 class="graphHeading colorWhite letterspacing2 textUpper center">Vistas Vinculadas</h1></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2" class="tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper center borderGrayBottom">No se han agregado Vistas</td>
                                    </tr>
                                </tbody>
                            </table>
                            <input type="hidden" id="cantidadDeVistas">
                            <input type="hidden" class="idRubroTemp" id="idRubroTemp">

                        </div>
                        <div class="col12" style="text-align: center;">

                            <div class="col4" style="display: inline-block;">
                                <button type="button" onclick="cerrarModal('modalVistas');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">ATRAS</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<div id="detalleIconos"></div>
<div id="vistaPreviaConfiguracionCotizador"> </div>
    </g:form>



 <script>
    $.getPasosCotizador = "/dashboard/getPasosCotizador";

    $(document).ready(function () {
    var idPaginacion = "paginationPasosCotizador";

    $('#'+idPaginacion).on('click', 'a.page', function (event) {
    event.preventDefault();
    var page = $(this).data('page');
    getPasosCotizador(page,idPaginacion);
    });
    getPasosCotizador(1,idPaginacion);
    });

    function getPasosCotizador(page,idPaginacion) {
    $("#currentPagePasosCotizador").val(page);

    var filter = new Object();
    filter.page = page;

    $.ajax({
    type: "POST",
    dataType: "json",
    url: $.getPasosCotizador,
    data: JSON.stringify(filter),
    contentType: "application/json",
    success: function (response) {
    var page = response.page;
    var totalPages = response.totalPages;
    $('#'+idPaginacion).empty();
    $("#listaPasosCotizador").empty();
    console.log(response.pasosCotizador);
            if (response.pasosCotizador.length > 0) {
    pagination(totalPages, page,idPaginacion);
    mostrarPasosCotizadorPaginados(response.pasosCotizador);
    } else {
    var row = "";
                row += '<tr></tr>';
                row += '<tr>';
                row += '<td colspan="5" class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">';
                row += '<span class="font14 tableDescriptionColor">No hay Pasos Registrados</span>';
                row += '</td>';
                row += '</tr>';
    $("#listaPasosSolicitud").append(row);
    }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {

    }
    });
    }
</script>