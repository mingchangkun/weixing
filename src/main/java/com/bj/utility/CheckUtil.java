package com.bj.utility;

import java.util.Arrays;

public class CheckUtil {
    private static final String token="wywming";
    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] arr=new String[]{token,timestamp,nonce};
        Arrays.sort(arr);
        //生成字符串
        StringBuffer buffer = new StringBuffer();
        for (String s : arr) {
            buffer.append(s);
        }
        //sha1加密
        String encode = Sha1.encode(buffer.toString());
        return encode.equals(signature);
    }
}
