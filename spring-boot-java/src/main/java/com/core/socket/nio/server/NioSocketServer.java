package com.core.socket.nio.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

public class NioSocketServer {

    /**
     * 日志
     */
    private static final Log LOGGER = LogFactory.getLog(NioSocketServer.class);

    private static final ConcurrentMap<Integer, StringBuilder> MESSAGECONTEXT = new ConcurrentHashMap<>();

    static {
        BasicConfigurator.configure();
    }

    public static void main(String[] args) throws Exception {
        // 开启一个轮询器
        Selector selector = Selector.open();

        // 开启一个服务端通道，并设置监听端口
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket serverSocket = serverChannel.socket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(new InetSocketAddress(83));

        // 开启第二个服务端通道，并设置监听端口
//        ServerSocketChannel serverChannel2 = ServerSocketChannel.open();
//        serverChannel2.configureBlocking(false);
//        ServerSocket serverSocket2 = serverChannel2.socket();
//        serverSocket2.setReuseAddress(true);
//        serverSocket2.bind(new InetSocketAddress(85));

        // 注意、服务器通道只能注册SelectionKey.OP_ACCEPT事件
        // 一个Selector可以注册管理多个ServerSocketChannel
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
//        serverChannel2.register(selector, SelectionKey.OP_ACCEPT);

        try {
            while(true) {
                // 如果条件成立，说明本次询问selector，并没有获取到任何准备好的、感兴趣的事件
                // java程序对多路复用IO的支持也包括了阻塞模式 和非阻塞模式两种。
                if(selector.select(100) == 0) {
                    continue;
                }
                // 这里就是本次询问操作系统，所获取到的“所关心的事件”的事件类型(每一个通道都是独立的)
                Iterator<SelectionKey> selecionKeys = selector.selectedKeys().iterator();

                while(selecionKeys.hasNext()) {
                    SelectionKey readyKey = selecionKeys.next();
                    // 这个已经处理的readyKey一定要移除。如果不移除，就会一直存在在selector.selectedKeys集合中
                    // 待到下一次selector.select() > 0时，这个readyKey又会被处理一次
                    selecionKeys.remove();
                    SelectableChannel selectableChannel = readyKey.channel();
                    if(readyKey.isValid() && readyKey.isAcceptable()) {
                        NioSocketServer.LOGGER.info("======channel通道已经准备好=======");
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel)selectableChannel;
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        // socket通道可以且只可以注册三种事件SelectionKey.OP_READ | SelectionKey.OP_WRITE | SelectionKey.OP_CONNECT
                        socketChannel.register(selector, SelectionKey.OP_READ , ByteBuffer.allocate(2048));
                    } else if(readyKey.isValid() && readyKey.isConnectable()) {
                        NioSocketServer.LOGGER.info("socket channel 建立连接");
                    } else if(readyKey.isValid() && readyKey.isReadable()) {
                        NioSocketServer.LOGGER.info("socket channel 数据准备完成，可以去读取");
                        readSocketChannel(readyKey);
                    }
                }
            }
        } catch(Exception e) {
            NioSocketServer.LOGGER.error(e.getMessage() , e);
        } finally {
            serverSocket.close();
        }
    }

    /**
     * 这个方法用于读取从客户端传来的信息。
     * 并且观察从客户端过来的socket channel在经过多次传输后，是否完成传输。
     * 如果传输完成，则返回一个true的标记。
     * @param readyKey
     * @throws Exception
     */
    private static void readSocketChannel(SelectionKey readyKey) throws Exception {
        SocketChannel clientSocketChannel = (SocketChannel)readyKey.channel();
        //获取客户端使用的端口
        InetSocketAddress sourceSocketAddress = (InetSocketAddress)clientSocketChannel.getRemoteAddress();
        Integer resoucePort = sourceSocketAddress.getPort();

        //拿到这个socket channel使用的缓存区，准备读取数据
        ByteBuffer contextBytes = (ByteBuffer)readyKey.attachment();
        StringBuilder message = new StringBuilder();
        int realLen = -1;
        while ((realLen = clientSocketChannel.read(contextBytes)) != 0) {
            // 一定要把buffer切换成读模式，否则由于limit = capacity, 在buffer没有写满情况下，会导致多读
            contextBytes.flip();
            byte[] messageBytes = contextBytes.array();
            String messageEncode = new String(messageBytes, 0, realLen, "UTF-8");
            message.append(messageEncode);
            // 再切回写模式
            contextBytes.clear();
        }

        NioSocketServer.LOGGER.info("端口" + resoucePort + "客户端发来的信息: " + message);
        int channelUUID = clientSocketChannel.hashCode();
        // 检测到over就说明发送结束了
        if(URLDecoder.decode(message.toString(), "UTF-8").indexOf("over") != -1) {
            //清空已经读取的缓存，并从新切换为写状态
            contextBytes.clear();
            StringBuilder fullMessage;
            StringBuilder historyMessage = MESSAGECONTEXT.remove(channelUUID);
            if (null == historyMessage) {
                fullMessage = message;
            } else {
                fullMessage = historyMessage.append(message);
            }
            NioSocketServer.LOGGER.info("端口" + resoucePort + "客户端发来的完整信息: " + URLDecoder.decode(fullMessage.toString(), "UTF-8"));
            // TODO ...
            //回发数据，并关闭channel
            ByteBuffer sendBuffer = ByteBuffer.wrap(URLEncoder.encode("回发处理结果", "UTF-8").getBytes());
            clientSocketChannel.write(sendBuffer);
            clientSocketChannel.close();
        } else {
            StringBuilder historyMessage = MESSAGECONTEXT.get(channelUUID);
            if (null == historyMessage) {
                historyMessage = new StringBuilder();
            }
            MESSAGECONTEXT.putIfAbsent(channelUUID, historyMessage.append(message));
        }
    }
}
