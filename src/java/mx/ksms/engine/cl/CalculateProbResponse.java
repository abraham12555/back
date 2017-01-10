
package mx.ksms.engine.cl;

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
 *         &lt;element name="EngineDataOutput" type="{http://cl.engine.ksms.mx/}engineDataOutput" minOccurs="0"/>
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
    "engineDataOutput"
})
public class CalculateProbResponse {

    @XmlElement(name = "EngineDataOutput")
    protected EngineDataOutput engineDataOutput;

    /**
     * Obtiene el valor de la propiedad engineDataOutput.
     * 
     * @return
     *     possible object is
     *     {@link EngineDataOutput }
     *     
     */
    public EngineDataOutput getEngineDataOutput() {
        return engineDataOutput;
    }

    /**
     * Define el valor de la propiedad engineDataOutput.
     * 
     * @param value
     *     allowed object is
     *     {@link EngineDataOutput }
     *     
     */
    public void setEngineDataOutput(EngineDataOutput value) {
        this.engineDataOutput = value;
    }

}
