package ch6;

import ch6.dao.ContactDao;
import ch6.dao.PlainContactDao;
import ch6.entities.Contact;

public class PlainJDBCSample {
    private static ContactDao cd = new PlainContactDao();

    public static void main(String[] args) {
        for (Contact c: cd.findAll()){
            System.out.println(c);
        }
    }
}
