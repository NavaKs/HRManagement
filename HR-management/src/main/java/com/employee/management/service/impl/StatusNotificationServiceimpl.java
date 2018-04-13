package com.employee.management.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.employee.management.service.StatusNotificationService;

@Service
public class StatusNotificationServiceimpl implements StatusNotificationService {
 // logging
    static final Logger                logger = LogManager.getLogger(StatusNotificationServiceimpl.class.getName());
    @Override
    public void initiateNotification(com.employee.management.dto.NotificationDTO notificationData)
            throws InterruptedException {

        logger.info("Notification started :Application Status for " + notificationData.getId()
                + " is updated to :" + notificationData.getName());

        Thread.sleep(5000);

        logger.info("Notification ended: Application Status for " + notificationData.getId() + " is updated to :"
                + notificationData.getName());
    }

}
