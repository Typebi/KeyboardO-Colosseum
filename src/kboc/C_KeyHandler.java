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
				case 119 : reactKeyIn("01"); break;// w �Է�
				case 87 : reactKeyIn("01"); break;// W �Է�
				case 97 : reactKeyIn("02"); break; // a �Է�
				case 65 : reactKeyIn("02"); break; // A �Է�
				case 115 : reactKeyIn("03"); break; // s �Է�
				case 83 : reactKeyIn("03"); break; // S �Է�
				case 100 : reactKeyIn("04"); break; // d �Է�
				case 68 : reactKeyIn("04"); break; // d �Է�
				case 105 : reactKeyIn("05"); break; // i �Է�
				case 73 : reactKeyIn("05"); break; // I �Է�
				case 106 : reactKeyIn("06"); break; // j �Է�
				case 74 : reactKeyIn("06"); break; // J �Է�
				case 107 : reactKeyIn("07"); break; // k �Է�
				case 75 : reactKeyIn("07"); break; // K �Է�
				case 108 : reactKeyIn("08"); break; // l �Է�
				case 76 : reactKeyIn("08"); break; // L �Է�
				case 32 : reactKeyIn("09"); break; // spaceBar �Է�
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
		case "09" : break; // ������ ���ȿ�� ����. ���� ������Ʈ(?)
		}
	}
	synchronized void compareBlock(String input, int c, String answerBlock) {
		if(C_Main.amI1P)
			compareBlock1P(input, c, answerBlock);
		if(C_Main.amI2P)
			compareBlock2P(input, c, answerBlock);
	}
	void compareBlock1P(String input, int c, String answerBlock) {
		if(cui.ll_board1P[6][c].getIcon()==cui.icons.get(input)) { // �Է��� �¾������
			cui.ll_board1P[6][c].setIcon(cui.icons.get(answerBlock));// ���� �̹����� �ٲ�
			try { new C_Effecter(this, c+11).start(); // ���� ����Ʈ ����
			}catch(IllegalThreadStateException i) { i.printStackTrace(); }
			cui.ll_board1P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(70, 70, 70), 2, true));
			C_BlcokMaker.currentColmn++;
			if(C_BlcokMaker.currentColmn>=cui.ll_board1P[6].length) { //8ĭ���� ���߸� ������� ���� �� ��� �� �Ʒ��� ����
				try { new C_Effecter(this, 10).start(); // ���� Ŭ���� ����Ʈ ����
				}catch(IllegalThreadStateException i) { i.printStackTrace(); }
				pullLine();
				for(int k=0;k<C_UI.BOARD_WIDTH;k++)
					tempIcons1P[k] = cui.ll_board1P[6][k].getIcon();
				C_BlcokMaker.currentColmn=0;
				bm.sendCurrentArena();
			}
			cui.ll_board1P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(220, 220, 30), 4, true));
		}else{ // �Է��� Ʋ�������
			try { new C_Effecter(this, c+31).start(); // ���� ����Ʈ ����
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
		if(cui.ll_board2P[6][c].getIcon()==cui.icons.get(input)) { // �Է��� �¾������
			cui.ll_board2P[6][c].setIcon(cui.icons.get(answerBlock));// ���� �̹����� �ٲ�
			try { new C_Effecter(this, c+21).start(); // ���� ����Ʈ ����
			}catch(IllegalThreadStateException i) { i.printStackTrace(); }
			cui.ll_board2P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(70, 70, 70), 2, true));
			C_BlcokMaker.currentColmn++;
			if(C_BlcokMaker.currentColmn>=cui.ll_board2P[6].length) { //8ĭ���� ���߸� ������� ���� �� ��� �� �Ʒ��� ����
				try { new C_Effecter(this, 20).start(); // ���� Ŭ���� ����Ʈ ����
				}catch(IllegalThreadStateException i) { i.printStackTrace(); }
				pullLine();
				for(int k=0;k<C_UI.BOARD_WIDTH;k++)
					tempIcons2P[k] = cui.ll_board2P[6][k].getIcon();
				C_BlcokMaker.currentColmn=0;
				bm.sendCurrentArena();
			}
			cui.ll_board2P[6][C_BlcokMaker.currentColmn].setBorder(new LineBorder(new Color(220, 220, 30), 4, true));
		}else{
			try { new C_Effecter(this, c+41).start(); // ���� ����Ʈ ����
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
					if(i==0) { // 2~7���� �� ������ �� �� ������ �� �ٷ� �����
						cui.ll_board1P[i][j].setIcon(cui.icons.get("00"));
						return;
					}
					cui.ll_board1P[i][j].setIcon(cui.ll_board1P[i-1][j].getIcon());
				}
		if(C_Main.amI2P)
			for(int i=C_UI.BOARD_HEIGHT-1;i>=0 ;i--)
				for(int j=0;j<C_UI.BOARD_WIDTH;j++) {
					if(i==0) { // 2~7���� �� ������ �� �� ������ �� �ٷ� �����
						cui.ll_board2P[i][j].setIcon(cui.icons.get("00"));
						return;
					}
					cui.ll_board2P[i][j].setIcon(cui.ll_board2P[i-1][j].getIcon());
				}
	}
}