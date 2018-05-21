package by.bsuir.spp.service;

import by.bsuir.spp.entities.User;
import by.bsuir.spp.mail.SSLEmailSender;
import by.bsuir.spp.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private final static Logger logger = LogManager.getLogger(EmailService.class);

    public boolean sendDirect(String subject, String text, String fromEmail, String toEmail) {
        try {
            emailSender.send(subject, text, fromEmail, toEmail);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
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
            logger.error(e.getMessage());
            return false;
        }
    }
}
