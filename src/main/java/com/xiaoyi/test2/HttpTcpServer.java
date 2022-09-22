package com.xiaoyi.test2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ������
 * @description HttpTcpServer
 * @date 2022/9/22 17:32
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/AGarden
 */
public class HttpTcpServer {

  public static void main(String[] args) {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(80);
      while (true) {
        Socket socket = serverSocket.accept();
        new Thread(
                () -> {
                  InputStream inputStream = null;
                  FileInputStream fileInputStream = null;
                  OutputStream outputStream = null;
                  try {
                    // ��ȡ�ļ�
                    inputStream = socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int read = inputStream.read(bytes);
                    String content = new String(bytes, 0, read);
                    String address = content.split("\r\n")[0].split(" ")[1];
                    // �����ļ�
                    // "D:\\JetBraints_workspace\\IntelliJ
                    // IDEA_workspace\\project\\network-programming\\src\\main\\resources\\static"
                    File file = new File("src\\main\\resources\\templates");
                    String path = file.getAbsolutePath();
                    fileInputStream = new FileInputStream(path + address);
                    byte[] data = new byte[1024];
                    int len = fileInputStream.read(data);
                    // ����ļ�
                    outputStream = socket.getOutputStream();
                    outputStream.write(data);
                  } catch (IOException e) {
                    throw new RuntimeException(e);
                  } finally {
                    try {
                      if (inputStream != null) {
                        inputStream.close();
                      }
                      socket.close();
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
        assert serverSocket != null;
        serverSocket.close();
      } catch (Exception e) {
        e.getStackTrace();
      }
    }
  }
}
