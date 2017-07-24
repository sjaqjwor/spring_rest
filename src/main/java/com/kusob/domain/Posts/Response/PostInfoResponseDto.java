package com.kusob.domain.Posts.Response;

import com.kusob.domain.Comments.Comments;
import com.kusob.domain.Comments.Response.CommentResponse;
import com.kusob.domain.Posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by seungki on 2017-07-21.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostInfoResponseDto {
    String message;
    String title;
    String contents;
    String writter_name;
    String written_date;
    int likes_count;
    boolean possibleEdditAndDelete;
    List<CommentResponse> comment_list;

    public PostInfoResponseDto(String message, Posts posts, String name, boolean type,List comments){
        this.message=message;
        this.title= posts.getTitle();
        this.contents= posts.getContents();
        this.writter_name=name;
        this.written_date=posts.getWritten_date();
        this.likes_count=posts.getLikes_count();
        this.possibleEdditAndDelete=type;
        this.comment_list=comments;

    }
    public PostInfoResponseDto(String message){
        this.message=message;
    }
}
