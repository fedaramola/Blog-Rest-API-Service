package com.demotek.posterminal.Terminalcontroller;

import com.demotek.posterminal.Service.Processor;
import com.demotek.posterminal.restModel.PosTerminalReq;
import com.demotek.posterminal.restModel.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    Processor processor;

    final Logger logger =  Logger.getLogger(Controller.class);

    @RequestMapping("uploadTerminals")
    public Response doTerminalsUpload(@RequestBody PosTerminalReq posTerminalReq) throws Exception{
        logger.info("Input parameter posTerminal: "+ posTerminalReq);
        return processor.TerminalsUpload(posTerminalReq);
    }
}
