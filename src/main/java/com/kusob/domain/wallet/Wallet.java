package com.kusob.domain.wallet;

import lombok.*;

import java.util.Date;

/**
 * Created by kusob on 2017. 7. 8..
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Wallet {
    int wallet_Id;
    String wallet_name;
    String wallet_add;
    String wallet_type;
    Date create_date;

}
