package service.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationRequest {
    private String to;
    private String message;
    public NotificationRequest(String message, String to) {
        this.message = message;
        this.to = to;
    }
}
