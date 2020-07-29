package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        TimeEntry createdTimeEntry = timeEntryRepository.create(timeEntry);

        return new ResponseEntity<>(createdTimeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry foundTimeEntry = timeEntryRepository.find(timeEntryId);
        if(foundTimeEntry != null) {
            return new ResponseEntity<>(foundTimeEntry, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> TimeEntryList = timeEntryRepository.list();
        return new ResponseEntity<>(TimeEntryList, HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(timeEntryId,expected);
        if(updatedTimeEntry != null) {
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
