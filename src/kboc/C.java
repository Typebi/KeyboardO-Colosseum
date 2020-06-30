package kboc;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class C extends JFrame {
	JPanel panel_CENTER;
	JLabel l_Title, label_nickName, label_Ip;
	JTextField tf_nickName, tf_Ip;
	JButton btn_Connect;
	static String ip, nickName;
	C(){
		setUI();
	}
	void setUI() {
		// 기본 GUI 설정
		setVisible(true);
		setTitle("KeyboardO Colosseum LOGIN");
		setSize(655, 399);
		setResizable(true);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //백그라운드 패널
        panel_CENTER = new JPanel();
		panel_CENTER.setLayout(null);
		panel_CENTER.setVisible(true);
		panel_CENTER.setBounds(0, 0, 640, 360);
		panel_CENTER.setBackground(new Color(0, 30, 100, 40));
		getContentPane().add(panel_CENTER);
		
		JLabel backLabel = new JLabel();
		backLabel.setIcon(new ImageIcon("imgs/login.gif"));
		backLabel.setBounds(0, 0, 640, 360);
		panel_CENTER.add(backLabel);
		
		//로그인창 게임제목 출력
			l_Title = new JLabel();
			l_Title.setFont(new Font("HY목각파임B", Font.BOLD, 54));
			l_Title.setText("<html><div style='text-align: center;'>KeyboardO<br>Colosseum</div></html>");
			l_Title.setBackground(new Color(255, 255, 255, 60)); 
			//l_Title.setBorder(new LineBorder(new Color(70, 70, 70), 2));
			l_Title.setForeground(Color.WHITE);
			l_Title.setBounds(110,40,420,120);
			l_Title.setHorizontalAlignment(JLabel.CENTER);
			l_Title.setVerticalAlignment(JLabel.CENTER);
			backLabel.add(l_Title);
		
		//닉네임 입력
			//'닉네임' 흰글씨 이미지
			label_nickName = new JLabel();
			//label_nickName.setIcon(new ImageIcon("imgs/nm.png"));
			label_nickName.setFont(new Font("맑은고딕", Font.BOLD, 18));
			label_nickName.setForeground(Color.WHITE);
			label_nickName.setText("Nickname");
			label_nickName.setBounds(195,200,100,25);
			backLabel.add(label_nickName);
			//닉네임 입력창
			tf_nickName = new JTextField(15);
			tf_nickName.setBorder(new LineBorder(Color.WHITE, 1));
			tf_nickName.setForeground(Color.WHITE);
			tf_nickName.setBackground(new Color(50, 50, 50));
			tf_nickName.setBounds(300,200,120,25);
			backLabel.add(tf_nickName);
		
		//IP 입력
			//'IP' 흰글씨 이미지
			label_Ip = new JLabel();
			//label_Ip.setIcon(new ImageIcon("imgs/ip.png"));
			label_Ip.setFont(new Font("맑은고딕", Font.BOLD, 18));
			label_Ip.setForeground(Color.WHITE);
			label_Ip.setText("IP Address");
			label_Ip.setBounds(190,235,100,25);
			label_Ip.setVisible(true);
			backLabel.add(label_Ip);
			//IP 입력창
			tf_Ip = new JTextField(15);
			tf_Ip.setBorder(new LineBorder(Color.WHITE, 1));
			tf_Ip.setForeground(Color.WHITE);
			tf_Ip.setBackground(new Color(50, 50, 50));
			tf_Ip.setBounds(300,235,120,25);
			backLabel.add(tf_Ip);
			
		//입장 버튼
			btn_Connect = new JButton("Enter");
			btn_Connect.setFocusPainted(false);
			btn_Connect.setFont(new Font("HY헤드라인M", Font.BOLD, 20));
			btn_Connect.setBackground(new Color(255, 255, 255, 60)); 
			btn_Connect.setBorder(new LineBorder(new Color(70, 70, 70), 2));
			btn_Connect.setForeground(Color.WHITE);
			btn_Connect.setBounds(260,280,120,40);
			backLabel.add(btn_Connect);
			//공백 입력 시 로컬호스트 개통 안내창 띄우기
			btn_Connect.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == btn_Connect){
						//닉네임 조건 ( 반드시 입력값 필요, 최대 6글자 ) 
						if(tf_nickName.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Please put the nickname!", "ERROR!", JOptionPane.WARNING_MESSAGE);
							return;
						}else if(tf_nickName.getText().trim().length() > 6){
							JOptionPane.showMessageDialog(null, "Nickname can be less than 6 letters.", "ERROR!", JOptionPane.WARNING_MESSAGE);
							tf_nickName.setText("");
							return;
						//IP 조건 ( 공백 시 로컬 IP로 접속 )
						}else if(tf_Ip.getText().equals("")){
							JOptionPane.showMessageDialog(null, "Enter to locale IP address!", "Warning", JOptionPane.WARNING_MESSAGE);
							try {
								InetAddress local = InetAddress.getLocalHost();
								ip = local.getHostAddress();
							} catch (UnknownHostException e1) { e1.printStackTrace(); 	}
						//IP 주소 검증
						}else{
							if(tf_Ip.getText().matches("(^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$)")){
								ip = tf_Ip.getText();
							}else{
								JOptionPane.showMessageDialog(null, "Please put the correct IP address! ", "ERROR!", JOptionPane.WARNING_MESSAGE);
								return;
							}
						}
						//모든 조건 통과 시 서버연결 후 닉네임 중복여부 체크
						nickName = tf_nickName.getText().trim();
						if(new C_Main().enterServer()) {
							//JOptionPane.showMessageDialog(null, "로그인 성공!", "KeyboardO Colosseum LOGIN", JOptionPane.INFORMATION_MESSAGE);
							setVisible(false);
							new C_Main().start();
						}else {
							JOptionPane.showMessageDialog(null, "Connection failed ! Check that server is opened and nickname is not duplicated.", "ERROR", JOptionPane.INFORMATION_MESSAGE);
							tf_nickName.setText("");
							return;
						}
					}
				}
			});
		revalidate();
		repaint();
	}
	public static void main(String agrs[]) {
		new C();
	}
}
