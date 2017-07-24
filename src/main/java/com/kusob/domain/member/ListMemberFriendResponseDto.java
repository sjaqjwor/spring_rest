package com.kusob.domain.member;

import com.kusob.domain.member.MemberFreindDto;
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
public class ListMemberFriendResponseDto {
    String message;
    List<MemberFreindDto> list;
}
