package com.demotek.posterminal.Service.impl;

import com.demotek.posterminal.restModel.PosTerminalReq;
import com.demotek.posterminal.utils.DBConnection;
import com.demotek.posterminal.Service.PosTerminalRegistrationService;
import com.demotek.posterminal.restModel.Response;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;

@Service
public class PosTerminalRegistration  implements PosTerminalRegistrationService {

    public static Logger logger = Logger.getLogger(PosTerminalRegistration.class);

    @Override
    public Response doTerminalUpload(PosTerminalReq req) throws Exception {

            logger.info("calling UploadPOSTerminal with F_check 1: " + req.toString());
            Connection conn = null;
            CallableStatement stmt = null;
            String finalResponse = "";
            final Response resp = new Response();
         //   final HeaderResponse respHeader = new HeaderResponse();
            try {
                logger.info("Inside the try block...F_check 2 ");

                logger.info("Affiliate Code: F_check 3 :" + req.getAffiliateCode());
                conn = DBConnection.getDataAffBaseConnection2(req.getAffiliateCode());

                stmt = conn.prepareCall("{call union_inquiry_pkg.pr_upload_terminals(?,?,?,?,?,?,?)}");
                stmt.registerOutParameter(6, Types.VARCHAR);
                stmt.registerOutParameter(7, Types.VARCHAR);
                stmt.setString(1, req.getTerminalId());
                stmt.setString(2, req.getCashGlBranchCode());
                stmt.setString(3, req.getCashGlAccountNo());
                stmt.setString(4, req.getTerminalAddress());
                stmt.setString(5, req.getChannel());
                stmt.execute();
                finalResponse = stmt.getString(6);
                logger.info(req.getRequestId() +" The final Response is: req.requestId F_check 4: "  + finalResponse);
                if (finalResponse.equals("00")) {
                    logger.info("Data returned from pr_upload_terminals  F_check 5 ");
                    resp.setResponseCode(stmt.getString(6) == null ? "" : stmt.getString(6));
                    resp.setResponseMessage(stmt.getString(7) == null ? "" : stmt.getString(7));

                    logger.info(req.getRequestId()+ " The response message is: F_check 6: " + stmt.getString(7) == null ? "" : stmt.getString(7));
                    logger.info(req.getRequestId()+ " The response prepared is: F_check 7: " + resp.toString());
                } else {
                    logger.info(req.getRequestId()+ " An error was returned with the following code F_check 8: " + stmt.getString(6) == null ? "" : stmt.getString(6) + " and message "
                            + "" + stmt.getString(7) == null ? "" : stmt.getString(7));
                    resp.setResponseCode(stmt.getString(6) == null ? "" : stmt.getString(6));
                    resp.setResponseMessage(stmt.getString(7) == null ? "" : stmt.getString(7));

                }
            } catch (SQLException ex) {
                logger.info(req.getRequestId()+ " SQLException error F_check 9 ", ex);
                resp.setResponseCode("E07");
                resp.setResponseMessage("SQL Exception F_check 9" +ex);

                logger.info("SQLException.  Reason: " + ex.getMessage() + ". SQLState:" + ex.getSQLState() + ". ErrorCode:" + ex.getErrorCode());
                logger.info("SQLException " + Arrays.toString(ex.getStackTrace()).replaceAll(", ", "\n"));
                logger.info("SQLException " + ex.getLocalizedMessage());
            } catch (Exception ex) {
                logger.info(req.getRequestId()+ " Throwable in POSTERMINaLS F_check 10 ", ex);
                resp.setResponseCode("E07");
                resp.setResponseMessage("Other Exception F_check 10: "+ex);

                logger.info("Throwable.  Reason: " + ex.getMessage());
                logger.info("Throwable " + Arrays.toString(ex.getStackTrace()).replaceAll(", ", "\n"));

            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception ex) {
                    logger.info("Exception", ex);
                }
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception ex) {
                    logger.info("Exception", ex);
                }
            }
            return resp;
        }

}
