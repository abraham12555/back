<section class="container marginBottom50 ">

    <table class="applicationContainers contactoClientes_table width990 autoMargin">
       
        
        <g:if test="${contactoClientes}">
            <g:each var='cliente' in='${contactoClientes}'>
                
            </g:each>
             
        </g:if>
            
        <g:else>
            <tr>
                <td colspan="9"  class="left tableTitleColor font12 paddingTop12 paddingRight12 paddingBottom5 paddingLeft10 textUpper">
                   
                </td>
            </tr>
        </g:else>
    </table>
  
<div class="col4 floatLeft">
    <center>
        <g:link action="descargarContactos" >
            <button type="submit" class="displayInline blueButton font16 fontweight300  paddingTop10 paddingBottom10 paddingLeft20 paddingRight20 pointer">Descargar Contactos</button>
        </g:link>
    </center>
</div>

  
</section>
