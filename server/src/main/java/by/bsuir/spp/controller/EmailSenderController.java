package by.bsuir.spp.controller;

import by.bsuir.spp.exceptions.AppException;
import by.bsuir.spp.payload.EmailRequest;
import by.bsuir.spp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/mail")
public class EmailSenderController {

    @Autowired
    private EmailService service;

    @PutMapping("/send")
    public ResponseEntity send(@RequestBody EmailRequest emailRequest) {
        if (service.sendDirect(
                emailRequest.getSubject(), emailRequest.getText(), emailRequest.getFromEmail(), emailRequest.getToEmail())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AppException("Cannot send email to user"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/sendAll")
    public ResponseEntity sendToAllUsers(@RequestBody EmailRequest emailRequest) {
        if (service.sendToAll(emailRequest.getSubject(), emailRequest.getText(), emailRequest.getFromEmail())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new AppException("Cannot send email to all users"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
