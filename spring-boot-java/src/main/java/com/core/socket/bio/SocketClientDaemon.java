package com.core.socket.bio;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * @Desc
 * @Author zhangzhigang
 * @Data 2020-10-11 19:00
 */
public class SocketClientDaemon {

    public static void main(String[] args) throws IOException {
        int clientNum = 20;
        CountDownLatch countDownLatch = new CountDownLatch(clientNum);

        // 分別开始启动这20个客户端请求
        for (int i = 0; i < clientNum; i++, countDownLatch.countDown()) {
            SocketClientThread client = new SocketClientThread(countDownLatch, i);
            new Thread(client).start();
        }

    }

    static class SocketClientThread implements Runnable {

        private static final Log LOGGER = LogFactory.getLog(SocketClientThread.class);

        private CountDownLatch countDownLatch;

        // 线程编号
        private Integer clientIndex;

        public SocketClientThread(CountDownLatch countDownLatch, Integer clientIndex) {
            this.countDownLatch = countDownLatch;
            this.clientIndex = clientIndex;
        }

        @Override
        public void run() {
            InputStream in = null;
            OutputStream out = null;
            Socket socket = null;

            try {
                socket = new Socket("localhost", 83);
                in = socket.getInputStream();
                out = socket.getOutputStream();

                // 等待，直到SocketClientDaemon完成所有线程启动，然后所有线程一起发送请求
                this.countDownLatch.await();

                // 发送请求信息
                out.write(("这是第" + this.clientIndex + "个客户端的请求。").getBytes());
                out.flush();

                SocketClientThread.LOGGER.info("第" + this.clientIndex + "个客户端的请求发送完成，等待服务器返回信息");
                int maxLen = 1024;
                byte[] contextBytes = new byte[maxLen];
                int realLen;
                String message = "";
                // 这个方法会阻塞，直到服务端有响应
                while ((realLen = in.read(contextBytes, 0, maxLen)) != -1) {
                    message += new String(contextBytes, 0, realLen);
                }
                SocketClientThread.LOGGER.info("接收到来自服务器的信息:" + message);
            } catch (Exception e) {
                SocketClientThread.LOGGER.error(e.getMessage(), e);
            } finally {
                try {
                    if (null != in) {
                        in.close();
                    }
                    if (null != out) {
                        out.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
