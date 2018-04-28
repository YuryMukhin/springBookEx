package ch4;

import java.security.MessageDigest;

public class MessageDigester {
    private MessageDigest d1;
    private MessageDigest d2;

    public void setD1(MessageDigest d1) {
        this.d1 = d1;
    }

    public void setD2(MessageDigest d2) {
        this.d2 = d2;
    }

    public void digest(String msg) {
        System.out.println("using d1");
        digest(msg, d1);

        System.out.println("using d2");
        digest(msg, d2);
    }

    private void digest(String msg, MessageDigest d) {
        System.out.println("using algorithm: " + d.getAlgorithm());
        d.reset();
        byte[] bytes = msg.getBytes();
        byte[] out = d.digest(bytes);

        System.out.println(out);
    }
}
