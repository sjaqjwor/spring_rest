package com.kusob.domain.priceInfo;

import lombok.*;

/**
 * Created by kusob on 2017. 7. 9..
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceInfo {
    private String site;
    private String price;
    private String circulatingSupply;
    private String volume;
    private String change;
    private String marketCap;
    private String graphImg;
    private String shortSite;
    private double div;
}
