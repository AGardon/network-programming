package com.xiaoyi.test1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author 王艺翔
 * @description Test01
 * @date 2022/9/21 12:52
 * @phone 18318436514
 * @email w2603494062@gmail.com
 * @github https://github.com/AGarden
 */
public class Test01 {
  public static void main(String[] args) {}

  /**
   * InetAddress用法
   *
   * @throws UnknownHostException
   */
  @Test
  public void test1() throws UnknownHostException {
    // 参数 ：主机名或者ip地址
    InetAddress address = InetAddress.getByName("www.baidu.com");
    // 获取ip地址
    String hostAddress = address.getHostAddress();
    // 获取名称
    String hostName = address.getHostName();

    System.out.println(hostAddress);
    System.out.println(hostName);
  }

  /**
   * 发送数据
   *
   * @throws IOException
   */
  @Test
  public void test2() throws IOException {
    // 1. 创建socket对象
    DatagramSocket socket = new DatagramSocket();
    // 2. 提供数据，并将数据封装到数据包中
    byte[] msg = "hello".getBytes();
    InetAddress address = InetAddress.getByName("activate.navicat.com");
    DatagramPacket datagramPacket = new DatagramPacket(msg, msg.length, address, 8080);
    // 3. 通过socket的发送功能，将数据包发送出去
    socket.send(datagramPacket);
    System.out.println("发送成功");
    // 4. 释放资源
    socket.close();
  }

  /**
   * 接收数据
   *
   * @throws IOException
   */
  @Test
  public void test3() throws IOException {
    // 1. 创建socket对象
    DatagramSocket datagramSocket = new DatagramSocket(8080);
    // 2. 接收数据
    byte[] bytes = new byte[1024];
    DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
    System.out.println("开始接收数据...");
    datagramSocket.receive(datagramPacket);
    System.out.println("接收数据完毕...");
    // 3. 解析数据
    byte[] data = datagramPacket.getData();
    // 4. 输出数据
    System.out.println(new String(data));
    // 5. 释放资源
    datagramSocket.close();
  }
}
