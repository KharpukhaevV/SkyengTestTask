package com.skyeng.test.skyengtesttask.model;

import com.skyeng.test.skyengtesttask.model.enums.EMailingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Mailing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer identifier;
    @Enumerated(EnumType.STRING)
    private EMailingType type;
    private String recipientIndex;
    private String recipientAddress;
    private String recipientName;
}
