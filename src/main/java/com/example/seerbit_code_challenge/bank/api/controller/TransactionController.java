package com.example.seerbit_code_challenge.bank.api.controller;

import com.example.seerbit_code_challenge.bank.api.resp.ResponseData;
import com.example.seerbit_code_challenge.bank.dtos.Transaction;
import com.example.seerbit_code_challenge.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    private ResponseEntity<ResponseData> addTransaction(@RequestBody Transaction transaction){
        var resp = transactionService.addTransaction(transaction);
        if(!resp.getCode().equalsIgnoreCase("201")){
            return ResponseEntity.badRequest().body(resp);
        }
        return ResponseEntity.ok(resp);
    }
    @GetMapping("/statistic")
    private ResponseEntity<ResponseData> getTransactionStatistic(){
        var statisticResp = transactionService.getTransactionStatistic();
        if(!statisticResp.getCode().equalsIgnoreCase("201")){
            return ResponseEntity.badRequest().body(statisticResp);
        }
        return ResponseEntity.ok(statisticResp);
    }
    @DeleteMapping ("/delete")
    private ResponseEntity<ResponseData> deleteTransaction(){
        var delResp = transactionService.deleteTransaction();
        return ResponseEntity.ok(delResp);
    }
}
