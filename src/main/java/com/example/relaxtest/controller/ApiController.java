package com.example.relaxtest.controller;

import com.example.relaxtest.enums.SequenceType;
import com.example.relaxtest.model.FileDTO;
import com.example.relaxtest.model.ApiResponse;
import com.example.relaxtest.model.FileResponse;
import com.example.relaxtest.services.ApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@RestController
public class ApiController{
    private final ApiService _apiService;
    @Autowired
    public ApiController(ApiService apiService){

        _apiService=apiService;
    }

    
    @Operation(summary="Максимальное значение ",description="Нахождение максимального значения чисел файла")
    @GetMapping(value="/get_max_value",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ApiResponse<Integer>> findMaxNumber() throws Exception {
        ApiResponse<Integer> apiResponse =new ApiResponse<>();
        Integer minNumber=_apiService.findMaxNumber();
        apiResponse.setData(minNumber);
        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


    @Operation(summary = "Минимальное значение",description = "Нахождение минимального значения чисел файла")
    @GetMapping(value="/get_min_value",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ApiResponse<Integer>> findMinNumber() throws Exception {
        ApiResponse<Integer> apiResponse =new ApiResponse<>();
        Integer minNumber=_apiService.findMinNumber();
        apiResponse.setData(minNumber);
        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(summary = "Медиана",description = "Нахождение медианы из чисел файла ")
    @GetMapping(value="/get_median",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ApiResponse<Double>> findMedian() throws Exception {
        ApiResponse<Double> apiResponse =new ApiResponse<>();
        Double median=_apiService.findMedian();
        apiResponse.setData(median);
        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }


    @Operation(summary = "Среднее арифметическое значение",description = "Нахождение среднего арифметического значения из чисел в файле")
    @GetMapping(value="/get_arithmetic_mean",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ApiResponse<Double>> findMean() throws Exception {
        ApiResponse<Double> apiResponse =new ApiResponse<>();
        Double mean=_apiService.findMean();
        apiResponse.setData(mean);
        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @Operation(summary = "Последовательности максимальной длины",description = "Нахождение максимальных увеличивающихся либо уменьшающихся последовательностей в файле взависимости от параметра type")
    @GetMapping(value="/get_sequence",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ApiResponse<ArrayList<ArrayList<Integer>>>> findMaxAscendingSeq(
                    @RequestParam("type") @Parameter(description = "Тип последовательности") SequenceType type
    ) throws Exception
    {
        ApiResponse<ArrayList<ArrayList<Integer>>> apiResponse =new ApiResponse<>();
        ArrayList<ArrayList<Integer>> sequences=_apiService.findSequences(type);
        apiResponse.setData(sequences);
        return  new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }



    @PostMapping(value = "/upload_file",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "Загрузка пути к файлу",description = "Загружает в систему путь к файлу,но не валидирует его ")
    public ResponseEntity<FileResponse> uploadFile(@RequestBody FileDTO fileDTO) throws FileNotFoundException {
        FileResponse fileResponse =new FileResponse();
        String message=_apiService.uploadFile(fileDTO);
        fileResponse.setMessage(message);
        return new ResponseEntity<>(fileResponse,HttpStatus.CREATED);
    }
}