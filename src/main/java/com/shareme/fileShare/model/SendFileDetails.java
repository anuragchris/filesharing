package com.shareme.fileShare.model;

import java.io.File;
import java.util.List;

public class SendFileDetails {

    // private String fileType;
    private String remoteMachineAddress;
    private List<File> fileDetails;

    public List<File> getFileDetails() {
	return fileDetails;
    }

    public void setFileDetails(List<File> fileDetails) {
	this.fileDetails = fileDetails;
    }

    private String[] fileNames;
    private String location;

    // public String getFileType() {
    // return fileType;
    // }
    //
    // public void setFileType(String fileType) {
    // this.fileType = fileType;
    // }

    public String getRemoteMachineAddress() {
	return remoteMachineAddress;
    }

    public void setRemoteMachineAddress(String remoteMachineAddress) {
	this.remoteMachineAddress = remoteMachineAddress;
    }

    public String[] getFileNames() {
	return fileNames;
    }

    public void setFileNames(String[] fileNames) {
	this.fileNames = fileNames;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

}
