package com.example.egttask.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@JacksonXmlRootElement(localName = "command")
@Data
public class CommandRequest {

    @NotBlank(message = "id cannot be blank")
    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @Valid
    @JacksonXmlProperty(localName = "get")
    private CurrentXMLRequest currentXMLRequest;
    @Valid
    @JacksonXmlProperty(localName = "history")
    private HistoryXMLRequest historyXMLRequest;

}

