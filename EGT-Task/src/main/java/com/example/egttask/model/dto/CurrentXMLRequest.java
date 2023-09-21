package com.example.egttask.model.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Consumer Id is mandatory")
    private String consumer;
    @NotBlank(message = "Currency Id is mandatory")
    @XmlElement(name = "currency")
    private String currency;

}