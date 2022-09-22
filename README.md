## HttpЭ���������

�ص� ��

1. �ײ����tcpЭ��ʵ�֣��������ӷ�ʽ��ȫ��
2. ��������request������Ӧ��response��ģ�͡�
3. ��״̬Э�飬����������û���κμ��书�ܡ�
4. ��������޷�������������cookie��session�������
5. httpЭ�����ݴ������������ͬ�����̣�����ͻ��˷������󵽷���ˣ������һֱû��Ӧ����ô���ܵ��¿ͻ���һֱ�����ȴ���
   ![img.png](image/img.png)

### �����ʽ

����һ����վ����[�ٶ�](www.baidu.com)Ϊ���ӣ�
���ʺ�f12����fn+f12���뿪���߹����У��ٴ�ˢ����ҳ��
![img_1.png](image/img_1.png)

1. ������  
   �������󷽷���url��http�汾  
   ![img_2.png](image/img_2.png)  
   ����Get��ʾ���󷽷���/��ʾurl��HTTP/1.1��ʾ�汾  
   ���󷽷��У�GET��POST��PUT��DELETE�ȡ�
2. ����ͷ����Ӧͷ
   �Լ�ֵ�Ե���ʽ����  
   [���ڳ��õ�����ͷ����Ӧͷ](https://juejin.cn/post/6844903745004765198)
3. ������  
   ������Ҫ�����ݴӿͻ��ˣ���������������͸�APIʱ���㽫����Ϊ�����巢�͡��������ǿͻ��˷��͸�API�����ݡ���Ӧ����API���͸��ͻ��˵����ݡ�
4. [��Ӧ״̬��](https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Status)

### �������͹���

![img_3.png](image/img_3.png)
![img_4.png](image/img_4.png)

### IP��ַ�Ͷ˿�

&emsp;&emsp;���ն�������```ipconfig```������Կ���ip��ַ
![img.png](image/img_5.png)
&ensp;&ensp;&ensp;&ensp;�ڵ�����ÿһ��Ӧ�ó�����һ���˿ں�  
&emsp;&emsp;ͨ��```netstat -aon```���鿴�������еĶ˿ں�  
&emsp;&emsp;ͨ��```netstat -aon|findstr "3306"```���鿴3306����˿ںű�PIDռ���ˡ�  
![img.png](image/img_6.png)
&emsp;&emsp;Ȼ��Ϳ���ͨ��PID���鿴���̣�����```tasklist|findstr "6808"```
![img_1.png](image/img_7.png)
&emsp;&emsp;�������̵�����Ϊ��```taskkill /T /F /PID 6808```  
&emsp;&emsp;�ڷ�����վʱ������IP��ַ�Ͷ˿ںţ�183.232.231.174:443  
&emsp;&emsp;ʹ��IP��ַ�Ͷ˿ں�Ҳ���Է��ʵ���վ���������������ھ��������뷢�͸�����һ���ļ�����ô����Ҫ��֪�����ĵ��Ե�IP��ַ�ͷ�������ļ���Ӧ�ó���Ķ˿ںš�  
&emsp;&emsp;����IP��ַ������ȷ���Է������ģ��˿ں�������ȷ��Ӧ�ó���ġ�

### InetAddress�÷�

```java
    // ���� ������������ip��ַ
    InetAddress address=InetAddress.getByName("www.baidu.com");
		    // ��ȡip��ַ
		    String hostAddress=address.getHostAddress();
		    // ��ȡ����
		    String hostName=address.getHostName();
		
		    System.out.println(hostAddress);
		    System.out.println(hostName);
```

### dns��������

�������������www.baidu.com����һ����������Ϊip��ַû�й����Һܳ�������ʹ�����������Ϳ��ԺܺõĽ��������⡣

## UDPЭ��

���������ӡ����ɿ���Э�飬��ȫϵ���ܵ͡����׶��������Ǵ����ٶȺܿ죬����Ҫ����tcpЭ���������֡�  
![img.png](image/img_8.png)

### UDP��������

```java
  /**
 * ��������
 * @throws IOException
 */
@Test
public void test2()throws IOException{
		// 1. ����socket����
		DatagramSocket socket=new DatagramSocket();
		// 2. �ṩ���ݣ��������ݷ�װ�����ݰ���
		byte[]msg="hello".getBytes();
		InetAddress address=InetAddress.getByName("activate.navicat.com");
		DatagramPacket datagramPacket=new DatagramPacket(msg,msg.length,address,8080);
		// 3. ͨ��socket�ķ��͹��ܣ������ݰ����ͳ�ȥ
		socket.send(datagramPacket);
		System.out.println("���ͳɹ�");
		// 4. �ͷ���Դ
		socket.close();
		}
```

### UDP��������

```java
  /**
 * ��������
 * @throws IOException
 */
@Test
public void test3()throws IOException{
		// 1. ����socket����
		DatagramSocket datagramSocket=new DatagramSocket(8080);
		// 2. ��������
		byte[]bytes=new byte[1024];
		DatagramPacket datagramPacket=new DatagramPacket(bytes,bytes.length);
		System.out.println("��ʼ��������...");
		datagramSocket.receive(datagramPacket);
		System.out.println("�����������...");
		// 3. ��������
		byte[]data=datagramPacket.getData();
		// 4. �������
		System.out.println(new String(data));
		// 5. �ͷ���Դ
		datagramSocket.close();
		}
```

### UDP��ϰ��

&emsp;&emsp;ʹ��UDPЭ�飬�ͻ��˿���һֱ�������ݸ�����ˣ� ����˿���һֱ
���տͻ��˷��͵����ݣ�����ͻ��˷���666�ͻ��˳��ͻ��˳���

```java
public class UDPTest {
	static final int port = 8080;
}

/**
 * UDP�ͻ���
 */
class UDPClient {
	public static void main(String[] args) {
		try {
			String str = "";
			DatagramSocket datagramSocket = new DatagramSocket();
			InetAddress address = InetAddress.getByName("activate.navicat.com");
			while (!"666".equals(str)) {
				System.out.println("�����뷢�͵�����");
				Scanner scanner = new Scanner(System.in);
				str = scanner.next();
				byte[] next = str.getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(next, next.length, address, UDPTest.port);
				datagramSocket.send(datagramPacket);
				System.out.println("���ͳɹ���" + str);
			}
			datagramSocket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

/**
 * UDP�����
 */
class UDPServer {
	public static void main(String[] args) {
		try {
			String str = "";
			DatagramSocket datagramSocket = new DatagramSocket(UDPTest.port);
			int count = 0;
			while (count!=10000) {
				byte[] bytes = new byte[1024];
				DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
				datagramSocket.receive(datagramPacket);
				byte[] data = datagramPacket.getData();
				str = new String(data).trim();
				System.out.println("���յ�������Ϊ:" + str);
				count++;
			}
			datagramSocket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
```

## TCPЭ��

&emsp;&emsp;�������ӵĿɿ�Э�飬ͨ��������������������  
&emsp;&emsp;�������� :  
![img_1.png](image/img_10.png)
&emsp;&emsp;�Ĵλ��� :  
![img_2.png](image/img_11.png)

### tcp��������

```java
/**
 * tcp ��������
 *
 * @throws IOException
 */
@Test
public void test1()throws IOException{
		// 1. ����socket����
		Socket socket=new Socket(InetAddress.getByName("activate.navicat.com"),port);
		// 2. ��ȡ���������
		OutputStream outputStream=socket.getOutputStream();
		// 3. ��������
		outputStream.write("hello".getBytes());
		// 4. �ͷ���Դ
		outputStream.flush();
		outputStream.close();
		socket.close();
		}
```

![img_3.png](image/img_12.png)  
&emsp;&emsp;�����ԭ���ǣ��Ҳ�������ˣ���ǰ�Ľ��ն˻�û�н�����

### tcp��������

```java
  /**
 * tcp ��������
 * @throws IOException
 */
@Test
public void test2()throws IOException{
		// 1. �������ն�socket����
		ServerSocket serverSocket=new ServerSocket(port);
		// 2. ����
		Socket socket=serverSocket.accept();
		// 3. ��ȡ����
		InputStream inputStream=socket.getInputStream();
		byte[]bytes=new byte[1024];
		int read=inputStream.read(bytes);
		// 4. �������
		System.out.println(new String(bytes,0,read));
		// 5. �ͷ���Դ
		inputStream.close();
		socket.close();
		serverSocket.close();
		}
```

### tcpС��ϰ

&emsp;&emsp;ʹ��TCPЭ�飬�ͻ��˿���һֱ�������ݸ�����ˣ� ����˿���һֱ
���տͻ��˷��͵����ݣ�����ͻ��˷���666�ͻ��˳��ͻ��˳���

```java
public class TCPTest {
	static final int port = 8080;
	static String str = "";
}

/** TCP�ͻ��� */
class TCPClient {
	String over = "666";
	
	public static void main(String[] args) {
		// ��������
		new TCPClient().send("activate.navicat.com");
	}
	
	/**
	 * ��������
	 *
	 * @param url ��������ip��
	 */
	public void send(String url) {
		try {
			Socket socket = new Socket(InetAddress.getByName(url), TCPTest.port);
			OutputStream outputStream = socket.getOutputStream();
			while (!over.equals(TCPTest.str)) {
				System.out.println("�����뷢�͵�����");
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
		try {
			ServerSocket serverSocket = new ServerSocket(TCPTest.port);
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			while (count!=10000) {
				int read = inputStream.read(bytes);
				System.out.println("���յ�������Ϊ:" + new String(bytes, 0, read));
				count++;
			}
			inputStream.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
```

&emsp;&emsp;�������������ж��ʵ��ʱ��������� :   
&emsp;&emsp;�޷�ʵ�ֶ�˵Ĵ��䡣  
&emsp;&emsp;�޸Ĵ��룺  
ʵ��ԭ��
![img.png](image/img_13.png)
&emsp;&emsp;����socket.getInputStream()������Ч�����������̵߳�ʱ��
������߳����û�д������ݾͻ�һֱ��������������һ���ͻ��˵����ݺ��������һ�����̣߳�ʹ���̳߳أ���  
&emsp;&emsp;����ֻ��Ҫ�޸Ľ��ն˵Ĵ��뼴�ɡ�

```java
while(count!=10000){
		new Thread(
		()->{
		try{
		InputStream inputStream=socket.getInputStream();
		while(count!=1000){
		int read=inputStream.read(bytes);
		System.out.println("���յ�������Ϊ:"+new String(bytes,0,read));
		count++;
		}
		inputStream.close();
		}catch(IOException e){
		throw new RuntimeException(e);
		}
		})
		.start();
		}
```

### TCPʵ�ַ������֤�û�������

![img_1.png](image/img_14.png)

```java
public class TCPTest02 {
	static final int port = 8080;
}

/** TCP�ͻ��� */
class TCPClient02 {
	
	public static void main(String[] args) {
		// ��������
		new TCPClient02().send("activate.navicat.com");
	}
	
	/**
	 * ��������
	 *
	 * @param url ��������ip��
	 */
	public void send(String url) {
		while (true) {
			OutputStream outputStream = null;
			InputStream inputStream = null;
			Socket socket = null;
			try {
				socket = new Socket(InetAddress.getByName(url), TCPTest02.port);
				outputStream = socket.getOutputStream();
				System.out.println("�����������˺�");
				Scanner scanner = new Scanner(System.in);
				String username = scanner.next();
				System.out.println("��������������");
				String password = scanner.next();
				String content = "username=" + username + "&password=" + password;
				outputStream.write(content.getBytes());
				byte[] bytes = new byte[1024];
				inputStream = socket.getInputStream();
				int read = inputStream.read(bytes);
				String result = new String(bytes, 0, read);
				if ("ok".equals(result)) {
					System.out.println("��¼�ɹ�");
				} else {
					System.out.println("��¼ʧ��");
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					assert outputStream!=null;
					outputStream.close();
					assert inputStream!=null;
					inputStream.close();
					socket.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}

/** ����� */
class TCPServer02 {
	
	public static void main(String[] args) {
		// ��������
		new TCPServer02().accept();
	}
	
	/** �������� */
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
									if (inputStream!=null) {
										inputStream.close();
									}
									if (outputStream!=null) {
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
				if (serverSocket!=null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
```

### ��дHTTP��������

&emsp;&emsp;ʹ����������ʾ�̬��Դ����������Ŀ�е�resources�д���һ��templatesĿ¼����Ŀ¼�´���login.html��info.html��  
&emsp;&emsp;HTTP��Ĭ�϶˿�Ϊ80������ʹ�����������ʱ���Բ���д�˿ںš�
![img.png](image/img_15.png)

```java
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
									if (inputStream!=null) {
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
				assert serverSocket!=null;
				serverSocket.close();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}
}
```
