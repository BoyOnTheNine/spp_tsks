package bertosh.service;

import bertosh.entities.User;
import bertosh.mail.SSLEmailSender;
import bertosh.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class EmailService {

    @Autowired
    private SSLEmailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    public boolean sendDirect(String subject, String text, String fromEmail, String toEmail) {
        try {
            emailSender.send(subject, text, fromEmail, toEmail);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean sendToAll(String subject, String text, String fromEmail) {
        try {
            List<User> userList = userRepository.findAll();
            for (User user : userList) {
                emailSender.send(subject, text, fromEmail, user.getEmail());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
