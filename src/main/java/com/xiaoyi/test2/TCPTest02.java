package com.xiaoyi.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 王艺翔
 * @description TCPTest02
 * @date 2022/9/22 13:41
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/AGarden
 */
public class TCPTest02 {
  static final int port = 8080;
  static String str = "";
}

/** TCP客户端 */
class TCPClient02 {

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
        System.out.println("请输入您的账号");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        System.out.println("请输入您的密码");
        String password = scanner.next();
        String content = "username=" + username + "&password=" + password;
        outputStream.write(content.getBytes());
        byte[] bytes = new byte[1024];
        InputStream inputStream = socket.getInputStream();
        int read = inputStream.read();
        System.out.println(new String(bytes, 0, read));
      }
      outputStream.close();
      socket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

/** 服务端 */
class TCPServer02 {

  byte[] bytes = new byte[1024];
  int count = 0;

  public static void main(String[] args) {
    // 接收数据
    new TCPServer().accept();
  }

  /** 接收数据 */
  public void accept() {
    ServerSocket serverSocket = null;
    Socket socket = null;
    try {
      serverSocket = new ServerSocket(TCPTest.port);
      socket = serverSocket.accept();
      while (count != 10000) {
        Socket finalSocket = socket;
        new Thread(
                () -> {
                  InputStream inputStream = null;
                  try {
                    inputStream = finalSocket.getInputStream();
                    while (count != 1000) {
                      int read = inputStream.read(bytes);
                      String content = new String(bytes, 0, read);
                      String username = content.split("&")[0].split("=")[1];
                      String password = content.split("&")[1].split("=")[1];
                      OutputStream outputStream = finalSocket.getOutputStream();
                      if ("zhangsan".equals(username) && "123456".equals(password)) {
                        outputStream.write("ok".getBytes());
                      } else {
                        outputStream.write("fail".getBytes());
                      }
                      outputStream.close();
                      count++;
                    }
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  } finally {
                    try {
                      assert inputStream != null;
                      inputStream.close();
                    } catch (IOException e) {
                      e.printStackTrace();
                    }
                  }
                })
            .start();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (socket != null) {
          socket.close();
        }
        if (serverSocket != null) {
          serverSocket.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
