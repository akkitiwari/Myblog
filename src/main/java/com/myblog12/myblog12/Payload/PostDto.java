package com.myblog12.myblog12.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {
    private long id ;
    private String title;
    private String description;
    private String content;

}

