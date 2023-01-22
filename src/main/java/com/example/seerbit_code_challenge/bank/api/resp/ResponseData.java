package com.example.seerbit_code_challenge.bank.api.resp;

import com.example.seerbit_code_challenge.bank.dtos.Statistic;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseData {
    private String code;
    private String message;

    private Statistic statistic;

}
