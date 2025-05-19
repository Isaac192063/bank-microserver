package com.bank.common.dto;

import lombok.Data;

@Data
public class SuccessDto <T>{
    private T data;
    private boolean success;
}
