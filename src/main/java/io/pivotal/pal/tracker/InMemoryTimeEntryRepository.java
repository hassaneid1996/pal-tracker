package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.List.copyOf;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private final Map<Long, TimeEntry> timeEntries = new HashMap<>();

    private long nextId = 1;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long currentId = nextId++;
        TimeEntry timeEntry1 = new TimeEntry(
                currentId,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());

        timeEntries.put(currentId, timeEntry1);

        return timeEntry1;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return copyOf(timeEntries.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (timeEntries.containsKey(id)) {
            TimeEntry timeEntry1 = new TimeEntry(
                    id,
                    timeEntry.getProjectId(),
                    timeEntry.getUserId(),
                    timeEntry.getDate(),
                    timeEntry.getHours());
            timeEntries.replace(id, timeEntry1);
            return timeEntry1;
        }
        else {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        timeEntries.remove(id);
    }
}
