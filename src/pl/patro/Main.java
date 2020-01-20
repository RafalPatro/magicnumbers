package pl.patro;

import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {

        String fileName = null;

        try{
            fileName = args[0];
        } catch (ArrayIndexOutOfBoundsException e ){
            System.out.println("type filename as argument");
            System.exit(1);
        }

        FileCodes fileCodes = new FileCodes();
        fileCodes.addExtensionCode("FFD8FFDB","jpg");
        fileCodes.addExtensionCode("FFD8FFE000104A4649460001","jpg");
        fileCodes.addExtensionCode("FFD8FFEE","jpg");
        fileCodes.addExtensionCode("474946383761","gif");
        fileCodes.addExtensionCode("474946383961","gif");
        fileCodes.addExtensionCode("89504E470D0A1A0A","png");
        fileCodes.addExtensionCode("CAFEBABE","class");


        MagicNumbersService magicNumbersService = new MagicNumbersService();

        if(magicNumbersService.isSupportedExtension(fileName,fileCodes.getMagicNumbers())){
            byte[] bytes = magicNumbersService.getFilesMagicNumbers(fileName,fileCodes.maxFileCodeLength);

            String realExtension = magicNumbersService.decodeExtension(fileCodes.getMagicNumbers(),bytes);

            if(realExtension.isEmpty() && fileName.toLowerCase().endsWith("txt")){
                System.out.println("Its txt file");
            } else if(fileName.toLowerCase().endsWith(realExtension)){
                System.out.println("Its "+realExtension+" file");
            } else {
                String[] splitFilename = fileName.split("\\.");
                System.out.println("Extension is "+ splitFilename[splitFilename.length-1]+ ", while actually it's a "+ realExtension);
            }
        }else{
            throw new NoSuchElementException("This extension is not supported");
        }











    }
}
