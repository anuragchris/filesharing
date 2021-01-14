package com.shareme.filesharing.service;

import java.util.concurrent.atomic.AtomicLong;

public class ReceivingFileData {
    private AtomicLong pos;

    private Long bytes;
    private String fileName;

    public ReceivingFileData(AtomicLong pos, Long bytes, String fileName) {
	super();
	this.pos = pos;
	this.bytes = bytes;
	this.fileName = fileName;
    }

    public AtomicLong getPos() {
	return pos;
    }

    public void setPos(AtomicLong pos) {
	this.pos = pos;
    }

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public Long getBytes() {
	return bytes;
    }

    public void setBytes(Long bytes) {
	this.bytes = bytes;
    }

}
