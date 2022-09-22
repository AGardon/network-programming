package com.xiaoyi.test1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ������
 * @description Test02
 * @date 2022/9/21 20:12
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/AGarden
 */
public class Test02 {

  static final int port = 8090;

  /**
   * tcp ��������
   *
   * @throws IOException
   */
  @Test
  public void test1() throws IOException {
    // 1. ����socket����
    Socket socket = new Socket(InetAddress.getByName("activate.navicat.com"), port);
    // 2. ��ȡ���������
    OutputStream outputStream = socket.getOutputStream();
    // 3. ��������
    outputStream.write("hello".getBytes());
    // 4. �ͷ���Դ
    outputStream.flush();
    outputStream.close();
    socket.close();
  }

  /**
   * tcp ��������
   *
   * @throws IOException
   */
  @Test
  public void test2() throws IOException {
    // 1. �������ն�socket����
    ServerSocket serverSocket = new ServerSocket(port);
    // 2. ����
    Socket socket = serverSocket.accept();
    // 3. ��ȡ����
    InputStream inputStream = socket.getInputStream();
    byte[] bytes = new byte[1024];
    int read = inputStream.read(bytes);
    // 4. �������
    System.out.println(new String(bytes, 0, read));
    // 5. �ͷ���Դ
    inputStream.close();
    socket.close();
    serverSocket.close();
  }
}
