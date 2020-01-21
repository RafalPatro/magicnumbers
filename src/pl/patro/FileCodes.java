package pl.patro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FileCodes implements ExtensionInterface {

    private int maxFileCodeLength=0;

    private Map<byte[],String> extensionCodes = new HashMap<>();

    public void addExtensionCode(String key, String value){
        if(key.length()> maxFileCodeLength){
            setMaxFileCodeLength(key.length());
        }
        byte[] data = new byte[key.length()/2];
        for(int i=0;i < key.length();i+=2) {
            data[i/2] = (Integer.decode("0x"+key.charAt(i)+key.charAt(i+1))).byteValue();
        }
        extensionCodes.put(data,value);
    }

    public String decodeExtension(byte[] fileBytes){
        return extensionCodes.entrySet().stream()
                .filter(x-> Arrays.equals(fileBytes, 0,x.getKey().length,x.getKey(),0,x.getKey().length))
                .map(x->x.getValue())
                .collect(Collectors.joining());
    }

    public  boolean isSupportedExtension(String fileName){
        if(fileName.toLowerCase().endsWith("txt")){
            return true;
        }
        return extensionCodes.entrySet()
                .stream().anyMatch(x -> fileName.toLowerCase().endsWith(x.getValue()));
    }

    @Override
    public int maxLengthOfCodedExtension() {
        return getMaxFileCodeLength();
    }

    public int getMaxFileCodeLength() {
        return maxFileCodeLength;
    }

    private void setMaxFileCodeLength(int maxFileCodeLength) {
        this.maxFileCodeLength = maxFileCodeLength;
    }
}
