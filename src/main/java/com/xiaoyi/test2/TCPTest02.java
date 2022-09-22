package com.xiaoyi.test2;

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
}

/** TCP客户端 */
class TCPClient02 {

  public static void main(String[] args) {
    // 发送数据
    new TCPClient02().send("activate.navicat.com");
  }

  /**
   * 发送数据
   *
   * @param url 域名或者ip号
   */
  public void send(String url) {
    while (true) {
      OutputStream outputStream = null;
      InputStream inputStream = null;
      Socket socket = null;
      try {
        socket = new Socket(InetAddress.getByName(url), TCPTest02.port);
        outputStream = socket.getOutputStream();
        System.out.println("请输入您的账号");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.next();
        System.out.println("请输入您的密码");
        String password = scanner.next();
        String content = "username=" + username + "&password=" + password;
        outputStream.write(content.getBytes());
        byte[] bytes = new byte[1024];
        inputStream = socket.getInputStream();
        int read = inputStream.read(bytes);
        String result = new String(bytes, 0, read);
        if ("ok".equals(result)) {
          System.out.println("登录成功");
        } else {
          System.out.println("登录失败");
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      } finally {
        try {
          assert outputStream != null;
          outputStream.close();
          assert inputStream != null;
          inputStream.close();
          socket.close();
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}

/** 服务端 */
class TCPServer02 {

  public static void main(String[] args) {
    // 接收数据
    new TCPServer02().accept();
  }

  /** 接收数据 */
  public void accept() {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(TCPTest02.port);
      while (true) {
        Socket accept = serverSocket.accept();
        new Thread(
                () -> {
                  InputStream inputStream = null;
                  OutputStream outputStream = null;
                  try {
                    inputStream = accept.getInputStream();
                    byte[] bytes = new byte[1024];
                    int read = inputStream.read(bytes);
                    String content = new String(bytes, 0, read);
                    String username = content.split("&")[0].split("=")[1];
                    String password = content.split("&")[1].split("=")[1];
                    outputStream = accept.getOutputStream();
                    if ("zhangsan".equals(username) && "123456".equals(password)) {
                      outputStream.write("ok".getBytes());
                    } else {
                      outputStream.write("fail".getBytes());
                    }
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  } finally {
                    try {
                      if (inputStream != null) {
                        inputStream.close();
                      }
                      if (outputStream != null) {
                        outputStream.close();
                      }
                      accept.close();
                    } catch (Exception e) {
                      throw new RuntimeException(e);
                    }
                  }
                })
            .start();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        if (serverSocket != null) {
          serverSocket.close();
        }
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
