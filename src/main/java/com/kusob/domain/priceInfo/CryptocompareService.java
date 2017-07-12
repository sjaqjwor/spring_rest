package com.kusob.domain.priceInfo;

/**
 * Created by kusob on 2017. 7. 12..
 */

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;


import java.io.InputStreamReader;
import java.net.URL;

@Service
public class CryptocompareService{
    public void getInfo() throws Exception{
        URL url = new URL("https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC,ETH,ETC,XRP,LTC,DASH&tsyms=BTC,KRW,BTC,USD");

        InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");

        JSONObject object = (JSONObject) JSONValue.parse(isr);

//        JSONObject btc = (JSONObject) object.get("BTC");
//        System.out.println(btc.toJSONString());

/* Object로 받을 경우 */

        JSONObject raw = (JSONObject) object.get("DISPLAY");
        
        JSONObject btc = (JSONObject) raw.get("BTC");
        JSONObject btcBtc = (JSONObject) btc.get("BTC");
        System.out.println(btcBtc.get("PRICE"));
        
        JSONObject eth = (JSONObject) raw.get("ETH");
        JSONObject ethBtc = (JSONObject) eth.get("BTC");
        System.out.println(ethBtc.get("PRICE"));

        JSONObject etc = (JSONObject) raw.get("ETC");
        JSONObject etcBtc = (JSONObject) etc.get("BTC");
        System.out.println(etcBtc.get("PRICE"));

        JSONObject xrp = (JSONObject) raw.get("XRP");
        JSONObject xrpBtc = (JSONObject) xrp.get("BTC");
//        System.out.println(xrpBtc);
        System.out.println(xrpBtc.get("PRICE"));

        JSONObject ltc = (JSONObject) raw.get("LTC");
        JSONObject ltcBtc = (JSONObject) ltc.get("BTC");
        System.out.println(ltcBtc.get("PRICE"));

        JSONObject dash = (JSONObject) raw.get("DASH");
        JSONObject dashBtc = (JSONObject) dash.get("BTC");
        System.out.println(dashBtc.get("PRICE"));

//        System.out.println(btc2);
    }   
}