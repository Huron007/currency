package com.kodilla.currency.entity;

import lombok.Getter;

@Getter
public enum Code {
    THB("bat (Tajlandia)", null),
    USD("dolar amerykański", null),
    AUD("dolar australijski", null),
    HKD("dolar Hongkongu", null),
    CAD("dolar kanadyjski", null),
    NZD("dolar nowozelandzki", null),
    SGD("dolar singapurski", null),
    EUR("euro", null),
    HUF("forint (Węgry)", null),
    CHF("frank szwajcarski", null),
    GBP("funt szterling", null),
    UAH("hrywna (Ukraina)", null),
    JPY("jen (Japonia)", null),
    CZK("korona czeska", null),
    DKK("korona duńska", null),
    ISK("korona islandzka", null),
    NOK("korona norweska", null),
    SEK("korona szwedzka", null),
    HRK("kuna (Chorwacja)", null),
    RON("lej rumuński", null),
    BGN("lew (Bułgaria)", null),
    TRY("lira turecka", null),
    ILS("nowy izraelski szekel", null),
    CLP("peso chilijskie", null),
    PHP("peso filipińskie", null),
    MXN("peso meksykańskie", null),
    ZAR("rand (Republika Południowej Afryki)", null),
    BRL("real (Brazylia)", null),
    MYR("ringgit (Malezja)", null),
    IDR("rupia indonezyjska", null),
    INR("rupia indyjska", null),
    KRW("won południowokoreański", null),
    CNY("yuan renminbi (Chiny)", null),
    XDR("SDR (MFW)", null),
    //Crypto
    btc("bitcoin","Bitcoin"),
    eth("ethereum", "Ethereum"),
    usdt("tether", "Tether"),
    usdc("usd-coin", "USD Coin"),
    bnb("binancecoin", "BNB"),
    ada("cardano", "Cardano"),
    xrp("ripple", "XRP"),
    busd("binance-usd", "Binance USD"),
    sol("solana", "Solana"),
    doge("dogecoin", "Dogecoin");

    public final String name;
    public final String fullname;

    Code(String name, String fullname) {
        this.name = name;
        this.fullname = fullname;
    }
}
