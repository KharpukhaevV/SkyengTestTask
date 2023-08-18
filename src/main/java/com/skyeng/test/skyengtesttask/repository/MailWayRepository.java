package com.skyeng.test.skyengtesttask.repository;

import com.skyeng.test.skyengtesttask.model.MailWay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailWayRepository extends JpaRepository<MailWay, Long> {
    List<MailWay> findAllByMailingIdOrderByIdDesc(Long id);
}
