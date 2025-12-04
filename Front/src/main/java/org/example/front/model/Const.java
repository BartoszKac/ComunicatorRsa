package org.example.front.model;

import java.util.prefs.Preferences;

public class Const {

    private static Preferences preferences = Preferences.userRoot().node("Klucz");

    public static void saveKey(String key,String key2) {

        preferences.put("key", key);
        preferences.put("key2", key2);
    }

    public static String getKey() {
        return preferences.get("key", null);
    }
    public static String getKey2() {
        return preferences.get("key2", null);
    }
}
