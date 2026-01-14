package org.example.front.RSA;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Collectors;

public class RsaImplementation {
<<<<<<< HEAD
    private final int MAX_SIZE = 512;
    private final int SIZE_E = 17;
=======
<<<<<<< HEAD
    private final int MAX_SIZE = 512;
    private final int SIZE_E = 17;
=======
    private final int MAX_SIZE = 128;
    private final int SIZE_E = 16;
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
    private BigInteger mQ;

    private boolean generated = false;

<<<<<<< HEAD

    private Key publicKey;
    private Key privatecKey;
=======
<<<<<<< HEAD

    private Key publicKey;
    private Key privatecKey;
=======
    private Key publicKey;
    private Key privateKey;
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)

    private Key generatePublicKey() {
        SecureRandom secureRandom = new SecureRandom();

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
        // przeliczamy na bity (przybliżone)
        int bitsP = MAX_SIZE;
        int bitsQ = MAX_SIZE;
        BigInteger e = BigInteger.probablePrime(SIZE_E, secureRandom);
        // generujemy liczby pierwsze
        BigInteger p = BigInteger.probablePrime(bitsP, secureRandom);
        BigInteger q = BigInteger.probablePrime(bitsQ, secureRandom);
        BigInteger m = p.multiply(q);
<<<<<<< HEAD
=======
=======

        BigInteger e = BigInteger.probablePrime(SIZE_E, secureRandom);

        BigInteger p = BigInteger.probablePrime(MAX_SIZE, secureRandom);
        BigInteger q = BigInteger.probablePrime(MAX_SIZE, secureRandom);

        BigInteger m = p.multiply(q);


>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
        mQ = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        return new Key(m, e);
    }

<<<<<<< HEAD
    private Key generetePrivateKey(Key key, BigInteger mq) {
        BigInteger m = key.getX1();
        BigInteger e = key.getX2();
=======
<<<<<<< HEAD
    private Key generetePrivateKey(Key key, BigInteger mq) {
        BigInteger m = key.getX1();
        BigInteger e = key.getX2();
=======
    private Key generatePrivateKey(Key pubKey, BigInteger mq) {
        BigInteger m = pubKey.getX1();
        BigInteger e = pubKey.getX2();


>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
        BigInteger d = e.modInverse(mq);

        return new Key(m, d);
    }

    public void run() {
        if (generated) {
            System.out.println("⚠️ Klucze już zostały wygenerowane!");
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
            return; // nic nie rób ponownie
        }

        publicKey = generatePublicKey();
        privatecKey = generetePrivateKey(publicKey, mQ);
        generated = true; // oznacz, że już wygenerowano
    }


<<<<<<< HEAD
=======
=======
            return;
        }

        this.publicKey = generatePublicKey();
        this.privateKey = generatePrivateKey(publicKey, mQ);
        this.generated = true;
    }

>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
    public Key getPublicKey() {
        return publicKey;
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
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
<<<<<<< HEAD
=======
=======
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
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
