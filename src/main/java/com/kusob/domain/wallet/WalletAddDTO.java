package com.kusob.domain.wallet;

import lombok.*;

/**
 * Created by kusob on 2017. 7. 8..
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletAddDTO {
    int wallet_id;
    String walletName;
    String walletAddr;
    String walletQr;
}
