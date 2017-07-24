package com.kusob.domain.Comments.Response;

import com.kusob.domain.Comments.Comments;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by seungki on 2017-07-21.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentListResponse {
    String message;
    List<CommentResponse> list;
}
