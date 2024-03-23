package BT2;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame {
	
	
	static DataInputStream dis;
	static DataOutputStream dos;
	static Socket socket;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextArea txtChat;
	private static JTextArea txtMsg;
	private static JButton btnSend;

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
		String msg = "";
		try {
			socket = new Socket("127.0.0.1", 4444);
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			while (msg.equals("End")) {
				msg = dis.readUTF();
				txtChat.append("\nServer say: " + msg.trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		
		ActionListener ac = new Controllerr(this);
		setTitle("Client");
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

		txtMsg = new JTextArea();
		txtMsg.setBounds(10, 190, 315, 60);
		contentPane.add(txtMsg);

		btnSend = new JButton("Send");
		btnSend.addActionListener(ac);
		this.getRootPane().setDefaultButton(btnSend);
		btnSend.setBounds(335, 190, 89, 60);
		contentPane.add(btnSend);
	}

	public void send() {
		try {
			dos.writeUTF(txtMsg.getText().trim());
			txtChat.append("\nClient Say: " + txtMsg.getText().trim());
			this.txtMsg.setText("");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
