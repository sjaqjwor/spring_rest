package com.kusob.domain.Posts.Request;

import lombok.*;

/**
 * Created by seungki on 2017-07-21.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateRequestDto {

    String title;
    String contents;
}
