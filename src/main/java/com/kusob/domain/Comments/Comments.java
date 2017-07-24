package com.kusob.domain.Comments;

import lombok.*;

/**
 * Created by seungki on 2017-07-21.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comments {
    int comment_id;
    int post_id;
    int writter_id;
    String contents;
    String written_date;
}
