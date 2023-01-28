package com.demotek.posterminal.Service;

import com.demotek.posterminal.restModel.PosTerminalReq;
import com.demotek.posterminal.restModel.Response;

public interface PosTerminalRegistrationService {
public Response doTerminalUpload(PosTerminalReq posTerminalReq) throws Exception;

}
