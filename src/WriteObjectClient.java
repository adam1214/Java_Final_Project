


import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class WriteObjectClient {
	public static void main(String args[]) {
		try {
			Socket s = new Socket("172.20.10.5", 2002);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
//			testobject to = new testobject(1, "object from client");
			ArrayList<String> a=new ArrayList();
			a.add("this is Client");
			a.add("how are you today");
			oos.writeObject(a);
			
			ArrayList<String> b=(ArrayList<String>) ois.readObject();
			if (b != null) {
				System.out.println(b.get(0));
				System.out.println(b.get(1));
			}
//			oos.writeObject(new String("another object from the client"));
			oos.close();
			os.close();
			s.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}