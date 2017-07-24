package com.kusob.domain.notice;

import lombok.*;

import java.util.List;

/**
 * Created by seungki on 2017-07-19.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoticeListResponse {
    String message;
    List<Notice> list;
}
