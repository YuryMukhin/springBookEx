package ch8.jpaService;

import ch7.contacts_schema.Contact;
import ch7.contacts_schema.Contact_;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

    final static String ALL_CONTACT_NATIVE_QUERY = "select c_id, first_name, last_name, birth_date from contact";

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findAll() {
        return em.createNamedQuery("Contact.findAll", Contact.class).getResultList();
    }

    @Override
    public List<Contact> findAllWithDetail() {
        return null;
    }

    @Override
    public Contact findById(Long id) {
        return null;
    }

    @Override
    public List<Contact> findAllBynativeQuery() {
        return em.createNativeQuery(ALL_CONTACT_NATIVE_QUERY, "contactResult").getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contact> findByCriteriaQuery(String firstName, String lastName) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Contact> cq = cb.createQuery(Contact.class);//создаётся типизированный запрос

        Root<Contact> contactRoot = cq.from(Contact.class);

        contactRoot.fetch(Contact_.ctds, JoinType.LEFT);
        contactRoot.fetch(Contact_.hobbies, JoinType.LEFT);

        cq.select(contactRoot).distinct(true);
        Predicate criteria = cb.conjunction();

        Predicate p_f = cb.equal(contactRoot.get(Contact_.firstName), firstName);
        criteria = cb.and(criteria, p_f);

        Predicate p_l = cb.equal(contactRoot.get(Contact_.lastName), lastName);
        criteria = cb.and(criteria, p_l);

        cq.where(criteria);

        return em.createQuery(cq).getResultList();
    }

    @Override
    public Contact save(Contact c) {
        if (c.getId() == null) {
            System.out.println("inserting new contact");
            em.persist(c);
        } else {
            System.out.println("updating new contact");
            em.merge(c);
        }
        return c;
    }

    @Override
    public void delete(Contact c) {
        Contact mergeC = em.merge(c);
        em.remove(mergeC);
    }
}
