package com.kdc.thread;

import sun.nio.ch.ThreadPool;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.net.ServerSocket;

/**
 * @program: sporthealth
 * @description: TODO
 * @author: kongdingchao
 * @create: 2019-05-03 16:27
 **/
public class SimpleHttpServer {
    static ExecutorService threadPool = Executors.newFixedThreadPool(16, new ThreadFactory() {
        private final ThreadFactory threadFactory = Executors.defaultThreadFactory();
        private final AtomicInteger threadNumber = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = threadFactory.newThread(r);
            thread.setName("HttpRequestHandler-" + threadNumber.getAndIncrement());
            return thread;
        }
    });
    //根路径
    static String basePath;
    static ServerSocket serverSocket;
    static int port = 8080;

    public static void main(String[] args) throws Exception {
        setPort(8880);
        setBasePath("E:\\study\\java\\test");
        start();
    }

    public static void setPort(int port) {
        SimpleHttpServer.port = port;
    }

    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void start() throws Exception{
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        System.out.println("start server ok: " + serverSocket.toString());
        while ((socket = serverSocket.accept()) != null) {
            System.out.println("accept a client socket: " + socket.toString());
            threadPool.execute(new HttpRequestHandler(socket));
        }
    }

    private static class HttpRequestHandler implements Runnable {
        Socket socket = null;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                //如果为jpg,ico，则读出文件
                if (filePath.endsWith("JPG") || filePath.endsWith("ico")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                    out.println("HTTP1.1 200 OK");
                    out.println("Server:Molly");
                    out.println("Content-Type: image/jpeg");

                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array, 0, array.length);
                } else {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out.println("HTTP1.1 200 OK");
                    out.println("Server:Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                }
                out.flush();
            } catch (Exception e) {
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(br, in, reader, out, socket);
            }
        }

        private void close(Closeable... closeables) {
            if (closeables != null) {
                for (Closeable closeable : closeables) {
                    try {
                        if (closeable != null) {
                            closeable.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
