package com.kusob.domain.Posts;

import lombok.*;

import java.util.Date;

/**
 * Created by seungki on 2017-07-20.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Posts {
    int post_id;
    String title;
    int writter_id;
    String contents;
    String written_date;
    int likes_count;
    String nickname;
    int c_count;
}
