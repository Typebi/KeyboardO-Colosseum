package kboc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class S_UI extends JFrame {
	JPanel panel_Main, panel_TextArea, panel_Btn;
	JLabel label_ServerStatus;
	JButton btn_ServerStart, btn_ServerClose;
	static JTextArea textArea;
	static JScrollPane scrollPane;
	JTextField textField;
	S_UIHandler sah;
	S_UI(){
	}
	void setUI(){
		//UI 메인 설정
		setTitle("KeyboardO Colosseum Server");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		
		//백그라운드 패널
		panel_Main = new JPanel();
		panel_Main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel_Main);
		panel_Main.setLayout(new BoxLayout(panel_Main, BoxLayout.Y_AXIS));
		
		//서버상태표시 멘트
		label_ServerStatus = new JLabel("[ Server Waiting .. ]");
		label_ServerStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
		label_ServerStatus.setPreferredSize(new Dimension(96, 50));
		panel_Main.add(label_ServerStatus);
		label_ServerStatus.setHorizontalTextPosition(SwingConstants.CENTER);
		label_ServerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		label_ServerStatus.setFont(new Font("나눔바른고딕", Font.PLAIN, 20));
		
		//로그 및 채팅입력창을 올릴 패널
		panel_TextArea = new JPanel();
		panel_TextArea.setLayout(new BorderLayout());
		panel_Main.add(panel_TextArea);
			//로그창
			textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setEditable(false);
			scrollPane = new JScrollPane();
			scrollPane.setBorder(new LineBorder(Color.DARK_GRAY));
			scrollPane.setViewportView(textArea);
			panel_TextArea.add(scrollPane);
			//채팅입력창
			textField = new JTextField();
			textField.setBorder(new LineBorder(Color.DARK_GRAY));
			panel_TextArea.add(textField, BorderLayout.SOUTH);
			textField.addActionListener(new S_UIHandler(this));
		
		//버튼들을 올릴 패널
		panel_Btn = new JPanel();
		panel_Btn.setPreferredSize(new Dimension(10, 43));
		panel_Btn.setAutoscrolls(true);
		panel_Main.add(panel_Btn);
		panel_Btn.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			//서버시작 버튼
			btn_ServerStart = new JButton(" Start Server ");
			btn_ServerStart.setHorizontalTextPosition(SwingConstants.CENTER);
			btn_ServerStart.setPreferredSize(new Dimension(120, 40));
			btn_ServerStart.setFocusPainted(false);
			btn_ServerStart.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
			btn_ServerStart.setAlignmentX(Component.CENTER_ALIGNMENT);
			btn_ServerStart.setForeground(Color.WHITE);
			btn_ServerStart.setBackground(Color.DARK_GRAY);
			btn_ServerStart.setBorder(null);
			panel_Btn.add(btn_ServerStart);
			btn_ServerStart.addActionListener(new S_UIHandler(this));
			//서버종료 버튼
			btn_ServerClose = new JButton(" Close Server ");
			btn_ServerClose.setHorizontalTextPosition(SwingConstants.CENTER);
			btn_ServerClose.setPreferredSize(new Dimension(120, 40));
			btn_ServerClose.setFocusPainted(false);
			btn_ServerClose.setFont(new Font("나눔바른고딕", Font.BOLD, 16));
			btn_ServerClose.setAlignmentX(Component.CENTER_ALIGNMENT);
			btn_ServerClose.setForeground(Color.WHITE);
			btn_ServerClose.setBackground(Color.DARK_GRAY);
			btn_ServerClose.setBorder(null);
			panel_Btn.add(btn_ServerClose);
			btn_ServerClose.addActionListener(new S_UIHandler(this));
			btn_ServerClose.setEnabled(false);
		revalidate();
		repaint();
	}
}
