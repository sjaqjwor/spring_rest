package com.kusob.domain.Friends;

import lombok.*;

/**
 * Created by seungki on 2017-07-18.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Friends {
    int id;
    int opponent_id;
    int status;
}
