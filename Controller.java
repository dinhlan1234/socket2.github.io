package BT2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
	Server server;

	public Controller(Server server) {
		super();
		this.server = server;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if (src.equals("Send")) {
			this.server.send();
		}

	}

}
