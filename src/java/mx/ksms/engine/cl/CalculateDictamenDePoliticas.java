
package mx.ksms.engine.cl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para calculateDictamenDePoliticas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="calculateDictamenDePoliticas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicios" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EDAD" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateDictamenDePoliticas", propOrder = {
    "solicitudId",
    "servicios",
    "edad"
})
public class CalculateDictamenDePoliticas {

    protected String solicitudId;
    protected String servicios;
    @XmlElement(name = "EDAD")
    protected Integer edad;

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

}
