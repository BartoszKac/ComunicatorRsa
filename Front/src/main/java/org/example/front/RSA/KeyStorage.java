package org.example.front.RSA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.math.BigInteger;

public class KeyStorage {
    private static final String KEYS_FILE = "user_keys.properties";


    public static void saveKeys(String username, BigInteger n, BigInteger e, BigInteger d) {
        Properties props = new Properties();
        File file = new File(KEYS_FILE);
        if (file.exists()) {
            try (InputStream in = new FileInputStream(file)) {
                props.load(in);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        props.setProperty(username + ".n", n.toString());
        props.setProperty(username + ".e", e.toString());
        props.setProperty(username + ".d", d.toString());

        try (OutputStream out = new FileOutputStream(KEYS_FILE)) {
            props.store(out, "User RSA Keys");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static KeyPair loadKeys(String username) {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream(KEYS_FILE)) {
            props.load(in);
            if (props.containsKey(username + ".n")) {
                BigInteger n = new BigInteger(props.getProperty(username + ".n"));
                BigInteger e = new BigInteger(props.getProperty(username + ".e"));
                BigInteger d = new BigInteger(props.getProperty(username + ".d"));
                return new KeyPair(n, e, d);
            }
        } catch (IOException ex) {
        }
        return null;
    }


    public static void saveSentMessage(String myName, String partnerName, String text) {
        String fileName = myName + "_sent_to_" + partnerName + ".txt";
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            out.println(text);
        } catch (IOException e) {
            System.err.println("Błąd zapisu lokalnej kopii: " + e.getMessage());
        }
    }


    public static List<String> loadSentMessages(String myName, String partnerName) {
        List<String> messages = new ArrayList<>();
        String fileName = myName + "_sent_to_" + partnerName + ".txt";
        File file = new File(fileName);

        if (!file.exists()) return messages;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                messages.add(line);
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu lokalnej kopii: " + e.getMessage());
        }
        return messages;
    }



    public static class KeyPair {
        public BigInteger n, e, d;
        public KeyPair(BigInteger n, BigInteger e, BigInteger d) {
            this.n = n; this.e = e; this.d = d;
        }
    }

    public static void saveContact(String myName, String partnerName) {
        List<String> contacts = loadContacts(myName);
        if (!contacts.contains(partnerName)) {
            String fileName = myName + "_contacts.txt";
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
                out.println(partnerName);
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public static List<String> loadContacts(String myName) {
        List<String> contacts = new ArrayList<>();
        File file = new File(myName + "_contacts.txt");
        if (!file.exists()) return contacts;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                contacts.add(line);
            }
        } catch (IOException e) { e.printStackTrace(); }
        return contacts;
    }
}