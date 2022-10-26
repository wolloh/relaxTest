package com.example.relaxtest.services.impl;

import com.example.relaxtest.enums.SequenceType;
import com.example.relaxtest.exception.SequenceNotFoundException;
import com.example.relaxtest.model.FileDTO;
import com.example.relaxtest.services.ApiService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class ApiServiceImpl implements ApiService {

    @Override
    @Cacheable("max")
    public Integer findMaxNumber(FileDTO dto) throws FileNotFoundException {

        IntStream numberSequence=createListFromFile(dto.pathFile());
        return numberSequence.max().orElseThrow();
    }

    @Override
    @Cacheable("min")
    public Integer findMinNumber(FileDTO dto) throws FileNotFoundException {
       IntStream numberSequence=createListFromFile(dto.pathFile());
       return numberSequence.min().orElseThrow();

    }

    @Override
    @Cacheable("median")
    public Double findMedian(FileDTO dto) throws FileNotFoundException {
        List<Integer> numberSequence=createListFromFile(dto.pathFile())
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
    public Double findMean(FileDTO dto) throws FileNotFoundException {
        IntStream numberSequence=createListFromFile(dto.pathFile());
        return numberSequence.average().orElseThrow();
    }


    @Override
    @Cacheable(value = "sequences",key = "#type")

    public ArrayList<ArrayList<Integer>> findSequences(FileDTO dto, SequenceType type) throws FileNotFoundException {
        List<Integer> numberSequence=createListFromFile(dto.pathFile())
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

    public IntStream createListFromFile(String filePath) throws FileNotFoundException {
        try{
            BufferedReader in= new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            Stream<String> numbers=in.lines();
            return numbers.mapToInt(x->Integer.parseInt(x));
        }
        catch (FileNotFoundException e){
            throw  new FileNotFoundException("Wrong file");
        }

    }
}