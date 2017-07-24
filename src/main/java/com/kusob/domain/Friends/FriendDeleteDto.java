package com.kusob.domain.Friends;

import lombok.*;

/**
 * Created by seungki on 2017-07-20.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendDeleteDto {
    int id;
    int opponentid;
}
