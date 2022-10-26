package com.example.relaxtest.services;


import com.example.relaxtest.enums.SequenceType;
import com.example.relaxtest.model.FileDTO;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.IntStream;

public interface ApiService {
    public Integer findMaxNumber() throws FileNotFoundException;
    public Integer findMinNumber() throws FileNotFoundException;

    public Double findMedian() throws FileNotFoundException;

    public Double findMean() throws FileNotFoundException;
    public ArrayList<ArrayList<Integer>> findSequences(SequenceType type )throws FileNotFoundException;


    public String uploadFile(FileDTO fileDTO) throws FileNotFoundException;
}