package com.grent.springcloud.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T>{
    Integer status;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this.status = code;
        this.message = message;
    }
}
