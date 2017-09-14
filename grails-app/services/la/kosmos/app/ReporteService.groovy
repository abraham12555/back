package la.kosmos.app

import grails.transaction.Transactional
import la.kosmos.rest.DossierSummary

import la.kosmos.rest.ClassificationResult
//import com.jameskleeh.excel.ExcelBuilder //+
import maxmoto1702.excel.ExcelBuilder
import org.apache.poi.ss.usermodel.CellStyle
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFFont
import spock.lang.*
import java.io.File

import grails.rest.Resource
import groovy.sql.Sql


@Transactional
class ReporteService {
    def dataSource
    def dashboardService
    
    def obtenerReporte(def tipo, def entidad, def finicio, def ffin ) {
        
        def db = new Sql(dataSource)
        def datosReporte = []
        
        if( tipo == 'solicitudes'){
            def query = "select * from reporte_solicitudes where to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi')  between to_timestamp('"+ finicio + " 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ ffin +" 23:59','dd/mm/yyyy hh24:mi')  order by to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') asc "
            def result = db.rows(query)
            result?.each{
                def datos =[:]
                datos.id = it.folio
                datos.fechaDeSolicitud = it.fecha_de_solicitud
                datos.tipo = it.tipo
                datos.origen = it.origen
                datos.status = it.status
                datos.medioDeContacto  = it.medio_de_contacto 
                datos.opcionDeContacto = it.opcion_de_contacto
                datos.nombreCliente = (it.nombre_cliente) ? String.valueOf(it.nombre_cliente) : ""
                datos.ultimoPaso = it.ultimo_paso
                datos.shortUrl = it.short_url
                datos.sucursal = it.sucursal
                datos.nombreDelProducto = it.nombre_del_producto
                datos.rubro = it.rubro
                datos.tipoDeDocumento = it.tipo_de_documento
                datos.enganche = it.enganche
                if(it.ha_tenido_atrasos == false){
                    datos.haTenidoAtrasos = "NO"
                }else if(it.ha_tenido_atrasos == true){
                    datos.haTenidoAtrasos = "SI"
                }
                datos.montoDelCredito = (it.monto_del_credito) ?: "" 
                datos.montoDelPago = (it.monto_del_pago) ?: "" 
                datos.montoDelSeguroDeDeuda = (it.monto_del_seguro_de_deuda) ?: "" 
                datos.montoPagoBuro = (it.monto_pago_buro)
                datos.plazos = (it.plazos) ?: ""
                datos.periodicidad = (it.periodicidad) ?: ""
                datos.fechaDeNacimiento = it.fecha_de_nacimiento
                datos.dependientesEconomicos = it.dependientes_economicos
                datos.estadoCivil = it.estado_civil
                datos.genero = it.genero
                datos.lugarDeNacimiento = it.lugar_de_nacimiento
                datos.nacionalidad = it.nacionalidad
                datos.rfc = it.rfc
                datos.curp = it.curp
                datos.regimenMatrimonial = it.regimen_matrimonial
                datos.nombreDelConyugue = it.nombre_del_conyugue
                datos.apellidoPaternoDelConyugue = it.apellido_paterno_del_conyugue
                datos.apellidoMaternoDelConyugue = it.apellido_materno_del_conyugue
                datos.fechaDeNacimientoDelConyugue = it.fecha_de_nacimiento_del_conyugue
                datos.lugarDeNacimientoDelConyugue = it.lugar_de_nacimiento_del_conyugue
                datos.nacionalidadDelConyugue = it.nacionalidad_del_conyugue
                datos.rfcDelConyugue = it.rfc_del_conyugue
                datos.curpDelConyugue = it.curp_del_conyugue
                datos.celular = it.celular
                datos.telefonoFijo = it.telefono_fijo
                datos.direccionDeCorreo = it.direccion_de_correo
                datos.calle = (it.calle) ?: ""
                datos.numeroExterior = it.numero_exterior
                datos.numeroInterior = it.numero_interior
                datos.municipio = it.municipio
                datos.estado = it.estado
                datos.codigoPostal = it.codigo_postal
                datos.colonia = it.colonia
                datos.tiempoDeEstadia = it.tiempo_de_estadia
                datos.tiempoDeVivienda = it.tiempo_de_vivienda
                datos.montoDeLaRenta = it.monto_de_la_renta
                datos.nombreDeLaEmpresa = it.nombre_de_la_empresa
                datos.ocupacion = it.ocupacion
                datos.profesion = it.profesion
                datos.fechaIngreso = it.fecha_ingreso
                datos.ingresosFijos = it.ingresos_fijos
                datos.ingresosVariables = it.ingresos_variables
                datos.consultaABuroEjecutada = it.consulta_a_buro_ejecutada
                datos.errorConsultaBuro = it.error_consulta_buro
                datos.dictamenCapacidadDePago = it.dictamen_capacidad_de_pago
                datos.dictamenConjunto = it.dictamen_conjunto
                datos.dictamenDePerfil = it.dictamen_de_perfil
                datos.dictamenDePoliticas = it.dictamen_de_politicas
                datos.dictamenFinal = it.dictamen_final
                datos.probabilidadDeMora = it.probabilidad_de_mora
                datos.razonDeCobertura = it.razon_de_cobertura
                datosReporte << datos
            }
            if(datosReporte.size() > 0 ){
                def workbook
                def builder = new ExcelBuilder()
                def plataforma = System.properties['os.name'].toLowerCase()
                def contenidoReporte = []
                def archivoDelReporte
                if(plataforma.contains('windows')){
                    archivoDelReporte = new File("C:/tmp/reporteGenerado_SolicitudesGeneradas.xlsx")
                } else {
                    archivoDelReporte = new File("/tmp/reporteGenerado_SolicitudesGeneradas.xlsx")
                }
                archivoDelReporte.createNewFile() 
                builder.config {
                    font('negrita') { font ->
                        font.bold = true
                    }
                    style('titulo') { cellStyle ->
                        cellStyle.font = font('negrita')
                        cellStyle.alignment = CellStyle.ALIGN_LEFT
                        cellStyle.verticalAlignment = CellStyle.VERTICAL_CENTER
                    }
                    style('contenido') { cellStyle ->
                        cellStyle.alignment = CellStyle.ALIGN_LEFT
                        cellStyle.verticalAlignment = CellStyle.VERTICAL_CENTER
                    }
                }
                workbook = builder.build {
                    sheet(name: "ReporteCentroDeContacto", widthColumns: [20,20,20,25,20,20,20,30,40,40,40,40,40,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,30,30,30,30,30,30,30,30,30,30,30,30]) {
                        row (style: 'titulo') {
                            cell { "FOLIO" }
                            cell { "FECHA DE SOLICITUD" }
                            cell { "ORIGEN DE SOLICITUD" } 
                            cell { "STATUS" }
                            cell { "MEDIO DE CONTACTO" }
                            cell { "OPCION DE CONTACTO" }
                            cell { "NOMBRE DEL CLIENTE" }
                            cell { "ULTIMO PASO" }
                            cell { "SHORT URL" }
                            cell { "SUCURSAL" }
                            cell { "EJECUTIVO QUE REGISTRÓ" }
                            cell { "NOMBRE DEL PRODUCTO" }
                            cell { "RUBRO" }
                            cell { "TIPO DE DOCUMENTO" }
                            cell { "ENGANCHE" }
                            cell { "HA TENIDO ATRASOS" }
                            cell { "MONTO DEL CREDITO" }
                            cell { "MONTO DEL PAGO" }
                            cell { "MONTO DEL SEGURO DE DEUDA" }
                            cell { "MONTO DEL PAGO DE BC" }
                            cell { "PLAZOS" }
                            cell { "PERIODICIDAD" }
                            cell { "FECHA DE NACIMIENTO" }
                            cell { "DEPENDIENTES ECONOMICOS" }
                            cell { "GENERO" }
                            cell { "LUGAR DE NACIMIENTO" }
                            cell { "ESTADO CIVIL" }
			    cell { "NACIONALIDAD" }
                            cell { "CURP" }
                            cell { "RFC" }
                            cell { "REGIMEN MATRIMONIAL" }
                            cell { "NOMBRE DEL CONYUGUE" }
                            cell { "APELLIDO PATERNO DEL CONYUGUE" }
                            cell { "APELLIDO MATERNO DEL CONYUGUE" }
                            cell { "FECHA DE NACIMIENTO DEL CONYUGUE" }
                            cell { "LUGAR DE NACIMIENTO DEL CONYUGUE" }
                            cell { "NACIONALIDAD DEL CONYUGUE" }
                            cell { "RFC DEL CONYUGUE" }
                            cell { "CURP DEL CONYUGUE" }
                            cell { "CELULAR" }
                            cell { "TELEFONO FIJO" }
                            cell { "DIRECCION DE CORREO" }
                            cell { "CALLE" }
                            cell { "NUMERO EXTERIOR" }
                            cell { "NUMERO INTERIOR" }
			    cell { "MUNICIPIO" }
                            cell { "ESTADO" }
                            cell { "CODIGO POSTAL" }
                            cell { "COLONIA" }
                            cell { "TIEMPO DE ESTADIA" }
                            cell { "TIEMPO DE VIVIENDA" }
                            cell { "MONTO DE LA RENTA" }
                            cell { "NOMBRE DE LA EMPRESA" }
                            cell { "OCUPACION" }
                            cell { "PROFESION" }
                            cell { "FECHA INGRESO" }
                            cell { "INGRESOS FIJOS" }
                            cell { "INGRESOS VARIABLES" }
                            cell { "CONSULTA A BURO EJECUTADA" }
                            cell { "ERROR CONSULTA BURO" }
                            cell { "DICTAMEN CAPACIDAD DEPAGO" }
                            cell { "DICTAMEN CONJUNTO" }
                            cell { "DICTAMEN DE PERFIL" }
                            cell { "DICTAMEN DE POLITICAS" }
                            cell { "DICTAMEN FINAL" }
                            cell { "PROBABILIDAD DE MORA" }
                            cell { "RAZON DE COBERTURA" }
                                
                        }
                        datosReporte.each { fila ->
                            row (style: 'contenido') {
                                cell { fila.id.trim() ?: "-" }
                                cell { fila.fechaDeSolicitud }
                                cell { fila.origen ?: ""}
                                cell { fila.status ?: ""} 
                                cell { fila.medioDeContacto ?: "" }
                                cell { fila.opcionDeContacto ?: ""} 
                                cell { fila.nombreCliente?.toUpperCase() } 
                                cell { fila.ultimoPaso } 
                                cell { fila.shortUrl ?: ""} 
                                cell { fila.sucursal ?: ""} 
                                cell { fila.usuario ?: ""} 
                                cell { fila.nombreDelProducto ?: ""} 
                                cell { fila.rubro ?: "" } 
                                cell { fila.tipoDeDocumento ?: ""}
                                cell { fila.enganche ?: ""} 
                                cell { fila.haTenidoAtrasos ?: ""} 
                                cell { fila.montoDelCredito }
                                cell { fila.montoDelPago ?: ""} 
                                cell { fila.montoDelSeguroDeDeuda ?: ""} 
                                cell { fila.montoPagoBuro ?: "" } 
                                cell { fila.plazos ?: "" }
                                cell { fila.periodicidad ?: ""} 
                                cell { fila.fechaDeNacimiento ?: "" } 
                                cell { fila.dependientesEconomicos ?: "" } 
				cell { fila.genero ?: ""}
                                cell { fila.lugarDeNacimiento ?: ""}
				cell { fila.estadoCivil ?: ""}
                                cell { fila.nacionalidad ?: ""} 
                                cell { fila.curp ?: ""} 
				cell { fila.rfc ?: "" } 
                                cell { fila.regimenMatrimonial ?: ""} 
                                cell { fila.nombreDelConyugue ? fila.nombreDelConyugue.toUpperCase(): ""} 
                                cell { fila.apellidoPaternoDelConyugue ? fila.apellidoPaternoDelConyugue.toUpperCase(): ""} 
                                cell { fila.apellidoMaternoDelConyugue ? fila.apellidoMaternoDelConyugue.toUpperCase(): "" } 
                                cell { fila.fechaDeNacimientoDelConyugue ?: ""} 
                                cell { fila.lugarDeNacimientoDelConyugue ?: ""} 
                                cell { fila.nacionalidadDelConyugue ?: "" }
                                cell { fila.rfcDelConyugue ?: "" } 
                                cell { fila.curpDelConyugue ?: ""} 
                                cell { fila.celular ? fila.celular.replaceAll("[^\\d]", "") : "" } 
                                cell { fila.telefonoFijo ? fila.telefonoFijo.replaceAll("[^\\d]", ""): "" }
                                cell { fila.direccionDeCorreo ?: ""}
                                cell { fila.calle ?: ""} 
                                cell { fila.numeroExterior ?: ""} 
                                cell { fila.numeroInterior ?: "" }
                                cell { fila.municipio ?: ""} 
                                cell { fila.estado ?: ""}
                                cell { fila.codigoPostal ?: ""}
                                cell { fila.colonia ?: ""} 
                                cell { fila.tiempoDeEstadia ?: ""} 
                                cell { fila.tiempoDeVivienda ?: ""} 
                                cell { fila.montoDeLaRenta ?: ""} 
                                cell { fila.nombreDeLaEmpresa ?: ""} 
                                cell { fila.ocupacion ?: ""} 
                                cell { fila.profesion ?: ""} 
                                cell { fila.fechaIngreso ?: "" } 
                                cell { fila.ingresosFijos ?: "" } 
                                cell { fila.ingresosVariables ?: ""} 
                                cell { fila.consultaABuroEjecutada ?: ""}
                                cell { fila.errorConsultaBuro ?: "" } 
                                cell { fila.dictamenCapacidadDePago ?: "" } 
                                cell { fila.dictamenConjunto ?: ""} 
                                cell { fila.dictamenDePerfil ?: ""} 
                                cell { fila.dictamenDePoliticas ?: ""} 
                                cell { fila.dictamenFinal ?: "" }
                                cell { fila.probabilidadDeMora ?: ""} 
                                cell { fila.razonDeCobertura ?: ""}
                            }
                        }
                    }
                }
                workbook.write(new FileOutputStream(archivoDelReporte))
                builder = null
                workbook.dispose();
                workbook = null
                return archivoDelReporte
            }
            

        } else if( tipo == 'mitek'){
            //REPORTE DE CONSULTAS MITEK 
            def result = db.rows("select * from consultas_mitek where dossier_summary_id_dossier_summary >= 1878 or classification_result_id_classificaction_result >= 1488 ") 
            result?.each {
                def datos = [:]
                datos.id = it.classification_result_id_classificaction_result  
                datos.classificationResultIdClassificactionResult = it.classification_result_id_classificaction_result  
                datos.classificationResultAddress = it.classification_result_address 
                datos.classificationResultAddress1 = it.classification_result_address1             
                datos.classificationResultAddress2 = it.classification_result_address2                 
                datos.classificationResultClave = it.classification_result_clave 
                datos.classificationResultCurp = it.classification_result_curp                
                datos.classificationResultDateOfBirth = it.classification_result_date_of_birth 
                datos.classificationResultDateOfExpiry = it.classification_result_date_of_expiry 
                datos.classificationResultDistrictCode = it.classification_result_district_code 
                datos.classificationResultDocumentId = it.classification_result_document_id   
                datos.classificationResultDossierId	= it.classification_result_dossier_id   
                datos.classificationResultGender = it.classification_result_gender 
                datos.classificationResultGivenname	= it.classification_result_givenname     
                datos.classificationResultIssuingYear = it.classification_result_issuing_year 
                datos.classificationResultName = it.classification_result_name                         
                datos.classificationResultParentNameFather = it.classification_result_parent_name_father 
                datos.classificationResultParentNameMother = it.classification_result_parent_name_mother 
                datos.classificationResultReference	= it.classification_result_reference  
                datos.classificationResultSolicitudId = it.classification_result_solicitud_id 
                datos.classificationResultSurname = it.classification_result_surname     
                datos.classificationResultYearOfExpiry = it.classification_result_year_of_expiry 
                datos.classificationResultFechaDeRespuesta  = it.classification_result_fecha_de_respuesta 
                datos.dossierSummaryIdDossierSummary = it.dossier_summary_id_dossier_summary 
                datos.dossierSummaryVersion	= it.dossier_summary_version 
                datos.dossierSummaryDossierId = it.dossier_summary_dossier_id
                datos.dossierSummaryNameOfCallback = it.dossier_summary_name_of_callback 
                datos.dossierSummarySolicitudId = it.dossier_summary_solicitud_id 
                datos.dossierSummarySource = it.dossier_summary_source
                datos.dossierSummaryStatus = it.dossier_summary_status
                datos.dossierSummaryReference = it.dossier_summary_reference
                datos.dossierSummaryFechaDeRespuesta = it.dossier_summary_fecha_de_respuesta
                datosReporte << datos 
            }
            if(datosReporte.size() > 0 ){
                def workbook
                def builder = new ExcelBuilder()
                def plataforma = System.properties['os.name'].toLowerCase()
                def contenidoReporte = []
                def archivoDelReporte
                if(plataforma.contains('windows')){
                    archivoDelReporte = new File("C:/tmp/reporteGenerado_ConsultasMitek.xlsx")
                } else {
                    archivoDelReporte = new File("/tmp/reporteGenerado_ConsultasMitek.xlsx")
                }
                archivoDelReporte.createNewFile() 
                builder.config {
                    font('negrita') { font ->
                        font.bold = true
                    }
                    style('titulo') { cellStyle ->
                        cellStyle.font = font('negrita')
                        cellStyle.alignment = CellStyle.ALIGN_LEFT
                        cellStyle.verticalAlignment = CellStyle.VERTICAL_CENTER
                    }
                    style('contenido') { cellStyle ->
                        cellStyle.alignment = CellStyle.ALIGN_LEFT
                        cellStyle.verticalAlignment = CellStyle.VERTICAL_CENTER
                    }
                }
                workbook = builder.build {
                    sheet(name: "ReporteConsultasMitek", widthColumns: [10,20,50,20,20,20,20,30,40,40,40,40,40,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20]) {
                        row (style: 'titulo') {
                            cell { "ID" }
                            cell { "CLASSIFICATION RESULT ID CLASSIFICACTION RESULT" }
                            cell { "CLASSIFICATION RESULT ADDRESS" }
                            cell { "CLASSIFICATION RESULT ADDRESS1" }
                            cell { "CLASSIFICATION RESULT ADDRESS2" }
                            cell { "CLASSIFICATION RESULT CLAVE" }
                            cell { "CLASSIFICATION RESULT CURP" }
                            cell { "CLASSIFICATION RESULT DATE OF BIRT" }
                            cell { "CLASSIFICATION RESULT DATE OF EXPIRY" }
                            cell { "CLASSIFICATION RESULT DISTRICT CODE" }
                            cell { "CLASSIFICATION RESULT DOCUMENT ID" }
                            cell { "CLASSIFICATION RESULT DOSSIER ID" }
                            cell { "CLASSIFICATION RESULT GENDER" }
                            cell { "CLASSIFICATION RESULT GIVEN NAME" }
                            cell { "CLASSIFICATION RESULT ISSUING YEAR" }
                            cell { "CLASSIFICATION RESULT NAME" }
                            cell { "CLASSIFICATION RESULT PARENT NAME FATHER" }
                            cell { "CLASSIFICATION RESULT PARENT NAME MOTHER" }
                            cell { "CLASSIFICATION RESULT REFERENCE" }
                            cell { "CLASSIFICATION RESULT SOLICITUD ID" }
                            cell { "CLASSIFICATION RESULT SURNAME" }
                            cell { "CLASSIFICATION RESULT YEAR OF EXPIRY" }
                            cell { "CLASSIFICATION RESULT FECHA DE RESPUESTA" }
                            cell { "DOSSIER SUMMARY ID DOSSIER SUMMARY" }
                            cell { "DOSSIER SUMMARY VERSION" }
                            cell { "DOSSIER SUMMARY DOSSIER ID" }
                            cell { "DOSSIER SUMMARY NAME OF CALLBACK" }
                            cell { "DOSSIER SUMMARY SOLICITUD ID" }
                            cell { "DOSSIER SUMMARY SOURCE" }
                            cell { "DOSSIER SUMMARY STATUS" }
                            cell { "DOSSIER SUMMARY REFERENCE" }
                           // cell { "DOSSIER SUMMARY FECHA DE RESPUESTA" }
                            
                        }
                        datosReporte.each { fila ->
                            row (style: 'contenido') {
                                cell { fila.id ?: ""}  
                                cell { fila.classificationResultIdClassificactionResult ?: ""}
                                cell { fila.classificationResultAddress ?: ""}
                                cell { fila.classificationResultAddress1 ?: ""}
                                cell { fila.classificationResultAddress2 ?: ""}
                                cell { fila.classificationResultClave ?: ""}
                                cell { fila.classificationResultCurp ?: ""}             
                                cell { fila.classificationResultDateOfBirth  ?: ""}
                                cell { fila.classificationResultDateOfExpiry ?: ""}
                                cell { fila.classificationResultDistrictCode ?: ""}
                                cell { fila.classificationResultDocumentId ?: ""}
                                cell { fila.classificationResultDossierId ?: ""} 
                                cell { fila.classificationResultGender ?: "" }
                                cell { fila.classificationResultGivenname ?: ""}
                                cell { fila.classificationResultIssuingYear ?: ""} 
                                cell { fila.classificationResultName ?: ""}                
                                cell { fila.classificationResultParentNameFather ?: "" }
                                cell { fila.classificationResultParentNameMother ?: ""}
                                cell { fila.classificationResultReference ?: ""}	
                                cell { fila.classificationResultSolicitudId ?: ""} 
                                cell { fila.classificationResultSurname ?: "" }
                                cell { fila.classificationResultYearOfExpiry ?: "" }
                                cell { fila.classificationResultFechaDeRespuesta ?: ""}
                                cell { fila.dossierSummaryIdDossierSummary ?: ""}
                                cell { fila.dossierSummaryVersion ?: ""}
                                cell { fila.dossierSummaryDossierId ?: ""} 
                                cell { fila.dossierSummaryNameOfCallback ?: "" }
                                cell { fila.dossierSummarySolicitudId ?: ""}
                                cell { fila.dossierSummarySource ?: ""}
                                cell { fila.dossierSummaryStatus ?: ""}
                                cell { fila.dossierSummaryReference ?: ""}
                               // cell { fila.dossierSummaryFechaDeRespuesta }
                            }
                        }
                    }
                }
                workbook.write(new FileOutputStream(archivoDelReporte))
                return archivoDelReporte
            }
            
        } else if( tipo == 'clientes'){
            def result = db.rows("select distinct on(id_cliente) id_cliente,cliente, numero_telefonico,direccion_de_correo, direccion,folio from contacto_clientes where entidad =  '"+entidad+"' order by 1 desc")
            result?.each {  
                def datos = [:]
                datos.id = it.id_cliente
                datos.cliente = it.cliente
                datos.celular = it.numero_telefonico
                datos.email = it.direccion_de_correo
                datos.direccion = it.direccion
                datos.folio = it.folio
                datosReporte << datos
            }
            if(datosReporte.size() > 0 ){
                def workbook
                def builder = new ExcelBuilder()
                def plataforma = System.properties['os.name'].toLowerCase()
                def contenidoReporte = []
                def archivoDelReporte
                if(plataforma.contains('windows')){
                    archivoDelReporte = new File("C:/tmp/reporteGenerado_Clientes.xlsx")
                } else {
                    archivoDelReporte = new File("/tmp/reporteGenerado_Clientes.xlsx")
                }
                archivoDelReporte.createNewFile() 
                builder.config {
                    font('negrita') { font ->
                        font.bold = true
                    }
                    style('titulo') { cellStyle ->
                        cellStyle.font = font('negrita')
                        cellStyle.alignment = CellStyle.ALIGN_LEFT
                        cellStyle.verticalAlignment = CellStyle.VERTICAL_CENTER
                    }
                    style('contenido') { cellStyle ->
                        cellStyle.alignment = CellStyle.ALIGN_LEFT
                        cellStyle.verticalAlignment = CellStyle.VERTICAL_CENTER
                    }
                }
                workbook = builder.build {
                sheet(name: "ReporteClientes", widthColumns: [80,20,30,100,20]) {
                    row (style: 'titulo') {
                        cell { "CLIENTE" }
                        cell { "TELEFONO CELULAR" }
                        cell { "EMAIL PERSONAL" }
                        cell { "DIRECCIÒN" }
                        cell { "FOLIO" }
                            
                    }
                    datosReporte.each { fila ->
                        row (style: 'contenido') {
                            cell { fila.cliente?.toUpperCase() }
                            cell { fila.celular ? fila.celular.replaceAll("[^\\d]", ""): "" }
                            cell { fila.email }
                            cell { fila.direccion }
                            cell { fila.folio ?: "-" }
                               
                        }
                    }
                }
            }
            workbook.write(new FileOutputStream(archivoDelReporte))
            builder = null
            workbook.dispose();
            workbook = null
            return archivoDelReporte
            }
        //
        }
        else if( tipo == 'operaciones'){
            def query = "select * from reporte_solicitudes where to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi')  between to_timestamp('"+ finicio + " 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ ffin +" 23:59','dd/mm/yyyy hh24:mi') order by to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') asc "
            def result = db.rows(query)
            result?.each{
                def datos =[:]
                datos.id = it.folio
                datos.fechaDeSolicitud = it.fecha_de_solicitud
                datos.tipo = it.tipo
                datos.origen = it.origen
                datos.status = it.status
                datos.medioDeContacto  = it.medio_de_contacto 
                datos.opcionDeContacto = it.opcion_de_contacto
                datos.nombreCliente = String.valueOf(it.nombre_cliente)
                datos.ultimoPaso = it.ultimo_paso
                datos.sucursal = it.sucursal
                datos.nombreDelProducto = it.nombre_del_producto
                datos.rubro = it.rubro
                datos.tipoDeDocumento = it.tipo_de_documento
                datos.enganche = it.enganche
                if(it.ha_tenido_atrasos == false){
                    datos.haTenidoAtrasos = "NO"
                }else if(it.ha_tenido_atrasos == true){
                    datos.haTenidoAtrasos = "SI"
                }
                datos.montoDelCredito = it.monto_del_credito
                datos.montoDelPago = it.monto_del_pago
                datos.montoDelSeguroDeDeuda = it.monto_del_seguro_de_deuda
                datos.plazos = it.plazos
                datos.periodicidad = it.periodicidad
                datos.fechaDeNacimiento = it.fecha_de_nacimiento
                datos.dependientesEconomicos = it.dependientes_economicos
                datos.genero = it.genero
                datos.lugarDeNacimiento = it.lugar_de_nacimiento
                datos.estadoCivil = it.estado_civil
                datos.nacionalidad = it.nacionalidad
                datos.regimenMatrimonial = it.regimen_matrimonial
                datos.fechaDeNacimientoDelConyugue = it.fecha_de_nacimiento_del_conyugue
                datos.lugarDeNacimientoDelConyugue = it.lugar_de_nacimiento_del_conyugue
                datos.nacionalidadDelConyugue = it.nacionalidad_del_conyugue
                datos.municipio = it.municipio
                datos.estado = it.estado
                datos.codigoPostal = it.codigo_postal
                datos.colonia = it.colonia
                datos.tiempoDeEstadia = it.tiempo_de_estadia
                datos.tiempoDeVivienda = it.tiempo_de_vivienda
                datos.montoDeLaRenta = it.monto_de_la_renta
                datos.nombreDeLaEmpresa = it.nombre_de_la_empresa
                datos.ocupacion = it.ocupacion
                datos.profesion = it.profesion
                datos.fechaIngreso = it.fecha_ingreso
                datos.ingresosFijos = it.ingresos_fijos
                datos.ingresosVariables = it.ingresos_variables
                datos.consultaABuroEjecutada = it.consulta_a_buro_ejecutada
                datos.errorConsultaBuro = it.error_consulta_buro
                datos.dictamenCapacidadDePago = it.dictamen_capacidad_de_pago
                datos.dictamenConjunto = it.dictamen_conjunto
                datos.dictamenDePerfil = it.dictamen_de_perfil
                datos.dictamenDePoliticas = it.dictamen_de_politicas
                datos.dictamenFinal = it.dictamen_final
                datos.probabilidadDeMora = it.probabilidad_de_mora
                datos.razonDeCobertura = it.razon_de_cobertura
                datos.log = it.log
                datos.respuesta = it.respuesta
                datosReporte << datos
            }
            if(datosReporte.size() > 0 ){
                def workbook
                def builder = new ExcelBuilder()
                def plataforma = System.properties['os.name'].toLowerCase()
                def contenidoReporte = []
                def archivoDelReporte
                if(plataforma.contains('windows')){
                    archivoDelReporte = new File("C:/tmp/reporteGenerado_Operaciones.xlsx")
                } else {
                    archivoDelReporte = new File("/tmp/reporteGenerado_Operaciones.xlsx")
                }
                archivoDelReporte.createNewFile() 
                builder.config {
                    font('negrita') { font ->
                        font.bold = true
                    }
                    style('titulo') { cellStyle ->
                        cellStyle.font = font('negrita')
                        cellStyle.alignment = CellStyle.ALIGN_LEFT
                        cellStyle.verticalAlignment = CellStyle.VERTICAL_CENTER
                    }
                    style('contenido') { cellStyle ->
                        cellStyle.alignment = CellStyle.ALIGN_LEFT
                        cellStyle.verticalAlignment = CellStyle.VERTICAL_CENTER
                    }
                }
                workbook = builder.build {
                    sheet(name: "ReporteOperaciones", widthColumns: [20,20,20,25,20,20,30,40,40,40,40,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,50]) {
                        row (style: 'titulo') {
                            cell { "FOLIO" }
                            cell { "FECHA DE SOLICITUD" }
                            cell { "ORIGEN DE SOLICITUD" } 
                            cell { "STATUS" }
                            cell { "MEDIO DE CONTACTO" }
                            cell { "OPCION DE CONTACTO" }
                            cell { "NOMBRE DEL CLIENTE" }
                            cell { "ULTIMO PASO" }
                            cell { "SUCURSAL" }
                            cell { "EJECUTIVO QUE REGISTRÓ" }
                            cell { "NOMBRE DEL PRODUCTO" }
                            cell { "RUBRO" }
                            cell { "TIPO DE DOCUMENTO" }
                            cell { "ENGANCHE" }
                            cell { "HA TENIDO ATRASOS" }
                            cell { "MONTO DEL CREDITO" }
                            cell { "MONTO DEL PAGO" }
                            cell { "MONTO DEL SEGURO DE DEUDA" }
                            cell { "MONTO DEL PAGO DE BC" }
                            cell { "PLAZOS" }
                            cell { "PERIODICIDAD" }
                            cell { "FECHA DE NACIMIENTO" }
                            cell { "DEPENDIENTES ECONOMICOS" }
                            cell { "GENERO" }
                            cell { "LUGAR DE NACIMIENTO" }
                            cell { "ESTADO CIVIL" }
			    cell { "NACIONALIDAD" }
                            cell { "REGIMEN MATRIMONIAL" }
                            cell { "FECHA DE NACIMIENTO DEL CONYUGUE" }
                            cell { "LUGAR DE NACIMIENTO DEL CONYUGUE" }
                            cell { "NACIONALIDAD DEL CONYUGUE" }
			    cell { "MUNICIPIO" }
                            cell { "ESTADO" }
                            cell { "CODIGO POSTAL" }
                            cell { "COLONIA" }
                            cell { "TIEMPO DE ESTADIA" }
                            cell { "TIEMPO DE VIVIENDA" }
                            cell { "MONTO DE LA RENTA" }
                            cell { "NOMBRE DE LA EMPRESA" }
                            cell { "OCUPACION" }
                            cell { "PROFESION" }
                            cell { "FECHA INGRESO" }
                            cell { "INGRESOS FIJOS" }
                            cell { "INGRESOS VARIABLES" }
                            cell { "CONSULTA A BURO EJECUTADA" }
                            cell { "ERROR CONSULTA BURO" }
                            cell { "DICTAMEN CAPACIDAD DE PAGO" }
                            cell { "DICTAMEN CONJUNTO" }
                            cell { "DICTAMEN DE PERFIL" }
                            cell { "DICTAMEN DE POLITICAS" }
                            cell { "DICTAMEN FINAL" }
                            cell { "PROBABILIDAD DE MORA" }
                            cell { "RAZON DE COBERTURA" }
                            cell { "LOG" }
			    cell { "RESPUESTA" }
                                
                        }
                        datosReporte.each { fila ->
                            row (style: 'contenido') {
                                cell { fila.id.trim() ?: "-" }
                                cell { fila.fechaDeSolicitud }
                                cell { fila.origen }
                                cell { fila.status } 
                                cell { fila.medioDeContacto ?: "" }
                                cell { fila.opcionDeContacto ?: ""} 
                                cell { fila.nombreCliente?.toUpperCase() } 
                                cell { fila.ultimoPaso } 
                                cell { fila.sucursal ?: ""} 
                                cell { fila.usuario ?: ""} 
                                cell { fila.nombreDelProducto ?: ""} 
                                cell { fila.rubro ?: "" } 
                                cell { fila.tipoDeDocumento ?: ""}
                                cell { fila.enganche ?: ""} 
                                cell { fila.haTenidoAtrasos ?: ""} 
                                cell { fila.montoDelCredito ?: ""}
                                cell { fila.montoDelPago ?: ""} 
                                cell { fila.montoDelSeguroDeDeuda ?: ""} 
                                cell { fila.montoPagoBuro ?: ""} 
                                cell { fila.plazos ?: ""}
                                cell { fila.periodicidad ?: ""} 
                                cell { fila.fechaDeNacimiento ?: "" } 
                                cell { fila.dependientesEconomicos ?: "" } 
				cell { fila.genero ?: ""}
                                cell { fila.lugarDeNacimiento ?: ""}
				cell { fila.estadoCivil ?: ""}
                                cell { fila.nacionalidad ?: ""} 
                                cell { fila.regimenMatrimonial ?: ""} 
                                cell { fila.fechaDeNacimientoDelConyugue ?: ""} 
                                cell { fila.lugarDeNacimientoDelConyugue ?: ""} 
                                cell { fila.nacionalidadDelConyugue ?: "" }
                                cell { fila.municipio ?: ""} 
                                cell { fila.estado ?: ""}
                                cell { fila.codigoPostal ?: ""}
                                cell { fila.colonia ?: ""} 
                                cell { fila.tiempoDeEstadia ?: ""} 
                                cell { fila.tiempoDeVivienda ?: ""} 
                                cell { fila.montoDeLaRenta ?: ""} 
                                cell { fila.nombreDeLaEmpresa ?: ""} 
                                cell { fila.ocupacion ?: ""} 
                                cell { fila.profesion ?: ""} 
                                cell { fila.fechaIngreso ?: "" } 
                                cell { fila.ingresosFijos ?: "" } 
                                cell { fila.ingresosVariables ?: ""} 
                                cell { fila.consultaABuroEjecutada ?: ""}
                                cell { fila.errorConsultaBuro ?: "" } 
                                cell { fila.dictamenCapacidadDePago ?: "" } 
                                cell { fila.dictamenConjunto ?: ""} 
                                cell { fila.dictamenDePerfil ?: ""} 
                                cell { fila.dictamenDePoliticas ?: ""} 
                                cell { fila.dictamenFinal ?: "" }
                                cell { fila.probabilidadDeMora ?: ""} 
                                cell { fila.razonDeCobertura ?: ""}
                                cell { fila.log ?: ""} 
                                cell { fila.respuesta ?: "" }
                            }
                        }
                    }
                }
                workbook.write(new FileOutputStream(archivoDelReporte))
                builder = null
                workbook.dispose();
                workbook = null
                return archivoDelReporte
            }
            

        }
       
    }
    
    def obtenerInformesAnalitica(def informe, def temporalidad, def fechaInicio, def fechaFinal ,def entidad ){
        def respuesta = [:]
        def query
        def qf
        if(temporalidad){
            switch(temporalidad){
            case 1:
                def fechaFormateada = (new Date()).format('dd/MM/yyyy')
                fechaInicio = (new Date()).format('dd/MM/yyyy')
                fechaFinal = (new Date()).format('dd/MM/yyyy')
                break;
            case 7:
                def rango = dashboardService.getFechasSemanaActual()
                fechaInicio = rango.fechaInicio.format('dd/MM/yyyy')
                fechaFinal = rango.fechaFinal.format('dd/MM/yyyy')
                break;
            case 31:
                def rango = dashboardService.getFechasMesActual()
                fechaInicio = rango.fechaInicio.format('dd/MM/yyyy')
                fechaFinal = rango.fechaFinal.format('dd/MM/yyyy')
                break;
            case 365:
                def anio = Calendar.getInstance().get(Calendar.YEAR)
                fechaInicio = "01/01/" + anio
                fechaFinal = "31/12/" + anio
                break;
            default:
                println "Entrando a DEFAULT"
                break;
            }
        }
       /* def sqlf = new Sql(dataSource)
        qf = "select version, id_entidad_financiera,nombre from entidad_financiera where nombre = '"+entidad+"' "
        def ent_f = sqlf.rows(qf)
        def dato = [:]
        ent_f.each{
            
            dato.entidad = ent_f.id_entidad_financiera
        }*/
            /*def qr = "select id_entidad_financiera as id from entidad_financiera where nombre = '"+entidad+"' "
            def sqlc = new Sql(dataSource)
            qf = sqcl.rows(qr)
            def entidadf
            qf.each{
                entidadf = it.id
            }
            println "551  => " + entidadf*/
        if(informe == "usoCredito"){
            query = "select count(folio) as folios, rubro from reporte_solicitudes where rubro <> '' and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+fechaFinal +" 23:59','dd/mm/yyyy hh24:mi')  and entidad = 6 group by 2"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "Uso del Credito"
            datosEstadisticas.colorByPoint = true
            datosEstadisticas.data = []
            
            resultados.each {
                datosEstadisticas.data << [name: (it.rubro + " ($it.folios)"), y: it.folios]
            }
            respuesta.estadisticas << datosEstadisticas
        }else if (informe == "productosSolicitados") {
            query = "select count(folio) as folios , nombre_del_producto from reporte_solicitudes where to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 and nombre_del_producto <> '' group by nombre_del_producto order by folios desc"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "Poductos Mas Solicitados"
            datosEstadisticas.colorByPoint = true
            datosEstadisticas.data = []
            
            resultados.each {
                datosEstadisticas.data << [name: (it.nombre_del_producto + " ($it.folios)"), y: it.folios]
            }
            respuesta.estadisticas << datosEstadisticas
        }else if (informe == "comprobanteIngresos") {
            query = "select count(folio) as folios, tipo_de_documento from reporte_solicitudes where tipo_de_documento <> ' ' and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+fechaFinal +" 23:59','dd/mm/yyyy hh24:mi')  and entidad = 6 group by 2"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "Comprobante de Gastos"
            datosEstadisticas.colorByPoint = true
            datosEstadisticas.data = []
            
            resultados.each {
                datosEstadisticas.data << [name: (it.tipo_de_documento + " ($it.folios)"), y: it.folios]
            }
            respuesta.estadisticas << datosEstadisticas
        }else if (informe == "volumenHora") {
            query  = "select count(folio) as  folios ,  (to_char((to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi')), 'HH24') ) as hora from reporte_solicitudes where  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6  group by hora order by folios desc"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.periodos = [:]
            respuesta.periodos.categories = []
            respuesta.periodos.crosshair = true

            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "volumen por Hora"
            datosEstadisticas.type = "column"
            datosEstadisticas.yAxis = 0
            datosEstadisticas.data = []
            datosEstadisticas.tooltip = [:]
            datosEstadisticas.tooltip.valuePrefix = ""
            
            resultados.each {
                respuesta.periodos.categories << (it.hora + ":00 hrs" )
                 datosEstadisticas.data << [ it.folios]
            }
            respuesta.estadisticas << datosEstadisticas
        }else if (informe == "volumenDia") {
            query = "select count(folio) folios, dia_semana(fecha_de_solicitud) as dia, (EXTRACT(DOW FROM DATE (to_date(fecha_de_solicitud, 'dd/MM/yyyy'))) ) as d from reporte_solicitudes where  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6  group by 2 ,d order by d"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.periodos = [:]
            respuesta.periodos.categories = []
            respuesta.periodos.crosshair = true

            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "Volumen por Dìa"
            datosEstadisticas.type = "column"
            datosEstadisticas.yAxis = 0
            datosEstadisticas.data = []
            datosEstadisticas.tooltip = [:]
            datosEstadisticas.tooltip.valuePrefix = ""
            
            resultados.each {
                respuesta.periodos.categories << (it.dia  )
                 datosEstadisticas.data << [ it.folios]
            }
            respuesta.estadisticas << datosEstadisticas
        }else if (informe == "dictamenStatus") {
            query = "select count(rmdd.id_resultado_motor_de_decision) as folios, rmdd.dictamen_final, sum(ps.monto_del_credito) from resultado_motor_de_decision rmdd, solicitud_de_credito sc, producto_solicitud ps  where rmdd.solicitud_id = sc.id_solicitud_de_credito and fecha_de_solicitud between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and rmdd.solicitud_id = ps.solicitud_id  group by dictamen_final"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "Dictamen "
            datosEstadisticas.colorByPoint = true
            datosEstadisticas.data = []
            
            resultados.each {
                if(it.dictamen_final == 'R'){
                    it.dictamen_final = 'Rechazado $ '
                }else if(it.dictamen_final == 'A'){
                    it.dictamen_final = 'Autorizado $ '
                }else if (it.dictamen_final == 'D'){
                    it.dictamen_final = 'Duda $ '
                }
                datosEstadisticas.data << [name: (it.dictamen_final +"  $it.sum"), y: it.folios]
            }
            respuesta.estadisticas << datosEstadisticas
        }else if (informe == "residenciaClientes") {
            query = "select count(folio) as folios, estado, avg(monto_del_credito) promedio_credito, sum(monto_del_credito) as monto  from reporte_solicitudes where estado <> ' ' and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by 2 order by 1"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.periodos = [:]
            respuesta.periodos.categories = []
            respuesta.periodos.crosshair = true

            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "Solicitudes por Estado"
            datosEstadisticas.type = "column"
            datosEstadisticas.yAxis = 0
            datosEstadisticas.data = []
            datosEstadisticas.tooltip = [:]
            datosEstadisticas.tooltip.valuePrefix = ""
            
                
            
            resultados.each {
                respuesta.periodos.categories << (it.estado  )
                 datosEstadisticas.data << [ it.folios]
            }
            respuesta.estadisticas << datosEstadisticas
        }else if (informe == "mPSProducto") {
            query = " select count(rmdd.id_resultado_motor_de_decision) as folios , rmdd.dictamen_final,p.nombre_del_producto as producto, sum(ps.monto_del_credito ) credito , avg(ps.monto_del_credito) as credito_promedio from resultado_motor_de_decision rmdd , producto_solicitud ps, producto  p, solicitud_de_credito sc where rmdd.solicitud_id  = ps.solicitud_id and p.id_producto = ps.producto_id and rmdd.solicitud_id = sc.id_solicitud_de_credito and sc.fecha_de_solicitud between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and sc.entidad_financiera_id = 6 group by  2 , p.nombre_del_producto   order by 3,2"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            def tf = 0
            def mat = []
            def qr = "select count(rmdd.id_resultado_motor_de_decision) as folios  from resultado_motor_de_decision rmdd , producto_solicitud ps, producto  p, solicitud_de_credito sc where rmdd.solicitud_id  = ps.solicitud_id and p.id_producto = ps.producto_id and rmdd.solicitud_id = sc.id_solicitud_de_credito and sc.fecha_de_solicitud between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and sc.entidad_financiera_id = 6 "
            def r = sql.rows(qr)
            def total
            r.each{
                total = it.folios
            }
            resultados.each{
                def datos = [:]
                tf = tf + it.folios
                datos.folio = it.folios
                datos.producto = it.producto
                if(it.dictamen_final == 'R'){
                    datos.dictamen_final = ' Rechazado '
                }else if(it.dictamen_final == 'A'){
                    datos.dictamen_final = ' Autorizado '
                }else if (it.dictamen_final == 'D'){
                    datos.dictamen_final = ' Duda '
                } 
                datos.promCredito = it.credito_promedio
                datos.porcentaje = (total > 0 ? (it.folios * 100/total) : 0)
                mat << datos
            }
            return mat
           
        }else if (informe == "mPGI") {
            query = "select genero, 'Menor a \$5000.00 ' as rango_ingresos, count(folio) as folios ,avg(monto_del_credito) prom_credito from  genero_edad where ingresos <= 5000 and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +"  00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +"  23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by genero UNION"
            query += " select genero,'Mayor a \$5000 y Menor a \$10000.00' as rango_ingresos, count(folio) as folios ,avg(monto_del_credito) prom_credito from  genero_edad where ingresos <= 10000 and ingresos > 5000 and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by genero  UNION "
            query += " select genero,'Mayor a \$10000 y Menor a \$15000.00' as rango_ingresos, count(folio) as folios  ,avg(monto_del_credito) prom_credito from  genero_edad where ingresos <= 15000 and ingresos > 10000 and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by genero UNION "
            query += " select genero,'Mayor a \$15000 y Menor a \$20000.00' as rango_ingresos, count(folio) as folios,avg(monto_del_credito) prom_credito  from  genero_edad where ingresos <= 20000 and ingresos > 15000 and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by genero  UNION "
            query += " select genero,'Mayor a \$20000 y Menor a \$25000.00' as rango_ingresos, count(folio) as folios,avg(monto_del_credito) prom_credito  from  genero_edad where ingresos <= 25000 and ingresos > 20000 and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by genero  UNION "
            query += " select genero,'Mayor a \$25000 y Menor a \$30000.00' as rango_ingresos, count(folio) as folios,avg(monto_del_credito) prom_credito  from  genero_edad where ingresos <= 30000 and ingresos > 25000 and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by genero UNION "
            query += " select genero,'Mayor a \$30000 y Menor a \$40000.00' as rango_ingresos, count(folio) as folios,avg(monto_del_credito) prom_credito from  genero_edad where ingresos <= 40000 and ingresos > 30000 and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by genero  UNION "
            query += " select genero,'Mayor a \$40000 y Menor a \$50000.00' as rango_ingresos, count(folio) as folios  ,avg(monto_del_credito) prom_credito from  genero_edad where ingresos <= 50000 and ingresos > 40000 and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by genero UNION "
            query += " select genero,'Mayor a \$50000 ' as rango_ingresos, count(folio) as folios,avg(monto_del_credito) prom_credito  from  genero_edad where  ingresos > 50000 and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by genero  order by genero,rango_ingresos"
            
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            def qt = "select count(*) as folios from genero_edad where to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6"
            def r = sql.rows(qt)
            def total
            def mat = []
            r.each{
                total = it.folios
            }
            
            resultados.each{
              def datos = [:]
              datos.genero = it.genero
              datos.rangoIngresos = it.rango_ingresos
              datos.folios = it.folios
              datos.porcentaje = (total > 0 ? (it.folios*100/total) : 0)
              datos.promedioCredito = it.prom_credito
              mat << datos
            }
            return mat 
            
        }else if (informe == "iMPEG") {
            query = "select count(folio) folios, genero, avg(monto_del_credito) prom_credito,avg(ingresos) prom_ingresos, 'Menor a 25 años' as rango_edad from genero_edad where edad <= 25 and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by genero  union  "
            query += "select count(folio) folios, genero, avg(monto_del_credito) prom_credito,avg(ingresos) prom_ingresos, 'Menor a 25 años' as rango_edad from genero_edad where edad <= 25 and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6   group by  genero  union "
            query += "select count(folio) folios, genero, avg(monto_del_credito) prom_credito,avg(ingresos) prom_ingresos, 'Mayor a 26 y Menor a 30 años' as rango_edad from genero_edad where edad >= 26 and edad <= 30 and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6  group by  genero UNION "
            query += "select count(folio) folios, genero, avg(monto_del_credito) prom_credito,avg(ingresos) prom_ingresos, 'Mayor a 31 y Menor a 45 años' as rango_edad from genero_edad where edad >= 31 and edad <= 45 and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6   group by  genero  union "
            query += "select count(folio) folios, genero, avg(monto_del_credito) prom_credito,avg(ingresos) prom_ingresos, 'Mayor a 46 y Menor a 65 años' as rango_edad from genero_edad where edad >= 46 and edad <= 65 and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi')  and entidad = 6  group by genero  union  "
            query += "select count(folio) folios, genero, avg(monto_del_credito) prom_credito,avg(ingresos) prom_ingresos, 'Mayor a 65 años' as rango_edad from genero_edad where edad >  65 and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6  group by  genero order by genero,rango_edad "
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            
            def qt = "select count(*) as folios from genero_edad where to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6"
            def r = sql.rows(qt)
            def total
            def mat = []
            r.each{
                total = it.folios
            }
            resultados.each{
              def datos = [:]
              datos.genero = it.genero
              datos.folios = it.folios
              datos.rangoEdad = it.rango_edad
              datos.porcentaje = (total > 0 ? (it.folios*100/total) : 0)
              datos.promedioCredito = it.prom_credito
              datos.promedioIngresos = it.prom_ingresos
              mat << datos 
            }
            return mat
        }else if (informe == "productosDiscriminados") {
            query = " select count(rmdd.id_resultado_motor_de_decision) as folios , rmdd.dictamen_final,p.nombre_del_producto as producto, sum(ps.monto_del_credito ) credito , avg(ps.monto_del_credito) as credito_promedio from resultado_motor_de_decision rmdd , producto_solicitud ps, producto  p, solicitud_de_credito sc where rmdd.solicitud_id  = ps.solicitud_id and p.id_producto = ps.producto_id and rmdd.solicitud_id = sc.id_solicitud_de_credito and sc.fecha_de_solicitud between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and sc.entidad_financiera_id = 6 group by  2 , p.nombre_del_producto   order by 3,2"
            
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            def mat = []
            def qr = "select count(rmdd.id_resultado_motor_de_decision) as folios  from resultado_motor_de_decision rmdd , producto_solicitud ps, producto  p, solicitud_de_credito sc where rmdd.solicitud_id  = ps.solicitud_id and p.id_producto = ps.producto_id and rmdd.solicitud_id = sc.id_solicitud_de_credito and sc.fecha_de_solicitud between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and sc.entidad_financiera_id = 6 "
            def r = sql.rows(qr)
            def total
            r.each{
                total = it.folios
            }
            resultados.each{
                def datos = [:]
                datos.folio = it.folios
                
                if(it.dictamen_final == 'R'){
                    datos.dictamen_final = ' Rechazado '
                }else if(it.dictamen_final == 'A'){
                    datos.dictamen_final = ' Autorizado '
                }else if (it.dictamen_final == 'D'){
                    datos.dictamen_final = ' Duda '
                } 
                datos.producto = it.producto + " " + datos.dictamen_final
                datos.credito = it.credito
                datos.porcentaje = (total > 0 ? (it.folios * 100/total) : 0)
                mat << datos
            }
            return mat
            
        }else if (informe == "contacto") {
            query = "select count(folio) as folios , medio_de_contacto, dictamen_final from reporte_produccion where medio_de_contacto <> '' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6  group by 2, 3 order by 2,3"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            def mat = []
            def qt = "select count(folio) as folios from reporte_produccion where medio_de_contacto <> '' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6"
            def r = sql.rows(qt)
            def total
            r.each{
                total = it.folios
            }
            resultados.each{
                def datos = [:]
                datos.folios = it.folios
                datos.medioContacto = it.medio_de_contacto
                if(it.dictamen_final == 'R'){
                    datos.dictamen_final = ' Rechazado '
                }else if(it.dictamen_final == 'A'){
                    datos.dictamen_final = ' Autorizado '
                }else if (it.dictamen_final == 'D'){
                    datos.dictamen_final = ' Duda '
                }else{
                    datos.dictamen_final = ' Sin Dictaminar '
                } 
                datos.porcentaje = (total > 0 ? (it.folios * 100/total) : 0)
                mat << datos
            }
            
            return mat 
        }else if (informe == "abandono") {
            query = "select count(rp.folio) as folios , ps.titulo , ps.numero_de_paso /*,rp.dictamen_final*/  from reporte_produccion rp, paso_solicitud_entidad_financiera ps  where rp.ultimo_paso = ps.numero_de_paso and  to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6 group by ps.titulo , 3 /*,rp.dictamen_final*/  order by 3 "
            
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.periodos = [:]
            respuesta.periodos.categories = []
            respuesta.periodos.crosshair = true
            
            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "No. Solicitudes "
            datosEstadisticas.type = "column"
            datosEstadisticas.yAxis = 0
            datosEstadisticas.data = []
            datosEstadisticas.tooltip = [:]
            datosEstadisticas.tooltip.valuePrefix = ""
            
            resultados.each {
                /*def dictamen_final
                 if(it.dictamen_final == 'R'){
                    dictamen_final = ' Rechazado '
                }else if(it.dictamen_final == 'A'){
                    dictamen_final = ' Autorizado '
                }else if (it.dictamen_final == 'D'){
                    dictamen_final = ' Duda '
                } */
                respuesta.periodos.categories << (it.titulo + " " )
                 datosEstadisticas.data << [ it.folios]
                 // datosEstadisticas.data << [name: (it.titulo + " ($it.folios)"), y: total]
            }
            respuesta.estadisticas << datosEstadisticas
            
        }else if (informe == "dFvsDP") {
            query = "select count(dictamen_final) as folios, 'Rechazados Totales' as tipo   from reporte_produccion where dictamen_final='R' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') UNION  "
            query += " select  count(dictamen_de_politicas) as folios, 'Rechazados x Dictamen Politicas '  as tipo  from reporte_produccion where dictamen_de_politicas = 'R' and dictamen_final = 'R' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') UNION  "
            query += " select  count(dictamen_de_politicas)  as folios, 'Autorizado x Dictamen Politicas '  as tipo   from reporte_produccion where dictamen_de_politicas = 'A' and dictamen_final = 'R' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') UNION  "
            query += " select  count(dictamen_de_politicas)  as folios, 'Duda x Dictamen Politicas '  as tipo   from reporte_produccion where dictamen_de_politicas = 'D' and dictamen_final = 'R' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi')  "
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            def mat = []
            def qt = "select count(dictamen_final) as folios, 'Rechazados Totales' as tipo   from reporte_produccion where dictamen_final='R' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6"
            def r = sql.rows(qt)
            def total
            r.each{
                total = it.folios
            }
            resultados.each{
                def datos = [:]
                datos.tipo = it.tipo
                datos.folios = it.folios
                datos.porcentaje = (total > 0 ? (it.folios * 100/total) : 0)
                mat << datos
            }
           return mat 
        }else if (informe == "causaRechazo") {
            def sql = new Sql(dataSource)
            def qdpoliticas  = "select count(folio)as folios  , dictamen_de_politicas as dictamen ,'Dictamen De Politicas' as tipo  from reporte_produccion where dictamen_de_politicas <> '' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6  group by 2 order by 1 desc"
            def qdperfil = "select count(folio) as folios , dictamen_de_perfil as dictamen, 'Dictamen De Perfil' as tipo from reporte_produccion where dictamen_de_perfil <> '' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6  group by 2 order by 1 desc "
            def qdconjunto = "select count(folio) as folios , dictamen_conjunto as dictamen, 'Dictamen Conjunto' as tipo  from reporte_produccion where dictamen_conjunto <> '' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6  group by 2 order by 1 desc"
            def qdcapacidad = "select count(folio) as folios , dictamen_capacidad_de_pago as dictamen , 'Dictamen De Pago' as tipo from reporte_produccion where dictamen_capacidad_de_pago <> '' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6  group by 2 order by 1 desc"
            def mat = []
            def rdpoliticas = sql.rows(qdpoliticas)
            def rdperfil = sql.rows(qdperfil)
            def rdconjunto  = sql.rows(qdconjunto)
            def rdcapacidad = sql.rows(qdcapacidad)
            def qt = " select count(folio)as folios   from reporte_produccion where dictamen_de_politicas <> '' and to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi') between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi') and entidad = 6  "
            def r = sql.rows(qt)
            def total
            r.each{
                total = it.folios
            }
            rdpoliticas.each{
                def politicas = [:]
                politicas.folios = it.folios
                if(it.dictamen == 'R'){
                    politicas.dictamen = ' Rechazado '
                }else if(it.dictamen == 'A'){
                    politicas.dictamen = ' Autorizado '
                }else if (it.dictamen == 'D'){
                    politicas.dictamen = ' Duda '
                } 
                politicas.tipo = it.tipo 
                politicas.porcentaje = (total > 0 ? (it.folios * 100/total) : 0)
                mat << politicas
            }
            rdperfil.each{
                def perfil = [:]
                perfil.folios = it.folios
                if(it.dictamen == 'R'){
                    perfil.dictamen = ' Rechazado '
                }else if(it.dictamen == 'A'){
                    perfil.dictamen = ' Autorizado '
                }else if (it.dictamen == 'D'){
                    perfil.dictamen = ' Duda '
                } 
                perfil.tipo = it.tipo 
                perfil.porcentaje = (total > 0 ? (it.folios * 100/total) : 0)
                mat << perfil
            }
            rdconjunto.each{
                def conjunto = [:]
                conjunto.folios = it.folios
                if(it.dictamen == 'R'){
                    conjunto.dictamen = ' Rechazado '
                }else if(it.dictamen == 'A'){
                    conjunto.dictamen = ' Autorizado '
                }else if (it.dictamen == 'D'){
                    conjunto.dictamen = ' Duda '
                } 
                conjunto.tipo = it.tipo 
                conjunto.porcentaje = (total > 0 ? (it.folios * 100/total) : 0)
                mat << conjunto 
            }
            rdcapacidad.each{
                def capacidad = [:]
                capacidad.folios = it.folios
                if(it.dictamen == 'S'){
                    capacidad.dictamen = ' Suficiente '
                }else if(it.dictamen == 'I'){
                    capacidad.dictamen = ' Insuficiente '
                }
                capacidad.tipo = it.tipo 
                capacidad.porcentaje = (total > 0 ? (it.folios * 100/total) : 0)
                mat << capacidad
            }
            return mat 
        }else if (informe == "consultasBC") {
            def sql = new Sql(dataSource)
            def tcb = "select count(*) as folios from reporte_buro_credito where fecha_consulta between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi')   "
            def r = sql.rows(tcb)
            def totalcb
            r.each{
                totalcb = it.folios
            }
            def tce = "select count(id_reporteburocredito)as folios   from reporte_buro_credito where  tipo_error_buro_credito_id <> 0 and fecha_consulta between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi')"
            def te = sql.rows(tce)
            def totalce
            te.each{
                totalce = it.folios
            }
            def consultasA = totalcb-totalce
            query = "select count(rbc.id_reporteburocredito)as folios, tebc.nombre as error from reporte_buro_credito rbc, tipo_error_buro_credito tebc, solicitud_de_credito sc  where tipo_error_buro_credito_id <> 0 and tebc.id_tipoerrorburocredito = rbc.tipo_error_buro_credito_id and  sc.reporte_buro_credito_id = rbc.id_reporteburocredito and fecha_consulta between to_timestamp('"+ fechaInicio +" 00:00','dd/mm/yyyy hh24:mi') and to_timestamp('"+ fechaFinal +" 23:59','dd/mm/yyyy hh24:mi')  and sc.entidad_financiera_id = 6 group by 2 order by 1 desc"
            def resultados = sql.rows(query)
            def mat = []
            resultados.each{
                def datos= [:]
                datos.error = it.error
                datos.folios = it.folios
                datos.tce = totalce
                datos.tcb = totalcb
                datos.poncentaje_error = (totalce > 0 ? (it.folios * 100/totalce) : 0)
                datos.porcentaje_consultaA = (totalcb > 0 ? (consultasA*100/totalcb) : 0)       
                mat << datos 
            }
           return  mat 
        }
        return respuesta
    }
        
    }
    
