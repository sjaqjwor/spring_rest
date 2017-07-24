package com.kusob.mapper;

import com.kusob.domain.Friends.FriendDeleteDto;
import com.kusob.domain.Friends.Friends;
import com.kusob.domain.Friends.FriendsAgreeDto;
import com.kusob.domain.Posts.PostCreateDto;
import com.kusob.domain.Posts.PostTop;
import com.kusob.domain.Posts.Posts;
import com.kusob.domain.Posts.Request.PostEdditReqeustDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by seungki on 2017-07-18.
 */
@Mapper
public interface PostsMapper {
    List<Posts> allList_3();
    List<Posts> allList(PostTop postTop);
    void createPost(PostCreateDto postCreateDto);
    void deletePosts(int id);
    Posts postsInfo(int id);
    void postlike(Posts post);
    void eddPost(PostEdditReqeustDto postEdditReqeustDto);

}
