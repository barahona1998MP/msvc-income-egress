package com.msvc.ingresos_egresos.repository;

import com.msvc.ingresos_egresos.documents.ActivityType;
import com.msvc.ingresos_egresos.dto.ActivityTypeDto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityTypeRepository extends MongoRepository<ActivityType, ObjectId> {
    ActivityTypeDto findByDescription(String description);

    List<ActivityTypeDto> findActivityTypesBy();

}
