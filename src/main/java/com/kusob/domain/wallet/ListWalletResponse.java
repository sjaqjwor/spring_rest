package com.kusob.domain.wallet;

import com.kusob.domain.wallet.Wallet;
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
public class ListWalletResponse {
    String message;
    List<Wallet> list;
}
