package org.example.front.RSA;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RsaImplementation {
    private final int MAX_SIZE = 512;
    private final int SIZE_E = 17;
    private BigInteger mQ;

    private boolean generated = false;


    private Key publicKey;
    private Key privatecKey;

    private Key generatePublicKey() {
        SecureRandom secureRandom = new SecureRandom();

        // przeliczamy na bity (przybliżone)
        int bitsP = MAX_SIZE;
        int bitsQ = MAX_SIZE;
        BigInteger e = BigInteger.probablePrime(SIZE_E, secureRandom);
        // generujemy liczby pierwsze
        BigInteger p = BigInteger.probablePrime(bitsP, secureRandom);
        BigInteger q = BigInteger.probablePrime(bitsQ, secureRandom);
        BigInteger m = p.multiply(q);
        mQ = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        return new Key(m, e);
    }

    private Key generetePrivateKey(Key key, BigInteger mq) {
        BigInteger m = key.getX1();
        BigInteger e = key.getX2();
        BigInteger d = e.modInverse(mq);

        return new Key(m, d);
    }

    public void run() {
        if (generated) {
            System.out.println("⚠️ Klucze już zostały wygenerowane!");
            return; // nic nie rób ponownie
        }

        publicKey = generatePublicKey();
        privatecKey = generetePrivateKey(publicKey, mQ);
        generated = true; // oznacz, że już wygenerowano
    }


    public Key getPublicKey() {
        return publicKey;
    }

    public Key getPrivatecKey() {
        return privatecKey;
    }

    private BigInteger encryption(BigInteger messege,Key publicKeyAnotherUser){
        BigInteger m = publicKeyAnotherUser.getX1();
        BigInteger e = publicKeyAnotherUser.getX2();

        BigInteger hash = messege.modPow(e,m);
        return hash;

    }
    private BigInteger decryption(BigInteger messege){

        BigInteger m = this.getPrivatecKey()
                .getX1();

        BigInteger d = this.getPrivatecKey()
                .getX2();

        BigInteger hash = messege.modPow(d,m);

        return hash;
    }

    public String EncryptionMessege(String s, Key publicKey){
        String encryption = s.chars().
                mapToObj(c -> BigInteger.valueOf(c)).
                map(bigInteger -> encryption(bigInteger,publicKey)).
                map(c -> c.toString()).
                collect(Collectors.joining(""));

        System.out.println(encryption);

        return  encryption;

    }


    public String DescriptionMessege(String s){
        return Arrays.stream(s.split(" "))
                .map(BigInteger::new)
                .map(this::decryption)
                .map(bi -> Character.toString((char) bi.intValue()))
                .collect(Collectors.joining());
    }


}
