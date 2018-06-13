package connect;
import java.net.InetSocketAddress;

import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.ObjectOutputStream;

public class SendClient extends java.lang.Thread implements Serializable{
	private String address = "127.0.0.1";
	private int port = 4000;
	private static Data serverData=new Data();
	private Data myData=new Data();
	

	public SendClient() {
		
	}
	public SendClient(Data serverData,Data myData) {
		//this.serverData=serverData;
		this.myData=myData;
		//System.out.println("constructer: " + myData.getX());
	}
	public void run() {
		try {
			Socket client = new Socket();
			client = new Socket(address, 4000);

			System.out.println("Client 連線成功 準備開始傳資料 !");
			
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

			
			//System.out.println(myData.getX());
			out.writeObject(myData);
			serverData=(Data)ois.readObject();
			//System.out.println(serverData.getX());
			client.close();
			client = null;
			ois.close();

		} catch (java.io.IOException e) {
			System.out.println("Socket連線有問題 !");
			System.out.println("IOException :" + e.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Data getServerData() {
		return serverData;
	}
	public void setServerData(Data serverData) {
		this.serverData = serverData;
	}
	public Data getMyData() {
		return myData;
	}
	public void setMyData(Data myDataIn) {
		this.myData.setX(myDataIn.getX());
//		System.out.print("setMyData: ");
//		System.out.println(myData.getX());
	}
}