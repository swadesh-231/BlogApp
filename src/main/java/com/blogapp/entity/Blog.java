package com.blogapp.entity;

import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "blog_entries")
@Data
@NoArgsConstructor
public class Blog {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    @CreatedDate
    private LocalDateTime date;
}
