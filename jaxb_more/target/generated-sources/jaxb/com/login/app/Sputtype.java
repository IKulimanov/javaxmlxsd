//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.01.15 at 02:43:35 PM MSK 
//


package com.login.app;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sputtype complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sputtype">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="namesputnik" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="agesputnik" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sputtype", propOrder = {
    "namesputnik",
    "agesputnik"
})
public class Sputtype {

    @XmlElement(required = true)
    protected String namesputnik;
    @XmlElement(required = true)
    protected String agesputnik;

    /**
     * Gets the value of the namesputnik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamesputnik() {
        return namesputnik;
    }

    /**
     * Sets the value of the namesputnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamesputnik(String value) {
        this.namesputnik = value;
    }

    /**
     * Gets the value of the agesputnik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgesputnik() {
        return agesputnik;
    }

    /**
     * Sets the value of the agesputnik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgesputnik(String value) {
        this.agesputnik = value;
    }

}
