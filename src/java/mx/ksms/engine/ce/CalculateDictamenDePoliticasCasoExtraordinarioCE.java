
package mx.ksms.engine.ce;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para calculateDictamenDePoliticasCasoExtraordinarioCE complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="calculateDictamenDePoliticasCasoExtraordinarioCE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EDAD" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="experiencia_crediticia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditos_liquidados" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="clienteCredVigente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="renovacion1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ultimaFechaCredito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="avanceCapital1" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="avanceCapital2" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="clienteConRenovacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="atrasoPago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="malaFe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="porcentajeDedescuento" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="asalariado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="listaNegra" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateDictamenDePoliticasCasoExtraordinarioCE", propOrder = {
    "solicitudId",
    "servicios",
    "edad",
    "experienciaCrediticia",
    "creditosLiquidados",
    "clienteCredVigente",
    "renovacion1",
    "ultimaFechaCredito",
    "avanceCapital1",
    "avanceCapital2",
    "clienteConRenovacion",
    "atrasoPago",
    "malaFe",
    "porcentajeDedescuento",
    "asalariado",
    "listaNegra"
})
public class CalculateDictamenDePoliticasCasoExtraordinarioCE {

    protected String solicitudId;
    protected String servicios;
    @XmlElement(name = "EDAD")
    protected Integer edad;
    @XmlElement(name = "experiencia_crediticia")
    protected String experienciaCrediticia;
    @XmlElement(name = "creditos_liquidados")
    protected int creditosLiquidados;
    protected String clienteCredVigente;
    protected String renovacion1;
    protected String ultimaFechaCredito;
    protected Double avanceCapital1;
    protected Double avanceCapital2;
    protected String clienteConRenovacion;
    protected String atrasoPago;
    protected String malaFe;
    protected Double porcentajeDedescuento;
    protected Boolean asalariado;
    protected String listaNegra;

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
     * Obtiene el valor de la propiedad servicios.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicios() {
        return servicios;
    }

    /**
     * Define el valor de la propiedad servicios.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicios(String value) {
        this.servicios = value;
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
     * Obtiene el valor de la propiedad experienciaCrediticia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExperienciaCrediticia() {
        return experienciaCrediticia;
    }

    /**
     * Define el valor de la propiedad experienciaCrediticia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExperienciaCrediticia(String value) {
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
     * Obtiene el valor de la propiedad clienteCredVigente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClienteCredVigente() {
        return clienteCredVigente;
    }

    /**
     * Define el valor de la propiedad clienteCredVigente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClienteCredVigente(String value) {
        this.clienteCredVigente = value;
    }

    /**
     * Obtiene el valor de la propiedad renovacion1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenovacion1() {
        return renovacion1;
    }

    /**
     * Define el valor de la propiedad renovacion1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenovacion1(String value) {
        this.renovacion1 = value;
    }

    /**
     * Obtiene el valor de la propiedad ultimaFechaCredito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUltimaFechaCredito() {
        return ultimaFechaCredito;
    }

    /**
     * Define el valor de la propiedad ultimaFechaCredito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUltimaFechaCredito(String value) {
        this.ultimaFechaCredito = value;
    }

    /**
     * Obtiene el valor de la propiedad avanceCapital1.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAvanceCapital1() {
        return avanceCapital1;
    }

    /**
     * Define el valor de la propiedad avanceCapital1.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAvanceCapital1(Double value) {
        this.avanceCapital1 = value;
    }

    /**
     * Obtiene el valor de la propiedad avanceCapital2.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAvanceCapital2() {
        return avanceCapital2;
    }

    /**
     * Define el valor de la propiedad avanceCapital2.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAvanceCapital2(Double value) {
        this.avanceCapital2 = value;
    }

    /**
     * Obtiene el valor de la propiedad clienteConRenovacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClienteConRenovacion() {
        return clienteConRenovacion;
    }

    /**
     * Define el valor de la propiedad clienteConRenovacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClienteConRenovacion(String value) {
        this.clienteConRenovacion = value;
    }

    /**
     * Obtiene el valor de la propiedad atrasoPago.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtrasoPago() {
        return atrasoPago;
    }

    /**
     * Define el valor de la propiedad atrasoPago.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtrasoPago(String value) {
        this.atrasoPago = value;
    }

    /**
     * Obtiene el valor de la propiedad malaFe.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMalaFe() {
        return malaFe;
    }

    /**
     * Define el valor de la propiedad malaFe.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMalaFe(String value) {
        this.malaFe = value;
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

    /**
     * Obtiene el valor de la propiedad asalariado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAsalariado() {
        return asalariado;
    }

    /**
     * Define el valor de la propiedad asalariado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAsalariado(Boolean value) {
        this.asalariado = value;
    }

    /**
     * Obtiene el valor de la propiedad listaNegra.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListaNegra() {
        return listaNegra;
    }

    /**
     * Define el valor de la propiedad listaNegra.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListaNegra(String value) {
        this.listaNegra = value;
    }

}
