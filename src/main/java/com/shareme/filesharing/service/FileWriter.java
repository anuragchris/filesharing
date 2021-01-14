package com.shareme.filesharing.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

final class FileWriter {

    private final FileChannel channel;

    FileWriter(final String path) throws IOException {
	if (org.springframework.util.StringUtils.isEmpty(path)) {
	    throw new IllegalArgumentException("path required");
	}

	this.channel = FileChannel.open(Paths.get(path), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
    }

    void transfer(final SocketChannel channel, final long bytes, String fileName) throws IOException {
	assert !Objects.isNull(channel);

	// long position = 0l;
	AtomicLong pos = new AtomicLong(0l);
	ReceivingFileData data = new ReceivingFileData(pos, bytes);
	Test test = new Test(data);
	Thread thread = new Thread(test);
	thread.start();
	while (pos.get() < bytes) {
	    pos.addAndGet(this.channel.transferFrom(channel, pos.get(), Constants.TRANSFER_MAX_SIZE));

	    // System.out.println(position / (1024 * 1024));
	}
    }

    int write(final ByteBuffer buffer, long position) throws IOException {
	assert !Objects.isNull(buffer);

	int bytesWritten = 0;
	while (buffer.hasRemaining()) {
	    bytesWritten += this.channel.write(buffer, position + bytesWritten);
	    // long size = position + bytesWritten;
	    // System.out.println(size);

	}

	return bytesWritten;
    }

    void close() throws IOException {
	this.channel.close();
    }
}
