package com.xiaoyi.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author ������
 * @description UDPTest
 * @date 2022/9/21 15:05
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/AGarden
 */
public class UDPTest {
  static final int port = 8080;
  static String str = "";
}
/** UDP�ͻ��� */
class UDPClient {
  public static void main(String[] args) {
    try {
      DatagramSocket datagramSocket = new DatagramSocket();
      InetAddress address = InetAddress.getByName("activate.navicat.com");
      while (!"666".equals(UDPTest.str)) {
        System.out.println("�����뷢�͵�����");
        Scanner scanner = new Scanner(System.in);
        UDPTest.str = scanner.next();
        byte[] next = UDPTest.str.getBytes();
        DatagramPacket datagramPacket =
            new DatagramPacket(next, next.length, address, UDPTest.port);
        datagramSocket.send(datagramPacket);
        System.out.println("���ͳɹ���" + UDPTest.str);
      }
      datagramSocket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

/** UDP����� */
class UDPServer {
  public static void main(String[] args) {
    try {
      DatagramSocket datagramSocket = new DatagramSocket(UDPTest.port);
      int count = 0;
      while (count != 10000) {
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        datagramSocket.receive(datagramPacket);
        byte[] data = datagramPacket.getData();
        UDPTest.str = new String(data).trim();
        System.out.println("���յ�������Ϊ:" + UDPTest.str);
        count++;
      }
      datagramSocket.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
