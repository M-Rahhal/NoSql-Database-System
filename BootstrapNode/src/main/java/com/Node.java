package com;

public class Node {
    private final String address;
    private final int number;
    private final int portNumber;
    private boolean writeAffinity;

    public Node(String address, int number, int portNumber) {
        this.address = address;
        this.number = number;
        this.portNumber = portNumber;
    }

    public String getAddress() {
        return address;
    }

    public int getNumber() {
        return number;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public boolean hasWriteAffinity() {
        return writeAffinity;
    }

    public void setWriteAffinity(boolean writeAffinity) {
        this.writeAffinity = writeAffinity;
    }
}
