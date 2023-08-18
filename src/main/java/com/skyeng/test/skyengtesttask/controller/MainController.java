package com.skyeng.test.skyengtesttask.controller;

import com.skyeng.test.skyengtesttask.model.MailWay;
import com.skyeng.test.skyengtesttask.model.Mailing;
import com.skyeng.test.skyengtesttask.model.enums.EMailingStatus;
import com.skyeng.test.skyengtesttask.repository.MailWayRepository;
import com.skyeng.test.skyengtesttask.repository.MailingRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "TestTask API", description = "Skyeng test task API")
public class MainController {
    private final MailingRepository mailingRepository;
    private final MailWayRepository mailWayRepository;

    @PostMapping("/register_mailing")
    public ResponseEntity<Mailing> registerMailing(@RequestBody Mailing mailing) {
        Mailing savedMailing = mailingRepository.save(mailing);
        return ResponseEntity.ok(savedMailing);
    }

    @PostMapping("/register_way")
    public ResponseEntity<MailWay> registerMailWay(@RequestBody MailWay mailWay) {
        MailWay savedMailWay = mailWayRepository.save(mailWay);
        return ResponseEntity.ok(savedMailWay);
    }

    @PutMapping("/receipt/{id}")
    public ResponseEntity<String> receiptByAddressee(@PathVariable("id") Long lastMailWayId) {
        MailWay mailWay = mailWayRepository.findById(lastMailWayId).orElse(null);
        if (mailWay != null) {
            mailWayRepository.save(new MailWay(null, mailWay.getMailing(), mailWay.getPostOffice(), EMailingStatus.RECEIVED, LocalDateTime.now()));
            return ResponseEntity.ok("Посылка успешно получена");
        } else return ResponseEntity.badRequest().body("Посылка не найдена");
    }

    @GetMapping("/full_way/{id}")
    public ResponseEntity<List<MailWay>> getFullMailWay(@PathVariable("id") Long mailingId) {
        List<MailWay> mailWays = mailWayRepository.findAllByMailingIdOrderByIdDesc(mailingId);
        if (mailWays.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else return ResponseEntity.ok(mailWays);
    }
}
