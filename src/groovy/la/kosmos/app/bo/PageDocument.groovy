/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.bo

/**
 *
 * @author elizabeth
 */
class PageDocument {
    Integer pagina
    String contenidoBase64

    PageDocument(){

    }

    PageDocument(Integer pagina, String contenidoBase64){
        this.pagina = pagina
        this.contenidoBase64 = contenidoBase64
    }
}

