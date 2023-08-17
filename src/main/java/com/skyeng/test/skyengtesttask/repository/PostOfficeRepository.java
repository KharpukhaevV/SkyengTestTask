package com.skyeng.test.skyengtesttask.repository;

import com.skyeng.test.skyengtesttask.model.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {

}
