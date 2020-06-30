package kboc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;

public class S_UIHandler extends S implements ActionListener{
	static ServerSocket ss;
	Socket s;
	S_UI sui;
	S_UIHandler(S_UI sui){
		this.sui = sui;
	}
	S_UIHandler(Socket s){
		this.s = s;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		//서버시작
		if((Object)sui.btn_ServerStart==obj) {
			new Thread() {
				public void run() {
					try {
						ss = new ServerSocket(8192);
						sui.label_ServerStatus.setText("[ Server Running ]");
						S_UI.textArea.append("[ Server started ]" + "\n");
						sui.btn_ServerStart.setEnabled(false);
						sui.btn_ServerClose.setEnabled(true);
						while(true){
							s = ss.accept();
							new S(s).start();
						}
					} catch(SocketException se){// se.printStackTrace();
					} catch (IOException e1) {// e1.printStackTrace();
					}
				}
			}.start();
		//서버종료
		}else if((Object)sui.btn_ServerClose==obj) {
			int select = JOptionPane.showConfirmDialog(null, "You sure to close the server?", "KeyboardO Colosseum Server", JOptionPane.OK_CANCEL_OPTION);
			try{
				if(select == JOptionPane.YES_OPTION){
					for(String nick : visitors.keySet())
						visitors.get(nick).close();
					ss.close();
					sui.label_ServerStatus.setText("[ Server Closed ]");
					S_UI.textArea.append("[ Server Closed ]" + "\n");
					sui.btn_ServerStart.setEnabled(true);
					sui.btn_ServerClose.setEnabled(false);
				}
			}catch(SocketException se){// se.printStackTrace();
			}catch(IOException ioe){// ioe.printStackTrace();
			}catch(NullPointerException npe){// npe.printStackTrace();
			}
		//채팅입력
		}else if((Object)sui.textField==obj) {
			String msg = sui.textField.getText();
			S.transmitChat(msg+"$"+S.admin);
			sui.textField.setText("");
			sui.textField.requestFocus();
		}

	}
}
 