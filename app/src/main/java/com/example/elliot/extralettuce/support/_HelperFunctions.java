package com.example.elliot.extralettuce.support;


public class _HelperFunctions {
    public static String getMoneyString(int value) {
        String returnValue = "" + value;
        StringBuilder builder = new StringBuilder(returnValue);
        if (returnValue.length() > 3) {
            builder.insert(builder.length() - 2, ".");
            builder.insert(0, "$");
        } else {
            builder.insert(0, "$0.");
        }

        return builder.toString();
    }
}
