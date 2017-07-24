package com.kusob.domain.Comments;

import com.kusob.domain.Comments.Request.CommentCreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by seungki on 2017-07-21.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentsCreate {

    int post_id;
    int writter_id;
    String contents;
    public CommentsCreate(CommentCreateRequestDto commentCreateReuqestDto,int id){
        this.post_id=commentCreateReuqestDto.getPost_id();
        this.writter_id=id;
        this.contents=commentCreateReuqestDto.getContents();
    }
}
