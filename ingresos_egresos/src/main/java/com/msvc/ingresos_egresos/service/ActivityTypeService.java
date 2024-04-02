package com.msvc.ingresos_egresos.service;

import com.msvc.ingresos_egresos.documents.ActivityType;
import com.msvc.ingresos_egresos.dto.ActivityTypeDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface ActivityTypeService {
    ActivityType save(ActivityTypeDto activityTypeDto);

    List<ActivityTypeDto> findActivityTypesBy();

    ActivityTypeDto findByDescription(String description);
}
