package com.example.seerbit_code_challenge.algorithms;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ParamDto {

    public int first;
    public int second;

    public ParamDto(int x, int y){
        this.first = x;
        this.second = y;
    }
}
