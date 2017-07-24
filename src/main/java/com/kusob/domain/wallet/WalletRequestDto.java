package com.kusob.domain.wallet;

import lombok.*;

/**
 * Created by seungki on 2017-07-19.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletRequestDto {
    String walletName;
    String walletAddr;
    String walletType;
}
