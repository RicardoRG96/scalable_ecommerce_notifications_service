package com.ricardo.scalable.ecommerce.platform.notifications_service.mail.model;

import java.util.Map;

public class EmailRequest {

    private String to;

    private EmailTemplateType templateType;

    private Map<String, Object> templateVariables;

    public EmailRequest() {
    }

    public EmailRequest(String to, EmailTemplateType templateType, Map<String, Object> templateVariables) {
        this.to = to;
        this.templateType = templateType;
        this.templateVariables = templateVariables;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public EmailTemplateType getTemplateType() {
        return templateType;
    }

    public void setTemplateType(EmailTemplateType templateType) {
        this.templateType = templateType;
    }

    public Map<String, Object> getTemplateVariables() {
        return templateVariables;
    }

    public void setTemplateVariables(Map<String, Object> templateVariables) {
        this.templateVariables = templateVariables;
    }

}
