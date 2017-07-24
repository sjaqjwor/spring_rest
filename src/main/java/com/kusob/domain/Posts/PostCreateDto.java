package com.kusob.domain.Posts;

import lombok.*;

/**
 * Created by seungki on 2017-07-20.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateDto {
    int writter_id;
    String title;
    String contents;
}
