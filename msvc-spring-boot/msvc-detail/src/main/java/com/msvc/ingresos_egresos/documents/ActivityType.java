package com.msvc.ingresos_egresos.documents;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "activity")
public class ActivityType {

    @MongoId
    private ObjectId id;
    private String description;

}
