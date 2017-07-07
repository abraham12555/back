<g:if test="listaTipoDeCampo">
    <g:each var="lista" in="${listaTipoDeCampo}">
        <section class="container marginBottom20">
            <div class="width990 solicitudBox autoMargin radius2">
                <div class="clearFix">
                    
                    <div class="col1fifth col6-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>Elemento de Entrada</strong></p>
                            <p class="font14 gray2">${lista.toString()}</p>
                        </div>
                    </div>
                     <div class="col1fifth col6-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>Expresion regular</strong></p>
                            <p class="font14 gray2">${lista.expresionRegular}</p>
                        </div>
                    </div>
                     <div class="col1fifth col6-tab col12-mob floatLeft">
                        <div class="borderGrayRight removeMobile marginLeft30 paddingTop15 paddingBottom10">
                            <p class="font12 gray2 marginBottom5"><strong>Requiere Validacion</strong></p>
                            <p class="font14 gray2">${lista.requiereValidacion}</p>
                        </div>
                    </div>
                   
                    <div class="col1fifth col12-tab col12-mob floatLeft">
                        <div class="marginTop10 marginBottom10 clearFix paddingAside10">
                            <a title="Editar" onclick="editarTipoDeCampo(${lista.id});" class="tabNoFloat floatRight marginLeft20 block width115 blueButton center radius4 paddingTop10 paddingBottom10 pointer">EDITAR</a>
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
<g:render template="configuracion/tipoDeCampo/altaTipoDeCampo"/>
<div id="detalleTipoDeCampo"></div>

<section class="container marginBottom50 marginTop50">
    <div class="width480 autoMargin solicitudBox">

        <div class="autoMargin">
            <div class="center">
                <div class="paginateButtons">
                    <g:paginate class="pageSelected" next="Siguiente" prev="Anterior" 
                        max="5" controller="dashboard" action="index" total="20" />
                </div>
            </div>
        </div>
    </div>
</section>