package com.kusob.domain.Comments.Response;

import com.kusob.domain.Comments.Comments;
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
public class CommentResponse {
    int comment_id;
    int post_id;
    String writter_name;
    String contents;
    String written_date;
    boolean posibleEdditAndDelete;

    public CommentResponse(Comments comments,String name,boolean type){
        this.comment_id=comments.getComment_id();
        this.post_id=comments.getPost_id();
        this.writter_name=name;
        this.contents=comments.getContents();
        this.written_date=comments.getWritten_date();
        this.posibleEdditAndDelete=type;
    }
}
