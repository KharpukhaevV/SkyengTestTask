package com.skyeng.test.skyengtesttask.controller;

import com.skyeng.test.skyengtesttask.model.MailWay;
import com.skyeng.test.skyengtesttask.model.Mailing;
import com.skyeng.test.skyengtesttask.model.PostOffice;
import com.skyeng.test.skyengtesttask.model.enums.EMailingStatus;
import com.skyeng.test.skyengtesttask.model.enums.EMailingType;
import com.skyeng.test.skyengtesttask.repository.MailWayRepository;
import com.skyeng.test.skyengtesttask.repository.MailingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MainControllerTest {
    @Mock
    private MailingRepository mailingRepository;
    @Mock
    private MailWayRepository mailWayRepository;

    @InjectMocks
    private MainController mainController;

    @Test
    public void testRegisterMailing() {
        Mailing mailing = new Mailing();
        mailing.setIdentifier(123);
        mailing.setType(EMailingType.PACKAGE);
        mailing.setRecipientIndex("123456");
        mailing.setRecipientAddress("ул. Пушкина, д. Колотушкина, кв. 15");
        mailing.setRecipientName("Иван Иванов");

        when(mailingRepository.save(mailing)).thenReturn(mailing);

        ResponseEntity<Mailing> responseEntity = mainController.registerMailing(mailing);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mailing, responseEntity.getBody());
    }

    @Test
    public void testRegisterMailWay() {
        Mailing mailing = new Mailing();
        mailing.setId(1L);
        mailing.setIdentifier(123);
        mailing.setType(EMailingType.PACKAGE);
        mailing.setRecipientIndex("123456");
        mailing.setRecipientAddress("ул. Пушкина, д. Колотушкина, кв. 15");
        mailing.setRecipientName("Иван Иванов");

        PostOffice postOffice = new PostOffice();
        postOffice.setId(1L);
        postOffice.setIndex(123456);
        postOffice.setName("Отделение связи №1");
        postOffice.setAddress("ул. Ленина, д. 1");

        MailWay mailWay = new MailWay();
        mailWay.setMailing(mailing);
        mailWay.setPostOffice(postOffice);
        mailWay.setStatus(EMailingStatus.DEPARTED);
        mailWay.setDateTime(LocalDateTime.now());

        when(mailWayRepository.save(mailWay)).thenReturn(mailWay);

        ResponseEntity<MailWay> responseEntity = mainController.registerMailWay(mailWay);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mailWay, responseEntity.getBody());
    }

    @Test
    public void testReceiptByAddressee() {
        MailWay mailWay = new MailWay();
        mailWay.setId(1L);
        mailWay.setStatus(EMailingStatus.DEPARTED);

        when(mailWayRepository.findById(1L)).thenReturn(Optional.of(mailWay));
        when(mailWayRepository.save(any(MailWay.class))).thenReturn(mailWay);

        ResponseEntity<String> responseEntity = mainController.receiptByAddressee(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Посылка успешно получена", responseEntity.getBody());
    }
}