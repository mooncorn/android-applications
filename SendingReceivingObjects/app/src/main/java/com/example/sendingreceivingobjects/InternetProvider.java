package com.example.sendingreceivingobjects;

import java.io.Serializable;

public class InternetProvider implements Serializable {
    private String clientNumber;
    private ProviderBase provider;
    private Integer nbOfMonths;

    public InternetProvider(String clientNumber, ProviderBase provider, Integer nbOfMonths) {
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

    public ProviderBase getProvider() {
        return provider;
    }

    public void setProvider(ProviderBase providers) {
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
                "\nNb Of Months: " + nbOfMonths;
    }
}
