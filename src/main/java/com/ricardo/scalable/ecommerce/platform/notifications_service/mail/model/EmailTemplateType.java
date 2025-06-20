package com.ricardo.scalable.ecommerce.platform.notifications_service.mail.model;

public enum EmailTemplateType {

    USER_REGISTERED("user-registration", "¡Bienvenido a nuestra tienda!"),
    USER_BIRTHDAY("user-birthday", "¡Feliz cumpleaños!");

    private final String template;
    private final String subject;

    EmailTemplateType(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }

    public String getTemplate() {
        return template;
    }

    public String getSubject() {
        return subject;
    }

}
