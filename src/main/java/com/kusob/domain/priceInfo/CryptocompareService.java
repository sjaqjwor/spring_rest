package com.kusob.domain.priceInfo;

/**
 * Created by kusob on 2017. 7. 12..
 */

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;


import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@Service
public class CryptocompareService{
    public List<Cryptocompare> getInfo() throws Exception{
        URL usdToKrw = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20(%22USD%22%2C%22KRW%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=");
        InputStreamReader isr2 = new InputStreamReader(usdToKrw.openConnection().getInputStream(), "UTF-8");
        JSONObject utkObject = (JSONObject) JSONValue.parse(isr2);
        JSONObject query = (JSONObject) utkObject.get("query");
        JSONObject results = (JSONObject) query.get("results");
        JSONArray rateArr = (JSONArray) results.get("rate");
        JSONObject rateArr1 = (JSONObject)rateArr.get(1);
        String rateString = rateArr1.get("Rate").toString(); //환율
        BigDecimal rate = new BigDecimal(rateString);
        
        URL url = new URL("https://min-api.cryptocompare.com/data/pricemultifull?fsyms=BTC,ETH,ETC,XRP,LTC,DASH&tsyms=BTC,KRW,BTC,USD");
        InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
        JSONObject object = (JSONObject) JSONValue.parse(isr);
        
        String s;
        BigDecimal percent = new BigDecimal(100);
        JSONObject raw = (JSONObject) object.get("RAW");
        //////-------------BTC - 비트코인--------------///////
        Cryptocompare BTC = new Cryptocompare();
        JSONObject btc = (JSONObject) raw.get("BTC");

        JSONObject btcKrw = (JSONObject) btc.get("KRW");
        s = String.valueOf(btcKrw.get("PRICE"));
        BigDecimal btcKr = new BigDecimal(s);
        BTC.setKrw(btcKr.toString());
        
        JSONObject btcBtc = (JSONObject) btc.get("BTC");
        s = String.valueOf(btcBtc.get("PRICE"));
        BTC.setBtc(new BigDecimal(s).toString());
        
        JSONObject btcUsd = (JSONObject) btc.get("USD");
        s = String.valueOf(btcUsd.get("PRICE"));
        BigDecimal btcUs = new BigDecimal(s);
        BTC.setUsd(btcUs.toString());
        
        
        BTC.setUsdKrw(rate.multiply(new BigDecimal(s)).toString()); //USDKRW

        BigDecimal btcPrem = btcKr.subtract(btcUs.multiply(rate));
        BTC.setUsdKrwPremium(btcPrem.toString()); //premium
        
        BTC.setPercent(percent.divide(btcKr.divide(btcPrem,3, BigDecimal.ROUND_CEILING),2, BigDecimal.ROUND_CEILING).toString());

        s = String.valueOf(btcUsd.get("MKTCAP"));
        BTC.setMktcap(new BigDecimal(s).toString());
        
        BTC.setTitle("BTC");
        
        ////////-------------ETH - 이더리움------------//////
        Cryptocompare ETH = new Cryptocompare();
        JSONObject eth = (JSONObject) raw.get("ETH");

        JSONObject ethKrw = (JSONObject) eth.get("KRW");
        s = String.valueOf(ethKrw.get("PRICE"));
        BigDecimal ethKr = new BigDecimal(s);
        ETH.setKrw(ethKr.toString());
        
        JSONObject ethBtc = (JSONObject) eth.get("BTC");
        s = String.valueOf(ethBtc.get("PRICE"));
        ETH.setBtc(new BigDecimal(s).toString());

        JSONObject ethUsd = (JSONObject) eth.get("USD");
        s = String.valueOf(ethUsd.get("PRICE"));
        BigDecimal ethUs = new BigDecimal(s);
        ETH.setUsd(ethUs.toString());

        ETH.setUsdKrw(rate.multiply(new BigDecimal(s)).toString()); //USDKRW

        BigDecimal ethPrem = ethKr.subtract(ethUs.multiply(rate));
        ETH.setUsdKrwPremium(ethPrem.toString()); //premium

        ETH.setPercent(percent.divide(ethKr.divide(ethPrem,3, BigDecimal.ROUND_CEILING),2, BigDecimal.ROUND_CEILING).toString());

        s = String.valueOf(ethUsd.get("MKTCAP"));
        ETH.setMktcap(new BigDecimal(s).toString());

        ETH.setTitle("ETH");
        
        ////////-----------ETC - 이더리움 클래식--------//////
        Cryptocompare ETC = new Cryptocompare();
        JSONObject etc = (JSONObject) raw.get("ETC");

        JSONObject etcKrw = (JSONObject) etc.get("KRW");
        s = String.valueOf(etcKrw.get("PRICE"));
        BigDecimal etcKr = new BigDecimal(s);
        ETC.setKrw(etcKr.toString());
        
        JSONObject etcBtc = (JSONObject) etc.get("BTC");
        s = String.valueOf(etcBtc.get("PRICE"));
        ETC.setBtc(new BigDecimal(s).toString());

        JSONObject etcUsd = (JSONObject) etc.get("USD");
        s = String.valueOf(etcUsd.get("PRICE"));
        BigDecimal etcUs = new BigDecimal(s);
        ETC.setUsd(etcUs.toString());

        ETC.setUsdKrw(rate.multiply(new BigDecimal(s)).toString()); //USDKRW

        BigDecimal etcPrem = etcKr.subtract(etcUs.multiply(rate));
        ETC.setUsdKrwPremium(etcPrem.toString()); //premium

        ETC.setPercent(percent.divide(etcKr.divide(etcPrem,3, BigDecimal.ROUND_CEILING),2, BigDecimal.ROUND_CEILING).toString());

        s = String.valueOf(etcUsd.get("MKTCAP"));
        ETC.setMktcap(new BigDecimal(s).toString());

        ETC.setTitle("ETC");
        
        /////////----------XRP - Ripple ------------//////
        Cryptocompare XRP = new Cryptocompare();
        JSONObject xrp = (JSONObject) raw.get("XRP");
        
        JSONObject xrpKrw = (JSONObject) xrp.get("KRW");
        s = String.valueOf(xrpKrw.get("PRICE"));
        BigDecimal xrpKr = new BigDecimal(s);
        XRP.setKrw(xrpKr.toString());
        
        JSONObject xrpBtc = (JSONObject) xrp.get("BTC");
        s = String.valueOf(xrpBtc.get("PRICE"));
        XRP.setBtc(new BigDecimal(s).toString());

        JSONObject xrpUsd = (JSONObject) xrp.get("USD");
        s = String.valueOf(xrpUsd.get("PRICE"));
        BigDecimal xrpUs = new BigDecimal(s);
        XRP.setUsd(xrpUs.toString());

        XRP.setUsdKrw(rate.multiply(new BigDecimal(s)).toString()); //USDKRW

        BigDecimal xrpPrem = xrpKr.subtract(xrpUs.multiply(rate));
        XRP.setUsdKrwPremium(xrpPrem.toString()); //premium

        XRP.setPercent(percent.divide(xrpKr.divide(xrpPrem,3, BigDecimal.ROUND_CEILING),2, BigDecimal.ROUND_CEILING).toString());

        s = String.valueOf(xrpUsd.get("MKTCAP"));
        XRP.setMktcap(new BigDecimal(s).toString());

        XRP.setTitle("XRP");
        
        //////////----------LTC - 라이트코인-----------///////
        Cryptocompare LTC = new Cryptocompare();
        JSONObject ltc = (JSONObject) raw.get("LTC");

        JSONObject ltcKrw = (JSONObject) ltc.get("KRW");
        s = String.valueOf(ltcKrw.get("PRICE"));
        BigDecimal ltcKr = new BigDecimal(s);
        LTC.setKrw(ltcKr.toString());
        
        JSONObject ltcBtc = (JSONObject) ltc.get("BTC");
        s = String.valueOf(ltcBtc.get("PRICE"));
        LTC.setBtc(new BigDecimal(s).toString());

        JSONObject ltcUsd = (JSONObject) ltc.get("USD");
        s = String.valueOf(ltcUsd.get("PRICE"));
        BigDecimal ltcUs = new BigDecimal(s);
        LTC.setUsd(ltcUs.toString());

        LTC.setUsdKrw(rate.multiply(new BigDecimal(s)).toString()); //USDKRW

        BigDecimal ltcPrem = ltcKr.subtract(ltcUs.multiply(rate));
        LTC.setUsdKrwPremium(ltcPrem.toString()); //premium

        LTC.setPercent(percent.divide(ltcKr.divide(ltcPrem,3, BigDecimal.ROUND_CEILING),2, BigDecimal.ROUND_CEILING).toString());

        s = String.valueOf(ltcUsd.get("MKTCAP"));
        LTC.setMktcap(new BigDecimal(s).toString());

        LTC.setTitle("LTC");
        
        /////////----------DASH - Dash ------------///////
        Cryptocompare DASH = new Cryptocompare();
        JSONObject dash = (JSONObject) raw.get("DASH");

        JSONObject dashKrw = (JSONObject) dash.get("KRW");
        s = String.valueOf(dashKrw.get("PRICE"));
        BigDecimal dashKr = new BigDecimal(s);
        DASH.setKrw(dashKr.toString());
        
        JSONObject dashBtc = (JSONObject) dash.get("BTC");
        s = String.valueOf(dashBtc.get("PRICE"));
        DASH.setBtc(new BigDecimal(s).toString());

        JSONObject dashUsd = (JSONObject) dash.get("USD");
        s = String.valueOf(dashUsd.get("PRICE"));
        BigDecimal dashUs = new BigDecimal(s);
        DASH.setUsd(dashUs.toString());

        DASH.setUsdKrw(rate.multiply(new BigDecimal(s)).toString()); //USDKRW

        BigDecimal dashPrem = dashKr.subtract(dashUs.multiply(rate));
        DASH.setUsdKrwPremium(dashPrem.toString()); //premium
        
        DASH.setPercent(percent.divide(dashKr.divide(dashPrem,3, BigDecimal.ROUND_CEILING),2, BigDecimal.ROUND_CEILING).toString());
        
        s = String.valueOf(dashUsd.get("MKTCAP"));
        DASH.setMktcap(new BigDecimal(s).toString());

        DASH.setTitle("DASH");
        ////////////////////////////////////////////////////
        
        List<Cryptocompare> list = new LinkedList<>();
        list.add(BTC);
        list.add(ETH);
        list.add(ETC);
        list.add(XRP);
        list.add(LTC);
        list.add(DASH);
        return list;
    }
}