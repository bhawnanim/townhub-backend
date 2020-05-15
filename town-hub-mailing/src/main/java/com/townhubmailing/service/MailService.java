package com.townhubmailing.service;

import com.townhubmailing.modal.EmailModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public Boolean sendEmail(EmailModal emailModal){
        try{
            SimpleMailMessage msg=new SimpleMailMessage();
            msg.setTo(emailModal.getEmailTo());
            msg.setSubject(emailModal.getSubjact());
            msg.setText(emailModal.getText());
            msg.setCc(emailModal.getCcEmail());
            javaMailSender.send(msg);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}