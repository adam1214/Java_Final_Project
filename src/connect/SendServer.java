package connect;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SendServer extends java.lang.Thread {
	private boolean OutServer = false;
	private ServerSocket server;
	Socket socket;
	private final int ServerPort = 4000;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	public Data clientData=new Data();  //new Data is for test unnecessary in real case
	public Data myData=new Data();

	public SendServer() {
		try {
			server = new ServerSocket(ServerPort);

		} catch (java.io.IOException e) {
			System.out.println("Socket啟動有問題 !");
			System.out.println("IOException :" + e.toString());
		}
	}
	public SendServer(Data myData,Data clientData) {
		this();
		this.myData=myData;
		this.clientData=clientData;
	}

	public void run() {

		System.out.println("伺服器已啟動 !");
		while (!OutServer) {

			try {
				if(socket==null) {
					synchronized (server) {
						
						socket = server.accept();  //wait for connection

					}
					//System.out.println("取得連線 : InetAddress = " + socket.getInetAddress());
					socket.setSoTimeout(15000);
				}
				
				
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				
				oos.writeObject(myData);
				clientData=(Data) ois.readObject();
				
				oos.close();
				ois.close();
				socket.close();

			} catch (java.io.IOException e) {
				//System.out.println("Socket連線有問題 !");
				socket=null;
				//System.out.println("IOException :" + e.toString());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} 
		}
		
	}

	public Data getClientData() {
		return clientData;
	}
	public void setClientData(Data clientData) {
		this.clientData = clientData;
	}
	public Data getMyData() {
		return myData;
	}
	public void setMyData(Data myData) {
		this.myData = myData;
	}
}
