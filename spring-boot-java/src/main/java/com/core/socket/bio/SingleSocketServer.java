package com.core.socket.bio;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Desc
 * @Author zhangzhigang
 * @Data 2020-10-11 19:00
 */
public class SingleSocketServer {
    private static final Log LOGGER = LogFactory.getLog(SingleSocketServer.class);

    static {
        BasicConfigurator.configure();
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(83);
        try {
            while (true) {
                // 这个方法会被阻塞，直到有客户端连接
                Socket socket = serverSocket.accept();
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                int sourcePort = socket.getPort();
                int maxLen = 2048;
                byte[] contextBytes = new byte[maxLen];
                // 读取数据，这里会阻塞，直到有数据准备好
                int readLen = in.read(contextBytes, 0, maxLen);
                String message = new String(contextBytes, 0, readLen);
                SingleSocketServer.LOGGER.info("服务器收到来自端口" + sourcePort + "的信息:" + message);
                // 向客户端返回数据
                out.write("响应客户端信息！".getBytes());
                // 关闭资源
                out.close();
                in.close();
                socket.close();
            }
        } catch (Exception e) {
            SingleSocketServer.LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != serverSocket) {
                serverSocket.close();
            }
        }
    }
}
