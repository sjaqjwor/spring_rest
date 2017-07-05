package com.kusob.domain.member;

import lombok.*;
import lombok.Setter;

/**
 * Created by kusob on 2017. 7. 4..
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private String email;
    private String password;
}
