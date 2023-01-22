package com.example.seerbit_code_challenge.bank.service.Implementation;

import com.example.seerbit_code_challenge.bank.api.resp.ResponseData;
import com.example.seerbit_code_challenge.bank.dtos.Statistic;
import com.example.seerbit_code_challenge.bank.dtos.Transaction;
import com.example.seerbit_code_challenge.bank.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    ObjectMapper objectMapper;

    private List<Transaction> transactions = new ArrayList<>();

    private Statistic statistic = new Statistic();

    @Scheduled(fixedDelay = 30000)
    public void transactionServiceJob(){
        System.out.println("<<<< Transaction service is running >>>>");
        Date currentTime = new Date();
        BigDecimal sum = BigDecimal.ZERO;
        int size = transactions.size();
        BigDecimal min = null;
        BigDecimal max = null;
        for (int i = 0; i <size; i++){
            Transaction trans  = transactions.get(i);
            String tranDate = trans.getTimestamp();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
            try {
                Date expTranDate = sdf.parse(tranDate);
                if(expTranDate.before(currentTime)){
                    BigDecimal amt = transactions.get(i).getAmount();
                    sum = sum.add(amt);
                    if(min == null || amt.compareTo(min) < 0){
                        min = amt;
                    }
                    if(max == null || amt.compareTo(max) > 0){
                        max = amt;
                    }
                    transactions.remove(i);
                    System.out.println("<<<<< condition is mate >>>> ");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if(sum.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal avg = sum.divide(BigDecimal.valueOf(size), RoundingMode.HALF_UP);
            statistic.setSum(sum);
            statistic.setAvg(avg);
            statistic.setMin(min);
            statistic.setMax(max);
            statistic.setCount(size);
        }

    }

    @Override
    public ResponseData addTransaction(Transaction transaction) {
        ResponseData respData = new ResponseData();
        try {
            var payload = objectMapper.writeValueAsString(transaction);
            if(null == transaction || transaction.getAmount() == null || isTransactionDateInFuture(transaction.getTimestamp())){
                respData.setCode("422");
                respData.setMessage("Field is required or transaction date is in future");
            }else if(!validateJSON(payload)){
                respData.setCode("400");
                respData.setMessage("JSON request is invalid");
            } else{
                respData.setCode("201");
                respData.setMessage("transaction saved successful");
                new Thread(){
                    public void run(){
                        BigDecimal sum = BigDecimal.ZERO;
                        int size = transactions.size();
                        BigDecimal min = null;
                        BigDecimal max = null;
                        for(int i =0; i<transactions.size(); i++){
                            BigDecimal amt = transactions.get(i).getAmount();
                            sum = sum.add(amt);
                            if(min == null || amt.compareTo(min) < 0){
                                min = amt;
                            }
                            if(max == null || amt.compareTo(max) > 0){
                                max = amt;
                            }
                        }
                        BigDecimal avg = sum.divide(BigDecimal.valueOf(size), RoundingMode.HALF_UP);
                        statistic.setSum(sum);
                        statistic.setAvg(avg);
                        statistic.setMin(min);
                        statistic.setMax(max);
                        statistic.setCount(size);
                    }
                }.start();
                transactions.add(transaction);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return respData;
    }

    @Override
    public ResponseData getTransactionStatistic() {
        ResponseData respData = new ResponseData();
        respData.setCode("201");
        respData.setMessage("Transaction deleted successful");
        respData.setStatistic(statistic);
        return respData;
    }

    @Override
    public ResponseData deleteTransaction() {
        ResponseData respData = new ResponseData();
        respData.setCode("201");
        respData.setMessage("Transaction deleted successful");
        statistic = new Statistic();
        transactions = new ArrayList<>();
        return respData;
    }

    private boolean isTransactionDateInFuture(String tranDate){
        boolean isDateTimeFuture = false;
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        try {
            if(null == tranDate){
                isDateTimeFuture = false;
            }else {
                Date transDate = sdf.parse(tranDate);
                if(transDate.after(now)){
                    isDateTimeFuture = true;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isDateTimeFuture;
    }
    public Boolean validateJSON(String strResp){
        boolean isObject = false;
        if(null != strResp && !StringUtils.isEmpty(strResp)){
            if(strResp.charAt(0) == '['){
                isObject = false;
            }else if(strResp.charAt(0) == '{'){
                isObject = true;
            }
            return isObject;
        }
        return null;
    }
}
