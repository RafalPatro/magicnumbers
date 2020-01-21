package pl.patro;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args)  {

        if(args.length==0){
            System.out.println("Type filename as argument");
            return;
        }
        String fileName = args[0];

        ExtensionInterface fileCodes = new FileCodes();

        fileCodes.addExtensionCode("FFD8FFDB","jpg");
        fileCodes.addExtensionCode("FFD8FFE000104A4649460001","jpg");
        fileCodes.addExtensionCode("FFD8FFEE","jpg");
        fileCodes.addExtensionCode("474946383761","gif");
        fileCodes.addExtensionCode("474946383961","gif");
        fileCodes.addExtensionCode("89504E470D0A1A0A","png");
        fileCodes.addExtensionCode("CAFEBABE","class");


        if(fileCodes.isSupportedExtension(fileName)){
            FileService fileService = new FileService(fileName);
            byte[] bytes;
            try {
                bytes = fileService.getMagicNumbers(fileCodes.maxLengthOfCodedExtension());
            } catch (FileNotFoundException e) {
               System.out.println("Wrong filename");
               return;
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } catch (EmptyFileException e) {
                System.out.println("File is empty");
                return;
            }
            String realExtension = fileCodes.decodeExtension(bytes);

            String[] splitFilename = fileName.split("\\.");
            if(realExtension.isEmpty() && fileName.toLowerCase().endsWith("txt")){
                System.out.println("Its txt file");
            } else if(realExtension.isEmpty()){
                System.out.println("Extension is "+ splitFilename[splitFilename.length-1]+ ", while actually it's a txt");
            } else if(fileName.toLowerCase().endsWith(realExtension)){
                System.out.println("Its "+realExtension+" file");
            } else {
                System.out.println("Extension is "+ splitFilename[splitFilename.length-1]+ ", while actually it's a "+ realExtension);
            }
        }else{
            throw new NoSuchElementException("This extension is not supported");
        }











    }
}
