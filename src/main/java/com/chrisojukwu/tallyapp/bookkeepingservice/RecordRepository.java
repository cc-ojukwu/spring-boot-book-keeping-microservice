package com.chrisojukwu.tallyapp.bookkeepingservice;

import com.chrisojukwu.tallyapp.bookkeepingservice.models.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Integer> {

List<Record> findByUserId(Integer userId);
}
