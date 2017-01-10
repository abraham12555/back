
package mx.ksms.engine.cl;

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
 *         &lt;element name="riesgogeografico" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ag_plazo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ag_periodicidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="R_CUO_INGFU" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ag_riesgoocupacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EDAD" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="R_COB_SLDPAS12" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ag_estadocivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ag_servicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="INDICAPASNOVISTA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ANTIGVIVIENDA" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ag_hist12_hist24" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ag_mop_peor_sis" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="R_DSPTOCON_SIS" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="d1_r_conoe1con_oe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="R_CONOE1CON_OE" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ingresos_fijos_mensuales" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ingresos_variables_mensuales" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="gastos_mensuales" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cuota_mensual_credito" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
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
    "riesgogeografico",
    "agPlazo",
    "agPeriodicidad",
    "rcuoingfu",
    "agRiesgoocupacion",
    "edad",
    "rcobsldpas12",
    "agEstadocivil",
    "agServicio",
    "indicapasnovista",
    "antigvivienda",
    "agHist12Hist24",
    "agMopPeorSis",
    "rdsptoconsis",
    "d1RConoe1ConOe",
    "rconoe1CONOE",
    "ingresosFijosMensuales",
    "ingresosVariablesMensuales",
    "gastosMensuales",
    "cuotaMensualCredito"
})
public class CalculateProb {

    protected String riesgogeografico;
    @XmlElement(name = "ag_plazo")
    protected Integer agPlazo;
    @XmlElement(name = "ag_periodicidad")
    protected String agPeriodicidad;
    @XmlElement(name = "R_CUO_INGFU")
    protected Double rcuoingfu;
    @XmlElement(name = "ag_riesgoocupacion")
    protected String agRiesgoocupacion;
    @XmlElement(name = "EDAD")
    protected Integer edad;
    @XmlElement(name = "R_COB_SLDPAS12")
    protected Double rcobsldpas12;
    @XmlElement(name = "ag_estadocivil")
    protected String agEstadocivil;
    @XmlElement(name = "ag_servicio")
    protected String agServicio;
    @XmlElement(name = "INDICAPASNOVISTA")
    protected String indicapasnovista;
    @XmlElement(name = "ANTIGVIVIENDA")
    protected Integer antigvivienda;
    @XmlElement(name = "ag_hist12_hist24")
    protected Double agHist12Hist24;
    @XmlElement(name = "ag_mop_peor_sis")
    protected Double agMopPeorSis;
    @XmlElement(name = "R_DSPTOCON_SIS")
    protected Double rdsptoconsis;
    @XmlElement(name = "d1_r_conoe1con_oe")
    protected String d1RConoe1ConOe;
    @XmlElement(name = "R_CONOE1CON_OE")
    protected Double rconoe1CONOE;
    @XmlElement(name = "ingresos_fijos_mensuales")
    protected Double ingresosFijosMensuales;
    @XmlElement(name = "ingresos_variables_mensuales")
    protected Double ingresosVariablesMensuales;
    @XmlElement(name = "gastos_mensuales")
    protected Double gastosMensuales;
    @XmlElement(name = "cuota_mensual_credito")
    protected Double cuotaMensualCredito;

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
     * Obtiene el valor de la propiedad agPlazo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAgPlazo() {
        return agPlazo;
    }

    /**
     * Define el valor de la propiedad agPlazo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAgPlazo(Integer value) {
        this.agPlazo = value;
    }

    /**
     * Obtiene el valor de la propiedad agPeriodicidad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgPeriodicidad() {
        return agPeriodicidad;
    }

    /**
     * Define el valor de la propiedad agPeriodicidad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgPeriodicidad(String value) {
        this.agPeriodicidad = value;
    }

    /**
     * Obtiene el valor de la propiedad rcuoingfu.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRCUOINGFU() {
        return rcuoingfu;
    }

    /**
     * Define el valor de la propiedad rcuoingfu.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRCUOINGFU(Double value) {
        this.rcuoingfu = value;
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
     * Obtiene el valor de la propiedad indicapasnovista.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINDICAPASNOVISTA() {
        return indicapasnovista;
    }

    /**
     * Define el valor de la propiedad indicapasnovista.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINDICAPASNOVISTA(String value) {
        this.indicapasnovista = value;
    }

    /**
     * Obtiene el valor de la propiedad antigvivienda.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getANTIGVIVIENDA() {
        return antigvivienda;
    }

    /**
     * Define el valor de la propiedad antigvivienda.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setANTIGVIVIENDA(Integer value) {
        this.antigvivienda = value;
    }

    /**
     * Obtiene el valor de la propiedad agHist12Hist24.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAgHist12Hist24() {
        return agHist12Hist24;
    }

    /**
     * Define el valor de la propiedad agHist12Hist24.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAgHist12Hist24(Double value) {
        this.agHist12Hist24 = value;
    }

    /**
     * Obtiene el valor de la propiedad agMopPeorSis.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAgMopPeorSis() {
        return agMopPeorSis;
    }

    /**
     * Define el valor de la propiedad agMopPeorSis.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAgMopPeorSis(Double value) {
        this.agMopPeorSis = value;
    }

    /**
     * Obtiene el valor de la propiedad rdsptoconsis.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRDSPTOCONSIS() {
        return rdsptoconsis;
    }

    /**
     * Define el valor de la propiedad rdsptoconsis.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRDSPTOCONSIS(Double value) {
        this.rdsptoconsis = value;
    }

    /**
     * Obtiene el valor de la propiedad d1RConoe1ConOe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getD1RConoe1ConOe() {
        return d1RConoe1ConOe;
    }

    /**
     * Define el valor de la propiedad d1RConoe1ConOe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setD1RConoe1ConOe(String value) {
        this.d1RConoe1ConOe = value;
    }

    /**
     * Obtiene el valor de la propiedad rconoe1CONOE.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRCONOE1CONOE() {
        return rconoe1CONOE;
    }

    /**
     * Define el valor de la propiedad rconoe1CONOE.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRCONOE1CONOE(Double value) {
        this.rconoe1CONOE = value;
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
     * Obtiene el valor de la propiedad gastosMensuales.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGastosMensuales() {
        return gastosMensuales;
    }

    /**
     * Define el valor de la propiedad gastosMensuales.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGastosMensuales(Double value) {
        this.gastosMensuales = value;
    }

    /**
     * Obtiene el valor de la propiedad cuotaMensualCredito.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCuotaMensualCredito() {
        return cuotaMensualCredito;
    }

    /**
     * Define el valor de la propiedad cuotaMensualCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCuotaMensualCredito(Double value) {
        this.cuotaMensualCredito = value;
    }

}
