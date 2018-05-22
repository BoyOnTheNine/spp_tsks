package by.bsuir.spp.mail;

import by.bsuir.spp.entities.User;
import by.bsuir.spp.exceptions.DbException;
import by.bsuir.spp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class AutoMailDispatcher {

    @Autowired
    private SSLEmailSender emailSender;
    @Autowired
    private UserService userService;

    private String messageSubject = "Test subject";
    private String messageText = "Итоговая сдача лабы";
    private int hour = 18;
    private int minute = 27;

    private static final Logger log = LoggerFactory.getLogger(AutoMailDispatcher.class);

    @Scheduled(cron = "0 */1 * * * *")
    public void dispatchToAllUser() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        if ((hour == cal.get(Calendar.HOUR) || ((hour - 12) == cal.get(Calendar.HOUR)) )
                && minute == cal.get(Calendar.MINUTE)) {
            try {
                List<User> users = userService.getAll();
                for (User user : users) {
                    emailSender.send(messageSubject,
                            messageText,
                            "freelanceplatformspp@gmail.com",
                            user.getEmail());
                }
                log.info("Send email");
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
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
