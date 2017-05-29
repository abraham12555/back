
package mx.ksms.engine.cl;

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
 *         &lt;element name="ag_plazo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ag_periodicidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ag_riesgoocupacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EDAD" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ag_estadocivil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ag_servicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ANTIGVIVIENDA" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ingresos_fijos_mensuales" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ingresos_variables_mensuales" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="otros_ingresos" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cuota_credito" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
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
    "agPlazo",
    "agPeriodicidad",
    "agRiesgoocupacion",
    "edad",
    "agEstadocivil",
    "agServicio",
    "antigvivienda",
    "ingresosFijosMensuales",
    "ingresosVariablesMensuales",
    "otrosIngresos",
    "cuotaCredito"
})
public class CalculateDictamenDePerfil {

    protected String solicitudId;
    protected String riesgogeografico;
    @XmlElement(name = "ag_plazo")
    protected Integer agPlazo;
    @XmlElement(name = "ag_periodicidad")
    protected String agPeriodicidad;
    @XmlElement(name = "ag_riesgoocupacion")
    protected String agRiesgoocupacion;
    @XmlElement(name = "EDAD")
    protected Integer edad;
    @XmlElement(name = "ag_estadocivil")
    protected String agEstadocivil;
    @XmlElement(name = "ag_servicio")
    protected String agServicio;
    @XmlElement(name = "ANTIGVIVIENDA")
    protected Integer antigvivienda;
    @XmlElement(name = "ingresos_fijos_mensuales")
    protected Double ingresosFijosMensuales;
    @XmlElement(name = "ingresos_variables_mensuales")
    protected Double ingresosVariablesMensuales;
    @XmlElement(name = "otros_ingresos")
    protected Double otrosIngresos;
    @XmlElement(name = "cuota_credito")
    protected Double cuotaCredito;

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

}
