package com.shareme.filesharing.service;

public class Test implements Runnable {

    private ReceivingFileData data;

    @Override
    public void run() {
	print(data);
    }

    public void print(ReceivingFileData data) {
	while (data.getPos().get() < data.getBytes()) {
	    System.out.println(data.getPos());
	}
    }

    public Test(ReceivingFileData data) {
	super();
	this.data = data;
    }
}
