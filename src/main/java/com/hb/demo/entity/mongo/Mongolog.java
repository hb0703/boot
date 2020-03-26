package com.hb.demo.entity.mongo;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("mongolog")
@Data
@Builder
public class Mongolog {

    @Id
    private ObjectId id;

    @Field("logName")
    private String logName;
}
