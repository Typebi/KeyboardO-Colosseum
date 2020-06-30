package kboc;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class C_Main extends Thread {
	C_UI cui;
	static Socket s;
	int port=8192;
	static boolean amI1P, amI2P, runningFlag;
	C_Main(){
	}
	boolean enterServer() {
		try {
			s = new Socket(C.ip,port);
			request("@@999", C.nickName);
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String respond = br.readLine();
			String protocol = respond.substring(0,6);
			if(protocol.equals("##9991")) {
				return true;
			}else {
				s.close();
			}
		} catch (UnknownHostException e) { e.printStackTrace();
		} catch (IOException e) { e.printStackTrace(); }
		return false;
	}
	static void sendChat(String txt) {
		PrintWriter pw; 
		try {
			pw = new PrintWriter(s.getOutputStream(), true);
			pw.println(txt);
		} catch (IOException e) { e.printStackTrace(); }
	}
	static void request(String protocol, String from) throws IOException {
		PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
		pw.println(protocol+from);
	}
	public void run() {
		cui = new C_UI();
		recieve();
	}
	void recieve() {
		try {
			BufferedReader brListen = new BufferedReader(new InputStreamReader((s.getInputStream())));
			while(true) {
				String respond = brListen.readLine().trim();
				//System.out.println(respond); // 개발자 테스트용 row
				String protocol = respond.substring(0, 5);
				String content = respond.substring(5);
				switch(protocol) {
					case "##000" : updateChatRoom(content); break; //일반채팅
					case "##010" : runningFlag=true; if(amI1P==true||amI2P==true) new C_BlcokMaker(cui).start(); break; //게임시작
					case "##011" : if(amI1P==false&&amI2P==false) resetArena(); in1P(); break; //1P로 입장. 1P권한받기
					case "##012" : if(amI1P==false&&amI2P==false) resetArena(); in2P(); break; //2P로 입장. 2P권한받기
					case "##013" : cui.l_pastTime.setText(content); break; // 경과시간 셋팅
					case "##015" : cui.btnEnter1P.setVisible(false); break; // 1P 입장버튼 삭제
					case "##016" : cui.btnEnter2P.setVisible(false); break; // 2P 입장버튼 삭제
					case "##017" : cui.btnEnter1P.setVisible(true); break; // 1P 입장버튼 생성
					case "##018" : cui.btnEnter2P.setVisible(true); break; // 2P 입장버튼 생성
					case "##041" : setLoose1P(); cui.btnEnter1P.setVisible(true); resetArena(); break; // 1P 패배 신호
					case "##042" : setLoose2P(); cui.btnEnter2P.setVisible(true); resetArena(); break; // 2P 패배 신호
					case "##043" : setEscape1P(); cui.btnEnter1P.setVisible(true); resetArena(); break; // 1P 탈주 신호
					case "##044" : setEscape2P(); cui.btnEnter2P.setVisible(true); resetArena(); break; // 1P 탈주 신호
					case "##051" : reflectArena1P(content); break; //경기장 정보 반영
					case "##052" : reflectArena2P(content); break; //경기장 정보 반영
					case "##060" : cui.l_HallOfFame.setText(content); break; // 명예의전당 셋팅
					case "##061" : cui.l_profile1P.setText(content); break; //1P 프로필 셋팅
					case "##062" : cui.l_profile2P.setText(content); break; //2P 프로필 셋팅
				}
			}
		}catch (NullPointerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to connect to the server!"+"\n"+"Please try again.", "ERROR!", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Disconnected to the server. Exiting the program.", "ERROR!", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
	void updateChatRoom(String content){
		cui.chat.txtArea.append(content+"\n");
		cui.chat.sp_txtAreaScroll.getVerticalScrollBar().setValue(cui.chat.sp_txtAreaScroll.getVerticalScrollBar().getMaximum());
		cui.chat.revalidate();
		cui.chat.repaint();
	}
	void in1P() { //1P 권한 받기
		amI1P=true;
		cui.btnEnter2P.setEnabled(false);
		cui.p_main1P.setBorder((new LineBorder(new Color(220, 220, 30), 3, true)));
	}
	void in2P() { //2P 권한 받기
		amI2P=true;
		cui.btnEnter1P.setEnabled(false);
		cui.p_main2P.setBorder((new LineBorder(new Color(220, 220, 30), 3, true)));
	}
	void resetArena() { //전체 경기장 빈칸으로 리셋하기
		for(int i=0; i<C_UI.BOARD_HEIGHT; i++)
			for(int j=0; j<C_UI.BOARD_WIDTH; j++)
				cui.ll_board1P[i][j].setIcon((cui.icons.get("00")));
		for(int i=0; i<C_UI.BOARD_HEIGHT; i++)
			for(int j=0; j<C_UI.BOARD_WIDTH; j++)
				cui.ll_board2P[i][j].setIcon((cui.icons.get("00")));
	}
	void setLoose1P() {
		runningFlag=false;
		cui.p_arena1P.setVisible(false);
		cui.p_main1P.add(cui.l_loose1P);
		cui.l_loose1P.setVisible(true);
		try {
			cui.l_loose1P.setText("<html><div style='text-align: center;'>DEFEAT !!<br>Executed<br>3 sec later</div></html>");
			Thread.sleep(1000);
			cui.l_loose1P.setText("<html><div style='text-align: center;'>DEFEAT !!<br>Executed<br>2 sec later</div></html>");
			Thread.sleep(1000);
			cui.l_loose1P.setText("<html><div style='text-align: center;'>DEFEAT !!<br>Executed<br>1 sec later</div></html>");
			Thread.sleep(1000);
		} catch (InterruptedException e) { e.printStackTrace(); }
		if(amI1P)
			System.exit(0);
		cui.l_loose1P.setVisible(false);
		cui.p_main1P.remove(cui.l_loose1P);
		cui.p_arena1P.setVisible(true);
		resetArena();
		new C_BlcokMaker(cui).sendCurrentArena();
	}
	void setLoose2P() {
		runningFlag=false;
		cui.p_arena2P.setVisible(false);
		cui.p_main2P.add(cui.l_loose2P);
		cui.l_loose2P.setVisible(true);
		try {
			cui.l_loose2P.setText("<html><div style='text-align: center;'>DEFEAT !!<br>Executed<br>3 sec later</div></html>");
			Thread.sleep(1000);
			cui.l_loose2P.setText("<html><div style='text-align: center;'>DEFEAT !!<br>Executed<br>2 sec later</div></html>");
			Thread.sleep(1000);
			cui.l_loose2P.setText("<html><div style='text-align: center;'>DEFEAT !!<br>Executed<br>1 sec later</div></html>");
			Thread.sleep(1000);
		} catch (InterruptedException e) { e.printStackTrace(); }
		if(amI2P)
			System.exit(0);
		cui.l_loose2P.setVisible(false);
		cui.p_main2P.remove(cui.l_loose2P);
		cui.p_arena2P.setVisible(true);
		resetArena();
		new C_BlcokMaker(cui).sendCurrentArena();
	}
	void setEscape1P() {
		runningFlag=false;
		cui.p_arena1P.setVisible(false);
		cui.p_main1P.add(cui.l_escape1P);
		cui.l_escape1P.setVisible(true);
		try {
			cui.l_escape1P.setText("<html><div style='text-align: center;'>Desertion !!<br>Reset<br>3 sec later</div></html>");
			Thread.sleep(1000);
			cui.l_escape1P.setText("<html><div style='text-align: center;'>Desertion !!<br>Reset<br>2 sec later</div></html>");
			Thread.sleep(1000);
			cui.l_escape1P.setText("<html><div style='text-align: center;'>Desertion !!<br>Reset<br>1 sec later</div></html>");
			Thread.sleep(1000);
		} catch (InterruptedException e) { e.printStackTrace(); }
		cui.l_escape1P.setVisible(false);
		cui.p_main1P.remove(cui.l_escape1P);
		cui.p_arena1P.setVisible(true);
	}
	void setEscape2P() {
		runningFlag=false;
		cui.p_arena2P.setVisible(false);
		cui.p_main2P.add(cui.l_escape2P);
		cui.l_escape2P.setVisible(true);
		try {
			cui.l_escape2P.setText("<html><div style='text-align: center;'>Desertion !!<br>Reset<br>3 sec later</div></html>");
			Thread.sleep(1000);
			cui.l_escape2P.setText("<html><div style='text-align: center;'>Desertion !!<br>Reset<br>2 sec later</div></html>");
			Thread.sleep(1000);
			cui.l_escape2P.setText("<html><div style='text-align: center;'>Desertion !!<br>Reset<br>1 sec later</div></html>");
			Thread.sleep(1000);
		} catch (InterruptedException e) { e.printStackTrace(); }
		cui.l_escape2P.setVisible(false);
		cui.p_main2P.remove(cui.l_escape2P);
		cui.p_arena2P.setVisible(true);
	}
	void reflectArena1P(String info) {
		int i = Integer.parseInt(info.substring(0, 1));
		int j = Integer.parseInt(info.substring(1, 2));
		String imgCode = info.substring(2,4);
		cui.ll_board1P[i][j].setIcon(cui.icons.get(imgCode));
	}
	void reflectArena2P(String info) {
		int i = Integer.parseInt(info.substring(0, 1));
		int j = Integer.parseInt(info.substring(1, 2));
		String imgCode = info.substring(2,4);
		cui.ll_board2P[i][j].setIcon(cui.icons.get(imgCode));
	}
}
