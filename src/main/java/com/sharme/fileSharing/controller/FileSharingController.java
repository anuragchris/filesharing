package com.sharme.fileSharing.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shareme.fileShare.model.FileProperties;
import com.shareme.fileShare.model.SendFileDetails;
import com.shareme.filesharing.service.FileProcessing;

@Controller
public class FileSharingController {

    @PostMapping("/send")
    public void send(SendFileDetails sendFileDetail) {
	ResponseEntity<List<String>> response = null;
	List<String> fileResponse;
	for (int i = 0; i < sendFileDetail.getFileDetails().size(); i++) {
	    try {
		File fileName = sendFileDetail.getFileDetails().get(i);
		Path filePath = Paths.get(fileName.getAbsolutePath());
		long size = Files.size(filePath);
		FileProcessing share = new FileProcessing();
		fileResponse = share.bigFile(sendFileDetail.getFileDetails(), sendFileDetail.getRemoteMachineAddress());
	    } catch (Exception e) {

	    }
	}

    }

    @PostMapping("/receive/smallfile")
    public ResponseEntity<String> receiveSmallFile(@RequestParam("file") MultipartFile multipartFile
    /* @RequestParam("file-name") MultipartFile multipartFileNam */) {
	// file to byte[], Path

	File file = new File("C:\\Users\\Anurag PC\\Desktop\\" + multipartFile.getOriginalFilename());
	try {
	    // List<Object> input = multipartFile.get("custom_file");
	    byte[] bytes = multipartFile.getBytes();
	    file.createNewFile();
	    Path filePath = Paths.get(file.getPath());
	    Files.write(filePath, bytes, StandardOpenOption.WRITE);
	    return new ResponseEntity<String>("Text File transfer successfull ! ", HttpStatus.OK);
	} catch (Exception e) {
	    return new ResponseEntity<String>("Text File transfer failed ! ->" + e.getMessage(),
		    HttpStatus.BAD_REQUEST);
	}

    }

    private static final int PORT = 9999;
    static int a = 0;
    static boolean check = true;

    @PostMapping("/receive/bigfile")
    public ResponseEntity<String> receiveBigFile(@RequestBody FileProperties fileProp) {
	// file to byte[], Path
	System.out.println("hi");
	try {
	    // if (a == 0) {
	    // final CountDownLatch latch = new CountDownLatch(1);
	    // Platform.runLater(new Runnable() {
	    //
	    // @Override
	    // public void run() {
	    // controller popwindow = new controller();
	    // try {
	    // check = popwindow.alertwindow();
	    // } catch (IOException e) {
	    // // TODO Auto-generated catch block
	    // e.printStackTrace();
	    // }
	    // latch.countDown();
	    // a++;
	    //
	    // }
	    // });
	    //
	    // try {
	    // latch.await();
	    // } catch (InterruptedException e) {
	    // Platform.exit();
	    // }
	    //
	    // }

	    if (check) {
		FileProcessing processFile = new FileProcessing();
		processFile.processBigFile(fileProp, "");
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);
	    } else {
		return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
	    }
	} catch (Exception e) {
	    return new ResponseEntity<String>("Text File transfer failed ! ->" + e.getMessage(),
		    HttpStatus.BAD_REQUEST);
	}

    }

    public static byte[] readFully(InputStream stream) throws IOException {
	byte[] buffer = new byte[8192];
	ByteArrayOutputStream baos = new ByteArrayOutputStream();

	int bytesRead;
	while ((bytesRead = stream.read(buffer)) != -1) {
	    baos.write(buffer, 0, bytesRead);
	}
	return baos.toByteArray();
    }

    public static byte[] loadFile(SendFileDetails sendFileDetail, String fileName) throws IOException {
	InputStream inputStream = null;
	try {
	    inputStream = new FileInputStream(
		    new File(sendFileDetail.getLocation() + System.getProperty("file.separator") + fileName));
	    return readFully(inputStream);
	} finally {
	    if (inputStream != null) {
		inputStream.close();
	    }
	}
    }

}
