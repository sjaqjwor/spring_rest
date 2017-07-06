package com.kusob.domain.member;

import lombok.*;

/**
 * Created by kusob on 2017. 7. 6..
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    String email;
    String password;
    String nickname;
}
