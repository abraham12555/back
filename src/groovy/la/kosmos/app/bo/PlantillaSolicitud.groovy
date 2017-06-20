/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.bo

import la.kosmos.app.SolicitudDeCredito
import la.kosmos.app.SolicitudTemporal
import java.lang.ArrayIndexOutOfBoundsException

/**
 *
 * @author elizabeth
 */
class PlantillaSolicitud {
    def nombreCliente
    def fechaSolicitud
    def entidadFinanciera
    def folio

    PlantillaSolicitud () {}

    PlantillaSolicitud(SolicitudTemporal solicitudTemporal){
        this.nombreCliente = formatName((solicitudTemporal.nombreDelCliente).trim())
        this.fechaSolicitud = solicitudTemporal.fechaDeSolicitud
        this.entidadFinanciera = solicitudTemporal.entidadFinanciera.nombre
        this.folio = solicitudTemporal.folio
    }

    PlantillaSolicitud(SolicitudDeCredito solicitudCredito){
        this.nombreCliente = formatName((solicitudCredito.cliente.nombre).trim()
            + " " + (solicitudCredito.cliente.apellidoPaterno).trim()
            + " " + (solicitudCredito.cliente.apellidoMaterno).trim())
        this.fechaSolicitud = solicitudCredito.fechaDeSolicitud
        this.entidadFinanciera = solicitudCredito.entidadFinanciera.nombre
        this.folio = solicitudCredito.folio
    }

    private String formatName(def source) {
        try {
            def name = source.toLowerCase()

            StringBuffer res = new StringBuffer();

            String[] strArr = name.split(" ");
            for (String str : strArr) {
                char[] stringArray = str.trim().toCharArray();
                stringArray[0] = Character.toUpperCase(stringArray[0]);
                str = new String(stringArray);

                res.append(str).append(" ");
            }

            return res.toString().trim()
        } catch(ArrayIndexOutOfBoundsException ex){
            return source;
        }
    }
}