package com.example.egttask.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@JacksonXmlRootElement(localName = "command")
@Data
public class CommandRequest {

    @JacksonXmlProperty(isAttribute = true)
    private String id;
    @JacksonXmlProperty(localName = "get")
    private CurrentXMLRequest currentXMLRequest;
    @JacksonXmlProperty(localName = "history")
    private HistoryXMLRequest historyXMLRequest;

}

