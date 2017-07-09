package com.kusob.api;

/**
 * Created by kusob on 2017. 7. 4..
 */

import com.kusob.domain.notice.Notice;
import com.kusob.mapper.NoticeMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(value = "공지사항 API", description = "coin.notice Table API", basePath = "/api/notice")
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    NoticeMapper noticeMapper;

//    @ApiOperation(value = "공지사항 작성", notes = "공지사항 작성")
//    @RequestMapping(value = "save", method = RequestMethod.POST)
//    public ResponseDTO saveArticle(@RequestBody Article article) {
//        try {
//            articleService.saveArticle(ArticleDto.of(article));
//            return ResponseDto.ofSuccess("success");
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        }
//        return ResponseDto.ofEmpty();
//    }

//    @ApiOperation(value = "게시물 삭제", notes = "게시물 삭제")
//    @RequestMapping(value = "delete", method = RequestMethod.GET)
//    public ResponseDto deleteArticle(@RequestParam(value = "id") Long id) {
//        try {
//            articleService.delete(id);
//            return ResponseDto.ofSuccess("success");
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        }
//        return ResponseDto.ofEmpty();
//    }

//    @ApiOperation(value = "공지사항 리스트", notes = "공지사항 리스트")
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public List<Notice> list(@ApiParam(value = "현재 공지사항 페이지 번호")
//                               @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {
//        try {
//            return noticeMapper.selectByPageNumber(currentPage);
//        } catch (Exception e) {
//            log.info(e.getMessage());
//        }
//        return null;
//    }

    @ApiOperation(value = "공지사항 전체 리스트", notes = "공지사항 전체 리스트")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Notice> allList() {
        try {
            return noticeMapper.selectAll();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        System.out.println("null");
        return null;
    }

//    @ApiOperation(value = "공지사항 읽기", notes = "공지사항 읽기")
//    @RequestMapping(value = "/read", method = RequestMethod.GET)
//    public Notice read(@ApiParam(value = "게시물 id")
//                                 @RequestParam(value = "notice_id") int notice_id) {
//        try {
//            return noticeMapper.selectById(notice_id);
//        } catch (Exception e) {
//            log.error("article read" + e.getMessage(), e);
//        }
//        return null;
//    }
}