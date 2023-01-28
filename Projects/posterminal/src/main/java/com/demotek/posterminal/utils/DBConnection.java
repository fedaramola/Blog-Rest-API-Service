package com.demotek.posterminal.utils;


import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
//import org.apache.log4j.Logger;
//import weblogic.i18n.logging.NonCatalogLogger;

/**
 *
 * @author aokoh
 */
public class DBConnection {

    public static Logger logger = Logger.getLogger(DBConnection.class);
    //NonCatalogLogger logger = new NonCatalogLogger("");

    public static Connection getDataAffBaseConnection2(String AffiliateCode) {
        Context initCtx = null;
        DataSource ds = null;
        Connection conn = null;
        try {
            initCtx = new InitialContext();
            String JndiConnection = TerminalProperties.getMessage(AffiliateCode);
            ds = (DataSource) initCtx.lookup("jdbc/" + JndiConnection + "FlexDataSourceFWS");
            conn = ds.getConnection();
          //  logger.info("Connection retrieved. SCHEMA: {" + conn.getSchema() + "} affcode: "+ AffiliateCode);
            //logger.info("Connection " + conn.toString());
        } catch (SQLException ex) {
            logger.info("SQLException thrown in getDataBaseConnection. Reason:" + ex.getMessage());
            logger.info("SQLException " + Arrays.toString(ex.getStackTrace()).replaceAll(", ", "\n"));
        } catch (NamingException ex) {
            logger.info("Exception thrown in getDataBaseConnection. Reason:" + ex.getMessage());
            logger.info("Exception " + Arrays.toString(ex.getStackTrace()).replaceAll(", ", "\n"));
        } catch (Throwable ex) {
            logger.info("Exception thrown in getDataBaseConnection. Reason:" + ex.getMessage());
            logger.info("Exception " + Arrays.toString(ex.getStackTrace()).replaceAll(", ", "\n"));
        }
        return conn;
    }

    

}
