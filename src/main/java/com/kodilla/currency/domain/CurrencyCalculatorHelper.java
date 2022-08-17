package com.kodilla.currency.domain;

import com.kodilla.currency.entity.Code;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Getter
@RequiredArgsConstructor
public class CurrencyCalculatorHelper {
    
    private final List<Code> currencyList = Arrays.asList(Code.THB, Code.USD, Code.AUD, Code.HKD, Code.CAD, Code.NZD, Code.SGD, Code.EUR, Code.HUF, Code.CHF, Code.GBP, Code.UAH, Code.JPY, Code.CZK,
           Code.DKK, Code.ISK, Code.NOK, Code.SEK, Code.HRK, Code.RON, Code.BGN, Code.TRY, Code.ILS, Code.CLP, Code.PHP, Code.MXN, Code.ZAR, Code.BRL, Code.MYR, Code.IDR, Code.INR, Code.KRW, Code.CNY, Code.XDR);
    private final List<Code> cryptoCurrencyList = Arrays.asList(Code.btc, Code.eth, Code.usdt, Code.usdc, Code.bnb, Code.ada, Code.xrp, Code.busd, Code.sol, Code.doge);
    private final List<String> cryptoCurrencyNames = Arrays.asList("Bitcoin", "Ethereum", "Tether", "USD Coin", "BNB", "Cardano", "XRP", "Binance USD", "Solana", "Dogecoin");
}
