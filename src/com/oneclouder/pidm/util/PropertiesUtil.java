package com.oneclouder.pidm.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Properties;

/**
 * Created by clouder on 16-9-11.
 */
public class PropertiesUtil {


    private static Properties props;

    static {
        try {
            props = new Properties();
            InputStream fis =PropertiesUtil.class.getResourceAsStream("/ftp.properties");
            props.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static String getProperty(String key){
        return props.getProperty(key);
    }



}
