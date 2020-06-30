package kboc;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.border.LineBorder;

public class C_KeyHandler implements KeyListener {
	C_UI cui;
	C_BlcokMaker bm;
	static Icon tempIcons1P[] = new Icon[8];
	static Icon tempIcons2P[] = new Icon[8];
	C_KeyHandler(C_UI c){
		cui = c;
		bm = new C_BlcokMaker(c);
	}
	public void keyPressed(KeyEvent e) {
		if(C_Main.runningFlag&&checkblank())
			try {
				switch(e.getKeyCode()) {
				case 119 : reactKeyIn("01"); break;// w 입력
				case 87 : reactKeyIn("01"); break;// W 입력
				case 97 : reactKeyIn("02"); break; // a 입력
				case 65 : reactKeyIn("02"); break; // A 입력
				case 115 : reactKeyIn("03"); break; // s 입력
				case 83 : reactKeyIn("03"); break; // S 입력
				case 100 : reactKeyIn("04"); break; // d 입력
				case 68 : reactKeyIn("04"); break; // d 입력
				case 105 : reactKeyIn("05"); break; // i 입력
				case 73 : reactKeyIn("05"); break; // I 입력
				case 106 : reactKeyIn("06"); break; // j 입력
				case 74 : reactKeyIn("06"); break; // J 입력
				case 107 : reactKeyIn("07"); break; // k 입력
				case 75 : reactKeyIn("07"); break; // K 입력
				case 108 : reactKeyIn("08"); break; // l 입력
				case 76 : reactKeyIn("08"); break; // L 입력
				case 32 : reactKeyIn("09"); break; // spaceBar 입력
				}
			}catch(ClassCastException cce) {cce.printStackTrace();}
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
	}
	boolean checkblank() {
		if(C_Main.amI1P&&cui.ll_board1P[6][0].getIcon().equals(cui.icons.get("00")))
			return false;
		if(C_Main.amI2P&&cui.ll_board2P[6][0].getIcon().equals(cui.icons.get("00")))
			return false;
		return true;
	}
	synchronized void reactKeyIn(String input) {
		String answerBlock=null;
		switch(input) {
		case "01" : answerBlock="11"; compareBlock(input,C_BlcokMaker.currentColmn,answerBlock); break;
		case "02" : answerBlock="12"; compareBlock(input,C_BlcokMaker.currentColmn,answerBlock); break;
		case "03" : answerBlock="13"; compareBlock(input,C_BlcokMaker.currentColmn,answerBlock); break;
		case "04" : answerBlock="14"; compareBlock(input,C_BlcokMaker.currentColmn,answerBlock); break;
		case "05" : answerBlock="15"; compareBlock(input,C_BlcokMaker.currentColmn,answerBlock); break;
		case "06" : answerBlock="16"; compareBlock(input,C_BlcokMaker.currentColmn,answerBlock); break;
		case "07" : answerBlock="17"; compareBlock(input,C_BlcokMaker.currentColmn,answerBlock); break;
		case "08" : answerBlock="18"; compareBlock(input,C_BlcokMaker.currentColmn,answerBlock); break;
		case "09" : break; // 아이템 사용효과 적용. 향후 업데이트(?)
		}
	}
	synchronized void compareBlock(String input, int c, String answerBlock) {
		if(C_Main.amI1P)
			compareBlock1P(input, c, answerBlock);
		if(C_Main.amI2P)
			compareBlock2P(input, c, answerBlock);
	}
	void compareBlock1P(String input, int c, String answerBlock) {
		if(cui.ll_board1P[6][c].getIcon()==cui.icons.get(input)) { // 입력이 맞았을경우
			cui.ll_board1P[6][c].setIcon(cui.icons.get(answerBlock));// 맞춘 이미지로 바꿈
			try { new C_Effecter(this, c+11).start(); // 정답 이펙트 적용
			}catch(IllegalThreadStateException i) { i.printStackTrace(); }
			cui.ll_board1P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(70, 70, 70), 2, true));
			C_BlcokMaker.currentColmn++;
			if(C_BlcokMaker.currentColmn>=cui.ll_board1P[6].length) { //8칸까지 맞추면 현재라인 삭제 및 모든 블럭 아래로 당기기
				try { new C_Effecter(this, 10).start(); // 라인 클리어 이펙트 적용
				}catch(IllegalThreadStateException i) { i.printStackTrace(); }
				pullLine();
				for(int k=0;k<C_UI.BOARD_WIDTH;k++)
					tempIcons1P[k] = cui.ll_board1P[6][k].getIcon();
				C_BlcokMaker.currentColmn=0;
				bm.sendCurrentArena();
			}
			cui.ll_board1P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(220, 220, 30), 4, true));
		}else{ // 입력이 틀렸을경우
			try { new C_Effecter(this, c+31).start(); // 오답 이펙트 적용
			}catch(IllegalThreadStateException i) { i.printStackTrace(); }
			cui.ll_board1P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(70, 70, 70), 2, true));
			for(int k=0;k<C_UI.BOARD_WIDTH;k++)
				cui.ll_board1P[6][k].setIcon(tempIcons1P[k]);
			C_BlcokMaker.currentColmn=0;
			cui.ll_board1P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(220, 220, 30), 4, true));
			bm.genBlock();
		}
	}
	void compareBlock2P(String input, int c, String answerBlock) {
		if(cui.ll_board2P[6][c].getIcon()==cui.icons.get(input)) { // 입력이 맞았을경우
			cui.ll_board2P[6][c].setIcon(cui.icons.get(answerBlock));// 맞춘 이미지로 바꿈
			try { new C_Effecter(this, c+21).start(); // 정답 이펙트 적용
			}catch(IllegalThreadStateException i) { i.printStackTrace(); }
			cui.ll_board2P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(70, 70, 70), 2, true));
			C_BlcokMaker.currentColmn++;
			if(C_BlcokMaker.currentColmn>=cui.ll_board2P[6].length) { //8칸까지 맞추면 현재라인 삭제 및 모든 블럭 아래로 당기기
				try { new C_Effecter(this, 20).start(); // 라인 클리어 이펙트 적용
				}catch(IllegalThreadStateException i) { i.printStackTrace(); }
				pullLine();
				for(int k=0;k<C_UI.BOARD_WIDTH;k++)
					tempIcons2P[k] = cui.ll_board2P[6][k].getIcon();
				C_BlcokMaker.currentColmn=0;
				bm.sendCurrentArena();
			}
			cui.ll_board2P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(220, 220, 30), 4, true));
		}else{
			try { new C_Effecter(this, c+41).start(); // 오답 이펙트 적용
			}catch(IllegalThreadStateException i) { i.printStackTrace(); }
			cui.ll_board2P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(70, 70, 70), 2, true));
			for(int k=0;k<C_UI.BOARD_WIDTH;k++)
				cui.ll_board2P[6][k].setIcon(tempIcons2P[k]);
			C_BlcokMaker.currentColmn=0;
			cui.ll_board2P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(220, 220, 30), 4, true));
			bm.genBlock();
		}
	}
	void pullLine() {
		if(C_Main.amI1P)
			for(int i=C_UI.BOARD_HEIGHT-1;i>=0 ;i--)
				for(int j=0;j<C_UI.BOARD_WIDTH;j++) {
					if(i==0) { // 2~7열을 다 내렸을 때 맨 윗줄은 빈 줄로 만들기
						cui.ll_board1P[i][j].setIcon(cui.icons.get("00"));
						return;
					}
					cui.ll_board1P[i][j].setIcon(cui.ll_board1P[i-1][j].getIcon());
				}
		if(C_Main.amI2P)
			for(int i=C_UI.BOARD_HEIGHT-1;i>=0 ;i--)
				for(int j=0;j<C_UI.BOARD_WIDTH;j++) {
					if(i==0) { // 2~7열을 다 내렸을 때 맨 윗줄은 빈 줄로 만들기
						cui.ll_board2P[i][j].setIcon(cui.icons.get("00"));
						return;
					}
					cui.ll_board2P[i][j].setIcon(cui.ll_board2P[i-1][j].getIcon());
				}
	}
}