<div class="idLightbox hide" id="modalDetalleProducto">  
    <div class="overlay"></div>
    <div class="visitaContainer">

        <div class="dashBordBox">
            <div class="loginForm gray font14" class="tabcontent">
                <form id="productoForm" class="loginForm gray font14">

                    <div class="formContainer">
                        <div class='col12' style='display:inline-block; position:relative;'>

                            <div class="width990 autoMargin solicitudWhiteBox clearFix paddingBottom20">
                                <input  name="idProducto" id="idProducto" type="hidden" value="${producto?.id}"/>
                                <input type="hidden" id="opcionRubro" value="5">
                                <input type="hidden" id="tipoDeDocumento">
                                <div class="clearFix width990 autoMargin borderGrayBottom">
                                    <center><h1 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">DETALLE DEL PRODUCTO: ${producto.nombreDelProducto.toUpperCase()}</h1></center>
                                </div>
                                <div class="clearFix width990 autoMargin ">
                                    <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20 marginTop10">Generales</h2></center>
                                </div>
                                <div class="col6 floatLeft marginTop20">
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Nombre del Producto</strong></p>
                                        </div>
                                        <div class="col6 floatLeft">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="nombreDelProducto" value="${producto.nombreDelProducto}">
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Titulo en Cotizador</strong></p>
                                        </div>
                                        <div class="col6 floatLeft">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="tituloEnCotizador" value="${producto.tituloEnCotizador}">
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Clave de Producto</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="claveDeProducto" value="${producto.claveDeProducto}"/>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Descripción</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="descripcion" value="${producto.descripcion}"/>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Icono</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input onfocus="verListaIconos('detalleIconosDetalleProducto','claseIconoPasoDetalleProducto');" class="block cameraBox width150 height30" type="text" id="claseIconoPasoDetalleProducto" name="claseIconoPaso" value="${producto.claseIconoPaso}">

                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Monto Maximo</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="montoMaximo" value="${producto.montoMaximo}"/>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Monto Minimo</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="montoMinimo" value="${producto.montoMinimo}"/>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Tasa de Interes</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="tasaDeInteres" value="${producto.tasaDeInteres}"/>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Cat</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <input type="text" class="block cameraBox col11 height30 radius2 font14 lightGray" style="text-align: center;" name="cat" value="${producto.cat}"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col6 floatLeft marginTop20">
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Esquema</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">

                                            <select class="js-example-basic-multiple" name="esquemaId">
                                                <g:each var="esquema" in="${la.kosmos.app.Marca.findAll()}">
                                                    <option value=${esquema.id}  >${esquema.nombre}</option>
                                                </g:each>
                                            </select>    

                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Marca</strong></p>
                                        </div>
                                         <div class="col6 floatLeft paddingTop8">

                                            <select class="js-example-basic-multiple" name="marcaId">
                                                <g:each var="marca" in="${la.kosmos.app.Marca.findAll()}">
                                                    <option value=${marca.id}  >${marca.nombre}</option>
                                                </g:each>
                                            </select>    

                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Tipo de Producto</strong></p>
                                        </div>
                                         <div class="col6 floatLeft paddingTop8">

                                            <select class="js-example-basic-multiple" name="tipoDeProductoId">
                                                <g:each var="tipoDeProducto" in="${la.kosmos.app.TipoDeProducto.findAll()}">
                                                    <option value=${tipoDeProducto.id}  >${tipoDeProducto.nombre}</option>
                                                </g:each>
                                            </select>    

                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Tipo de Tasa de Interes</strong></p>
                                        </div>
                                           <div class="col6 floatLeft paddingTop8">

                                            <select class="js-example-basic-multiple" name="tipoDeTasaDeInteresId">
                                                <g:each var="tipoDeTasaDeInteres" in="${la.kosmos.app.TipoDeTasaDeInteres.findAll()}">
                                                    <option value=${tipoDeTasaDeInteres.id}  >${tipoDeTasaDeInteres.nombre}</option>
                                                </g:each>
                                            </select>    

                                        </div>
                                    </div>


                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 marginLeft20"><strong>Imagen</strong></p>
                                        </div>
                                        <div class="col6 floatLeft">
                                            <section class="container marginBottom20">
                                                <div class="solicitudBox width480 autoMargin radius2 ">
                                                    <div id="urlImagenRubro" class="clearFix paddingTop15 paddingBottom15 paddingLeft25 paddingRight25">
                                                        <div  class="rubro-bg" style="background-image: url('data:image/${rutaImagenDefault?.extension};base64,${rutaImagenDefault?.base64}');">
                                                        </div>
                                                        <div id="divDropzone2"  class="floatLeft subirImagen">
                                                            <a id="subirImagenProducto" title="BUSCAR EN MI PC" class="block whiteBox center gray2 paddingTop15 paddingBottom15 paddingLeft80 paddingRight80 pointer">BUSCAR EN MI PC</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Uso en Perfilador</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                            <g:if test="${producto.usoEnPerfilador == true}">
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>Si</label>
                                                        <input class="block cameraBox width150 height30 "  type="radio"  name="usoEnPerfilador"  value="true" checked>

                                                    </div>
                                                </div>
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>No</label>
                                                        <input class="block cameraBox width150 height30 " type="radio"  name="usoEnPerfilador"  value="false">

                                                    </div>

                                                </div>
                                            </g:if>

                                            <g:if test="${producto.usoEnPerfilador == false}">
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>Si</label>
                                                        <input class="block cameraBox width150 height30 "  type="radio"  name="usoEnPerfilador"  value="true" >

                                                    </div>
                                                </div>
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>No</label>
                                                        <input class="block cameraBox width150 height30 " type="radio"  name="usoEnPerfilador"  value="false" checked>
                                                    </div>
                                                </div>
                                            </g:if>

                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Segundo Crédito</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                        <g:if test="${producto.segundoCredito == true}">
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>Si</label>
                                                        <input class="block cameraBox width150 height30 "  type="radio"  name="segundoCredito"  value="true" checked>

                                                    </div>
                                                </div>
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>No</label>
                                                        <input class="block cameraBox width150 height30 " type="radio"  name="segundoCredito"  value="false">

                                                    </div>

                                                </div>
                                            </g:if>

                                            <g:if test="${producto.segundoCredito == false}">
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>Si</label>
                                                        <input class="block cameraBox width150 height30 "  type="radio"  name="segundoCredito"  value="true" >

                                                    </div>
                                                </div>
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>No</label>
                                                        <input class="block cameraBox width150 height30 " type="radio"  name="segundoCredito"  value="false" checked>

                                                    </div>

                                                </div>
                                            </g:if>
                                        </div>
                                    </div>
                                    <div class="col12 floatLeft">
                                        <div class="col6 floatLeft">
                                            <p class="gray font14 fontWeight500 letterspacing1 paddingTop15 marginLeft20"><strong>Activo</strong></p>
                                        </div>
                                        <div class="col6 floatLeft paddingTop8">
                                        <g:if test="${producto.activo == true}">
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>Si</label>
                                                        <input class="block cameraBox width150 height30 "  type="radio"  name="activo"  value="true" checked>

                                                    </div>
                                                </div>
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>No</label>
                                                        <input class="block cameraBox width150 height30 " type="radio"  name="activo"  value="false">

                                                    </div>

                                                </div>
                                            </g:if>

                                            <g:if test="${producto.activo == false}">
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>Si</label>
                                                        <input class="block cameraBox width150 height30 "  type="radio"  name="activo"  value="true" >

                                                    </div>
                                                </div>
                                                <div class="col2 floatLeft">
                                                    <div class="paddingTop15">
                                                        <label>No</label>
                                                        <input class="block cameraBox width150 height30 " type="radio"  name="activo"  value="false" checked>

                                                    </div>

                                                </div>
                                            </g:if>
                                        </div>
                                    </div>
                                </div>
                                <br /> &nbsp;
                                <div class="clearFix width990 autoMargin ">
                                    <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20">Documentos Asociados al Producto</h2></center>
                                </div>
                                <div class="col12 floatLeft">
                                    <table id='documentoProductoAgregado' class="applicationContainers solicitudes_table dashboard" >
                                        <thead>
                                            <tr>
                                                <th  colspan="5" class="navyBg">
                                                    <h1 class="graphHeading colorWhite letterspacing2 textUpper center">Documentos Vinculados</h1>
                                                    <button type="button" onclick="altaDocumentoProducto(${producto.id});"class="blueButton marginLeft20 pointer floatRight" style="width:20%; margin-bottom:auto ;" >Agregar Documento</button>
                                                </th>
                                            </tr>
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Obligatorio</th>
                                                <th>Activo</th>
                                                <th>Modificar</th>
                                                <th>Eliminar</th>
                                            </tr>
                                        </thead>   
                                        <tbody>
                                            <g:each var="documentos" in="${documentosProducto}">
                                                <tr><td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${documentos.tipoDeDocumento.nombre}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${documentos.obligatorio}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper"> ${documentos.activo}</td>
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper"><button type="button" onclick="modificarDocumentoProducto(${documentos.id});"class="blueButton  pointer" style="width:100% ;height:20% ;" >Modificar</button></td>
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" ><button type="button" onclick="eliminarDocumentoProducto(${documentos.id});"class="blueButton  pointer" style="width:100% ; height:20% ;" >Eliminar</button></td>
                                                </tr>
                                            </g:each>

                                        </tbody>
                                    </table>
                                </div>
                                <br /> &nbsp;
                                <div class="clearFix width990 autoMargin ">
                                    <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20">Plazos Asociados al Producto</h2></center>
                                </div>
                                <div class="col12 floatLeft">
                                    <table id="plazoProductoAgregado" class="applicationContainers solicitudes_table dashboard" >
                                        <thead>
                                            <tr>
                                                <th  colspan="9" class="navyBg"><h1 class="graphHeading colorWhite letterspacing2 textUpper center">Plazos Vinculados</h1>
                                                    <button type="button" onclick="altaPlazoProducto(${producto.id});"class="blueButton marginLeft20 pointer floatRight" style="width:20%; margin-bottom:auto ;" >Agregar Plazo</button>
                                                </th>
                                            </tr>
                                            <tr>
                                                <th>Importe Maximo</th>
                                                <th>Importe Minimo</th>
                                                <th>Periodicidad</th>
                                                <th>Plazo Maximo</th>
                                                <th>Plazo Minimo</th>
                                                <th>Salto</th>
                                                <th>Plazos Permitidos</th>

                                                <th>Modificar</th>
                                                <th>Eliminar</th>
                                            </tr>
                                        </thead>   
                                        <tbody>
                                            <g:each var="plazo" in="${plazoProducto}">
                                                <tr>
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">${plazo.importeMaximo}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${plazo.importeMinimo}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${plazo.periodicidad.nombre}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${plazo.plazoMaximo}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${plazo.plazoMinimo}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" > ${plazo.saltoSlider}</td>
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" > ${plazo.plazosPermitidos}</td>
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" ><button type="button" onclick="modificarPlazoProducto(${plazo.id});"class="blueButton  pointer" style="width:100% ;height:20% ;" >Modificar</button></td>
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper"><button type="button" onclick="eliminarPlazoProducto(${plazo.id});"class="blueButton  pointer" style="width:100% ; height:20% ;" >Eliminar</button></td>
                                                </tr>
                                            </g:each>

                                        </tbody>
                                    </table>
                                </div>
                                <br /> &nbsp;
                                <div class="clearFix width990 autoMargin ">
                                    <center><h2 class="font18 fontWeight600 darkBluetitle marginLeft20">Limites Plazo</h2></center>
                                </div>
                                <div class="col12 floatLeft">
                                    <table id="limitePlazoProductoAgregado"class="applicationContainers solicitudes_table dashboard">
                                        <thead>
                                            <tr>
                                                <th  colspan="6" class="navyBg"><h1 class="graphHeading colorWhite letterspacing2 textUpper center">Limites Plazo</h1>
                                                    <button type="button" onclick="altaLimitePlazoProducto(${producto.id});"class="blueButton marginLeft20 pointer floatRight" style="width:20%; margin-bottom:auto ;" >Agregar Limite</button>

                                                </th>

                                            </tr>
                                            <tr>
                                                <th>Limite Maximo</th>
                                                <th>Limite Minimo</th>
                                                <th>Periodicidad</th>
                                                <th>Plazo </th>
                                                <th>Modificar</th>
                                                <th>Eliminar</th>
                                            </tr>
                                        </thead>   
                                        <tbody>
                                            <g:each var="limitePlazo" in="${limitePlazoProducto}">
                                                <tr>
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">${limitePlazo.limiteMaximo}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${limitePlazo.limiteMinimo}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${limitePlazo.periodicidad.nombre}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${limitePlazo.plazo}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" ><button type="button" onclick="modificarLimiteProducto(${limitePlazo.id});"class="blueButton  pointer" style="width:100% ;height:20% ;" >Modificar</button></td>
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" > <button type="button" onclick="eliminarLimiteProducto(${limitePlazo.id});"class="blueButton  pointer" style="width:100% ; height:20% ;" >Eliminar</button></td>
                                                </tr>
                                            </g:each>

                                        </tbody>
                                    </table>




                                </div>
                               
                                <br /> &nbsp;
                  
                                <div class="col12 floatLeft">
                                    <table id="garantiaProductoAgregado"class="applicationContainers solicitudes_table dashboard">
                                        <thead>
                                            <tr>
                                                <th  colspan="6" class="navyBg"><h1 class="graphHeading colorWhite letterspacing2 textUpper center">Garantías del Producto</h1>
                                                    <button type="button" onclick="altaGarantiaProducto(${producto.id});"class="blueButton marginLeft20 pointer floatRight" style="width:20%; margin-bottom:auto ;" >Agregar Garantía</button>

                                                </th>
                                            </tr>
                                            <tr>
                                                <th>Cantidad Maxima</th>
                                                <th>Cantidad Minima</th>
                                                <th>Descripcion</th>
                                                <th>Tipo de Garantía </th>
                                                <th>Modificar</th>
                                                <th>Eliminar</th>
                                            </tr>
                                        </thead>   
                                        <tbody>
                                            <g:each var="garantiaProducto" in="${garantiasProducto}">
                                                <tr>
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">${garantiaProducto.cantidadMaxima}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${garantiaProducto.cantidadMinima}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${garantiaProducto.descripcion}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" >${garantiaProducto.tipoDeGarantia.nombre}</td> 
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" ><button type="button" onclick="modificarGarantiaProducto(${garantiaProducto.id});"class="blueButton  pointer" style="width:100% ;height:20% ;" >Modificar</button></td>
                                                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper" > <button type="button" onclick="eliminarGarantiaProducto(${garantiaProducto.id});"class="blueButton  pointer" style="width:100% ; height:20% ;" >Eliminar</button></td>
                                                </tr>
                                            </g:each>
                                        </tbody>
                                    </table>

                                </div>

                            </div>
                        </div>
                        <div class="formContainer">
                            <button type="button" onclick="actualizarProducto();" class="loginButton blueButton letterspacing2 font14 pointer">ACTUALIZAR</button>
                            <button type="button" onclick="cerrarModal('modalDetalleProducto');" class="loginButton letterspacing2 font14 pointer" style="background-image: #ffffff;box-shadow: 0 6px 9px 0 rgba(219, 220, 232, 0.5);">CANCELAR</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

</div>
<div id="detalleIconosDetalleProducto"></div>    
<div id="altaDocumentoProducto"></div>
<div id="detalleDocumentoProducto"></div>
<div id="detallePlazoProducto"></div>
<div id="detalleLimitePlazoProducto"></div>
<div id="altaPlazoProducto"></div>
<div id="altaLimitePlazoProducto"></div>
<div id='altaGarantiaProducto'></div>
<div id="detalleGarantiaProducto"></div>
