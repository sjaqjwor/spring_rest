package com.kusob.domain.wallet;

/**
 * Created by seungki on 2017-07-19.
 */

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletResponse {

    String message;
    Wallet wallet;
}
