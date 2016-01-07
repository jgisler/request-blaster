package org.gislers.requestblaster.model;

/**
 * Created by:   jgisle
 * Created date: 11/16/15
 */
public enum Environment {
    ESBDEVV("esbdevv-esb-digital.nikedev.com"),
    ESBSBOXV("esbsboxv-esb-digital.nikedev.com"),
    ESBTIEV("esbtiev-esb-digital.nikedev.com"),
    ESBQAV("esbqav-esb-digital.nikedev.com"),
    ESBPERFV("esbperfv-esb-digital.nikedev.com"),
    ESBPRODCLONE("esbprodclone-esb-digital.nikedev.com"),
    ESBSTGV("esbstgv-esb-digital.nikedev.com"),
    ESBPRDV("esb-digital.nikedev.com");

    private String hostname;

    Environment(String hostname) {
        this.hostname = hostname;
    }

    public String getHostname() {
        return hostname;
    }
}
