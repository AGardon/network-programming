package com.xiaoyi.test1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author ������
 * @description TCPTest
 * @date 2022/9/21 20:30
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/AGarden
 */
public class TCPTest {
  static final int port = 8080;
  static String str = "";
}

/** TCP�ͻ��� */
class TCPClient {
  String over = "666";

  public static void main(String[] args) {
    // ��������
    new TCPClient().send("127.0.0.1");
  }

  /**
   * ��������
   *
   * @param url ��������ip��
   */
  public void send(String url) {
    try {
      while (true) {
        System.out.println("�����뷢�͵�����");
        Scanner scanner = new Scanner(System.in);
        TCPTest.str = scanner.next();
        if ("666".equals(TCPTest.str)) {
          break;
        }
        Socket socket = new Socket(url, TCPTest.port);
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(TCPTest.str.getBytes());
        outputStream.close();
        socket.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

/** ����� */
class TCPServer {

  byte[] bytes = new byte[1024];
  int count = 0;

  public static void main(String[] args) {
    // ��������
    new TCPServer().accept();
  }

  /** �������� */
  public void accept() {

    while (count != 10000) {
      try {
        ServerSocket serverSocket = new ServerSocket(TCPTest.port);
        Socket socket = serverSocket.accept();
        new Thread(
                () -> {
                  InputStream inputStream = null;
                  try {
                    inputStream = socket.getInputStream();
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                  System.out.println(Thread.currentThread().getName());
                  int read = 0;
                  try {
                    read = inputStream.read(bytes);
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                  System.out.println("���յ�������Ϊ:" + new String(bytes, 0, read));
                  try {
                    count++;
                    inputStream.close();
                    socket.close();
                    serverSocket.close();
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                })
            .start();
      } catch (Exception e) {
        e.getStackTrace();
      }
    }
  }
}
