package com.msvc.ingresos_egresos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailDto {

    private String activity;
    private String type;
    private Double value;
    private String activityUrl;
    private LocalDate date;
    private int userId;

}
