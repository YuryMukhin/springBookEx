package ch4;

import java.security.MessageDigest;

public class MessageDigestFactory {
    private String algorithmName = "MD5";

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public MessageDigest createInstance() throws Exception {
        return MessageDigest.getInstance(algorithmName);
    }
}
