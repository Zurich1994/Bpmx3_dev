//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.03.13 at 04:48:47 ���� CST 
//


package com.hotent.core.bpmn20.entity.ht;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.jee_soft.bpmn20ext package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Order_QNAME = new QName("http://www.jee-soft.cn/BPMN20EXT", "order");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.jee_soft.bpmn20ext
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.jee-soft.cn/BPMN20EXT", name = "order")
    public JAXBElement<Integer> createOrder(Integer value) {
        return new JAXBElement<Integer>(_Order_QNAME, Integer.class, null, value);
    }

}