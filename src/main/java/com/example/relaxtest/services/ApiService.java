package com.example.relaxtest.services;


import com.example.relaxtest.enums.SequenceType;
import com.example.relaxtest.model.FileDTO;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.stream.IntStream;

public interface ApiService {
    public Integer findMaxNumber(FileDTO dto) throws FileNotFoundException;
    public Integer findMinNumber(FileDTO dto ) throws FileNotFoundException;

    public Double findMedian(FileDTO dto) throws FileNotFoundException;

    public Double findMean(FileDTO dto) throws FileNotFoundException;
    public ArrayList<ArrayList<Integer>> findSequences(FileDTO dto, SequenceType type )throws FileNotFoundException;

    public IntStream createListFromFile(String filePath) throws FileNotFoundException;
}