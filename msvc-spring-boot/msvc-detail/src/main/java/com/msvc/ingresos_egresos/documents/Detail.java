package com.msvc.ingresos_egresos.documents;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "detalles")
public class Detail {

    @Id
    private String id;
    private String activity;
    private Double value;
    private String activityUrl;
    private LocalDate date;
    private String type;
    private int userId;


}
