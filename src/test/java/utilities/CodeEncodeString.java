package utilities;

import org.apache.commons.codec.binary.Base64;


public class CodeEncodeString {

   public static String decodeString(String stringToDecode){
        byte[] decodedString = Base64.decodeBase64(stringToDecode);
        return (new String(decodedString));
    }


    public static String encodeString(String stringToEncode){
        byte[] encodedString = Base64.encodeBase64(stringToEncode.getBytes());
        return (new String(encodedString));
    }


}
