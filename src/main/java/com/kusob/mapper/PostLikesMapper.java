package com.kusob.mapper;

import com.kusob.domain.PostLikes.PostLikes;
import com.kusob.domain.PostLikes.PostLikesCreate;
import com.kusob.domain.Posts.PostCreateDto;
import com.kusob.domain.Posts.Posts;
import com.kusob.domain.Posts.Request.PostEdditReqeustDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by seungki on 2017-07-18.
 */
@Mapper
public interface PostLikesMapper {
    void insertLikes(PostLikesCreate postLikesCreate);

}
