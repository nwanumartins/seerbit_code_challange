package com.example.seerbit_code_challenge;

import com.example.seerbit_code_challenge.algorithms.AlgorithmSolutions;
import com.example.seerbit_code_challenge.algorithms.ParamDto;
import com.example.seerbit_code_challenge.bank.dtos.Transaction;
import com.example.seerbit_code_challenge.bank.enums.TransactionType;
import com.example.seerbit_code_challenge.bank.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest
class SeerbitCodeChallengeApplicationTests {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    TransactionService transactionService;
    @Autowired
    AlgorithmSolutions algorithmSolutions;

    @Test
    void contextLoads() {
    }

    @Test
    void testMerge_Overlapping_Intervals(){
        ArrayList<ParamDto> v = new ArrayList<ParamDto>();

        v.add(new ParamDto(1, 5));
        v.add(new ParamDto(3, 7));
        v.add(new ParamDto(4, 6));
        v.add(new ParamDto(6, 8));
        v.add(new ParamDto(10, 12));
        v.add(new ParamDto(11, 15));

        ArrayList<ParamDto> result = algorithmSolutions.mergeIntervals(v);

        for(int i=0; i<result.size(); i++){
            System.out.print(String.format("[%d, %d] ", result.get(i).first, result.get(i).second));
        }
    }

    @Test
    void testMaximum_Subarray_XOR(){
        int arr[] = {1,2,3,4};
        int n = 4;
        System.out.println("Max subarray XOR is " +
                algorithmSolutions.maxSubarrayXOR(arr, n));
    }

}
