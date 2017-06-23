/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.bo

import java.lang.ArrayIndexOutOfBoundsException
import java.text.DecimalFormat
import java.text.NumberFormat
import la.kosmos.app.ProductoSolicitud
import la.kosmos.app.SolicitudDeCredito
import la.kosmos.app.SolicitudTemporal

/**
 *
 * @author elizabeth
 */
class PlantillaSolicitud {
    def nombreCliente
    def fechaSolicitud
    def entidadFinanciera
    def folio
    def shortUrl
    def montoCredito
    def producto
    def sucursal //SolicitudDeCredito

    PlantillaSolicitud () {}

    PlantillaSolicitud(SolicitudTemporal solicitudTemporal){
        this.nombreCliente = formatName((solicitudTemporal.nombreDelCliente).trim())
        this.fechaSolicitud = solicitudTemporal.fechaDeSolicitud
        this.entidadFinanciera = solicitudTemporal.entidadFinanciera.nombre
        this.folio = solicitudTemporal.folio
        this.shortUrl = solicitudTemporal.shortUrl
        this.montoCredito = this.format(solicitudTemporal.montoDelCredito)
        this.producto = solicitudTemporal.producto.nombreDelProducto
    }

    PlantillaSolicitud(SolicitudDeCredito solicitudCredito){
        this.nombreCliente = formatName((solicitudCredito.cliente.nombre).trim()
            + " " + (solicitudCredito.cliente.apellidoPaterno).trim()
            + " " + (solicitudCredito.cliente.apellidoMaterno).trim())
        this.fechaSolicitud = solicitudCredito.fechaDeSolicitud
        this.entidadFinanciera = solicitudCredito.entidadFinanciera.nombre
        this.folio = solicitudCredito.folio
        this.shortUrl = solicitudCredito.shortUrl
        this.sucursal = (solicitudCredito.sucursal != null) ? solicitudCredito.sucursal.nombre : ""
    }

    PlantillaSolicitud(ProductoSolicitud ps){
        this.nombreCliente = formatName((ps.solicitud.cliente.nombre).trim()
            + " " + (ps.solicitud.cliente.apellidoPaterno).trim()
            + " " + (ps.solicitud.cliente.apellidoMaterno).trim())
        this.fechaSolicitud = ps.solicitud.fechaDeSolicitud
        this.entidadFinanciera = ps.solicitud.entidadFinanciera.nombre
        this.folio = ps.solicitud.folio
        this.shortUrl = ps.solicitud.shortUrl
        this.montoCredito = this.format(ps.montoDelCredito)
        this.producto = ps.producto.nombreDelProducto
        this.sucursal = (ps.solicitud.sucursal != null) ? ps.solicitud.sucursal.nombre : ""
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

    private String format(monto){
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(monto)
    }
}