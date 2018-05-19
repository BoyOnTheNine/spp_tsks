package bertosh.controller;

import bertosh.mail.SSLEmailSender;
import bertosh.payload.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/mail")
public class EmailSenderController {

    @Autowired
    private SSLEmailSender emailSender;

    @PostMapping("/send")
    public ResponseEntity send(@RequestBody EmailRequest emailRequest) {
        emailSender.send(emailRequest.getSubject(), emailRequest.getText(), emailRequest.getFromEmail(), emailRequest.getToEmail());
        return new ResponseEntity(HttpStatus.OK);
    }
}
