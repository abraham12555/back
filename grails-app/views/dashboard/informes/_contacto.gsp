<section class="container marginBottom50 ">
    <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
    <table class="applicationContainers solicitudes_table width990 autoMargin">
        <tr class="lightGrayBG">
            <td class="gray left">Medio de Contacto  <i class="fa fa-caret-down" aria-hidden="true"></i></td>
            <td class="gray left">Estatus <i class="fa fa-caret-down" aria-hidden="true"></i></td>
            <td class="gray left">Folios <i class="fa fa-caret-down" aria-hidden="true"></i></td>
            <td class="gray left">En %  <i class="fa fa-caret-down" aria-hidden="true"></i></td>
            
        </tr>
        <g:if test="${contacto}">
            <g:each name="contacto" in="${contacto}">
                <tr>
                    <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                         <span class="font14 textlower tableDescriptionColor">${it.medioContacto}  </span>
                    </td>
                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <span class="font14 textlower tableDescriptionColor">${it.dictamen_final}</span>
                    </td>
                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                       <span class="font14 textlower tableDescriptionColor">${it.folios}</span>
                    </td>
                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <span class="font14 textlower tableDescriptionColor">${it.porcentaje}</span>
                    </td>
                </tr>
            </g:each>
        </g:if>
        <g:else>
            <tr>
                <td colspan="9"  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                    <span class="font14 textlower tableDescriptionColor">No hay Registros</span>
                </td>
            </tr>
        </g:else>
    </table>
    </sec:ifAnyGranted>
</section>
