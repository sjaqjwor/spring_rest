package com.kusob.domain.notice;

import com.kusob.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by seungki on 2017-07-19.
 */
@Service
public class NoticeService {

    @Autowired
    NoticeMapper noticeMapper;
    public NoticeListResponse all(HttpServletRequest httpServletRequest)throws Exception{
        List<Notice> list=null;
        try {
            list=noticeMapper.selectAll();
            return new NoticeListResponse("SUCCESS",list);
        }catch (Exception e){
            throw new Exception();
        }
    }
}
