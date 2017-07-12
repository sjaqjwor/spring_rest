package com.kusob.domain.priceInfo;

import lombok.*;

/**
 * Created by kusob on 2017. 7. 13..
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cryptocompare {
    String krw;
    String btc;
    String btcKrw;
    String usd;
    String usdKrw;
    String usdKrwPremium;
    String mktcap;
}
