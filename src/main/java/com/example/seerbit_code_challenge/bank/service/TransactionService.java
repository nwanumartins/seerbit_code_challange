package com.example.seerbit_code_challenge.bank.service;

import com.example.seerbit_code_challenge.bank.api.resp.ResponseData;
import com.example.seerbit_code_challenge.bank.dtos.Transaction;

public interface TransactionService {

    ResponseData addTransaction(Transaction transaction);

    ResponseData getTransactionStatistic();

    ResponseData deleteTransaction();

}
