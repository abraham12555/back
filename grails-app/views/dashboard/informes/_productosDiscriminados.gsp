<section class="container marginBottom50 ">
    <sec:ifAnyGranted roles='ROLE_ADMIN, ROLE_ADMINISTRADOR, ROLE_DIRECTOR'>
    <table class="applicationContainers solicitudes_table width990 autoMargin">
        <tr class="lightGrayBG">
            <td class="gray left">Producto por Estatus  <i class="fa fa-caret-down" aria-hidden="true"></i></td>
            <td class="gray left">Folios <i class="fa fa-caret-down" aria-hidden="true"></i></td>
            <td class="gray left">En % <i class="fa fa-caret-down" aria-hidden="true"></i></td>
            <td class="gray left">Monto de Credito  <i class="fa fa-caret-down" aria-hidden="true"></i></td>
        </tr>
        <g:if test="${productosDiscriminados}">
            <g:each var='pD' in='${productosDiscriminados}'>
                <tr>
                    <td  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <span class="font14 textlower tableDescriptionColor">${pD.producto}  </span>
                    </td>
                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <span class="font14 textlower tableDescriptionColor">${pD.folio}</span>
                    </td>
                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <span class="font14 textlower tableDescriptionColor">${pD.porcentaje}</span>
                    </td>
                    <td class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                        <span class="font14 textlower tableDescriptionColor">${pD.credito}</span>
                    </td>
                    
                </tr>
            </g:each>
        </g:if>
        <g:else>
            <tr>
                <td colspan="9"  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                    <span class="font14 textlower tableDescriptionColor">No hay Registros ENcontrados </span>
                </td>
            </tr>
        </g:else>
    </table>
    </sec:ifAnyGranted>
</section>
