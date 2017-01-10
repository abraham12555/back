
package mx.ksms.engine.cl;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para engineDataOutput complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="engineDataOutput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dictamen_de_perfil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dictamen_capacidad_de_pago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dictamen_conjunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="probabilidadDeMora" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="razonDeCobertura" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="riesgogeografico" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ag_plazo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ag_periodicidad" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="R_CUO_INGFU" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ag_riesgoocupacion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="EDAD" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="R_COB_SLDPAS12" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ag_estadocivil" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ag_servicio" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="INDICAPASNOVISTA" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ANTIGVIVIENDA" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ag_hist12_hist24" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="ag_mop_peor_sis" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="R_DSPTOCON_SIS" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="d1_r_conoe1con_oe" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="R_CONOE1CON_OE" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "engineDataOutput", propOrder = {
    "dictamenDePerfil",
    "dictamenCapacidadDePago",
    "dictamenConjunto",
    "probabilidadDeMora",
    "razonDeCobertura",
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
    "rconoe1CONOE"
})
public class EngineDataOutput {

    @XmlElement(name = "dictamen_de_perfil")
    protected String dictamenDePerfil;
    @XmlElement(name = "dictamen_capacidad_de_pago")
    protected String dictamenCapacidadDePago;
    @XmlElement(name = "dictamen_conjunto")
    protected String dictamenConjunto;
    protected BigDecimal probabilidadDeMora;
    protected BigDecimal razonDeCobertura;
    protected double riesgogeografico;
    @XmlElement(name = "ag_plazo")
    protected double agPlazo;
    @XmlElement(name = "ag_periodicidad")
    protected double agPeriodicidad;
    @XmlElement(name = "R_CUO_INGFU")
    protected double rcuoingfu;
    @XmlElement(name = "ag_riesgoocupacion")
    protected double agRiesgoocupacion;
    @XmlElement(name = "EDAD")
    protected double edad;
    @XmlElement(name = "R_COB_SLDPAS12")
    protected double rcobsldpas12;
    @XmlElement(name = "ag_estadocivil")
    protected double agEstadocivil;
    @XmlElement(name = "ag_servicio")
    protected double agServicio;
    @XmlElement(name = "INDICAPASNOVISTA")
    protected double indicapasnovista;
    @XmlElement(name = "ANTIGVIVIENDA")
    protected double antigvivienda;
    @XmlElement(name = "ag_hist12_hist24")
    protected double agHist12Hist24;
    @XmlElement(name = "ag_mop_peor_sis")
    protected double agMopPeorSis;
    @XmlElement(name = "R_DSPTOCON_SIS")
    protected double rdsptoconsis;
    @XmlElement(name = "d1_r_conoe1con_oe")
    protected double d1RConoe1ConOe;
    @XmlElement(name = "R_CONOE1CON_OE")
    protected double rconoe1CONOE;

    /**
     * Obtiene el valor de la propiedad dictamenDePerfil.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDictamenDePerfil() {
        return dictamenDePerfil;
    }

    /**
     * Define el valor de la propiedad dictamenDePerfil.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDictamenDePerfil(String value) {
        this.dictamenDePerfil = value;
    }

    /**
     * Obtiene el valor de la propiedad dictamenCapacidadDePago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDictamenCapacidadDePago() {
        return dictamenCapacidadDePago;
    }

    /**
     * Define el valor de la propiedad dictamenCapacidadDePago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDictamenCapacidadDePago(String value) {
        this.dictamenCapacidadDePago = value;
    }

    /**
     * Obtiene el valor de la propiedad dictamenConjunto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDictamenConjunto() {
        return dictamenConjunto;
    }

    /**
     * Define el valor de la propiedad dictamenConjunto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDictamenConjunto(String value) {
        this.dictamenConjunto = value;
    }

    /**
     * Obtiene el valor de la propiedad probabilidadDeMora.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProbabilidadDeMora() {
        return probabilidadDeMora;
    }

    /**
     * Define el valor de la propiedad probabilidadDeMora.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProbabilidadDeMora(BigDecimal value) {
        this.probabilidadDeMora = value;
    }

    /**
     * Obtiene el valor de la propiedad razonDeCobertura.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRazonDeCobertura() {
        return razonDeCobertura;
    }

    /**
     * Define el valor de la propiedad razonDeCobertura.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRazonDeCobertura(BigDecimal value) {
        this.razonDeCobertura = value;
    }

    /**
     * Obtiene el valor de la propiedad riesgogeografico.
     * 
     */
    public double getRiesgogeografico() {
        return riesgogeografico;
    }

    /**
     * Define el valor de la propiedad riesgogeografico.
     * 
     */
    public void setRiesgogeografico(double value) {
        this.riesgogeografico = value;
    }

    /**
     * Obtiene el valor de la propiedad agPlazo.
     * 
     */
    public double getAgPlazo() {
        return agPlazo;
    }

    /**
     * Define el valor de la propiedad agPlazo.
     * 
     */
    public void setAgPlazo(double value) {
        this.agPlazo = value;
    }

    /**
     * Obtiene el valor de la propiedad agPeriodicidad.
     * 
     */
    public double getAgPeriodicidad() {
        return agPeriodicidad;
    }

    /**
     * Define el valor de la propiedad agPeriodicidad.
     * 
     */
    public void setAgPeriodicidad(double value) {
        this.agPeriodicidad = value;
    }

    /**
     * Obtiene el valor de la propiedad rcuoingfu.
     * 
     */
    public double getRCUOINGFU() {
        return rcuoingfu;
    }

    /**
     * Define el valor de la propiedad rcuoingfu.
     * 
     */
    public void setRCUOINGFU(double value) {
        this.rcuoingfu = value;
    }

    /**
     * Obtiene el valor de la propiedad agRiesgoocupacion.
     * 
     */
    public double getAgRiesgoocupacion() {
        return agRiesgoocupacion;
    }

    /**
     * Define el valor de la propiedad agRiesgoocupacion.
     * 
     */
    public void setAgRiesgoocupacion(double value) {
        this.agRiesgoocupacion = value;
    }

    /**
     * Obtiene el valor de la propiedad edad.
     * 
     */
    public double getEDAD() {
        return edad;
    }

    /**
     * Define el valor de la propiedad edad.
     * 
     */
    public void setEDAD(double value) {
        this.edad = value;
    }

    /**
     * Obtiene el valor de la propiedad rcobsldpas12.
     * 
     */
    public double getRCOBSLDPAS12() {
        return rcobsldpas12;
    }

    /**
     * Define el valor de la propiedad rcobsldpas12.
     * 
     */
    public void setRCOBSLDPAS12(double value) {
        this.rcobsldpas12 = value;
    }

    /**
     * Obtiene el valor de la propiedad agEstadocivil.
     * 
     */
    public double getAgEstadocivil() {
        return agEstadocivil;
    }

    /**
     * Define el valor de la propiedad agEstadocivil.
     * 
     */
    public void setAgEstadocivil(double value) {
        this.agEstadocivil = value;
    }

    /**
     * Obtiene el valor de la propiedad agServicio.
     * 
     */
    public double getAgServicio() {
        return agServicio;
    }

    /**
     * Define el valor de la propiedad agServicio.
     * 
     */
    public void setAgServicio(double value) {
        this.agServicio = value;
    }

    /**
     * Obtiene el valor de la propiedad indicapasnovista.
     * 
     */
    public double getINDICAPASNOVISTA() {
        return indicapasnovista;
    }

    /**
     * Define el valor de la propiedad indicapasnovista.
     * 
     */
    public void setINDICAPASNOVISTA(double value) {
        this.indicapasnovista = value;
    }

    /**
     * Obtiene el valor de la propiedad antigvivienda.
     * 
     */
    public double getANTIGVIVIENDA() {
        return antigvivienda;
    }

    /**
     * Define el valor de la propiedad antigvivienda.
     * 
     */
    public void setANTIGVIVIENDA(double value) {
        this.antigvivienda = value;
    }

    /**
     * Obtiene el valor de la propiedad agHist12Hist24.
     * 
     */
    public double getAgHist12Hist24() {
        return agHist12Hist24;
    }

    /**
     * Define el valor de la propiedad agHist12Hist24.
     * 
     */
    public void setAgHist12Hist24(double value) {
        this.agHist12Hist24 = value;
    }

    /**
     * Obtiene el valor de la propiedad agMopPeorSis.
     * 
     */
    public double getAgMopPeorSis() {
        return agMopPeorSis;
    }

    /**
     * Define el valor de la propiedad agMopPeorSis.
     * 
     */
    public void setAgMopPeorSis(double value) {
        this.agMopPeorSis = value;
    }

    /**
     * Obtiene el valor de la propiedad rdsptoconsis.
     * 
     */
    public double getRDSPTOCONSIS() {
        return rdsptoconsis;
    }

    /**
     * Define el valor de la propiedad rdsptoconsis.
     * 
     */
    public void setRDSPTOCONSIS(double value) {
        this.rdsptoconsis = value;
    }

    /**
     * Obtiene el valor de la propiedad d1RConoe1ConOe.
     * 
     */
    public double getD1RConoe1ConOe() {
        return d1RConoe1ConOe;
    }

    /**
     * Define el valor de la propiedad d1RConoe1ConOe.
     * 
     */
    public void setD1RConoe1ConOe(double value) {
        this.d1RConoe1ConOe = value;
    }

    /**
     * Obtiene el valor de la propiedad rconoe1CONOE.
     * 
     */
    public double getRCONOE1CONOE() {
        return rconoe1CONOE;
    }

    /**
     * Define el valor de la propiedad rconoe1CONOE.
     * 
     */
    public void setRCONOE1CONOE(double value) {
        this.rconoe1CONOE = value;
    }

}
