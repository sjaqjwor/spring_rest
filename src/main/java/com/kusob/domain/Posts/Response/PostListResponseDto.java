package com.kusob.domain.Posts.Response;

import com.kusob.domain.Posts.PostTop;
import com.kusob.domain.Posts.Posts;
import lombok.*;

import java.util.List;

/**
 * Created by seungki on 2017-07-20.
 */
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PostListResponseDto {
    String message;
    List<Posts> list;
    List<Posts> top;
}
