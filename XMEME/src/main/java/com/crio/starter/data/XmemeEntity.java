package com.crio.starter.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "usersPosts")
@AllArgsConstructor
@Builder
public class XmemeEntity {

    @Transient
    public static final String SEQUENCE_NAME = "posts_sequence";

    @Id
    private long id;

    private String name;

    private String url;

    private String caption;


}


