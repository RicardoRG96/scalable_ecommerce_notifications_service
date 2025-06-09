package com.ricardo.scalable.ecommerce.platform.notifications_service.messaging;

public interface EventListener<T> {

    void onEvent(T event);

}
