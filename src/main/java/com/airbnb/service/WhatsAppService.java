package com.airbnb.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.util.Date;

@Service
public class WhatsAppService {

    @Value("${twilio.phone.number}")
    private String twilioWhatsAppNumber;
    private static final Logger logger = LoggerFactory.getLogger(WhatsAppService.class);
    public void sendWhatsAppMessage(String toPhoneNumber, String messageBody) {
        try {
            logger.info("sending......"+new Date());
            Message message = Message.creator(
                    new PhoneNumber("whatsapp:" + toPhoneNumber),  // To WhatsApp number
                    new PhoneNumber(twilioWhatsAppNumber),         // From WhatsApp number
                    messageBody                                    // Message content
            ).create();

            logger.info("sent......" + message.getSid()+"  "+new Date());
        } catch (Exception e) {
            logger.error(e.getMessage()+""+new Date());
        }
    }
}
