package service.service;

import service.model.request.NotificationRequest;

public interface NotificationService {
    void sendOTP(NotificationRequest notificationRequest);
}
