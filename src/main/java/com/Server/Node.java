package com.Server;


public class Node {
    private  String address;
    private  int number;
    private  int portNumber;
    private boolean writeAffinity;

    public Node(String address, int number, int portNumber , boolean writeAffinity) {
        this.address = address;
        this.number = number;
        this.portNumber = portNumber;
        this.writeAffinity = false;
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

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

}
