package com.skyeng.test.skyengtesttask.model;

import com.skyeng.test.skyengtesttask.model.enums.EMailingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MailWay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Mailing mailing;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private PostOffice postOffice;
    @Enumerated(EnumType.STRING)
    private EMailingStatus status;
    private LocalDateTime dateTime;
}
