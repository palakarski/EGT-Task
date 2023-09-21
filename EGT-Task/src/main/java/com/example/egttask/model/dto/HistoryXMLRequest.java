package com.example.egttask.model.dto;

import jakarta.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "history")
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryXMLRequest extends CurrentXMLRequest {

    @XmlElement(name = "period")
    @NotNull(message = "Period is mandatory")
    private Integer period;

}