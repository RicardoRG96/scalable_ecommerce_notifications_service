package com.ricardo.scalable.ecommerce.platform.notifications_service.mail.model;

public enum EmailTemplateType {

    USER_REGISTERED("user-registration.html", "¡Bienvenido a nuestra tienda!"),
    USER_BIRTHDAY("user-birthday.html", "¡Feliz cumpleaños!");

    private final String templateFileName;
    private final String subject;

    EmailTemplateType(String templateFileName, String subject) {
        this.templateFileName = templateFileName;
        this.subject = subject;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public String getSubject() {
        return subject;
    }

}
