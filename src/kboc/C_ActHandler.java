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
			if((Object)cui.btn_ChatOn==obj)	// 채팅 버튼 클릭
				cui.chat.setVisible(true);
			if((Object)cui.btnEnter1P==obj&&C_Main.amI1P==false&&C_Main.amI2P==false) // 1P 입장버튼 클릭
				C_Main.request("@@015", "$"+C.nickName); //1P입장 신호 송신
			if((Object)cui.btnEnter2P==obj&&C_Main.amI1P==false&&C_Main.amI2P==false) // 2P 입장버튼 클릭
				C_Main.request("@@016", "$"+C.nickName); //2P입장 신호 송신
		}catch(IOException ioe) {ioe.printStackTrace();}
	}
}