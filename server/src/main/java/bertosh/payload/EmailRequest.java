package bertosh.payload;

public class EmailRequest {

    private String subject;
    private String text;
    private String fromEmail;
    private String toEmail;

    public EmailRequest() {

    }

    public EmailRequest(String subject, String text, String fromEmail, String toEmail) {
        this.subject = subject;
        this.text = text;
        this.fromEmail = fromEmail;
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
}
