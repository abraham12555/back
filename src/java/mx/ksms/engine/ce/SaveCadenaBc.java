
package mx.ksms.engine.ce;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para save_cadena_bc complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="save_cadena_bc">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="solicitudId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cadena_bc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "save_cadena_bc", propOrder = {
    "solicitudId",
    "cadenaBc"
})
public class SaveCadenaBc {

    protected String solicitudId;
    @XmlElement(name = "cadena_bc")
    protected String cadenaBc;

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

}
