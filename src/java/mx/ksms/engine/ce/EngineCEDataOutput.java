
package mx.ksms.engine.ce;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para engineCEDataOutput complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="engineCEDataOutput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="probabilidadDeMora" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="razonDeCobertura" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="dictamen_de_perfil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dictamen_capacidad_de_pago" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dictamen_conjunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dictamen_de_politicas" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dictamen_final" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="log" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "engineCEDataOutput", propOrder = {
    "probabilidadDeMora",
    "razonDeCobertura",
    "dictamenDePerfil",
    "dictamenCapacidadDePago",
    "dictamenConjunto",
    "dictamenDePoliticas",
    "dictamenFinal",
    "log"
})
public class EngineCEDataOutput {

    protected BigDecimal probabilidadDeMora;
    protected BigDecimal razonDeCobertura;
    @XmlElement(name = "dictamen_de_perfil")
    protected String dictamenDePerfil;
    @XmlElement(name = "dictamen_capacidad_de_pago")
    protected String dictamenCapacidadDePago;
    @XmlElement(name = "dictamen_conjunto")
    protected String dictamenConjunto;
    @XmlElement(name = "dictamen_de_politicas")
    protected String dictamenDePoliticas;
    @XmlElement(name = "dictamen_final")
    protected String dictamenFinal;
    protected String log;

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
     * Obtiene el valor de la propiedad dictamenDePoliticas.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDictamenDePoliticas() {
        return dictamenDePoliticas;
    }

    /**
     * Define el valor de la propiedad dictamenDePoliticas.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDictamenDePoliticas(String value) {
        this.dictamenDePoliticas = value;
    }

    /**
     * Obtiene el valor de la propiedad dictamenFinal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDictamenFinal() {
        return dictamenFinal;
    }

    /**
     * Define el valor de la propiedad dictamenFinal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDictamenFinal(String value) {
        this.dictamenFinal = value;
    }

    /**
     * Obtiene el valor de la propiedad log.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLog() {
        return log;
    }

    /**
     * Define el valor de la propiedad log.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLog(String value) {
        this.log = value;
    }

}
