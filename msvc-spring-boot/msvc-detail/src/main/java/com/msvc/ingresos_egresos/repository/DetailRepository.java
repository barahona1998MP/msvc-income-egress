package com.msvc.ingresos_egresos.repository;

import com.msvc.ingresos_egresos.documents.Detail;
import com.msvc.ingresos_egresos.dto.DetailDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DetailRepository extends MongoRepository<Detail, String > {
    List<DetailDto> findByUserId(int userId);

    List<DetailDto> findByDate(LocalDate date);

    Optional<DetailDto> findDetailDtoById(String id);

    List<DetailDto> findDetailsBy();




}
