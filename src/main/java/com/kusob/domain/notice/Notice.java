package com.kusob.domain.notice;

import lombok.*;

import java.sql.Date;

/**
 * Created by kusob on 2017. 7. 4..
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notice {
    private int notice_id;
    private String title;
    private String contents;
    private Date create_date;
}
