package com.shareme.fileShare.model;

public class FileProperties {

    private String FileName;

    private long fileLen;

    public String getFileName() {
	return FileName;
    }

    public void setFileName(String fileName) {
	FileName = fileName;
    }

    public long getFileLen() {
	return fileLen;
    }

    public void setFileLen(long fileLen) {
	this.fileLen = fileLen;
    }

}
