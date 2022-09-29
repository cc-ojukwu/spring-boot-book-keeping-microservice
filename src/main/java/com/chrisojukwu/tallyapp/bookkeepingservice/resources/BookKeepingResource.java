package com.chrisojukwu.tallyapp.bookkeepingservice.resources;

import com.chrisojukwu.tallyapp.bookkeepingservice.RecordRepository;
import com.chrisojukwu.tallyapp.bookkeepingservice.models.Record;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bookkeeping")
public class BookKeepingResource {

    private final RecordRepository recordRepository;

    public BookKeepingResource(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping("/{userId}/records")
    public List<Record> getAllRecordsForUser(@PathVariable Integer userId) {
        return recordRepository.findByUserId(userId);
    }

    @PostMapping("/{userId}/records/")
    public ResponseEntity<Record> createRecord(@PathVariable Integer userId, @Valid @RequestBody Record record) {

        record.setUserId(userId);
        Record savedRecord = recordRepository.save(record);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(savedRecord.getId())
                .toUri();

        return ResponseEntity.created(location).build(); //return 201 status code instead of 200
    }

    @PutMapping("/{userId}/records/{id}")
    public Record updateRecord(@PathVariable Integer userId, @PathVariable Integer id,
                                   @Valid @RequestBody Record newRecord) {

         return recordRepository.findById(id).map(record-> {
             record.setUserId(userId);
             record.setAmount(newRecord.getAmount());
             record.setDate(newRecord.getDate());
             record.setDescription(newRecord.getDescription());
             return recordRepository.save(record);
                 }).orElseGet(() -> {
                     return recordRepository.save(newRecord);
         });

    }

    @DeleteMapping("/{userId}/records/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable int id) {

        Optional<Record> record = recordRepository.findById(id);

        if(record != null){
            recordRepository.deleteById(id);
            return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Record not found", HttpStatus.BAD_REQUEST);
    }


}
