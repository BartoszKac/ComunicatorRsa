package org.example.front.RSA;

import java.math.BigInteger;

public class Key {
    private BigInteger x1;
    private BigInteger x2;

    @Override
    public String toString() {
        return "Key{" +
                "x1=" + x1 +
                ", x2=" + x2 +
                '}';
    }

    public Key(BigInteger x1, BigInteger x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public BigInteger getX1() {
        return x1;
    }

    public void setX1(BigInteger x1) {
        this.x1 = x1;
    }

    public BigInteger getX2() {
        return x2;
    }

    public void setX2(BigInteger x2) {
        this.x2 = x2;
    }


}
