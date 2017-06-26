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
    
    def obtenerReporte(def tipo, def entidad, def finicio, def ffin ) {
        println "entro 22 " +tipo + entidad
        
        def db = new Sql(dataSource)
        def datosReporte = []
        
        if( tipo == 'solicitudes'){
            def query = "select *from reporte_solicitudes where to_timestamp(fecha_de_solicitud,'dd/mm/yyyy hh24:mi')  between to_timestamp('"+ finicio + "','dd/mm/yyyy hh24:mi') and to_timestamp('"+ ffin +"','dd/mm/yyyy hh24:mi') "
            def result = db.rows(query)
            result?.each{
                def datos =[:]
                datos.id = it.folio
                datos.fechaDeSolicitud = it.fecha_de_solicitud
                datos.tipo = it.tipo
                datos.status = it.status
                datos.medioDeContacto  = it.medio_de_contacto 
                datos.opcionDeContacto = it.opcion_de_contacto
                datos.nombreCliente = it.nombre_cliente
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
                datos.montoDelCredito = it.monto_del_credito
                datos.montoDelPago = it.monto_del_pago
                datos.montoDelSeguroDeDeuda = it.monto_del_seguro_de_deuda
                datos.plazos = it.plazos
                datos.periodicidad = it.periodicidad
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
                datos.calle = it.calle
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
                datos.log = it.log
                datos.respuesta = it.respuesta
                datosReporte << datos
            }
            if(datosReporte.size() > 1 ){
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
                    sheet(name: "ReporteSolicitudesGeneradas", widthColumns: [20,20,20,25,20,20,20,30,40,40,40,40,40,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20]) {
                        row (style: 'titulo') {
                            cell { "FOLIO" }
                            cell { "FECHA DE SOLICITUD" }
                            cell { "STATUS" }
                            cell { "MEDIO DE CONTACTO" }
                            cell { "OPCION DE CONTACTO" }
                            cell { "NOMBRE DEL CLIENTE" }
                            cell { "ULTIMO PASO" }
                            cell { "SHORT URL" }
                            cell { "SUCURSAL" }
                            cell { "NOMBRE DEL PRODUCTO" }
                            cell { "RUBRO" }
                            cell { "TIPO DE DOCUMENTO" }
                            cell { "ENGANCHE" }
                            cell { "HA TENIDO ATRASOS" }
                            cell { "MONTO DEL CREDITO" }
                            cell { "MONTO DEL PAGO" }
                            cell { "MONTO DEL SEGURO DE DEUDA" }
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
                            cell { "LOG" }
			    cell { "RESPUESTA" }
                                
                        }
                        datosReporte.each { fila ->
                            row (style: 'contenido') {
                                cell { fila.id ?: "-" }
                                cell { fila.fechaDeSolicitud }
                                cell { fila.status } 
                                cell { fila.medioDeContacto ?: "" }
                                cell { fila.opcionDeContacto ?: ""} 
                                cell { fila.nombreCliente?.toUpperCase() } 
                                cell { fila.ultimoPaso } 
                                cell { fila.shortUrl ?: ""} 
                                cell { fila.sucursal ?: ""} 
                                cell { fila.nombreDelProducto ?: ""} 
                                cell { fila.rubro ?: "" } 
                                cell { fila.tipoDeDocumento ?: ""}
                                cell { fila.enganche ?: ""} 
                                cell { fila.haTenidoAtrasos ?: ""} 
                                cell { fila.montoDelCredito }
                                cell { fila.montoDelPago } 
                                cell { fila.montoDelSeguroDeDeuda } 
                                cell { fila.plazos }
                                cell { fila.periodicidad } 
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
                                cell { fila.calle } 
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
            if(datosReporte.size() > 1 ){
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
            if(datosReporte.size() > 1 ){
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
       
    }
    
        
    }
    