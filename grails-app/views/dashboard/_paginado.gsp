<g:if test="${cuantos}">
    <section class="container marginBottom50 marginTop50">
        <div class="width480 autoMargin solicitudBox">
            <div class="autoMargin">
                <div class="center">

                    <div class="paginateButtons displayInline font20 fontWeight500 darkBluetitle padding20 pointer">
                        <g:paginate class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer" next="Siguiente" prev="Anterior" 
                        max="1" controller="dashboard" action="solicitudes" total="${cuantos}" />
                    </div>
                </div>
            </div>
        </div>
    </section>
</g:if>


<g:if test="${cuantos2}">
    <section class="container marginBottom50 marginTop50">
        <div class="width990 autoMargin solicitudBox">

            <div class="autoMargin">
                <div class="center">
                    <div class="paginateButtons">


                        <a onclick="mostrarPaginado('opcSolicitudes','solicitudesTab','${div}','${tempo}',0,1);" title="Ver Solicitudes Dictaminadas"  class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Primera Pagina</a>

                        <% def a =0;  def i=1;  def cuantos= (Math.ceil(cuantos2))%> 
                        <% 
                        for(i = 1; i <=cuantos; i+=1) { %>
                        <a onclick="mostrarPaginado('opcSolicitudes','solicitudesTab','${div}','${tempo}',${a} ,1);" title="Ver Solicitudes Dictaminadas"  class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer"> ${i}   </a>
                        <% 
                                  a+=1
                            } 

                        a=a-1
                        %>

                        <a onclick="mostrarPaginado('opcSolicitudes','solicitudesTab','${div}','${tempo}',${a},1);" title="Ver Solicitudes Dictaminadas"  class="displayInline font20 fontWeight500 darkBluetitle padding20 pointer">Ultima Pagina </a>


                    </div>
                </div>
            </div>
        </div>
    </section>
</g:if>

