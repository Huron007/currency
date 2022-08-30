package com.kodilla.currency.service;

import com.kodilla.currency.entity.Alert;
import com.kodilla.currency.entity.Code;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @InjectMocks
    private EmailService service;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    void send() {
        //Given
        Alert alert = new Alert(1L, "test", Code.USD, 1.0, LocalDate.now(), true);

        //When
        service.send(alert);

        //Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }
}