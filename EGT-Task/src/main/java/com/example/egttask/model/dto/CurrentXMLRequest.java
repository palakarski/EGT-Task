package com.example.egttask.model.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "get")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrentXMLRequest {

    @XmlElement(name = "consumer")
    private String consumer;
    @XmlElement(name = "currency")
    private String currency;

}