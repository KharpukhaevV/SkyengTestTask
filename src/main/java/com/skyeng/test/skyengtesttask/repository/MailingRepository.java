package com.skyeng.test.skyengtesttask.repository;

import com.skyeng.test.skyengtesttask.model.Mailing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailingRepository extends JpaRepository<Mailing, Long> {

}
