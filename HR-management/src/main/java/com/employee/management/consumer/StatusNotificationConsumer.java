package com.employee.management.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.dto.NotificationDTO;
import com.employee.management.service.StatusNotificationService;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
public class StatusNotificationConsumer implements Consumer<Event<NotificationDTO>> {

    @Autowired
    private StatusNotificationService statusNotificationService;

    @Override
    public void accept(Event<NotificationDTO> notificationDataEvent) {

    	NotificationDTO notificationData = notificationDataEvent.getData();
        try {
        	statusNotificationService.initiateNotification(notificationData);
        } catch (InterruptedException e) {
        }

    }

}
