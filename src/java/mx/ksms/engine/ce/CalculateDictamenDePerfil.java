
package mx.ksms.engine.ce;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para calculateDictamenDePerfil complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="calculateDictamenDePerfil">
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
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateDictamenDePerfil", propOrder = {
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
    "rcobsldpas12"
})
public class CalculateDictamenDePerfil {

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

}
