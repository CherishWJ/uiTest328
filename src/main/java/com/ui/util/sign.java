package com.ui.util;

//import sun.security.mscapi.RSASignature;
import sun.security.provider.MD5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*private String createSign(Map<String, Object> params, String accessSecret) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = String.valueOf(value);
            }
            temp.append(valueString);
        }
        temp.append("&").append(ACCESS_SECRET).append("=").append(accessSecret);
        return MD5Util.MD52(temp.toString()).toUpperCase();
    }*/
public class sign {

    //Map<String,String> postData = new HashMap<String,String>();

    public static void getSign(Map<String, Object> params,String secret){
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = String.valueOf(value);
            }
            temp.append(valueString);
        }

        // return md5(sha1($signature)) . '_' . $postData['api_time_stamp'] . '_' . $postData['api_random_number'];
       // return MD5(RSASignature.SHA1)
    }











    }

