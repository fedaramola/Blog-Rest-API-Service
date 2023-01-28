package com.demotek.posterminal.restModel;

import lombok.Data;

@Data
public class PosTerminalReq {

    String affiliateCode;
    String requestId;
    String sourceCode;
    String terminalId;
    String cashGlAccountNo;
    String cashGlBranchCode;
    String terminalAddress;
    String channel;
}
