package pl.patro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileService{

    File file;

    public FileService(String filename) {
        this.file = new File(filename);
    }

    byte[] getMagicNumbers(int size) throws IOException, EmptyFileException {
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] fileBytes = new byte[size];
            int inputSize= fileInputStream.read(fileBytes);
            if(inputSize == -1){
                throw new EmptyFileException();
            }
            return fileBytes;
        }
    }



}
