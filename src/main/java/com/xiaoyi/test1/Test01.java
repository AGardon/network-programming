package com.xiaoyi.test1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author ������
 * @description Test01
 * @date 2022/9/21 12:52
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/AGarden
 */
public class Test01 {
  public static void main(String[] args) {}

  /**
   * InetAddress�÷�
   *
   * @throws UnknownHostException
   */
  @Test
  public void test1() throws UnknownHostException {
    // ���� ������������ip��ַ
    InetAddress address = InetAddress.getByName("www.baidu.com");
    // ��ȡip��ַ
    String hostAddress = address.getHostAddress();
    // ��ȡ����
    String hostName = address.getHostName();

    System.out.println(hostAddress);
    System.out.println(hostName);
  }

  /**
   * ��������
   *
   * @throws IOException
   */
  @Test
  public void test2() throws IOException {
    // 1. ����socket����
    DatagramSocket socket = new DatagramSocket();
    // 2. �ṩ���ݣ��������ݷ�װ�����ݰ���
    byte[] msg = "hello".getBytes();
    InetAddress address = InetAddress.getByName("activate.navicat.com");
    DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length, address, 8080);
    // 3. ͨ��socket�ķ��͹��ܣ������ݰ����ͳ�ȥ
    socket.send(datagramPacket);
    System.out.println("���ͳɹ�");
    // 4. �ͷ���Դ
    socket.close();
  }

  /**
   * ��������
   *
   * @throws IOException
   */
  @Test
  public void test3() throws IOException {
    // 1. ����socket����
    DatagramSocket datagramSocket = new DatagramSocket(8080);
    // 2. ��������
    byte[] bytes = new byte[1024];
    DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
    System.out.println("��ʼ��������...");
    datagramSocket.receive(datagramPacket);
    System.out.println("�����������...");
    // 3. ��������
    byte[] data = datagramPacket.getData();
    // 4. �������
    System.out.println(new String(data));
    // 5. �ͷ���Դ
    datagramSocket.close();
  }
}
