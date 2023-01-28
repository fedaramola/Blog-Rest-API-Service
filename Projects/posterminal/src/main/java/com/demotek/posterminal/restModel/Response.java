package com.demotek.posterminal.restModel;

import lombok.Data;



@Data
public class Response {
    private String responseCode;
    private String responseMessage;

    private Object data;
  //  private final HashMap<String, Object> artefacts = new HashMap<>();
}
