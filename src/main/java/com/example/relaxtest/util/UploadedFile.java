package com.example.relaxtest.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UploadedFile {
    public String filePath;
    public UploadedFile(String filePath){
        this.filePath=filePath;
    }
    public String getFileStream() {
        return filePath;
    }

    public void setFileStream(String filePath) {
        this.filePath = filePath;
    }
    public boolean checkEmptyFilePath(){
        return filePath.isEmpty();
    }
    public IntStream createStream() throws FileNotFoundException {
        try{
            BufferedReader in= new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            Stream<String> numbers=in.lines();
            return numbers.mapToInt(x->Integer.parseInt(x));
        }
        catch (FileNotFoundException e){
            throw  new FileNotFoundException("Wrong file name ");
        }
    }
}
