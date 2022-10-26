package com.example.relaxtest.controller;

import com.example.relaxtest.enums.SequenceType;
import com.example.relaxtest.model.FileDTO;
import com.example.relaxtest.model.Response;
import com.example.relaxtest.services.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ApiController{
    private final ApiService _apiService;
    @Autowired
    public ApiController(ApiService apiService){

        _apiService=apiService;
    }

    
    @Operation(summary="Максимальное значение ",description="Нахождение максимального значения чисел файла")
    @PostMapping(value="/get_max_value")
    public ResponseEntity<Response<Integer>> findMaxNumber(@RequestBody FileDTO dto) throws Exception {
        Response<Integer> response=new Response<>();
        Integer minNumber=_apiService.findMaxNumber(dto);
        response.setData(minNumber);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }


    @Operation(summary = "Минимальное значение",description = "Нахождение минимального значения чисел файла")
    @PostMapping(value="/get_min_value",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Response<Integer>> findMinNumber(@RequestBody FileDTO dto) throws Exception {
        Response<Integer> response=new Response<>();
        Integer minNumber=_apiService.findMinNumber(dto);
        response.setData(minNumber);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Operation(summary = "Медиана",description = "Нахождение медианы из чисел файла ")
    @PostMapping(value="/get_median")
    public ResponseEntity<Response<Double>> findMedian(@RequestBody FileDTO dto) throws Exception {
        Response<Double> response=new Response<>();
        Double median=_apiService.findMedian(dto);
        response.setData(median);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }


    @Operation(summary = "Среднее арифметическое значение",description = "Нахождение среднего арифметического значения из чисел в файле")
    @PostMapping(value="/get_arithmetic_mean")
    public ResponseEntity<Response<Double>> findMean(@RequestBody FileDTO dto) throws Exception {
        Response<Double> response=new Response<>();
        Double mean=_apiService.findMean(dto);
        response.setData(mean);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Operation(summary = "Последовательности максимальной длины",description = "Нахождение максимальных увеличивающихся либо уменьшающихся последовательностей в файле взависимости от параметра type")
    @PostMapping(value="/get_sequence")
    public ResponseEntity<Response<ArrayList<ArrayList<Integer>>>> findMaxAscendingSeq(
                    @RequestBody FileDTO dto,
                    @RequestParam("type") @Parameter(description = "Тип последовательности") SequenceType type
    ) throws Exception
    {
        Response<ArrayList<ArrayList<Integer>>> response=new Response<>();
        ArrayList<ArrayList<Integer>> sequences=_apiService.findSequences(dto,type);
        response.setData(sequences);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

}