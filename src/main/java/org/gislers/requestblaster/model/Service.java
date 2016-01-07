package org.gislers.requestblaster.model;

/**
 * Created by:   jgisle
 * Created date: 11/16/15
 */
public enum Service {

    ESB_DIGITAL_COMMUNICATION_PUSH(
            "esb-digital-communication-push",
            "/esb-digital-communication-push/http/send/"
    ),

    ESB_EMAIL_COMM(
            "esb-email-comm",
            "/esb-email-comm/http/emailcomm/sendemail/"
    ),

    ESB_DIGITAL_PRODUCT(
            "esb-digital-product",
            "/esb-digital-product/http/product/notify/"
    ),

    ESB_DIGITAL_PRODUCT_V4(
            "esb-digital-product.v4",
            "/esb-digital-product/http/product/notify/v4/"
    ),

    ESB_SUBSCRIPTION_CREATE_SUBSCRIPTION(
            "esb-subscription",
            "/esb-subscription/SubscriptionService/createSubscription"
    ),

    ESB_DIGITAL_EVENTS(
            "esb-digital-events",
            "/esb-digital-events/http/publish/events/"
    ),

    ;

    private String serviceName;
    private String resourcePath;

    Service(String serviceName, String resourcePath) {
        this.serviceName = serviceName;
        this.resourcePath = resourcePath;
    }

    public String serviceName() {
        return serviceName;
    }

    public String resourcePath() {
        return resourcePath;
    }
}
