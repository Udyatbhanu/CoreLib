package ubbs.home.com.core.lib.utilities;

import java.util.HashMap;

/**
 * Created by udyatbhanu-mac on 7/27/15.
 */
public class ApplicationSession {

    private static HashMap<String, String> sessionParam;

    private ApplicationSession(){

    }

    /**
     *
     * @param key
     * @param value
     */
    public static void setSessionParam(String key, String value){

        if(null==sessionParam){
            sessionParam = new HashMap<String, String>();

        }
        sessionParam.put(key, value);

    }


    /**
     *
     * @param key
     * @return
     */
    public static Object getSessionParam(String key){
        Object param = sessionParam.get(key);
        return param;
    }
}
