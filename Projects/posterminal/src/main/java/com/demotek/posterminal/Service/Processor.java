package com.demotek.posterminal.Service;

import com.demotek.posterminal.restModel.PosTerminalReq;
import com.demotek.posterminal.Service.impl.PosTerminalRegistration;
import com.demotek.posterminal.restModel.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Processor {

    public static Logger logger = Logger.getLogger(Processor.class);
    @Autowired
    PosTerminalRegistration  posTerminalRegistration;


    public Response TerminalsUpload(PosTerminalReq posTerminalReq) throws Exception{
        logger.info("<<<<<<PosTerminalReq>>>>>>");
      return   posTerminalRegistration.doTerminalUpload(posTerminalReq);
    }
}
