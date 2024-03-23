package BT2;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame {
	static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream dis;
	static DataOutputStream dos;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextArea txtChat;
	private static JButton btnSend;
	private static JTextArea txtMsg;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		String msg = "";
		try {
			serverSocket = new ServerSocket(4444);
			socket = serverSocket.accept(); // chap nhan ket noi tu client
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			while (!msg.equals("time")) {
				msg = dis.readUTF();
				txtChat.append("\nClient say: " + msg.trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Create the frame.
	 */
	public Server() {
		
		ActionListener ac = new Controller(this);
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtChat = new JTextArea();
		txtChat.setEditable(false);
		txtChat.setBounds(10, 0, 414, 179);
		contentPane.add(txtChat);

		btnSend = new JButton("Send");
		btnSend.addActionListener(ac);
		this.getRootPane().setDefaultButton(btnSend);
		btnSend.setBounds(335, 190, 89, 60);
		contentPane.add(btnSend);

		txtMsg = new JTextArea();
		txtMsg.setBounds(10, 190, 315, 60);
		contentPane.add(txtMsg);
	}

	public void send() {
		try {
			SimpleDateFormat form = new SimpleDateFormat("hh:mm:ss");
			Date date = new Date();
			String date_ngay = form.format(date);
			dos.writeUTF(date_ngay);
			txtChat.append("\nServer Say: " + date_ngay);
			this.txtMsg.setText("");
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
