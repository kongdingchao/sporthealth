package com.sporthealth.monitor;

import java.io.Closeable;
import java.io.OutputStream;

public interface MonitorResponse extends Closeable {
	/**
	 * 获得消息
	 * 
	 * @return
	 */
	Message getMessage();

	/**
	 * 关闭这个response已经其中的输出流
	 */
	void close();
}
