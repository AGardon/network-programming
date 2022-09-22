package com.xiaoyi.test1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 王艺翔
 * @description TCPTestDemo
 * @date 2022/9/21 23:58
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/AGarden
 */
public class TCPTestDemo {
  static final int port = 8080;
  static String str = "";
}

/** TCP客户端 */
class TCPClientDemo {
  String over = "666";

  public static void main(String[] args) {
    // 发送数据
    new TCPClient().send("activate.navicat.com");
  }

  /**
   * 发送数据
   *
   * @param url 域名或者ip号
   */
  public void send(String url) {
    try {
      Socket socket = new Socket(InetAddress.getByName(url), TCPTest.port);
      OutputStream outputStream = socket.getOutputStream();
      while (!over.equals(TCPTest.str)) {
        System.out.println("请输入发送的内容");
        Scanner scanner = new Scanner(System.in);
        TCPTest.str = scanner.next();
        outputStream.write(TCPTest.str.getBytes());
      }
      outputStream.close();
      socket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

/** 服务端 */
class TCPServerDemo {

  byte[] bytes = new byte[1024];
  int count = 0;

  public static void main(String[] args) {
    // 接收数据
    new TCPServer().accept();
  }

  /** 接收数据 */
  public void accept() {
    try {
      ServerSocket serverSocket = new ServerSocket(TCPTest.port);
      Socket socket = serverSocket.accept();
      while (count != 10000) {
        new Thread(
                () -> {
                  try {
                    InputStream inputStream = socket.getInputStream();
                    while (count != 1000) {
                      int read = inputStream.read(bytes);
                      System.out.println("接收到的数据为:" + new String(bytes, 0, read));
                      count++;
                    }
                    inputStream.close();
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  }
                })
            .start();
      }
      socket.close();
      serverSocket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
