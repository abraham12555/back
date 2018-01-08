
package mx.ksms.engine.ce;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para calculateProb complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="calculateProb">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="riesgogeografico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="renovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="R_DIFMS_PWULTCPT12" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="R_EVOPAGOS3" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="PROPMONTOLIBERADO" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="FECANTIGCLICRED" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="EDAD" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ANTIGEMPLEO" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ag_riesgoocupacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ag_estadocivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ag_servicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="R_COB_SLDPAS12" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ingresos_fijos_mensuales" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ingresos_variables_mensuales" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="otros_ingresos" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cantidad_integrantes_familia" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="gastos_de_alquiler" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cuota_credito" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="tipo_de_vivienda" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="asalariado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="experiencia_crediticia" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="creditos_liquidados" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cadena_bc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="porcentajeDedescuento" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateProb", propOrder = {
    "solicitudId",
    "riesgogeografico",
    "renovacion",
    "rdifmspwultcpt12",
    "revopagos3",
    "propmontoliberado",
    "fecantigclicred",
    "edad",
    "antigempleo",
    "agRiesgoocupacion",
    "agEstadocivil",
    "agServicio",
    "rcobsldpas12",
    "ingresosFijosMensuales",
    "ingresosVariablesMensuales",
    "otrosIngresos",
    "cantidadIntegrantesFamilia",
    "gastosDeAlquiler",
    "cuotaCredito",
    "tipoDeVivienda",
    "asalariado",
    "experienciaCrediticia",
    "creditosLiquidados",
    "cadenaBc",
    "porcentajeDedescuento"
})
public class CalculateProb {

    protected String solicitudId;
    protected String riesgogeografico;
    protected String renovacion;
    @XmlElement(name = "R_DIFMS_PWULTCPT12")
    protected Double rdifmspwultcpt12;
    @XmlElement(name = "R_EVOPAGOS3")
    protected Double revopagos3;
    @XmlElement(name = "PROPMONTOLIBERADO")
    protected Double propmontoliberado;
    @XmlElement(name = "FECANTIGCLICRED")
    protected Double fecantigclicred;
    @XmlElement(name = "EDAD")
    protected Integer edad;
    @XmlElement(name = "ANTIGEMPLEO")
    protected Integer antigempleo;
    @XmlElement(name = "ag_riesgoocupacion")
    protected String agRiesgoocupacion;
    @XmlElement(name = "ag_estadocivil")
    protected String agEstadocivil;
    @XmlElement(name = "ag_servicio")
    protected String agServicio;
    @XmlElement(name = "R_COB_SLDPAS12")
    protected Double rcobsldpas12;
    @XmlElement(name = "ingresos_fijos_mensuales")
    protected Double ingresosFijosMensuales;
    @XmlElement(name = "ingresos_variables_mensuales")
    protected Double ingresosVariablesMensuales;
    @XmlElement(name = "otros_ingresos")
    protected Double otrosIngresos;
    @XmlElement(name = "cantidad_integrantes_familia")
    protected Integer cantidadIntegrantesFamilia;
    @XmlElement(name = "gastos_de_alquiler")
    protected Double gastosDeAlquiler;
    @XmlElement(name = "cuota_credito")
    protected Double cuotaCredito;
    @XmlElement(name = "tipo_de_vivienda")
    protected Integer tipoDeVivienda;
    protected boolean asalariado;
    @XmlElement(name = "experiencia_crediticia")
    protected boolean experienciaCrediticia;
    @XmlElement(name = "creditos_liquidados")
    protected int creditosLiquidados;
    @XmlElement(name = "cadena_bc")
    protected String cadenaBc;
    protected Double porcentajeDedescuento;

    /**
     * Obtiene el valor de la propiedad solicitudId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolicitudId() {
        return solicitudId;
    }

    /**
     * Define el valor de la propiedad solicitudId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolicitudId(String value) {
        this.solicitudId = value;
    }

    /**
     * Obtiene el valor de la propiedad riesgogeografico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRiesgogeografico() {
        return riesgogeografico;
    }

    /**
     * Define el valor de la propiedad riesgogeografico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRiesgogeografico(String value) {
        this.riesgogeografico = value;
    }

    /**
     * Obtiene el valor de la propiedad renovacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenovacion() {
        return renovacion;
    }

    /**
     * Define el valor de la propiedad renovacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenovacion(String value) {
        this.renovacion = value;
    }

    /**
     * Obtiene el valor de la propiedad rdifmspwultcpt12.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRDIFMSPWULTCPT12() {
        return rdifmspwultcpt12;
    }

    /**
     * Define el valor de la propiedad rdifmspwultcpt12.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRDIFMSPWULTCPT12(Double value) {
        this.rdifmspwultcpt12 = value;
    }

    /**
     * Obtiene el valor de la propiedad revopagos3.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getREVOPAGOS3() {
        return revopagos3;
    }

    /**
     * Define el valor de la propiedad revopagos3.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setREVOPAGOS3(Double value) {
        this.revopagos3 = value;
    }

    /**
     * Obtiene el valor de la propiedad propmontoliberado.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPROPMONTOLIBERADO() {
        return propmontoliberado;
    }

    /**
     * Define el valor de la propiedad propmontoliberado.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPROPMONTOLIBERADO(Double value) {
        this.propmontoliberado = value;
    }

    /**
     * Obtiene el valor de la propiedad fecantigclicred.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFECANTIGCLICRED() {
        return fecantigclicred;
    }

    /**
     * Define el valor de la propiedad fecantigclicred.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFECANTIGCLICRED(Double value) {
        this.fecantigclicred = value;
    }

    /**
     * Obtiene el valor de la propiedad edad.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEDAD() {
        return edad;
    }

    /**
     * Define el valor de la propiedad edad.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEDAD(Integer value) {
        this.edad = value;
    }

    /**
     * Obtiene el valor de la propiedad antigempleo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getANTIGEMPLEO() {
        return antigempleo;
    }

    /**
     * Define el valor de la propiedad antigempleo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setANTIGEMPLEO(Integer value) {
        this.antigempleo = value;
    }

    /**
     * Obtiene el valor de la propiedad agRiesgoocupacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgRiesgoocupacion() {
        return agRiesgoocupacion;
    }

    /**
     * Define el valor de la propiedad agRiesgoocupacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgRiesgoocupacion(String value) {
        this.agRiesgoocupacion = value;
    }

    /**
     * Obtiene el valor de la propiedad agEstadocivil.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgEstadocivil() {
        return agEstadocivil;
    }

    /**
     * Define el valor de la propiedad agEstadocivil.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgEstadocivil(String value) {
        this.agEstadocivil = value;
    }

    /**
     * Obtiene el valor de la propiedad agServicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgServicio() {
        return agServicio;
    }

    /**
     * Define el valor de la propiedad agServicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgServicio(String value) {
        this.agServicio = value;
    }

    /**
     * Obtiene el valor de la propiedad rcobsldpas12.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRCOBSLDPAS12() {
        return rcobsldpas12;
    }

    /**
     * Define el valor de la propiedad rcobsldpas12.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRCOBSLDPAS12(Double value) {
        this.rcobsldpas12 = value;
    }

    /**
     * Obtiene el valor de la propiedad ingresosFijosMensuales.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIngresosFijosMensuales() {
        return ingresosFijosMensuales;
    }

    /**
     * Define el valor de la propiedad ingresosFijosMensuales.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIngresosFijosMensuales(Double value) {
        this.ingresosFijosMensuales = value;
    }

    /**
     * Obtiene el valor de la propiedad ingresosVariablesMensuales.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIngresosVariablesMensuales() {
        return ingresosVariablesMensuales;
    }

    /**
     * Define el valor de la propiedad ingresosVariablesMensuales.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIngresosVariablesMensuales(Double value) {
        this.ingresosVariablesMensuales = value;
    }

    /**
     * Obtiene el valor de la propiedad otrosIngresos.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOtrosIngresos() {
        return otrosIngresos;
    }

    /**
     * Define el valor de la propiedad otrosIngresos.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOtrosIngresos(Double value) {
        this.otrosIngresos = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadIntegrantesFamilia.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadIntegrantesFamilia() {
        return cantidadIntegrantesFamilia;
    }

    /**
     * Define el valor de la propiedad cantidadIntegrantesFamilia.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadIntegrantesFamilia(Integer value) {
        this.cantidadIntegrantesFamilia = value;
    }

    /**
     * Obtiene el valor de la propiedad gastosDeAlquiler.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGastosDeAlquiler() {
        return gastosDeAlquiler;
    }

    /**
     * Define el valor de la propiedad gastosDeAlquiler.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGastosDeAlquiler(Double value) {
        this.gastosDeAlquiler = value;
    }

    /**
     * Obtiene el valor de la propiedad cuotaCredito.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCuotaCredito() {
        return cuotaCredito;
    }

    /**
     * Define el valor de la propiedad cuotaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCuotaCredito(Double value) {
        this.cuotaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoDeVivienda.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTipoDeVivienda() {
        return tipoDeVivienda;
    }

    /**
     * Define el valor de la propiedad tipoDeVivienda.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTipoDeVivienda(Integer value) {
        this.tipoDeVivienda = value;
    }

    /**
     * Obtiene el valor de la propiedad asalariado.
     * 
     */
    public boolean isAsalariado() {
        return asalariado;
    }

    /**
     * Define el valor de la propiedad asalariado.
     * 
     */
    public void setAsalariado(boolean value) {
        this.asalariado = value;
    }

    /**
     * Obtiene el valor de la propiedad experienciaCrediticia.
     * 
     */
    public boolean isExperienciaCrediticia() {
        return experienciaCrediticia;
    }

    /**
     * Define el valor de la propiedad experienciaCrediticia.
     * 
     */
    public void setExperienciaCrediticia(boolean value) {
        this.experienciaCrediticia = value;
    }

    /**
     * Obtiene el valor de la propiedad creditosLiquidados.
     * 
     */
    public int getCreditosLiquidados() {
        return creditosLiquidados;
    }

    /**
     * Define el valor de la propiedad creditosLiquidados.
     * 
     */
    public void setCreditosLiquidados(int value) {
        this.creditosLiquidados = value;
    }

    /**
     * Obtiene el valor de la propiedad cadenaBc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCadenaBc() {
        return cadenaBc;
    }

    /**
     * Define el valor de la propiedad cadenaBc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCadenaBc(String value) {
        this.cadenaBc = value;
    }

    /**
     * Obtiene el valor de la propiedad porcentajeDedescuento.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPorcentajeDedescuento() {
        return porcentajeDedescuento;
    }

    /**
     * Define el valor de la propiedad porcentajeDedescuento.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPorcentajeDedescuento(Double value) {
        this.porcentajeDedescuento = value;
    }

}
