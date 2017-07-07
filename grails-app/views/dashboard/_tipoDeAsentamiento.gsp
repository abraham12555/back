<g:if test="listaDeUsuarios">
    <g:each var="lista" in="${listaTipoDeAsentamiento}">
        <section class="container marginBottom20">
            <div class="width990 solicitudBox autoMargin radius2">
                <div class="clearFix">
                    
                    <div class="col1fifth col6-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>NOMBRE</strong></p>
                            <p class="font14 gray2">${lista.toString()}</p>
                        </div>
                    </div>
                   
                    <div class="col1fifth col12-tab col12-mob floatLeft">
                        <div class="marginTop10 marginBottom10 clearFix paddingAside10">
                            <a title="Editar" onclick="editarTipoDeAsentamiento(${lista.id});" class="tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer">EDITAR</a>
                        </div>
                    </div>
                    <div class="col1fifth col12-tab col12-mob floatLeft">
                        <div class="marginTop10 marginBottom10 clearFix paddingAside10">
                            <a title="Editar" onclick="eliminarTipoDeAsentamiento(${lista.id});" class="tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer">ELIMINAR</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </g:each>
  </g:if>
<g:else>
    <section class="container marginBottom20">
        <div class="width990 solicitudBox autoMargin radius2">
            <div class="clearFix">
                <div class="col1fifth col6-tab col12-mob floatLeft">
                    <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                        <p class="font12 gray2 marginBottom5">NO HAY USUARIOS REGISTRADOS</p>
                    </div>
                </div>
            </div>
        </div>s
    </section>
</g:else>
<g:render template="configuracion/tipoDeAsentamiento/altaTipoDeAsentamiento"/>
<div id="detalleTipoDeAsentamiento"></div>