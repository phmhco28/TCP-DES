
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//Create Socket server
			ServerSocket serverSocket = new ServerSocket(7777);
			System.out.println("Server is listening.....");
			
			while (true) {
				Socket client = serverSocket.accept();
				System.out.println("Connect success !!!");
				DataInputStream is = new DataInputStream(client.getInputStream());
				DataOutputStream os = new DataOutputStream(client.getOutputStream());

				String message = is.readUTF();
				System.out.println("Data from Client: " + message);

				String[] listMessages = message.split("@",2);
				String encryptString = listMessages[0];
				String key = listMessages[1];
				
//				//DES Algorithm_Decrypt
				DES des = new DES();
				String decryptString = des.decryption(encryptString, key);
				System.out.println("Giải mã (Plain Text): " + des.decodeBase64(decryptString));
				System.out.println("Giải mã (Base64): " + decryptString);
				System.out.println("Giải mã (BIN): " + des.textToBin(decryptString));
				
//				//Upper plain text and send to Client
				decryptString = des.decodeBase64(decryptString);
				decryptString = decryptString.toUpperCase();
				os.writeUTF(decryptString);
			}
		}
		catch (IOException ioe) {
			System.out.println(ioe);
		}

	}

}
