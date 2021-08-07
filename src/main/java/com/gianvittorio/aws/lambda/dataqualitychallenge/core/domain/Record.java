package com.gianvittorio.aws.lambda.dataqualitychallenge.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record {

    private String identiFMask;
    private String modelo;
    private BigInteger score;
    private BigInteger restritivo;
    private BigInteger positivo;
    private String msg;
    private Date anomesdia;
}
