package kboc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class C_ActHandler implements ActionListener {
	C_UI cui;
	C_ActHandler(C_UI c){
		cui=c;
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		try {
			if((Object)cui.btn_ChatOn==obj)	// ä�� ��ư Ŭ��
				cui.chat.setVisible(true);
			if((Object)cui.btnEnter1P==obj&&C_Main.amI1P==false&&C_Main.amI2P==false) // 1P �����ư Ŭ��
				C_Main.request("@@015", "$"+C.nickName); //1P���� ��ȣ �۽�
			if((Object)cui.btnEnter2P==obj&&C_Main.amI1P==false&&C_Main.amI2P==false) // 2P �����ư Ŭ��
				C_Main.request("@@016", "$"+C.nickName); //2P���� ��ȣ �۽�
		}catch(IOException ioe) {ioe.printStackTrace();}
	}
}