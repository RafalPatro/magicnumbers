package pl.patro;

import java.util.HashMap;
import java.util.Map;

public class FileCodes {

    int maxFileCodeLength=0;

    private Map<byte[],String> extensionCodes = new HashMap<>();

    void addExtensionCode(String key,String value){
        if(key.length()> maxFileCodeLength){
            maxFileCodeLength=key.length();
        }
        byte[] data = new byte[key.length()/2];
        for(int i=0;i < key.length();i+=2) {
            data[i/2] = (Integer.decode("0x"+key.charAt(i)+key.charAt(i+1))).byteValue();
        }
        extensionCodes.put(data,value);
    }

    public Map<byte[], String> getMagicNumbers() {
        return extensionCodes;
    }

}
