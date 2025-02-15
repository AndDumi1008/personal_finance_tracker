package org.example.model.lists;

import lombok.Getter;

@Getter
public enum FinancialOperationCurrency {
    AED("د.إ"),
    AUD("A$"),
    AZN("₼"),
    BDT("৳"),
    BOB("Bs."),
    BRL("R$"),
    BYN("Br"),
    CAD("C$"),
    CHF("CHF"),
    CLP("$"),
    CNY("¥"),
    COP("$"),
    CZK("Kč"),
    DKK("kr"),
    DZD("د.ج"),
    EGP("£"),
    EUR("€"),
    GBP("£"),
    GHS("₵"),
    HKD("HK$"),
    HUF("Ft"),
    IDR("Rp"),
    ILS("₪"),
    INR("₹"),
    IQD("ع.د"),
    JPY("¥"),
    KES("KSh"),
    KRW("₩"),
    KZT("₸"),
    LBP("ل.ل"),
    LKR("Rs"),
    MAD("د.م."),
    MXN("$"),
    MYR("RM"),
    MZN("MT"),
    NGN("₦"),
    NOK("kr"),
    NZD("NZ$"),
    PHP("₱"),
    PKR("₨"),
    PLN("zł"),
    QAR("ر.ق"),
    RON("lei"),
    RUB("₽"),
    RSD("дин."),
    RWF("FRw"),
    SAR("ر.س"),
    SDG("ج.س."),
    SEK("kr"),
    SGD("S$"),
    SOS("Sh"),
    THB("฿"),
    TND("د.ت"),
    TRY("₺"),
    TWD("NT$"),
    TZS("TSh"),
    UAH("₴"),
    UGX("USh"),
    USD("$"),
    UZS("лв"),
    VEF("Bs."),
    VND("₫"),
    XAF("FCFA"),
    XOF("CFA"),
    ZAR("R");

    private final String symbol;

    FinancialOperationCurrency(String symbol) {
        this.symbol = symbol;
    }

}
