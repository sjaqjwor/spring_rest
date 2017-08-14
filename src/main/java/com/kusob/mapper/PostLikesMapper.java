package com.kusob.mapper;

import com.kusob.domain.PostLikes.PostLikes;
import com.kusob.domain.PostLikes.PostLikesCreate;
import com.kusob.domain.Posts.PostCreateDto;
import com.kusob.domain.Posts.Posts;
import com.kusob.domain.Posts.Request.PostEdditReqeustDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by seungki on 2017-07-18.
 */
@Mapper
public interface PostLikesMapper {
    void insertLikes(PostLikesCreate postLikesCreate);
    int confirmLike(Map<String,Integer> map);
    void deleteLikse(Map<String,Integer> map);
}
