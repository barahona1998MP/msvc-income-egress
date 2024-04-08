package com.msvc.ingresos_egresos.service;

import com.msvc.ingresos_egresos.documents.ActivityType;
import com.msvc.ingresos_egresos.dto.ActivityTypeDto;
import com.msvc.ingresos_egresos.repository.ActivityTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ActivityTypeServiceImpl implements ActivityTypeService {

    @Autowired
    ActivityTypeRepository activityTypeRepository;

    @Override
    public ActivityType save(ActivityTypeDto activityTypeDto) {
        ActivityType activityType = ActivityType.builder()
                .description(activityTypeDto.getDescription())
                .build();
        return activityTypeRepository.save(activityType);
    }

    @Override
    public List<ActivityTypeDto> findActivityTypesBy() {
        return activityTypeRepository.findActivityTypesBy();
    }

    @Override
    public ActivityTypeDto findByDescription(String description) {
        return activityTypeRepository.findByDescription(description);
    }
}
