package com.airbnb.service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public void sendSms(String toPhoneNumber, String messageBody) {
        try {
            Message message = Message.creator(
                    new PhoneNumber(toPhoneNumber),  // To phone number
                    new PhoneNumber(twilioPhoneNumber), // From Twilio number
                    messageBody // Message content
            ).create();
            System.out.println("message sent");

        } catch (Exception e) {
            System.out.println("Failed to send SMS: " + e.getMessage());

        }
    }
}
