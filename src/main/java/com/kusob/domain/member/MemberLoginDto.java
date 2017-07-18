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
public class MemberLoginDto {
    String email;
    String password;
}
