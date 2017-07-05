package com.kusob.mapper;

import com.kusob.domain.notice.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by kusob on 2017. 7. 4..
 */
@Mapper
public interface NoticeMapper {
    Notice selectById(int notice_id);
    List<Notice> selectByPageNumber(int currentPage);
    List<Notice> selectAll();
}