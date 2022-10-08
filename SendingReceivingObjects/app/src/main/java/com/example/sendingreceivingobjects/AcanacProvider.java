package com.example.sendingreceivingobjects;

public class AcanacProvider extends ProviderBase {
    @Override
    public Double calculateSubtotal(Integer months) throws Exception {
        switch (months) {
            case 1:
                return 45d;
            case 12:
                return 495d;
            default:
                throw new Exception("Incorrect value of 'months' parameter");
        }
    }

    @Override
    public Integer[] getAcceptedMonths() {
        return new Integer[] { 1, 12 };
    }

}
