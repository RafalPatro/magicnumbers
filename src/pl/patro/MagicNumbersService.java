package pl.patro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MagicNumbersService {

    byte[] getFilesMagicNumbers(String fileName, int size) {
        File file = new File(fileName);
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] fileBytes = new byte[size];
            fileInputStream.read(fileBytes);
            return fileBytes;
        } catch (FileNotFoundException e) {
           System.out.println("File no exist");
           System.exit(2);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(3);
        }
        return new byte[0];
    }

    String decodeExtension(Map<byte[],String> map, byte[] fileBytes){
        return map.entrySet().stream()
                .filter(x-> Arrays.equals(fileBytes, 0,x.getKey().length,x.getKey(),0,x.getKey().length))
                .map(x->x.getValue())
                .collect(Collectors.joining());
    }

    boolean isSupportedExtension(String fileName, Map<byte[],String>map){
        if(fileName.toLowerCase().endsWith("txt")){
            return true;
        }
        return map.entrySet()
                .stream().anyMatch(x -> fileName.toLowerCase().endsWith(x.getValue()));
    }

}
