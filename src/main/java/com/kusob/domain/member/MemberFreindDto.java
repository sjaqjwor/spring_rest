package com.kusob.domain.member;

import lombok.*;

/**
 * Created by seungki on 2017-07-18.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberFreindDto {
    String nickname;
    int id;
}
