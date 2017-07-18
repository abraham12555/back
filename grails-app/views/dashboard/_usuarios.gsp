<section class="container paddingBottom38">
    <div class="div-message div-message-danger center hide" id="error-users-div"></div>
    <table class="applicationContainers solicitudes_table dashboard" id="usuarios-tb">
        <thead>
        <th colspan="6" class="left navyBg">
            <h1 class="graphHeading colorWhite letterspacing2 textUpper">usuarios</h1>
        </th>
        </thead>
        <tbody>
        </tbody>
    </table>
</section>
<section class="container">
    <div class="width480 autoMargin solicitudBox marginBottom84">
        <div class="autoMargin">
            <input type="hidden" id="currentPage"/>
            <ul class="clearFix" id="pagination">
            </ul>
        </div>
    </div>
</section>
<g:render template="configuracion/usuario/altaDeUsuario"/>
<g:render template="configuracion/usuario/autenticacionUsuario"/>