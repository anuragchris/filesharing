package com.shareme.fileShare.model;

/**
 * @author Anurag PC
 *
 */
public class FileDetails {

    private String fileName;

    private byte[] data;

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public byte[] getData() {
	return data;
    }

    public void setData(byte[] data) {
	this.data = data;
    }

}
