package kboc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.LinkedHashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class C_UI extends JFrame {
	C_ActHandler actionEvent;
	C_KeyHandler keyEvent;
	C_Chat chat;
	Container backGround;
	JPanel backPanel,p_main1P, p_main2P, p_arena1P, p_arena2P;
	JLabel backImg, l_HallOfFame, l_pastTime,
			l_profile1P, l_profile2P,
			l_num11, l_num12, l_num13, l_num14, l_num15, l_num16, l_num17, l_num18,
			l_line11,l_line12,l_line13,l_line14,l_line15,l_line16,l_line17,l_line18,
			l_num21, l_num22, l_num23, l_num24, l_num25, l_num26, l_num27, l_num28,
			l_line21,l_line22,l_line23,l_line24,l_line25,l_line26,l_line27,l_line28,
			l_loose1P, l_loose2P, l_escape1P, l_escape2P;
	public static final int BOARD_HEIGHT = 7;
	public static final int BOARD_WIDTH = 8; 
	JLabel ll_board1P[][] = new JLabel[BOARD_HEIGHT][BOARD_WIDTH],
		   ll_board2P[][] = new JLabel[BOARD_HEIGHT][BOARD_WIDTH];
	JButton btnEnter1P, btnEnter2P, btn_ChatOn;
	ImageIcon L1, L2, L3, L4, R1, R2, R3, R4, D1, D2, D3, D4, D5, D6, D7, D8, I1, I2, I3, B, BL, O, X, Line;
	LinkedHashMap<String, ImageIcon> icons = new LinkedHashMap<String, ImageIcon>();
	int currentColmn;
	C_UI(){
		actionEvent = new C_ActHandler(this);
		keyEvent = new C_KeyHandler(this);
		setImgs();
		setUI();
		//new BGM().play();
		revalidate();
		repaint();
	}
	void setUI(){
		//기본 GUI 셋팅 ( 전체사이즈 = 1280x720 )
		backGround = getContentPane();
		backGround.setLayout(new BorderLayout());
		backGround.setBackground(new Color(0, 0, 0,40));
		setBounds(0, 0, 1280, 720);
		setLocationRelativeTo(null); // 창을 화면 가운데에 띄움
		setTitle("KeyboardO Colosseum");
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//백그라운드 패널
		backPanel = new JPanel();
		backPanel.setLayout(null);
		backPanel.setBounds(0, 0, 1280, 720);
		backPanel.setVisible(true);
		backPanel.setBackground(new Color(0, 0, 0, 40));
		backGround.add(backPanel);
		
		setEffect();
		
		//배경 라벨
		backImg = new JLabel();
		backImg.setIcon(new ImageIcon("imgs/tri.gif"));
		backImg.setBounds(0, 0, 1280, 720);

		//채팅
		//챗 활성화 버튼
		btn_ChatOn = new JButton("Chat ON");
		btn_ChatOn.setBackground(new Color(255,255,255));
		btn_ChatOn.setBounds(980, 30, 90, 30);
		btn_ChatOn.addActionListener(actionEvent);
		backPanel.add(btn_ChatOn);
			//채팅창
			chat = new C_Chat(this);

		//입장버튼 
			//1P
			btnEnter1P = new JButton("1P");
			btnEnter1P.setFont(new Font("HY헤드라인M", Font.BOLD, 24));
			btnEnter1P.setForeground(Color.YELLOW);
			btnEnter1P.setBackground(new Color(0,0,0));
			btnEnter1P.setBorder(new LineBorder(new Color(114, 0, 0), 4, true));
			btnEnter1P.setBounds(305,280,200,100);
			btnEnter1P.setVisible(true);
			btnEnter1P.addActionListener(actionEvent);
			backPanel.add(btnEnter1P);
			//2P
			btnEnter2P = new JButton("2P");
			btnEnter2P.setFont(new Font("HY헤드라인M", Font.BOLD, 24));
			btnEnter2P.setForeground(Color.YELLOW);
			btnEnter2P.setBackground(new Color(0,0,0));
			btnEnter2P.setBorder(new LineBorder(new Color(114, 0, 0), 4, true));
			btnEnter2P.setBounds(775,280,200,100);
			btnEnter2P.addActionListener(actionEvent);
			backPanel.add(btnEnter2P);

		//명예의전당(랭킹)
		l_HallOfFame = new JLabel("<< Hall of Fame >>　　[1st]　_____　　[2nd]　_____　　[3rd]　_____　　[4th]　_____　　[5th]　_____");
		l_HallOfFame.setVerticalAlignment(SwingConstants.CENTER);
		l_HallOfFame.setHorizontalAlignment(SwingConstants.CENTER);
		l_HallOfFame.setFont(new Font("HY헤드라인M", Font.BOLD, 12));
		l_HallOfFame.setForeground(Color.YELLOW);
		l_HallOfFame.setBackground(new Color(255,255,255,0));
		l_HallOfFame.setBorder(new LineBorder(new Color(114, 0, 0), 4, true));
		l_HallOfFame.setBounds(200, 30, 760, 30);
		backPanel.add(l_HallOfFame);
		
		//경과 시간
		l_pastTime = new JLabel("<html><div style='text-align: center;'>Lv . 1<br>00 : 00</div></html>");
		l_pastTime.setForeground(Color.YELLOW);
		l_pastTime.setFont(new Font("HY헤드라인M", Font.BOLD, 12));
		l_pastTime.setHorizontalAlignment(JLabel.CENTER);
		l_pastTime.setVerticalAlignment(JLabel.CENTER);
		l_pastTime.setBackground(new Color(255,255,255,0));
		l_pastTime.setBorder(new LineBorder(new Color(114, 0, 0), 4, true));
		l_pastTime.setBounds(565, 90, 140, 40);
		backPanel.add(l_pastTime);		

		//1P 파트
			//1P 선수정보 판
			l_profile1P = new JLabel("< Waiting challenger >");
			l_profile1P.setFont(new Font("HY헤드라인M", Font.BOLD, 12));
			l_profile1P.setForeground(Color.YELLOW);
			l_profile1P.setVerticalAlignment(SwingConstants.CENTER);
			l_profile1P.setHorizontalAlignment(SwingConstants.CENTER);
			l_profile1P.setBackground(new Color(255,255,255,0));
			l_profile1P.setBorder(new LineBorder(new Color(114, 0, 0), 4, true));
			l_profile1P.setBounds(200, 90, 340, 40);
			backPanel.add(l_profile1P, BorderLayout.CENTER);
			
			//1P 메인 판
			p_main1P = new JPanel();
			p_main1P.setLayout(new BorderLayout());
			p_main1P.setBackground(new Color(255,255,255,0));
			p_main1P.setBorder(new LineBorder(new Color(114, 0, 0), 4, true));
			p_main1P.setBounds(200, 160, 400, 400);
			backPanel.add(p_main1P);
				//1P 경기장
				setarena1P();
		
		//2P 파트
			//2P 상태정보
			l_profile2P = new JLabel("< Waiting challenger >");
			l_profile2P.setForeground(Color.YELLOW);
			l_profile2P.setFont(new Font("HY헤드라인M", Font.BOLD, 12));
			l_profile2P.setVerticalAlignment(SwingConstants.CENTER);
			l_profile2P.setHorizontalAlignment(SwingConstants.CENTER);
			l_profile2P.setBackground(new Color(255,255,255,0));
			l_profile2P.setBorder(new LineBorder(new Color(114, 0, 0), 4, true));
			l_profile2P.setBounds(730, 90, 340, 40);
			backPanel.add(l_profile2P);
			//2P 메인 판
			p_main2P = new JPanel();
			p_main2P.setLayout(new BorderLayout());
			p_main2P.setBackground(new Color(255,255,255,0));
			p_main2P.setBorder(new LineBorder(new Color(114, 0, 0), 4, true));
			p_main2P.setBounds(670, 160, 400, 400);
			backPanel.add(p_main2P);
				//2P 경기장
				setarena2P();
		
		//1P 패배
		l_loose1P = new JLabel();//Check
		l_loose1P.setFont(new Font("HY헤드라인M", Font.BOLD, 60));
		l_loose1P.setForeground(Color.YELLOW);
		l_loose1P.setVerticalAlignment(SwingConstants.CENTER);
		l_loose1P.setHorizontalAlignment(SwingConstants.CENTER);
		l_loose1P.setBounds(30,30,200,100);
		l_loose1P.setVisible(false);
		//p_main_1P.add(l_loose1P);
		
		//2P 패배
		l_loose2P = new JLabel();
		l_loose2P.setFont(new Font("HY헤드라인M", Font.BOLD, 60));
		l_loose2P.setForeground(Color.YELLOW);
		l_loose2P.setVerticalAlignment(SwingConstants.CENTER);
		l_loose2P.setHorizontalAlignment(SwingConstants.CENTER);
		l_loose2P.setBounds(775,330,200,100);
		l_loose2P.setVisible(false);
		//p_main_2P.add(l_loose2P);
		
		//1P 탈주
		l_escape1P = new JLabel();
		l_escape1P.setFont(new Font("HY헤드라인M", Font.BOLD, 60));
		l_escape1P.setForeground(Color.YELLOW);
		l_escape1P.setVerticalAlignment(SwingConstants.CENTER);
		l_escape1P.setHorizontalAlignment(SwingConstants.CENTER);
		l_escape1P.setBounds(30,30,200,100);
		l_escape1P.setVisible(false);
		
		//2P 탈주
		l_escape2P = new JLabel();
		l_escape2P.setFont(new Font("HY헤드라인M", Font.BOLD, 60));
		l_escape2P.setForeground(Color.YELLOW);
		l_escape2P.setVerticalAlignment(SwingConstants.CENTER);
		l_escape2P.setHorizontalAlignment(SwingConstants.CENTER);
		l_escape2P.setBounds(775,330,200,100);
		l_escape2P.setVisible(false);
		
		//키설정 안내
		JLabel l_info =new JLabel("< Control >");
		l_info.setBounds(585,570,100,50);
		l_info.setBackground(new Color(255,255,255,0));
		l_info.setFont(new Font("HY헤드라인M", Font.BOLD, 16));
		l_info.setForeground(Color.YELLOW);
		l_info.setVerticalAlignment(SwingConstants.CENTER);
		l_info.setHorizontalAlignment(SwingConstants.CENTER);
		l_info.setVisible(true);
		backPanel.add(l_info);
		
		JLabel l_lw =new JLabel();
		l_lw.setIcon(L1);
		l_lw.setBounds(530,570,50,50);
		l_lw.setBackground(new Color(255,255,255,0));
		l_lw.setBorder(new LineBorder(new Color(114, 0, 0), 2, true));
		l_lw.setVisible(true);
		JLabel l_la =new JLabel();
		l_la.setIcon(L2);
		l_la.setBounds(480,620,50,50);
		l_la.setBackground(new Color(255,255,255,0));
		l_la.setBorder(new LineBorder(new Color(114, 0, 0), 2, true));
		l_la.setVisible(true);
		JLabel l_ls =new JLabel();
		l_ls.setIcon(L3);
		l_ls.setBounds(530,620,50,50);
		l_ls.setBackground(new Color(255,255,255,0));
		l_ls.setBorder(new LineBorder(new Color(114, 0, 0), 2, true));
		l_ls.setVisible(true);
		JLabel l_ld =new JLabel();
		l_ld.setIcon(L4);
		l_ld.setBounds(580,620,50,50);
		l_ld.setBackground(new Color(255,255,255,0));
		l_ld.setBorder(new LineBorder(new Color(114, 0, 0), 2, true));
		l_ld.setVisible(true);
		backPanel.add(l_lw);
		backPanel.add(l_la);
		backPanel.add(l_ls);
		backPanel.add(l_ld);
		
		JLabel l_ri =new JLabel();
		l_ri.setIcon(R1);
		l_ri.setBounds(690,570,50,50);
		l_ri.setBackground(new Color(255,255,255,0));
		l_ri.setBorder(new LineBorder(new Color(114, 0, 0), 2, true));
		l_ri.setVisible(true);
		JLabel l_rj =new JLabel();
		l_rj.setIcon(R2);
		l_rj.setBounds(640,620,50,50);
		l_rj.setBackground(new Color(255,255,255,0));
		l_rj.setBorder(new LineBorder(new Color(114, 0, 0), 2, true));
		l_rj.setVisible(true);
		JLabel l_rk =new JLabel();
		l_rk.setIcon(R3);
		l_rk.setBounds(690,620,50,50);
		l_rk.setBackground(new Color(255,255,255,0));
		l_rk.setBorder(new LineBorder(new Color(114, 0, 0), 2, true));
		l_rk.setVisible(true);
		JLabel l_rl =new JLabel();
		l_rl.setIcon(R4);
		l_rl.setBounds(740,620,50,50);
		l_rl.setBackground(new Color(255,255,255,0));
		l_rl.setBorder(new LineBorder(new Color(114, 0, 0), 2, true));
		l_rl.setVisible(true);
		backPanel.add(l_ri);
		backPanel.add(l_rj);
		backPanel.add(l_rk);
		backPanel.add(l_rl);
		
		backPanel.add(backImg);
	}
	void setarena1P() {
		p_arena1P = new JPanel();
		p_arena1P.setLayout(new GridLayout(7,8));
		p_arena1P.setBackground(new Color(255,255,255,0));
		p_main1P.add(p_arena1P,BorderLayout.CENTER);
		for(int i=0; i<BOARD_HEIGHT; i++)
            for(int j=0; j<BOARD_WIDTH; j++)
        		p_arena1P.add(ll_board1P[i][j]= new JLabel(BL));
		for(int i=0; i<BOARD_WIDTH; i++)
			ll_board1P[6][i].setBorder(new LineBorder(new Color(70, 70, 70), 2, true));
		p_arena1P.addKeyListener(keyEvent);
	}
	void setarena2P() {
		p_arena2P = new JPanel();
		p_arena2P.setLayout(new GridLayout(7,8));
		p_arena2P.setBackground(new Color(255,255,255,0));
		p_main2P.add(p_arena2P,BorderLayout.CENTER);
		for(int i=0; i<BOARD_HEIGHT; i++)
            for(int j=0; j<BOARD_WIDTH; j++)
        		p_arena2P.add(ll_board2P[i][j]= new JLabel(BL));
		for(int i=0; i<BOARD_WIDTH; i++)
			ll_board2P[6][i].setBorder(new LineBorder(new Color(70, 70, 70), 2, true));
		p_arena2P.addKeyListener(keyEvent);
	}
	void setImgs() {
		L1 = new ImageIcon("imgs/lw.png");
		L2 = new ImageIcon("imgs/la.png");
		L3 = new ImageIcon("imgs/ls.png");
		L4 = new ImageIcon("imgs/ld.png");
		R1 = new ImageIcon("imgs/ri.png");
		R2 = new ImageIcon("imgs/rj.png");
		R3 = new ImageIcon("imgs/rk.png");
		R4 = new ImageIcon("imgs/rl.png");
		D1 = new ImageIcon("imgs/dlw.png");
		D2 = new ImageIcon("imgs/dla.png");
		D3 = new ImageIcon("imgs/dls.png");
		D4 = new ImageIcon("imgs/dld.png");
		D5 = new ImageIcon("imgs/dri.png");
		D6 = new ImageIcon("imgs/drj.png");
		D7 = new ImageIcon("imgs/drk.png");
		D8 = new ImageIcon("imgs/drl.png");
		B = new ImageIcon("imgs\\blank.png");
		O = new ImageIcon("imgs\\answer.gif"); //정답 효과
		X = new ImageIcon("imgs\\wrong.png"); //오답 효과
		Line = new ImageIcon("imgs\\line.gif"); //라인파괴 이펙트
		icons.put("00", B);
		icons.put("01", L1);icons.put("02", L2);icons.put("03", L3);icons.put("04", L4);
		icons.put("05", R1);icons.put("06", R2);icons.put("07", R3);icons.put("08", R4);
		icons.put("11", D1);icons.put("12", D2);icons.put("13", D3);icons.put("14", D4);
		icons.put("15", D5);icons.put("16", D6);icons.put("17", D7);icons.put("18", D8);
		icons.put("80", O);
		icons.put("90", X);
		icons.put("81", Line);
	}
	void setEffect() {
		//1P 블록 정답 이펙트
		l_num11 = new JLabel();
		l_num11.setIcon(O);
		l_num11.setBounds(200,505,50,50);
		l_num11.setVisible(false);
		backPanel.add(l_num11);
		l_num12 = new JLabel();
		l_num12.setIcon(O);
		l_num12.setBounds(250,505,50,50);
		l_num12.setVisible(false);
		backPanel.add(l_num12);
		l_num13 = new JLabel();
		l_num13.setIcon(O);
		l_num13.setBounds(300,505,50,50);
		l_num13.setVisible(false);
		backPanel.add(l_num13);
		l_num14 = new JLabel();
		l_num14.setIcon(O);
		l_num14.setBounds(350,505,50,50);
		l_num14.setVisible(false);
		backPanel.add(l_num14);
		l_num15 = new JLabel();
		l_num15.setIcon(O);
		l_num15.setBounds(400,505,50,50);
		l_num15.setVisible(false);
		backPanel.add(l_num15);
		l_num16 = new JLabel();
		l_num16.setIcon(O);
		l_num16.setBounds(450,505,50,50);
		l_num16.setVisible(false);
		backPanel.add(l_num16);
		l_num17 = new JLabel();
		l_num17.setIcon(O);
		l_num17.setBounds(500,505,50,50);
		l_num17.setVisible(false);
		backPanel.add(l_num17);
		l_num18 = new JLabel();
		l_num18.setIcon(O);
		l_num18.setBounds(550,505,50,50);
		l_num18.setVisible(false);
		backPanel.add(l_num18);
		
		//1P 블록 line 이펙트
		l_line11 = new JLabel();
		l_line11.setIcon(Line);
		l_line11.setBounds(185,485,80,80);
		l_line11.setVisible(false);
		backPanel.add(l_line11);
		l_line12 = new JLabel();
		l_line12.setIcon(Line);
		l_line12.setBounds(235,485,80,80);
		l_line12.setVisible(false);
		backPanel.add(l_line12);
		l_line13 = new JLabel();
		l_line13.setIcon(Line);
		l_line13.setBounds(285,485,80,80);
		l_line13.setVisible(false);
		backPanel.add(l_line13);
		l_line14 = new JLabel();
		l_line14.setIcon(Line);
		l_line14.setBounds(335,485,80,80);
		l_line14.setVisible(false);
		backPanel.add(l_line14);
		l_line15 = new JLabel();
		l_line15.setIcon(Line);
		l_line15.setBounds(385,485,80,80);
		l_line15.setVisible(false);
		backPanel.add(l_line15);
		l_line16 = new JLabel();
		l_line16.setIcon(Line);
		l_line16.setBounds(435,485,80,80);
		l_line16.setVisible(false);
		backPanel.add(l_line16);
		l_line17 = new JLabel();
		l_line17.setIcon(Line);
		l_line17.setBounds(485,485,80,80);
		l_line17.setVisible(false);
		backPanel.add(l_line17);
		l_line18 = new JLabel();
		l_line18.setIcon(Line);
		l_line18.setBounds(535,485,80,80);
		l_line18.setVisible(false);
		backPanel.add(l_line18);
		
		//2P 블록 정답 이펙트
		l_num21 = new JLabel();
		l_num21.setIcon(O);
		l_num21.setBounds(670,505,50,50);
		l_num21.setVisible(false);
		backPanel.add(l_num21);
		l_num22 = new JLabel();
		l_num22.setIcon(O);
		l_num22.setBounds(720,505,50,50);
		l_num22.setVisible(false);
		backPanel.add(l_num22);
		l_num23 = new JLabel();
		l_num23.setIcon(O);
		l_num23.setBounds(770,505,50,50);
		l_num23.setVisible(false);
		backPanel.add(l_num23);
		l_num24 = new JLabel();
		l_num24.setIcon(O);
		l_num24.setBounds(820,505,50,50);
		l_num24.setVisible(false);
		backPanel.add(l_num24);
		l_num25 = new JLabel();
		l_num25.setIcon(O);
		l_num25.setBounds(870,505,50,50);
		l_num25.setVisible(false);
		backPanel.add(l_num25);
		l_num26 = new JLabel();
		l_num26.setIcon(O);
		l_num26.setBounds(920,505,50,50);
		l_num26.setVisible(false);
		backPanel.add(l_num26);
		l_num27 = new JLabel();
		l_num27.setIcon(O);
		l_num27.setBounds(970,505,50,50);
		l_num27.setVisible(false);
		backPanel.add(l_num27);
		l_num28 = new JLabel();
		l_num28.setIcon(O);
		l_num28.setBounds(1020,505,50,50);
		l_num28.setVisible(false);
		backPanel.add(l_num28);
		
		//2P블록 line 이펙트
		l_line21 = new JLabel();
		l_line21.setIcon(Line);
		l_line21.setBounds(655,485,80,80);
		l_line21.setVisible(false);
		backPanel.add(l_line21);
		l_line22 = new JLabel();
		l_line22.setIcon(Line);
		l_line22.setBounds(705,485,80,80);
		l_line22.setVisible(false);
		backPanel.add(l_line22);
		l_line23 = new JLabel();
		l_line23.setIcon(Line);
		l_line23.setBounds(755,485,80,80);
		l_line23.setVisible(false);
		backPanel.add(l_line23);
		l_line24 = new JLabel();
		l_line24.setIcon(Line);
		l_line24.setBounds(805,485,80,80);
		l_line24.setVisible(false);
		backPanel.add(l_line24);
		l_line25 = new JLabel();
		l_line25.setIcon(Line);
		l_line25.setBounds(855,485,80,80);
		l_line25.setVisible(false);
		backPanel.add(l_line25);
		l_line26 = new JLabel();
		l_line26.setIcon(Line);
		l_line26.setBounds(905,485,80,80);
		l_line26.setVisible(false);
		backPanel.add(l_line26);
		l_line27 = new JLabel();
		l_line27.setIcon(Line);
		l_line27.setBounds(955,485,80,80);
		l_line27.setVisible(false);
		backPanel.add(l_line27);
		l_line28 = new JLabel();
		l_line28.setIcon(Line);
		l_line28.setBounds(1005,485,80,80);
		l_line28.setVisible(false);
		backPanel.add(l_line28);
	}
	ImageIcon getNewBlock(int i) {
		switch(i) {
			case 0 : return L1;
			case 1 : return L2;
			case 2 : return L3;
			case 3 : return L4;
			case 4 : return R1;
			case 5 : return R2;
			case 6 : return R3;
			case 7 : return R4;
		}
		return icons.get("0");
	}
}
