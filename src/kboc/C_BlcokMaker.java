package kboc;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;
import javax.swing.border.LineBorder;

public class C_BlcokMaker extends Thread {
	C_UI cui;
	Random random = new Random();
	static int currentColmn;
	String timeMs, timeSs, lvS;
	C_BlcokMaker(C_UI c){
		cui=c;
	}
	public void run() {
		currentColmn=0;
		spawnLine();
	}
	void spawnLine() {
		try {
			int lv=1;
			int timeM=0, timeS=0, lvSetter=0, genTime=0;
			genBlock();
			if(C_Main.amI1P) {
				for(int k=0;k<C_UI.BOARD_WIDTH;k++)
					C_KeyHandler.tempIcons1P[k] = cui.ll_board1P[6][k].getIcon();
				cui.ll_board1P[6][0].setBorder(new LineBorder(new Color(220, 220, 30), 4, true));
			}else if(C_Main.amI2P) {
				for(int k=0;k<C_UI.BOARD_WIDTH;k++)
					C_KeyHandler.tempIcons2P[k] = cui.ll_board2P[6][k].getIcon();
				cui.ll_board2P[6][0].setBorder(new LineBorder(new Color(220, 220, 30), 4, true));
			}
			while(C_Main.runningFlag) {
				Thread.sleep(1000);
				timeS++;
				lvSetter++;
				genTime++;
				if(genTime>=5.5-(lv*0.5)) {
					genBlock();
					genTime=0;
				}
				if(timeS>=60) {	timeM++; timeS=0; }
				if(lvSetter >= 10) { lv++; lvSetter = 0; }
				timeMs = timeM+"";
				timeSs = timeS+"";
				if(timeS<10)
					timeSs = "0"+timeS;
				if(timeM<10)
					timeMs = "0"+timeM;
				lvS=lv+"";
				if(lv>=10) { lv=10; lvS="MAX"; }
				C_Main.request("@@013"+"<html><div style='text-align: center;'>Lv . "+lvS+"<br>"+timeMs+" : "+timeSs+"</div></html>", C.nickName);
			}
			lv=1; lvSetter=0; timeM=0; timeS=0; lvS="1"; timeMs = "00"; timeSs = "00";
			C_Main.request("@@013"+"<html><div style='text-align: center;'>Lv . "+lvS+"<br>"+timeMs+" : "+timeSs+"</div></html>", C.nickName);
		} catch (InterruptedException e) {	e.printStackTrace();
		} catch (IOException e) {e.printStackTrace(); }
	}
	void endGame() {
		try {
			if(C_Main.amI1P==true)
				C_Main.request("@@041", C.nickName);
			if(C_Main.amI2P==true)
				C_Main.request("@@042", C.nickName);
		}catch(IOException e) {	e.printStackTrace(); }
	}
	int checkGenLine() { //블록을 생성시킬 라인을 확인
		if(C_Main.amI1P)
			for(int i=0;i<C_UI.BOARD_HEIGHT;i++ )
				if(cui.ll_board1P[i][0].getIcon()!=cui.icons.get("00"))
					return i-1;
		if(C_Main.amI2P)
			for(int i=0;i<C_UI.BOARD_HEIGHT;i++ )
				if(cui.ll_board2P[i][0].getIcon()!=cui.icons.get("00"))
					return i-1;
		return 6;
	}
	void genBlock() { // 블록 1줄 생성
		int i=checkGenLine();
		if(i==-1) { endGame(); return;} //판이 꽉 찬 경우 패배처리
		if(C_Main.amI1P) {
	        for(int j=0; j<C_UI.BOARD_WIDTH; j++){
	        	int r = random.nextInt(8);
	        	cui.ll_board1P[i][j].setIcon((cui.getNewBlock(r)));
	        }
	        sendCurrentArena();
	        cui.p_arena1P.requestFocus();
		}else if(C_Main.amI2P){
	        for(int j=0; j<C_UI.BOARD_WIDTH; j++){
	        	int r = random.nextInt(8);
	        	cui.ll_board2P[i][j].setIcon((cui.getNewBlock(r)));
	        }
	        sendCurrentArena();
	        cui.p_arena2P.requestFocus();
		}
	}
	void sendCurrentArena() {
		try {
		if(C_Main.amI1P)
			for(int i=0; i<C_UI.BOARD_HEIGHT; i++)
	            for(int j=0; j<C_UI.BOARD_WIDTH; j++)
	            	for(String bNum : cui.icons.keySet())
	            		if(cui.ll_board1P[i][j].getIcon()==cui.icons.get(bNum))
	            			C_Main.request("@@051"+i+j+bNum, C.nickName);
		if(C_Main.amI2P)
			for(int i=0; i<C_UI.BOARD_HEIGHT; i++)
	            for(int j=0; j<C_UI.BOARD_WIDTH; j++)
	            	for(String bNum : cui.icons.keySet())
	            		if(cui.ll_board2P[i][j].getIcon()==cui.icons.get(bNum))
	            			C_Main.request("@@052"+i+j+bNum, C.nickName);
		}catch(IOException e) { e.printStackTrace(); }
	}
}
