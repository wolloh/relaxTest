package com.example.relaxtest.services.impl;

import com.example.relaxtest.enums.SequenceType;
import com.example.relaxtest.exception.NotUploadedFileException;
import com.example.relaxtest.exception.SequenceNotFoundException;
import com.example.relaxtest.model.FileDTO;
import com.example.relaxtest.services.ApiService;
import com.example.relaxtest.util.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class ApiServiceImpl implements ApiService {


    public UploadedFile uploadedFile;
    @Autowired
    public ApiServiceImpl(UploadedFile uploadedFile){
        this.uploadedFile = uploadedFile;
    }
    @Override
    @Cacheable("max")
    public Integer findMaxNumber() throws FileNotFoundException {
        if(uploadedFile.checkEmptyFilePath())
            throw new NotUploadedFileException("Upload a file");
        IntStream numberSequence= uploadedFile.createStream();
        return numberSequence.max().orElseThrow();
    }

    @Override
    @Cacheable(value = "min")
    public Integer findMinNumber() throws FileNotFoundException {
        if(uploadedFile.checkEmptyFilePath())
            throw new NotUploadedFileException("Upload a file");
       IntStream numberSequence= uploadedFile.createStream();
       return numberSequence.min().orElseThrow();

    }

    @Override
    @Cacheable("median")
    public Double findMedian() throws FileNotFoundException {
        if(uploadedFile.checkEmptyFilePath())
            throw new NotUploadedFileException("Upload a file");
        List<Integer> numberSequence= uploadedFile.createStream()
                .boxed()
                .sorted()
                .toList();
        Double median;
        int size=numberSequence.size();
        if(size%2==0){
            median=numberSequence.get(size/2).doubleValue()+numberSequence.get(size/2-1);
        }
        else{
            median=numberSequence.get(size/2).doubleValue();
        }
        return median;
    }

    @Override
    @Cacheable("mean")
    public Double findMean() throws FileNotFoundException {
        if(uploadedFile.checkEmptyFilePath())
            throw new NotUploadedFileException("Upload a file");
        IntStream numberSequence= uploadedFile.createStream();
        return numberSequence.average().orElseThrow();
    }


    @Override
    @Cacheable(value = "sequences",key = "#type")

    public ArrayList<ArrayList<Integer>> findSequences( SequenceType type) throws FileNotFoundException {
        if(uploadedFile.checkEmptyFilePath())
            throw new NotUploadedFileException("Upload a file");
        List<Integer> numberSequence= uploadedFile.createStream()
                .boxed()
                .toList();
        ArrayList<ArrayList<Integer>> sequenceArray=new ArrayList<ArrayList<Integer>>();
        Integer temp;
        Integer maxCount=0;
        ArrayList<Integer> currMaxSeq=new ArrayList<>();
        currMaxSeq.add(numberSequence.get(0));
        Integer tempCount=1;
        int i=1;
        while(i!=numberSequence.size()){
            temp=numberSequence.get(i);
            if((type.equals(SequenceType.ASCENDING) && temp>currMaxSeq.get(currMaxSeq.size()-1)) || ( type.equals(SequenceType.DESCENDING) && temp<currMaxSeq.get(currMaxSeq.size()-1))){
                    currMaxSeq.add(temp);
                    tempCount++;
                }
                else {
                    if (tempCount > maxCount) {
                        sequenceArray.clear();
                        sequenceArray.add(new ArrayList<>(currMaxSeq));
                        maxCount = tempCount;
                    } else if (tempCount == maxCount) {
                        sequenceArray.add(new ArrayList<>(currMaxSeq));
                    }
                    tempCount = 1;
                    currMaxSeq.clear();
                    currMaxSeq.add(temp);
                }
                i++;
        }
        if(tempCount>maxCount){
                sequenceArray.clear();
                sequenceArray.add(new ArrayList<>(currMaxSeq));
                maxCount=tempCount;
            }
        else if(tempCount==maxCount){
                sequenceArray.add(new ArrayList<>(currMaxSeq));
            }
        if(maxCount==1){
            throw new SequenceNotFoundException("There is no Sequence in file");
        }
        return sequenceArray;
    }


    @Override
    public String uploadFile(FileDTO fileDTO){
        uploadedFile.setFileStream(fileDTO.pathFile());
        return fileDTO.pathFile() + " Successfully upload";

    }

}