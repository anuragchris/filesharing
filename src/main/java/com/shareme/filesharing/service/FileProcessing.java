package com.shareme.filesharing.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.shareme.fileShare.model.FileProperties;

@Service
public class FileProcessing {
    // public final static int SOCKET_PORT = 8081;
    private static final int PORT = 9999;
    protected static final String TARGET = "C:\\Users\\Anurag PC\\Desktop";

    public List<String> bigFile(List<File> fileToSend, String remoteMachineAddress) {
	List<String> fileSendStatus = new ArrayList<String>();

	for (int i = 0; i < fileToSend.size(); i++) {

	    File f = fileToSend.get(i);
	    String filePath = f.getAbsolutePath();
	    long fileSize = f.length();
	    String fileName = f.getName();

	    FileProperties fileProp = new FileProperties();
	    fileProp.setFileLen(fileSize);
	    fileProp.setFileName(fileName);
	    RestTemplate template = new RestTemplate();

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<FileProperties> fileDetailHttpEntity = new HttpEntity<>(fileProp, headers);
	    try {
		ResponseEntity<String> response = template.exchange(
			"http://" + remoteMachineAddress + ":8080/receive/bigfile", HttpMethod.POST,
			fileDetailHttpEntity, String.class);
		FileReader reader;
		try {
		    reader = new FileReader(new FileSender(remoteMachineAddress, PORT), filePath);
		    reader.read();
		    fileSendStatus.add(response.getBody() + fileName);
		} catch (IOException e) {
		    fileSendStatus.add("file transfer failed: " + fileName);
		}

	    } catch (HttpClientErrorException ex) {

		if (ex.getRawStatusCode() == 406) {
		    fileSendStatus.add("file transfer failed: receiver not accepting ");
		}
	    }
	}
	return fileSendStatus;

    }

    public void processBigFile(FileProperties fileProp, String directory) throws IOException, InterruptedException {
	final FileReceiver receiver = new FileReceiver(PORT, new FileWriter(TARGET + "\\" + fileProp.getFileName()),
		fileProp.getFileLen());
	new Thread() {
	    @Override
	    public void run() {
		try {
		    receiver.receive(fileProp.getFileName());
		} catch (IOException e) {

		} finally {
		    // latch.countDown();
		}
	    }
	}.start();
	// latch.await();

    }

}
