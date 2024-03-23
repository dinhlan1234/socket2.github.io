package BT2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controllerr implements ActionListener {
	Client client;
	

	


	public Controllerr(Client client) {
		super();
		this.client = client;
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if(src.equals("Send")) {
			this.client.send();
		}
		
	}

}
