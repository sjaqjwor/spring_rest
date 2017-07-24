package com.kusob.domain.Comments.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by seungki on 2017-07-21.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDeleteDto {

    int post_id;
    int comment_id;
}
