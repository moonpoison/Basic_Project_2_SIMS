package ui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import model.User;
import util.Login;

public class LoginUI {

	private JFrame frame;
	private JTextField tf_ID;
	private JPasswordField passwordField;

	public LoginUI() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setTitle("학생정보관리 프로그램");
		frame.setBounds(100, 100, 320, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocation(800, 300);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		mainPanel.setBounds(19, 154, 273, 109);
		mainPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JButton btn_Login = new JButton("로그인");
		btn_Login.setBackground(Color.WHITE);
		btn_Login.setBounds(207, 6, 56, 62);
		mainPanel.add(btn_Login);
		btn_Login.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btn_Login.setMargin(new Insets(2, 2, 2, 2));
		
		JButton btn_toRegister = new JButton("회원가입");
		btn_toRegister.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btn_toRegister.setBackground(Color.WHITE);
		btn_toRegister.setBounds(10, 78, 253, 23);
		mainPanel.add(btn_toRegister);
		
		JPanel idField = new JPanel();
		idField.setBackground(Color.WHITE);
		idField.setBounds(10, 10, 189, 23);
		mainPanel.add(idField);
		idField.setLayout(new BorderLayout(0, 0));
		
		JLabel la_ID = new JLabel("ID: ");
		la_ID.setBackground(Color.WHITE);
		la_ID.setPreferredSize(new Dimension(64, 15));
		idField.add(la_ID, BorderLayout.CENTER);
		
		JPanel idPanel = new JPanel();
		idPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		idPanel.setPreferredSize(new Dimension(125, 10));
		idField.add(idPanel, BorderLayout.EAST);
		idPanel.setLayout(new BorderLayout(0, 0));
		
		tf_ID = new JTextField();
		tf_ID.setBorder(new EmptyBorder(0, 0, 0, 0));
		idPanel.add(tf_ID);
		tf_ID.setColumns(10);
		
		JButton btn_idClear = new JButton("X");
		btn_idClear.setMargin(new Insets(2, 2, 2, 2));
		btn_idClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_ID.setText("");
			}
		});
		btn_idClear.setPreferredSize(new Dimension(25, 25));
		idPanel.add(btn_idClear, BorderLayout.EAST);
		btn_idClear.setBackground(Color.WHITE);
		btn_idClear.setBorderPainted(false);
		btn_idClear.setFont(new Font("굴림", Font.BOLD, 10));
		
		JPanel pwField = new JPanel();
		pwField.setBackground(Color.WHITE);
		pwField.setBounds(10, 43, 189, 23);
		mainPanel.add(pwField);
		pwField.setLayout(new BorderLayout(0, 0));
		
		JLabel la_PW = new JLabel("Password:");
		la_PW.setBackground(Color.WHITE);
		la_PW.setPreferredSize(new Dimension(15, 15));
		pwField.add(la_PW, BorderLayout.CENTER);
		
		JPanel pwPanel = new JPanel();
		pwPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		pwPanel.setPreferredSize(new Dimension(125, 10));
		pwField.add(pwPanel, BorderLayout.EAST);
		pwPanel.setLayout(new BorderLayout(0, 0));
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new EmptyBorder(0, 0, 0, 0));
		pwPanel.add(passwordField, BorderLayout.CENTER);
		
		JButton btn_pwClear = new JButton("X");
		btn_pwClear.setPreferredSize(new Dimension(25, 25));
		btn_pwClear.setMargin(new Insets(2, 2, 2, 2));
		btn_pwClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordField.setText("");
			}
		});
		btn_pwClear.setFont(new Font("굴림", Font.BOLD, 10));
		btn_pwClear.setBorderPainted(false);
		btn_pwClear.setBackground(Color.WHITE);
		pwPanel.add(btn_pwClear, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("학생 정보 관리");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 25));
		lblNewLabel.setBounds(19, 70, 267, 74);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lbStatus = new JLabel("");
		lbStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lbStatus.setBounds(19, 273, 273, 15);
		frame.getContentPane().add(lbStatus);
		
		btn_toRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RegisterUI();
				frame.dispose();
			}
		});
		btn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = new User(tf_ID.getText(), String.valueOf(passwordField.getPassword()));
				Login login = new Login(user);
				boolean author = login.loginUser();
				if(author) {
					//로그인성공
					lbStatus.setText("로그인성공!");
				}else {
					//로그인실패
					lbStatus.setText("입력된 정보가 유효하지 않습니다.");
				}
			}
		});
	}
}