package com.employee.management.service;

public interface StatusNotificationService {

    void initiateNotification(com.employee.management.dto.NotificationDTO notificationData) throws InterruptedException;

}
