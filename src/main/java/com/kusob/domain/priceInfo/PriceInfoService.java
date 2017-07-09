package com.kusob.domain.priceInfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kusob on 2017. 7. 8..
 */
@Service
public class PriceInfoService {

    public List<PriceInfo> getPrice() throws IOException{
        String articleUrl = "http://coinmarketcap.com/";
        Document document = Jsoup.connect(articleUrl).get();
        Elements bitcoin = document.select("tr#id-bitcoin");
        String bitcoinGraph = document.select("tr#id-bitcoin td a img.sparkline").attr("src");
        Elements ethereum = document.select("tr#id-ethereum");
        String ethereumGraph = document.select("tr#id-ethereum td a img.sparkline").attr("src");
        Elements ethereumClassic = document.select("tr#id-ethereum-classic");
        String ethereumClassicGraph = document.select("tr#id-ethereum-classic td a img.sparkline").attr("src");
        Elements ripple = document.select("tr#id-ripple");
        String rippleGraph = document.select("tr#id-ripple td a img.sparkline").attr("src");
        Elements litecoin = document.select("tr#id-litecoin");
        String litecoinGraph = document.select("tr#id-litecoin td a img.sparkline").attr("src");
        Elements dashs = document.select("tr#id-dash");
        String dashGraph = document.select("tr#id-dash td a img.sparkline").attr("src");
        
        String[] btcList = bitcoin.text().split(" ");
        String[] ethList = ethereum.text().split(" ");
        String[] etcList = ethereumClassic.text().split(" ");
        String[] xrpList = ripple.text().split(" ");
        String[] ltcList = litecoin.text().split(" ");
        String[] dashList = dashs.text().split(" ");

        PriceInfo btc = new PriceInfo(btcList[1],btcList[3],
                btcList[4]+" "+btcList[5],btcList[6],btcList[7],btcList[2],bitcoinGraph,btcList[5]);
        PriceInfo eth = new PriceInfo(ethList[1],ethList[3],
                ethList[4]+" "+ethList[5],ethList[6],ethList[7],ethList[2],ethereumGraph,ethList[5]);
        PriceInfo etc = new PriceInfo(etcList[1]+etcList[2],etcList[3],
                etcList[5]+" "+etcList[6],etcList[7],etcList[8],etcList[3],ethereumClassicGraph,etcList[6]);
        PriceInfo xrp = new PriceInfo(xrpList[1],xrpList[3],
                xrpList[4]+" "+xrpList[5],xrpList[7],xrpList[8],xrpList[2],rippleGraph,xrpList[5]);
        PriceInfo ltc = new PriceInfo(ltcList[1],ltcList[3],
                ltcList[4]+" "+ltcList[5],ltcList[6],ltcList[7],ltcList[2],litecoinGraph,ltcList[5]);
        PriceInfo dash = new PriceInfo(dashList[1],dashList[3],
                dashList[4]+" "+dashList[5],dashList[6],dashList[7],dashList[2],dashGraph,dashList[5]);
        
        List<PriceInfo> list = new ArrayList<>();
        list.add(btc);
        list.add(eth);
        list.add(etc);
        list.add(xrp);
        list.add(ltc);
        list.add(dash);
        return list;
    }
}
