//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.26 at 04:53:23 PM WEST 
//


package br.com.klayrocha;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerDetail" type="{http://klayrocha.com.br}CustomerDetail"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "customerDetail"
})
@XmlRootElement(name = "InsertCustomerRequisicao")
public class InsertCustomerRequisicao {

    @XmlElement(name = "CustomerDetail", required = true)
    protected CustomerDetail customerDetail;

    /**
     * Gets the value of the customerDetail property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerDetail }
     *     
     */
    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    /**
     * Sets the value of the customerDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerDetail }
     *     
     */
    public void setCustomerDetail(CustomerDetail value) {
        this.customerDetail = value;
    }

}
