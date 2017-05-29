
package mx.ksms.engine.ce;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para calculateProbResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="calculateProbResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EngineCEDataOutput" type="{http://ce.engine.ksms.mx/}engineCEDataOutput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculateProbResponse", propOrder = {
    "engineCEDataOutput"
})
public class CalculateProbResponse {

    @XmlElement(name = "EngineCEDataOutput")
    protected EngineCEDataOutput engineCEDataOutput;

    /**
     * Obtiene el valor de la propiedad engineCEDataOutput.
     * 
     * @return
     *     possible object is
     *     {@link EngineCEDataOutput }
     *     
     */
    public EngineCEDataOutput getEngineCEDataOutput() {
        return engineCEDataOutput;
    }

    /**
     * Define el valor de la propiedad engineCEDataOutput.
     * 
     * @param value
     *     allowed object is
     *     {@link EngineCEDataOutput }
     *     
     */
    public void setEngineCEDataOutput(EngineCEDataOutput value) {
        this.engineCEDataOutput = value;
    }

}
