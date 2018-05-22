package by.bsuir.spp.payload;

public class ConfigDispatchRequest {

    private String messageSubject;
    private String messageText;
    private int hour;
    private int minute;

    public ConfigDispatchRequest() {

    }

    public ConfigDispatchRequest(String messageSubject, String messageText, int hour, int minute) {
        this.messageSubject = messageSubject;
        this.messageText = messageText;
        this.hour = hour;
        this.minute = minute;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
