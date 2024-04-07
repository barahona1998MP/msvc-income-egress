package com.msvc.ingresos_egresos.service;

import com.msvc.ingresos_egresos.documents.Detail;
import com.msvc.ingresos_egresos.dto.DetailDto;

import java.time.LocalDate;
import java.util.List;

public interface DetailService {

    Detail saveDetail(DetailDto detailDto);

    //List<DetailDto> findAllDetails();

    List<DetailDto> findByUserId(int userId);

    List<DetailDto> findByDate(LocalDate date);

    List<DetailDto> findDetailsBy();


}
