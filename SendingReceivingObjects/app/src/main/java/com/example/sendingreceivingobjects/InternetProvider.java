package com.example.sendingreceivingobjects;

import java.io.Serializable;

public class InternetProvider implements Serializable {
    private String clientNumber;
    private Provider provider;
    private Integer nbOfMonths;

    public InternetProvider(String clientNumber, Provider provider, Integer nbOfMonths) {
        this.clientNumber = clientNumber;
        this.provider = provider;
        this.nbOfMonths = nbOfMonths;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider providers) {
        this.provider = providers;
    }

    public Integer getNbOfMonths() {
        return nbOfMonths;
    }

    public void setNbOfMonths(Integer nbOfMonths) {
        this.nbOfMonths = nbOfMonths;
    }

    @Override
    public String toString() {
        return "Client Number: " + clientNumber +
                "\nProvider: " + provider +
                "\nNb Of Months: " + nbOfMonths + "\n";
    }
}
