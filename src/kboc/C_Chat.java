package kboc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class C_Chat extends JFrame {
	C_UI cui;
	C_ChatActionHandler cah;
	JPanel p_txtInput;
	JTextArea txtArea;
	JTextField txtField;
	JScrollPane sp_txtAreaScroll, sp_txtFieldScroll;
	JButton btn_chatSend;
	C_Chat(C_UI c) {
		cui = c;
		setChatUI();
		cah = new C_ChatActionHandler(this);
    }
	void setChatUI(){
		setTitle("Chatting room");
		setSize(300,500);
        setResizable(true);
        setVisible(false);
        JPanel ChatWindow = new JPanel();
        setContentPane(ChatWindow);
        ChatWindow.setLayout(new BoxLayout(ChatWindow, BoxLayout.Y_AXIS));
        
		//채팅창 .. 가능하면 본인 채팅은 오른쪽 정렬
		txtArea = new JTextArea();
		txtArea.setEditable(false);
		txtArea.setLineWrap(true);
		txtArea.setBackground(Color.WHITE);
		txtArea.setCaretPosition(txtArea.getDocument().getLength());
		sp_txtAreaScroll = new JScrollPane(txtArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//sp_txtAreaScroll.setBounds(0, 0, 300, 440);
		sp_txtAreaScroll.setPreferredSize(new Dimension(300 , 440));
		sp_txtAreaScroll.setVisible(true);
		
		//채팅입력파트
		p_txtInput = new JPanel();
		p_txtInput.setLayout(new BoxLayout(p_txtInput, BoxLayout.X_AXIS));
		p_txtInput.setPreferredSize(new Dimension(300 , 30));
		
			//입력창
			txtField = new JTextField();
			txtField.setColumns(10);
			sp_txtFieldScroll = new JScrollPane(txtField, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			sp_txtFieldScroll.setPreferredSize(new Dimension(230 , 30));
			//sp_txtFieldScroll.setBounds(0, 0, 230, 30);
			sp_txtFieldScroll.setVisible(true);
			txtField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String msg = txtField.getText();
					C_Main.sendChat("@@000"+msg+"$"+C.nickName);
					txtField.setText("");
					txtField.requestFocus();
				}
			});

			//채팅 전송버튼
			btn_chatSend = new JButton("Send");
			btn_chatSend.setBounds(230, 0, 70, 30);
			btn_chatSend.setVisible(true);
			btn_chatSend.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String msg = txtField.getText();
					C_Main.sendChat("@@000"+msg+"$"+C.nickName);
					txtField.setText("");
					txtField.requestFocus();
				}
			});
		
		ChatWindow.add(sp_txtAreaScroll);
		ChatWindow.add(p_txtInput, BorderLayout.SOUTH);
        p_txtInput.add(sp_txtFieldScroll);
        p_txtInput.add(btn_chatSend);
	}
}
