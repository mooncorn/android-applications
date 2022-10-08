package com.example.sendingreceivingobjects;

import java.io.Serializable;

public abstract class ProviderBase implements Serializable {
    public abstract Integer[] getAcceptedMonths();
    public abstract Double calculateSubtotal(Integer months) throws Exception;

    public String getAcceptedMonthsString() {
        StringBuilder sb = new StringBuilder("");
        Integer[] acceptedMonths = getAcceptedMonths();

        for (int i = 0; i < acceptedMonths.length; i++) {
            sb.append(acceptedMonths[i]);

            if (i + 1 != acceptedMonths.length) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }
}
