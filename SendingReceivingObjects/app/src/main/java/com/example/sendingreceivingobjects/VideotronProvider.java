package com.example.sendingreceivingobjects;

public class VideotronProvider extends ProviderBase {
    @Override
    public Double calculateSubtotal(Integer months) throws Exception {
        switch (months) {
            case 1:
                return 70d;
            case 6:
                return 350d;
            case 12:
                return 588d;
            default:
                throw new Exception("Incorrect 'months' parameter");
        }
    }

    @Override
    public Integer[] getAcceptedMonths() {
        return new Integer[] { 1, 6, 12 };
    }
}
