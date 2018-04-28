package ch8.jpaService;

import ch8.entities.ContactSummary;

import java.util.List;

public interface ContactSummaryService {
    List<ContactSummary> findAll();
}
