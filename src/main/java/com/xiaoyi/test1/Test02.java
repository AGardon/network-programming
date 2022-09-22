package com.xiaoyi.test1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 王艺翔
 * @description Test02
 * @date 2022/9/21 20:12
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/AGarden
 */
public class Test02 {

  static final int port = 8090;

  /**
   * tcp 发送数据
   *
   * @throws IOException
   */
  @Test
  public void test1() throws IOException {
    // 1. 创建socket对象
    Socket socket = new Socket(InetAddress.getByName("activate.navicat.com"), port);
    // 2. 获取输出流对象
    OutputStream outputStream = socket.getOutputStream();
    // 3. 发送数据
    outputStream.write("hello".getBytes());
    // 4. 释放资源
    outputStream.flush();
    outputStream.close();
    socket.close();
  }

  /**
   * tcp 接收数据
   *
   * @throws IOException
   */
  @Test
  public void test2() throws IOException {
    // 1. 创建接收端socket对象
    ServerSocket serverSocket = new ServerSocket(port);
    // 2. 监听
    Socket socket = serverSocket.accept();
    // 3. 获取数据
    InputStream inputStream = socket.getInputStream();
    byte[] bytes = new byte[1024];
    int read = inputStream.read(bytes);
    // 4. 输出数据
    System.out.println(new String(bytes, 0, read));
    // 5. 释放资源
    inputStream.close();
    socket.close();
    serverSocket.close();
  }
}
