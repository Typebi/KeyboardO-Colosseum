package kboc;

public class C_Effecter extends Thread {
	C_KeyHandler ch;
	private int i;
	C_Effecter(C_KeyHandler c, int i){
		ch = c;
		this.i = i;
	}
	public void run() {
		try {
			switch(this.i) {
			case 10 : showLine1(); break;
			case 11 : show11(); break;
			case 12 : show12(); break;
			case 13 : show13(); break;
			case 14 : show14(); break;
			case 15 : show15(); break;
			case 16 : show16(); break;
			case 17 : show17(); break;
			case 18 : show18(); break;
			case 20 : showLine2(); break;
			case 21 : show21(); break;
			case 22 : show22(); break;
			case 23 : show23(); break;
			case 24 : show24(); break;
			case 25 : show25(); break;
			case 26 : show26(); break;
			case 27 : show27(); break;
			case 28 : show28(); break;
			case 31 : show31(); break;
			case 32 : show32(); break;
			case 33 : show33(); break;
			case 34 : show34(); break;
			case 35 : show35(); break;
			case 36 : show36(); break;
			case 37 : show37(); break;
			case 38 : show38(); break;
			case 41 : show41(); break;
			case 42 : show42(); break;
			case 43 : show43(); break;
			case 44 : show44(); break;
			case 45 : show45(); break;
			case 46 : show46(); break;
			case 47 : show47(); break;
			case 48 : show48(); break;
			}
		} catch (InterruptedException e) { e.printStackTrace(); }
	}
	void show11() throws InterruptedException {
		ch.cui.l_num11.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num11.setVisible(false);
	}
	void show12() throws InterruptedException {
		ch.cui.l_num12.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num12.setVisible(false);
	}
	void show13() throws InterruptedException {
		ch.cui.l_num13.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num13.setVisible(false);
	}
	void show14() throws InterruptedException {
		ch.cui.l_num14.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num14.setVisible(false);
	}
	void show15() throws InterruptedException {
		ch.cui.l_num15.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num15.setVisible(false);
	}
	void show16() throws InterruptedException {
		ch.cui.l_num16.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num16.setVisible(false);
	}
	void show17() throws InterruptedException {
		ch.cui.l_num17.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num17.setVisible(false);
	}
	void show18() throws InterruptedException {
		ch.cui.l_num18.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num18.setVisible(false);
	}
	void showLine1() throws InterruptedException {
		ch.cui.l_line11.setVisible(true);
		ch.cui.l_line12.setVisible(true);
		ch.cui.l_line13.setVisible(true);
		ch.cui.l_line14.setVisible(true);
		ch.cui.l_line15.setVisible(true);
		ch.cui.l_line16.setVisible(true);
		ch.cui.l_line17.setVisible(true);
		ch.cui.l_line18.setVisible(true);
		Thread.sleep(300);
		ch.cui.l_line11.setVisible(false);
		ch.cui.l_line12.setVisible(false);
		ch.cui.l_line13.setVisible(false);
		ch.cui.l_line14.setVisible(false);
		ch.cui.l_line15.setVisible(false);
		ch.cui.l_line16.setVisible(false);
		ch.cui.l_line17.setVisible(false);
		ch.cui.l_line18.setVisible(false);
	}
	void show21() throws InterruptedException {
		ch.cui.l_num21.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num21.setVisible(false);
	}
	void show22() throws InterruptedException {
		ch.cui.l_num22.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num22.setVisible(false);
	}
	void show23() throws InterruptedException {
		ch.cui.l_num23.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num23.setVisible(false);
	}
	void show24() throws InterruptedException {
		ch.cui.l_num24.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num24.setVisible(false);
	}
	void show25() throws InterruptedException {
		ch.cui.l_num25.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num25.setVisible(false);
	}
	void show26() throws InterruptedException {
		ch.cui.l_num26.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num26.setVisible(false);
	}
	void show27() throws InterruptedException {
		ch.cui.l_num27.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num27.setVisible(false);
	}
	void show28() throws InterruptedException {
		ch.cui.l_num28.setVisible(true);
		Thread.sleep(400);
		ch.cui.l_num28.setVisible(false);
	}
	void showLine2() throws InterruptedException {
		ch.cui.l_line21.setVisible(true);
		ch.cui.l_line22.setVisible(true);
		ch.cui.l_line23.setVisible(true);
		ch.cui.l_line24.setVisible(true);
		ch.cui.l_line25.setVisible(true);
		ch.cui.l_line26.setVisible(true);
		ch.cui.l_line27.setVisible(true);
		ch.cui.l_line28.setVisible(true);
		Thread.sleep(300);
		ch.cui.l_line21.setVisible(false);
		ch.cui.l_line22.setVisible(false);
		ch.cui.l_line23.setVisible(false);
		ch.cui.l_line24.setVisible(false);
		ch.cui.l_line25.setVisible(false);
		ch.cui.l_line26.setVisible(false);
		ch.cui.l_line27.setVisible(false);
		ch.cui.l_line28.setVisible(false);
	}
	
	//ø¿¥‰ ¿Ã∆Â∆Æ 1P
	void show31() throws InterruptedException {
		ch.cui.l_num11.setIcon(ch.cui.X);
		ch.cui.l_num11.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num11.setVisible(false);
		ch.cui.l_num11.setIcon(ch.cui.O);
	}
	void show32() throws InterruptedException {
		ch.cui.l_num12.setIcon(ch.cui.X);
		ch.cui.l_num12.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num12.setVisible(false);
		ch.cui.l_num12.setIcon(ch.cui.O);
	}
	void show33() throws InterruptedException {
		ch.cui.l_num13.setIcon(ch.cui.X);
		ch.cui.l_num13.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num13.setVisible(false);
		ch.cui.l_num13.setIcon(ch.cui.O);
	}
	void show34() throws InterruptedException {
		ch.cui.l_num14.setIcon(ch.cui.X);
		ch.cui.l_num14.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num14.setVisible(false);
		ch.cui.l_num14.setIcon(ch.cui.O);
	}
	void show35() throws InterruptedException {
		ch.cui.l_num15.setIcon(ch.cui.X);
		ch.cui.l_num15.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num15.setVisible(false);
		ch.cui.l_num15.setIcon(ch.cui.O);
	}
	void show36() throws InterruptedException {
		ch.cui.l_num16.setIcon(ch.cui.X);
		ch.cui.l_num16.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num16.setVisible(false);
		ch.cui.l_num16.setIcon(ch.cui.O);
	}
	void show37() throws InterruptedException {
		ch.cui.l_num17.setIcon(ch.cui.X);
		ch.cui.l_num17.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num17.setVisible(false);
		ch.cui.l_num17.setIcon(ch.cui.O);
	}
	void show38() throws InterruptedException {
		ch.cui.l_num18.setIcon(ch.cui.X);
		ch.cui.l_num18.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num18.setVisible(false);
		ch.cui.l_num18.setIcon(ch.cui.O);
	}	
	
	//ø¿¥‰ ¿Ã∆Â∆Æ 2P
	void show41() throws InterruptedException {
		ch.cui.l_num21.setIcon(ch.cui.X);
		ch.cui.l_num21.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num21.setVisible(false);
		ch.cui.l_num21.setIcon(ch.cui.O);
	}
	void show42() throws InterruptedException {
		ch.cui.l_num22.setIcon(ch.cui.X);
		ch.cui.l_num22.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num22.setVisible(false);
		ch.cui.l_num22.setIcon(ch.cui.O);
	}
	void show43() throws InterruptedException {
		ch.cui.l_num23.setIcon(ch.cui.X);
		ch.cui.l_num23.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num23.setVisible(false);
		ch.cui.l_num23.setIcon(ch.cui.O);
	}
	void show44() throws InterruptedException {
		ch.cui.l_num24.setIcon(ch.cui.X);
		ch.cui.l_num24.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num24.setVisible(false);
		ch.cui.l_num24.setIcon(ch.cui.O);
	}
	void show45() throws InterruptedException {
		ch.cui.l_num25.setIcon(ch.cui.X);
		ch.cui.l_num25.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num25.setVisible(false);
		ch.cui.l_num25.setIcon(ch.cui.O);
	}
	void show46() throws InterruptedException {
		ch.cui.l_num26.setIcon(ch.cui.X);
		ch.cui.l_num26.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num26.setVisible(false);
		ch.cui.l_num26.setIcon(ch.cui.O);
	}
	void show47() throws InterruptedException {
		ch.cui.l_num27.setIcon(ch.cui.X);
		ch.cui.l_num27.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num27.setVisible(false);
		ch.cui.l_num27.setIcon(ch.cui.O);
	}
	void show48() throws InterruptedException {
		ch.cui.l_num28.setIcon(ch.cui.X);
		ch.cui.l_num28.setVisible(true);
		Thread.sleep(1000);
		ch.cui.l_num28.setVisible(false);
		ch.cui.l_num28.setIcon(ch.cui.O);
	}
}