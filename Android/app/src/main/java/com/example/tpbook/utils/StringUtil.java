package com.example.tpbook.utils;

import java.util.Locale;

class StringUtil {
    public static String formatPrice(int price)
    {
        String n= String.format(Locale.US, "%,d", price).replace(',', '.');
        return n+"đ";
    }
}
