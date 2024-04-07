package com.msvc.ingresos_egresos.controller;

import com.msvc.ingresos_egresos.documents.ActivityType;
import com.msvc.ingresos_egresos.dto.ActivityTypeDto;
import com.msvc.ingresos_egresos.service.ActivityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActivityTypeController {

    @Autowired
    ActivityTypeService activityTypeService;

    @PostMapping("/activity")
    public ResponseEntity<ActivityType> create(@RequestBody ActivityTypeDto activityTypeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(activityTypeService.save(activityTypeDto));
    }

    @GetMapping("/activities")
    public ResponseEntity<List<ActivityTypeDto>> findActivityTypesBy() {
        List<ActivityTypeDto> getActivityTypes = activityTypeService.findActivityTypesBy();
        if (getActivityTypes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(getActivityTypes);
    }

    @GetMapping("/activity/{description}")
    public ResponseEntity<ActivityTypeDto> findActivityTypeByDescription(@PathVariable String description) {
        ActivityTypeDto activityTypeBD = activityTypeService.findByDescription(description);
        if (activityTypeBD == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(activityTypeBD);
    }

}
