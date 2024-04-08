package com.msvc.ingresos_egresos.service;

import com.msvc.ingresos_egresos.documents.Detail;
import com.msvc.ingresos_egresos.dto.DetailDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DetailService {

    Detail saveDetail(DetailDto detailDto);

    //List<DetailDto> findAllDetails();

    List<DetailDto> findByUserId(int userId);


    List<DetailDto> findByDate(LocalDate date) throws IOException;

    List<DetailDto> findDetailsBy();

    Optional<DetailDto> findDetailDtoById(String id);

    String saveImage(MultipartFile file);



}
