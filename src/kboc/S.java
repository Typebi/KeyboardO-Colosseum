package kboc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Vector;

public class S extends Thread {
	static Vector<String> nickNames = new Vector<String>();
	static Hashtable<String, Socket> visitors = new Hashtable<String, Socket>();
	static TreeMap<Integer, String> rankers = new TreeMap<Integer, String>();
	static String players[] = new String[2];
	Socket s;
	String nickName;
	static String admin = "Manager";
	static int winCount1P, winCount2P;
	String ranking="##060<< Hall of Fame >>����[1st]��_____����[2nd]��_____����[3rd]��_____����[4th]��_____����[5th]��_____";
	boolean isEscape1P=true, isEscape2P=true;
	S(){
	}
	S(Socket s){
		this.s = s;
	}
	public void run() {
		receive();
	}
	synchronized void receive() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while(true) {
				String request= br.readLine();
				request = request.trim();
				String protocol = request.substring(0, 5);
				String context = request.substring(5);
				switch(protocol) {
				case "@@000" : transmitChat(context); break;
				case "@@013" : broadcastOrder("##013"+context); break; // ����ð� ����
				case "@@015" : transmitOrder("##011", nickName, nickName); players[0]=nickName; broadcastOrder("##015"); //1P���Ѻο�, 1P���Ի�� ��ü����
								sendCurrentProfile(); // ������ �� �������� ���� 
								if(players[0]!=null&&players[1]!=null) broadcastOrder("##010"); break; // 1P, 2P ��� ���� �� ���ӽ���
				case "@@016" : transmitOrder("##012", nickName, nickName); players[1]=nickName; broadcastOrder("##016"); //2P���Ѻο�, 2P���Ի�� ��ü����
								sendCurrentProfile(); // ������ �� �������� ���� 
								if(players[0]!=null&&players[1]!=null) broadcastOrder("##010"); break; // 1P, 2P ��� ���� �� ���ӽ���
				case "@@041" : isEscape1P=false; players[0]=null; broadcastOrder("##041"); winCount1P=0; winCount2P++; break; //1P �й�
				case "@@042" : isEscape2P=false; players[1]=null; broadcastOrder("##042"); winCount2P=0; winCount1P++; break; //2P �й�
				case "@@051" : broadcastOrder("##051"+context); break; //1P ���� �� ����
				case "@@052" : broadcastOrder("##052"+context); break; //2P ���� �� ����
				case "@@999" : checkNick(context); break;
				}
			}
		}catch(SocketException s) {//Ż�� ���� ��
//			s.printStackTrace();//Ŭ�� X��ư ����� �߻�
			System.out.println("SocketException �߻�");
			if(nickName.equals(players[0])&&isEscape1P) { //1P�ΰ�?
				broadcastOrder("##043");
				players[0]=null;
				winCount1P=0;
				if(players[1]!=null)
					winCount2P++;
			}else if(nickName.equals(players[1])&&isEscape2P) { //2P�ΰ�?
				broadcastOrder("##044");
				players[1]=null;
				winCount2P=0;
				if(players[0]!=null)
					winCount1P++;
			}
			isEscape1P = true;
			isEscape2P = true;
		}catch (IOException e) { e.printStackTrace();
		}finally {
			visitors.remove(nickName);
			transmitNotice(nickName+" has left.");
			sendCurrentProfile();
			setRanking();
			sendRankerData();
		}
	}
	void transmitNotice(String txt) {
		PrintWriter pw = null;
		S_UI.textArea.append(txt+"\n");
		S_UI.scrollPane.getVerticalScrollBar().setValue(S_UI.scrollPane.getVerticalScrollBar().getMaximum());
		try {
			for(String nick : visitors.keySet()) {
				pw = new PrintWriter(visitors.get(nick).getOutputStream(), true);
				pw.println("##000"+txt);
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
	static void transmitChat(String txt) {
		PrintWriter pw = null;
		String msg = txt.substring(0, txt.indexOf("$"));
		String name = txt.substring(txt.indexOf("$")+1);
		S_UI.textArea.append(name +" : "+msg+"\n");
		S_UI.scrollPane.getVerticalScrollBar().setValue(S_UI.scrollPane.getVerticalScrollBar().getMaximum());
		try {
			for(String nick : visitors.keySet()) {
				pw = new PrintWriter(visitors.get(nick).getOutputStream(), true);
				pw.println("##000"+name +" : "+msg);
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
	void transmitOrder(String protocol, String from, String to) {
		try {
			PrintWriter pw = new PrintWriter(visitors.get(to).getOutputStream(), true);
			pw.println(protocol+from);
		} catch (IOException e) { e.printStackTrace(); }
	}
	void broadcastOrder(String protocol) {
		PrintWriter pw = null;
		try {
			for(String nick : visitors.keySet()) {
				pw = new PrintWriter(visitors.get(nick).getOutputStream(), true);
				pw.println(protocol);
			}
		} catch (IOException e) { e.printStackTrace(); }
	}
	void checkNick(String nickName) {
		try {
			if(visitors.size()!=0)
				for(String tempNick : visitors.keySet())
					if(tempNick.equals(nickName)) {
						new PrintWriter(s.getOutputStream(), true).println("##9990"); //�г��� �ߺ� ����
						return;
					}
			new PrintWriter(s.getOutputStream(), true).println("##9991"); //���ͻ���� 0�̰ų�, �г����ߺ� ����
			visitors.put(nickName, s);//�÷��̾� ���� ����
			this.nickName = nickName;
			transmitNotice(nickName+" has entered.");
			sendCurrentProfile();
			setEnterButton();
			sendRankerData();
		}catch(IOException ioe) { ioe.printStackTrace(); }
	}
	void setEnterButton() {
		if(players[0]!=null)
			broadcastOrder("##015");
		if(players[1]!=null)
			broadcastOrder("##016");
		if(players[0]==null)
			broadcastOrder("##017");
		if(players[1]==null)
			broadcastOrder("##018");
	}
	void sendCurrentProfile() {
		if(players[0]!=null) {
			switch(winCount1P) {
			case 0 : broadcastOrder("##061"+"< "+players[0]+" // Challenger >"); break;
			case 1 : broadcastOrder("##061"+"< "+players[0]+" // 1 Win >"); break;
			default : broadcastOrder("##061"+"< "+players[0]+" // "+winCount1P+" Wins IN A ROW !! >"); break;
			}
		}else {
			broadcastOrder("##061"+"< Waiting challenger >");
			broadcastOrder("##017"); //1P �����ư �����
		}
		if(players[1]!=null) {
			switch(winCount2P) {
			case 0 : broadcastOrder("##062"+"< "+players[1]+" // Challenger >"); break;
			case 1 : broadcastOrder("##062"+"< "+players[1]+" // 1 Win >"); break;
			default : broadcastOrder("##062"+"< "+players[1]+" // "+winCount2P+" Wins IN A ROW !! >"); break;
			}
		}else {
			broadcastOrder("##062"+"< Waiting challenger >");
			broadcastOrder("##018"); //2P �����ư �����
		}
	}
	void setRanking() {
		if(players[1]!=null) {
			for(int winNum : rankers.keySet()) //�÷��� �ȿ� �г������ִ��� ���� ã��,
				if(rankers.get(winNum)==players[1])
					rankers.remove(winNum); //�ִٸ� �����
			rankers.put(winCount2P, players[1]); //��ŷ �ݿ�
		}else if(players[0]!=null) {
			for(int winNum : rankers.keySet()) //�÷��� �ȿ� �г������ִ��� ���� ã��,
				if(rankers.get(winNum)==players[0])
					rankers.remove(winNum); //�ִٸ� �����
			rankers.put(winCount1P, players[0]); //��ŷ �ݿ�
		}
	}
	void sendRankerData() {
		String name[] = new String[5];
		int win[] = new int[5];
		int k=0;
		for(int i :rankers.descendingKeySet()) {
			name[k]= rankers.get(i);
			win[k]=i;
			k++;
			if(k>=4)
				break;
		}
		ranking="##060<< Hall of Fame >>����[1st]��_____����[2nd]��_____����[3rd]��_____����[4th]��_____����[5th]��_____";
		broadcastOrder(ranking);
		if(name[0]==null) return;
		ranking="##060<< Hall of Fame >>����[1st]��"+name[0]+" // "+win[0]+" [2nd]��_____����[3rd]��_____����[4th]��_____����[5th]��_____";
		broadcastOrder(ranking);
		if(name[1]==null) return;
		ranking="##060<< Hall of Fame >>����[1st]��"+name[0]+" // "+win[0]+"��[2nd]��"+name[1]+" // "+win[1]+"��[3rd]��_____����[4th]��_____����[5th]��_____";
		broadcastOrder(ranking);		
		if(name[2]==null) return;
		ranking="##060<< Hall of Fame >>����[1st]��"+name[0]+" // "+win[0]+"��[2nd]��"+name[1]+" // "+win[1]+"��[3rd]��"+name[2]+" // "+win[2]+"��[4th]��_____����[5th]��_____";
		broadcastOrder(ranking);		
		if(name[3]==null) return;
		ranking="##060<< Hall of Fame >>����[1st]��"+name[0]+" // "+win[0]+"��[2nd]��"+name[1]+" // "+win[1]+"��[3rd]��"+name[2]+" // "+win[2]+"��[4th]��"+name[3]+" // "+win[3]+"��[5th]��_____";
		broadcastOrder(ranking);		
		if(name[4]==null) return;
		ranking="##060<< Hall of Fame >>����[1st]��"+name[0]+" // "+win[0]+"��[2nd]��"+name[1]+" // "+win[1]+"��[3rd]��"+name[2]+" // "+win[2]+"��[4th]��"+name[3]+" // "+win[3]+"��[5th]��"+name[4]+" // "+win[4];
		broadcastOrder(ranking);		
	}
	void runGame() {
		new S_UI().setUI();
	}
	public static void main(String agrs[]) {
		new S().runGame();
	}
}
