## Socket网络编程

相关知识网络编程中使用最多的就是Socket 

### 网络的基础知识

* 两台计算机想要进行通信，就要满足一些必然的条件   
1.两台主机需要一个唯一的标识，用来表示各自的身份和各自的位置，IP地址   
2.需要共同的语言进行通话交流，协议   
3.每台主机还都需要相应的端口号，一台主机上可以运行多个应用程序，每个应用程序之间需要使用端口号来区分辨别通信
* TCP / IP是目前世界上应用最为广泛的协议  
    * 是以TCP和IP为基础的不同层次上多个协议的集合，也可称为TCP / IP协议族或者TCP / IP协议栈，两个主机要进行通信都要遵循这两个基本的协议  
    * TCP：传输控制协议传输控制协议  
    * IP：Ineternet协议互联网协议
    * 我们在实际应用当中会将网络分层，即物理，数据链路，网络，传输，应用五层模型。基础网络知识部分另讲。
* 端口号，实际上就是用来区 不同的应用程序，用来标示不同的应用程序，端口号的范围为0〜65535，其中0〜1023为系统所保留。
    * 倘若将IP地址看作手机号的话，那么端口号就是每一个分机号。由IP地址和端口号组成了所谓的插座，插座是网络上运行的程序之间双向通信链路的终结点，是TCP和UDP的基础。
    * 常用的协议所使用的端口号：HTTP：80，HTTPS：443，FTP：21，telnet：23，可自行搜索常见[端口号](http://blog.csdn.net/u014421556/article/details/51671353)
 * Java对于网络提供了良好的支持，针对网络通信的不同层次，Java提供了不同的API，其功能有四大类： 
    * InetAddress：用于标识网络上的硬件资源，主要用来标识IP地址的相关信息   * URL：统一资源定位符，通过URL可以直接读取或写入网络上的数据。   
    * Socket：使用TCP协议实现网络通信的Socket相关类。基于TCP / IP协议的网络通信   
    * Datagram:使用UDP协议，将数据保存在数据报中，通过网络进行通信。





### InetAddress   
* 用于标识网络上的硬件资源，主要用来标识IP地址的相关信息    

可自行查看InetAddress类的JavaAPI文档，会发现并没有构造方法，即我们不能生成关于此类的实例，所以我们只能使用他的一些静态方法直接使用。



```java
    import java.net.InetAddress;
    import java.util.Arrays;
    import java.net.UnknownHostException;

    /*
     * InetAddress类 
     */
    public class InetAddress_01{
        public static void main(String[] args)throws UnknownHostException{
                //返回本地主机。
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("计算机名:"+address.getHostName());
                //获取此IP地址的主机名。
            System.out.println("IP地址:"+address.getHostAddress());
                //直接获取IP地址的字节数组形式
            byte[] bytes = address.getAddress();
            System.out.println("字节数组形式的IP："+Arrays.toString(bytes));
                //直接输出本机的InetAddress的对象
            System.out.println(address);

            //根据主机名获取InetAddress实例
            //getByName(String host)在给定主机名的情况下确定主机的IP地址。
            InetAddress address2 = InetAddress.getByName("麦克白");
            System.out.println("计算机名："+address2.getHostName());

        }
    }
```
### URL
* 统一资源定位符，表示Internet上的某一个资源的地址。
* URL由两部分组成：协议名称和资源名称，中间:隔开.例如`https://www.baidu.com`
* 在Java中提供了一个java.net.URL类来表示URL。
    * 请查看API文档，根据构造函数来创建URL实例。

```java
    import java.net.MalformedURLException;
    import java.net.URL;

    /*
    * URL常用方法
    */
    public class InetAddress_02 {
        public static void main(String[] args) {
        //创建一个URL实例
        try {
            URL weibo = new URL("https://weibo.com/");
            URL url = new URL(weibo,"index.html");
            //查看当前URL协议的信息
            System.out.println("协议："+url.getProtocol());
            System.out.println("主机："+url.getHost());
            /*端口号会返回-1，是因为我们在指定这个URL端口号的时候并没有指定端口号，
             * 则会使用这个协议默认的端口号，https默认是443，
             * 由于使用时未设置，getPort方法的返回值是-1
             */
            System.out.println("端口号："+url.getPort());
            System.out.println("文件路径:"+url.getPath());
            System.out.println("相对路径："+url.getRef());
            System.out.println("查询字符串："+url.getQuery());   
        } catch (MalformedURLException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }  
    }
``` 

> 协议：https   
> 主机：weibo.com   
> 端口号：-1   
> 文件路径：/index.html   
> 相对路径：null   
> 查询字符串：null

### 使用URL读取网页内容的方法

* 通过URL对象的openStream()方法可以得到指定资源的输入流
* 通过流来读取、访问网络上的数据

### Socket
1.TCP协议是面向连接、可靠的、有序的、以字节流的方式发送数据 
 
* 基于TCP协议实现网络通信的类
* 客户端的Socket类
* 服务器端的ServerSocket类 
				
2.Socket通信模型  

* 两台主机需要通信就需要一个作为服务端Server，另一个为客户端Client
* 首先在服务器端建立一个Server Socket，绑定相应的端口，并在指定端口建立服务端监听，等待客户端的连接。
* 当我们在客户端建立Socket并且向服务器端发送请求，此时，服务器收到请求并且接收客户端的请求信息，一旦接收请求后会创建一个连接socket用来与客户端的socket进行通信。
* 通信方式，通过相应的输入流和输出流：InputSream和OutputSream进行数据的交换、发送、接收以及数据响应等。
* 最后在双方通信完毕以后，我们需要在两端的socket进行通信的断开。

3.Socket通信实现步骤

* 创建ServerSocket和Socket，网络通信的基础
* 打开连接到Socket的输入/输出流，进行数据的通信
* 按照协议对Socket进行读/写
* 关闭输入输出流、关闭Socket

4.查看API文档

* ServerSocket
   * 常用的构造方法：
   `ServerSocket(int port)`在创建ServerSocket的同时，绑定到指定的端口侦听客户端的连接
   * 常用方法:`accept()`侦听并接受到此套接字的连接，当我们调用到accept()方法的时候会阻塞当前的监听等待客户端的连接，一旦客户端连接成功以后，会创建一个socket实例用来与当前的客户端进行通信。
   * 使用`close()`方法关闭当前的套接字
   * 调用`getInetAddress()`返回一个此服务器套接字的本地地址
   * 使用`getLocalPort()`方法，返回在当前套接字上监听的端口号
* Socket
   * 常用的构造方法：
   常使用`Socket(InetAddress address, int port)`构造方法,根据InetAddress实例和端口号进行创建。
   使用`Socket(String host,int port)`创建一个流套接字并将其连接到指定主机上的指定端口号。
   * 当我们建立连接时，我们需要在两端建立通信，这时就需要getInputStream()方法获取当前套接字的输入流
   * `getOutputStream()`方法获取当前套接字的输出流 
   * `shutdownInput()/shutdownOutput`用来关闭当前Socket的输入流和输出流
* 服务器端实现Socket
 * 步骤 ：
     * 创建`ServerSocket`对象，绑定监听端口
     * 通过`accept()`方法监听客户端请求
     * 连接建立后，通过输入流读取客户端发送的请求信息
     * 同时通过输出流用来向客户端发送响应
     * 最后关闭相关资源：输入输出流、关闭Socket关闭
* 客户端实现Socket
     * 创建Socket对象，指明需要连接的服务器的地址和端口号
     * 连接建立后，通过输出流向服务器端发送请求信息
     * 通过输入流获取服务器响应的信息
     * 关闭相关资源
* 客户端实现示例如下：

```java 
    import java.io.IOException;
    import java.io.OutputStream;
    import java.io.PrintWriter;
    import java.net.Socket;
    import java.net.UnknownHostException;
    /*
     * 客户端，发送数据
     */
    public class Client {
        public static void main(String[] args) {
            try {
                // 1.创建客户端Socket，指定服务器地址和端口
                Socket socket = new Socket("localhost",8879);
                //2.获取输出流，向服务器放送信息
                OutputStream oStream = socket.getOutputStream();//字节输出流
                PrintWriter pWriter = new PrintWriter(oStream);//将输出流包装为打印流
                pWriter.write("用户名:麦克白，密码:maccat");
                pWriter.flush();
                socket.shutdownOutput();
                //3.关闭资源
                pWriter.close();
                oStream.close();
                socket.close(); 
            } catch (UnknownHostException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }
```

 * 服务端实现实例如下：
 
```java 
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.net.ServerSocket;
    import java.net.Socket;
    /*
     * 基于TCP协议的Socket通信，实现用户登陆
     * 服务器端实现
     */
    public class Server {
    public static void main(String[] args) {
        try {
            // 1.创建一个服务器端 Socket，即ServerSocket,指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8879);
            // 2.调用accept()方法开始监听，等待客户端的连接
            System.out.println("****服务端即将启动，等待客户端建立连接***");
            Socket socket = serverSocket.accept();
            //3.获取一个输入流，用来读取客户端信息，字符流与字节流参见IO
            InputStream is = socket.getInputStream();//字节输入流
            InputStreamReader isr = new InputStreamReader(is);//将字节流包装为字符流 
            BufferedReader bReader = new BufferedReader(isr);//为输入流添加缓冲
            String info = null;
            while ((info=bReader.readLine())!=null) {//循环读取客户端的信息
            System.out.println("这里是服务器，接收到客户端提交的信息为："+info);
            }
        socket.shutdownInput();
        //4.关闭资源
        bReader.close();
        isr.close();
        is.close();
        socket.close();
        serverSocket.close();     
        } catch (IOException e) {
        // TODO 自动生成的 catch 块
        e.printStackTrace();
        }
    }
}
```



* 以上部分当服务器端接收完消息没有返回响应的消息，所以为了完善在服务器端添加输出流响应消息，并在客户端添加输入流接收。  

服务器完善部分如下：

```java
//4.获取输出流，对客户端请求进行响应
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pWriter = new PrintWriter(outputStream);
        pWriter.write("Hi,Welcome!!!!");
        pWriter.flush();//刷新缓存输出
        //5.关闭资源
        pWriter.close();
        outputStream.close();

```

   
客户端完善部分如下：

```java 
//3.获取输入流，并读取服务器端的响应信息
InputStream iStream = socket.getInputStream();
BufferedReader bReader =new BufferedReader(new InputStreamReader(iStream));
String info = null;
while ((info=bReader.readLine())!=null) {
    System.out.println("这里是客户端，服务器返回的消息是："+info);
}
//4.关闭资源
bReader.close();
iStream.close();
```

* 多线程实现多客户端的通信

以上程序只是本机的一个客户端与服务器进行通信，而实际应用当中这是服务器实现一个永久的程序，响应多个客户端的请求并提供服务。对于此种场景可以使用多线程来实现响应多客户端的机制。
    
* 基本步骤:
    * 服务器端创建ServerSocket,循环调用accrpt()等待客户端连接
    * 客户端创建一个socket并请求和服务器端连接
    * 服务器端接受客户端请求，创建socket与该客户建立专线连接
    * 建立连接的两个socket在一个单独的线程上对话通信
    * 服务器端继续等待新的连接 
    
实现实例代码如下：

```java
    public class Server {
    public static void main(String[] args) {  
        try {
            // 1.创建一个服务器端 Socket，即ServerSocket,指定绑定的端口，并监听此端口
            ServerSocket serverSocket = new ServerSocket(8879);
            Socket  socket = null;
            //记录连接客户端的数量
            int count = 0;
            System.out.println("****服务端即将启动，等待客户端建立连接***");
            //循环监听等待客户端的连接
            while (true) {
                // 2.调用accept()方法开始监听，等待客户端的连接
                socket = serverSocket.accept();
                //创建一个新线程
                SeverThread severThread = new SeverThread(socket);
                //启动线程
                severThread.start();
                count++;
                System.out.println("客户端的数量为："+count);
                InetAddress address = socket.getInetAddress();
                System.out.println("当前客户端的IP："+address.getHostAddress());
            }       
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }    
    }
}

    /*
    *新建一个继承与Thread的服务器类
    */   
        public class SeverThread extends Thread {
        //和本线程相关的socket
        Socket socket = null;
        public SeverThread(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {

            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader bReader = null;
            OutputStream outputStream = null;
            PrintWriter pWriter = null;
            try {
                //获取一个输入流，用来读取客户端信息，字符流与字节流参见IO
                is = socket.getInputStream();
                isr = new InputStreamReader(is);
                bReader = new BufferedReader(isr);
                String info = null;
                while ((info=bReader.readLine())!=null) {
                //循环读取客户端的信息
                    System.out.println("这里是服务器，接收到客户端提交的信息为："+info);
                }
                socket.shutdownInput(); 
                //获取输出流，对客户端请求进行响应
                outputStream = socket.getOutputStream();
                pWriter = new PrintWriter(outputStream);
                pWriter.write("Hi,Welcome!!!!");
                pWriter.flush();//刷新缓存输出
            } catch (IOException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }finally {
                //关闭资源
                try {
                    if (pWriter!=null) {
                        pWriter.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (bReader != null) {
                        bReader.close();
                    }
                    if (isr != null) {
                        isr.close();
                    }
                    if (is!= null) {
                        is.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }   
            }
        }
```




###  UDP编程
 * UDP协议（用户数据报协议）是无连接、不可靠、无序的，但其速度先对较快
 * UDP协议作为数据传输的载体，即当我们使用UDP进行数据传输时，首先需要将等待传输的数据定义成数据报(Datagram)，在数据报中指明数据所要达到的Socket(主机地址和端口号)，然后再将数据报发送出去。
 * DatagramPacket:数据报类,用来表示UDP通信时的控制单元
   * `DatagramPacket(byte[] buf, int lenght, InetAdress, int port)` 包含四个参数:数据长度，目标地址和响应的端口号，构建数据报用来将指定长度的字节的包发送到指定主机上的指定端口号。
   * `DatagramPacket(byte[] buf, int length)`包含两个参数，用来接收长度为length的数据包。
 * DatagramSocket:进行端对端通信的类，用来发送和接收数据报包的套接字，实现基于UDP的socket通信
   * `DatagramSocket()`构造数据报套接字并将其绑定在本地主机上任何可用的端口上，
   * `DatagramSocket(int port,InetAddress laddr)`创建数据报套接字，将其绑定在指定的本地地址,可指定端口号和InetAddress实例
   * 提供的方法 
   * `getInetAdress()`返回套接字的连接地址
   * `getPort()`返回此套接字的端口
   * `receive(DatagramPacket p)`从此套接字接收数据报包 
   * `send(DatagramPacket p)`从此套接字发送数据报包
 * 通过UDP Socket实现用户登录的案例
 * 服务器端实现步骤 
   * 在服务器端创建一个DatagramSocket,指定端口号
   * 创建DatagramPaket,接收客户端发送的数据
   * 接收客户端发送的数据信息
   * 读取数据
 * 客户端实现步骤
   * 定义发送的相应的信息,比如发送的服务器的地址端口号以及发送的内容
   * 创建DatagramPacket数据报，包含将要发送的信息
   * 创建DatagranSocket对象，用来实现数据的发送
   * 发送数据,通过之前创建的DatagramSocket
 * 实现如下：  

```java
/*
 * 服务器端，实现基于UDP的用户登陆
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
	public static void main(String[] args) throws IOException {
	    DatagramSocket socke = new DatagramSocket(7779);//1.创建服务器端DatagramSocket,指定相应的端口号 
	    byte[] data = new byte[1024];//创建字节数组，指定接收的数据包的大小
	    DatagramPacket packet;
	    System.out.println("****服务器端已启动，等待客户端发送数据****");
	    int num = 0;
	    while (true) {	
		    packet = new DatagramPacket(data, data.length);//2. 创建数据报，用于接收客户端发送的数据
		    socke.receive(packet);//此方法在接收到数据报之前会保持阻塞
		    UDPSeverThread udpSeverThread = new UDPSeverThread(packet, socke, data);
		    udpSeverThread.start();
		    System.out.println("访问的数量："+(++num));
	    }
    }

}

/***************************************/

/*
 * 服务器端线程处理类
 */
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSeverThread extends Thread {
	String info =null;
	InetAddress address =null;
	DatagramSocket socke = null;
	int port = 0;
	public UDPSeverThread(DatagramPacket packet,DatagramSocket socket,byte[] data) {//读取数据	
		//初始化客户端的地址、端口号、数据
		socke = socket;
		info = new String(data, 0, packet.getLength());
		address = packet.getAddress();
		port = packet.getPort();
	}
	@Override
	 public void run() {
        /*
         * 向客户端响应数据
         */
         System.out.println("这里是服务器，接收到客户端的数据为："+info);
        byte[] data2 = "Welcome!!!!!!".getBytes();//创建数据报，包含响应的数据信息
        DatagramPacket datagramPacket = new DatagramPacket(data2, data2.length,address,port);
        try {//响应客户端
            socke.send(datagramPacket);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
}

/**************************************/

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPclient {
    public static void main(String[] args) throws IOException {
        /*
         * 向服务器端发送数据
         */
        InetAddress address = InetAddress.getByName("localhost");//定义服务器的地址、端口号。及数据
        int port = 7779;
        byte[] data = "用户名：Mac;密码：123456".getBytes();

        DatagramPacket packet = new DatagramPacket(data, data.length,address,port);//根据已定义的信息，创建数据报
        DatagramSocket socket = new DatagramSocket();//创建DatagramSocket对象，使用DatagramSocket向服务器端发送数据报
        socket.send(packet);
        /*
         * 接收服务器返回的响应
         *创建数据报，用于接收服务器端响应的数据
         *接收服务器端响应的数据
         */
        byte[] data2 = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(data2, data2.length,address,port);
        System.out.println("********客户端已启动*******");
        socket.receive(datagramPacket);
        String reply = new String(data2, 0, datagramPacket.getLength());//读取数据
        System.out.println("这里是客户端，服务器返回了响应："+reply);
        socket.close();//关闭资源
    }
}

```
### 总结 

这里学习了简略的Socket通信原理和基于TCP的Socket通信，还需要学习其他课程加深印象，进一步理解。在这里主要有以下几个问题需要注意： 
 * 关于多线程的部分，因为是死循环，就更加需要考虑需要设置优先级，若不设置优先级运行时速度就会变慢，可适当降低优先级
 
```java
/******TCP*******/
while (true) {
				socket = serverSocket.accept();// 2.调用accept()方法开始监听，等待客户端的连接
				SeverThread severThread = new SeverThread(socket);//创建一个新线程
				severThread.setPriority(4);//设置优先级
				severThread.start();//启动线程
				count++;
				System.out.println("客户端的数量为："+count);
				InetAddress address = socket.getInetAddress();
				System.out.println("当前客户端的IP："+address.getHostAddress());
			}	
/***UDP****/	
while (true) {	
		    packet = new DatagramPacket(data, data.length);//2. 创建数据报，用于接收客户端发送的数据
		    socke.receive(packet);//此方法在接收到数据报之前会保持阻塞
		    UDPSeverThread udpSeverThread = new UDPSeverThread(packet, socke, data);
            udpSeverThread.setPriority(4);//设置优先级
		    udpSeverThread.start();
		    System.out.println("访问的数量："+(++num));
	    }
```
 * 关于基于TCP的Socket的编程，服务器和客户端的通信需要响应的输入输出流来进行通信，对于同一个socket,如果关闭了输出流，这与该输出流关联的Socket也会被关闭，所以一般不用关闭流，直接关闭socket即可
 
 * 实际应用当作，输入输出的信息并非只有字符串，更多是以对象的形式进行传输，我们可以将用户名和密码和需要传输的数据封装成一个User对象，这才是面向对象的思想。我们可以使用在I/O中所学习的Object.InputStream和Object.  OutputStream来构建对象的传输。倘若想要进行文件的传递，又该如何？同样也是使用I/O中的方法通过输入输出流读取文件中的数据，new一个 `BufferedOutputStream(FileOutputStream(filename))` 并且发送到服务器端。
 * 当我们传送数据时的加密，查重，等等


 ----

 * 还需要进一步学习，加油！
 

