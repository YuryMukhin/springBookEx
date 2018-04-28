package ch8.jpaService;

import ch8.entities.ContactSummary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("contactSummaryService")
@Repository
@Transactional
public class ContactSummaryServiceImpl implements ContactSummaryService {
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<ContactSummary> findAll() {
        return em.createQuery(
                     "select new ch8.entities.ContactSummary(c.firstName, c.lastName, t.telNumber) " +
                        "from Contact c left join c.contactTelDetails t " +
                        "where t.telType ='Home' ",
                ContactSummary.class).getResultList();
    }
}
