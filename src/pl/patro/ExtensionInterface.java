package pl.patro;

public interface ExtensionInterface {

     String decodeExtension(byte[] fileBytes);

     boolean isSupportedExtension(String fileName);

     int maxLengthOfCodedExtension();

     void addExtensionCode(String key,String value);


}
