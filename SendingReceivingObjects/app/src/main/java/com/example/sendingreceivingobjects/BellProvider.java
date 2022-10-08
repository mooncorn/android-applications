package com.example.sendingreceivingobjects;

public class BellProvider extends ProviderBase {
    @Override
    public Double calculateSubtotal(Integer months) throws Exception {
        switch (months) {
            case 1:
                return 60d;
            case 12:
                return 600d;
            default:
                throw new Exception("Incorrect value of 'months' parameter");
        }
    }

    @Override
    public Integer[] getAcceptedMonths() {
        return new Integer[] { 1, 12 };
    }
}
