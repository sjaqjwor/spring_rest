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
public class PostEdditReqeustDto {
    int post_id;
    String title;
    String contents;
}
