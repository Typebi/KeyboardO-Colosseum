package kboc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class C_ChatActionHandler implements ActionListener {
	C_Chat c;
	C_ChatActionHandler(C_Chat cc){
		c=cc;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if((Object)c.txtField==obj) {
			String msg = c.txtField.getText();
			C_Main.sendChat("@@000"+msg+"$"+C.nickName);
			c.txtField.setText("");
			c.txtField.requestFocus();
		}else if((Object)c.btn_chatSend==obj) {
			String msg = c.txtField.getText();
			C_Main.sendChat("@@000"+msg+"$"+C.nickName);
			c.txtField.setText("");
			c.txtField.requestFocus();
		}
	}

}
