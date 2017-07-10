package com.kusob.domain.inquire;

import lombok.*;

/**
 * Created by kusob on 2017. 7. 10..
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inquire {
    private String content;
    private String senderEmail;
}
