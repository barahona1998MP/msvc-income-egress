package com.msvc.ingresos_egresos.repository;

import com.msvc.ingresos_egresos.documents.Detail;
import com.msvc.ingresos_egresos.dto.DetailDto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface DetailRepository extends MongoRepository<Detail, ObjectId> {
    List<DetailDto> findByUserId(int userId);

    List<DetailDto> findByDate(LocalDate date);

    List<DetailDto> findDetailsBy();


}
