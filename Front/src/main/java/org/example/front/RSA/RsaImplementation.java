package org.example.front.RSA;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RsaImplementation {
    private final int MAX_SIZE = 128;
    private final int SIZE_E = 16;
    private BigInteger mQ;

    private boolean generated = false;

    private Key publicKey;
    private Key privateKey;

    private Key generatePublicKey() {
        SecureRandom secureRandom = new SecureRandom();


        BigInteger e = BigInteger.probablePrime(SIZE_E, secureRandom);

        BigInteger p = BigInteger.probablePrime(MAX_SIZE, secureRandom);
        BigInteger q = BigInteger.probablePrime(MAX_SIZE, secureRandom);

        BigInteger m = p.multiply(q);


        mQ = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        return new Key(m, e);
    }

    private Key generatePrivateKey(Key pubKey, BigInteger mq) {
        BigInteger m = pubKey.getX1();
        BigInteger e = pubKey.getX2();


        BigInteger d = e.modInverse(mq);

        return new Key(m, d);
    }

    public void run() {
        if (generated) {
            System.out.println("⚠️ Klucze już zostały wygenerowane!");
            return;
        }

        this.publicKey = generatePublicKey();
        this.privateKey = generatePrivateKey(publicKey, mQ);
        this.generated = true;
    }

    public Key getPublicKey() {
        return publicKey;
    }

    public Key getPrivateKey() {
        return privateKey;
    }

    private BigInteger encryptValue(BigInteger message, Key pubKey) {
        return message.modPow(pubKey.getX2(), pubKey.getX1());
    }

    private BigInteger decryptValue(BigInteger encryptedMessage) {

        return encryptedMessage.modPow(privateKey.getX2(), privateKey.getX1());
    }


    public String EncryptionMessege(String s, Key targetPublicKey) {
        if (s == null || s.isEmpty()) return "";

        return s.chars()
                .mapToObj(c -> BigInteger.valueOf(c))
                .map(bi -> encryptValue(bi, targetPublicKey).toString())
                .collect(Collectors.joining(" "));
    }


    public String DescriptionMessege(String s) {
        if (s == null || s.trim().isEmpty()) return "";

        try {
            return Arrays.stream(s.split(" "))
                    .filter(part -> !part.isEmpty())
                    .map(BigInteger::new)
                    .map(this::decryptValue)
                    .map(bi -> Character.toString((char) bi.intValue()))
                    .collect(Collectors.joining());
        } catch (Exception e) {
            System.err.println("Błąd deszyfrowania: " + e.getMessage());
            return "[Błąd: Wiadomość zaszyfrowana innym kluczem]";
        }
    }
    public void setKeys(BigInteger n, BigInteger e, BigInteger d) {
        this.publicKey = new Key(n, e);
        this.privateKey = new Key(n, d);
    }
}