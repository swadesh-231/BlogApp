package com.blogapp.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "blog_entries")
@Data
@NoArgsConstructor
public class Blog {
    @Id
    private ObjectId id;
    @NotNull
    private String title;
    private String content;
    @CreatedDate
    private LocalDateTime date;

    @DBRef(lazy = true)    // <-- Owner reference
    private User owner;
}
