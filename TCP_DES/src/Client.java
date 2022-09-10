import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static javax.swing.JOptionPane.showMessageDialog;

import java.net.*;
import java.security.PublicKey;
import java.io.*;
import java.math.BigInteger;
import java.util.*;



public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputMessage;
	private JTextField txtKey;
	private JTextField txtEncryptMessage;
	private JTextField txtOutputMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//DES_Algorithm_Encryption-------------------------------------------------------------------------------------------
	
	
	
	//DONE----------------------------------------------------------------------------------------------------------------

	/**
	 * Create the frame.
	 */
	public Client() {
		setTitle("Client Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 387);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("WELCOME");
		lblTitle.setForeground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setBounds(305, 10, 118, 39);
		contentPane.add(lblTitle);
		
		JLabel lblSendMessage = new JLabel("Nh\u1EADp tin nh\u1EAFn:");
		lblSendMessage.setForeground(new Color(255, 255, 255));
		lblSendMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSendMessage.setBounds(43, 79, 109, 29);
		contentPane.add(lblSendMessage);
		
		txtInputMessage = new JTextField();
		txtInputMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtInputMessage.setBounds(155, 79, 200, 29);
		contentPane.add(txtInputMessage);
		txtInputMessage.setColumns(10);
		
		JLabel lblNhpKha = new JLabel("Nh\u1EADp kh\u00F3a:");
		lblNhpKha.setForeground(new Color(255, 255, 255));
		lblNhpKha.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNhpKha.setBounds(65, 129, 109, 29);
		contentPane.add(lblNhpKha);
		
		txtKey = new JTextField();
		txtKey.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtKey.setColumns(10);
		txtKey.setBounds(155, 129, 200, 29);
		contentPane.add(txtKey);
		
		JLabel lblEncryptMessage = new JLabel("M\u00E3 h\u00F3a tin nh\u1EAFn");
		lblEncryptMessage.setForeground(new Color(255, 255, 255));
		lblEncryptMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEncryptMessage.setBounds(410, 79, 140, 29);
		contentPane.add(lblEncryptMessage);
		
		txtEncryptMessage = new JTextField();
		txtEncryptMessage.setBackground(new Color(240, 255, 240));
		txtEncryptMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEncryptMessage.setEditable(false);
		txtEncryptMessage.setColumns(10);
		txtEncryptMessage.setBounds(410, 129, 262, 29);
		contentPane.add(txtEncryptMessage);
		
		JButton btnEncrypt = new JButton("M\u00E3 h\u00F3a");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DES Algorithm_encrypt
				DES des = new DES();
				if (txtKey.getText().length() > 8) {
					showMessageDialog(null, "Vui lòng nhập khóa có độ dài <= 8 ký tự");
				}
				else {
					String encryptMessage = des.encryption(txtInputMessage.getText(), txtKey.getText());
					//txtEncryptMessage.setText(des.encodeBase64(encryptMessage));
					txtEncryptMessage.setText(des.binToText(encryptMessage));
					System.out.println("Mã hóa(BIN): " + encryptMessage);

				}
				
				
			}
		});
		
		btnEncrypt.setForeground(new Color(70, 130, 180));
		btnEncrypt.setBackground(new Color(211, 211, 211));
		btnEncrypt.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEncrypt.setBounds(550, 75, 96, 36);
		contentPane.add(btnEncrypt);
		
		JButton btnSend = new JButton("G\u1EECI");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtEncryptMessage.getText().length() == 0) {
					showMessageDialog(null, "Vui lòng Mã hóa tin nhắn trước khi gửi đi !!!");
				}
				else {
//					//Connect to server use TCP	
					if (txtKey.getText().length() > 8) {
						showMessageDialog(null, "Vui lòng nhập khóa có độ dài <= 8 ký tự");
					}
					else {
						try {
							Socket client = new Socket("127.0.0.1", 7777);
							DataInputStream is = new DataInputStream(client.getInputStream());
							DataOutputStream os = new DataOutputStream(client.getOutputStream());
							DES des = new DES();
							
							//Send encrypt message and key to Server
							String message = des.encryption(txtInputMessage.getText(), txtKey.getText()) + "@" + txtKey.getText();
							os.writeUTF(message);
							
							//Receive message from Server
							String messageFromServer = is.readUTF();
							txtOutputMessage.setText(messageFromServer);							
							
							client.close();
						}
						catch (IOException ioe) {
							System.out.println(ioe);
						}
					}					
				}
			}
		});
		btnSend.setForeground(new Color(70, 130, 180));
		btnSend.setBackground(new Color(211, 211, 211));
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSend.setBounds(305, 186, 118, 50);
		contentPane.add(btnSend);
		
		txtOutputMessage = new JTextField();
		txtOutputMessage.setBackground(new Color(255, 255, 224));
		txtOutputMessage.setHorizontalAlignment(SwingConstants.CENTER);
		txtOutputMessage.setText("Tin nh\u1EAFn t\u1EEB server...");
		txtOutputMessage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtOutputMessage.setEditable(false);
		txtOutputMessage.setColumns(10);
		txtOutputMessage.setBounds(99, 263, 511, 64);
		contentPane.add(txtOutputMessage);
	}
}
