package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.DatabaseManager;
import dao.QueryMaker;
import model.Student;
import util.StudentManager;

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.FlowLayout;

public class InsertStudentUI {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertStudentUI window = new InsertStudentUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InsertStudentUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(650, 300, 600, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		Object[][] students = null; 
		String columnNames[] = {"학번", "이름", "학년", "구분", "학과"};
		//첫 열에 체크박스를 넣기위해 재정의 -> ChatGPT 4o 참고
		DefaultTableModel tableModel = new DefaultTableModel(students, columnNames);
		JTable table = new JTable(tableModel);
		table.setBackground(Color.white);
		table.setOpaque(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(Color.white);
		
		JScrollPane sp = new JScrollPane(table);
		sp.getViewport().setBackground(Color.white);
		sp.setPreferredSize(new Dimension(720, 230));
		panel.add(sp, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lb_title = new JLabel("학생 추가");
		lb_title.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		panel_1.add(lb_title, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_1.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btn_addrow = new JButton("   행 추가   ");
		btn_addrow.setContentAreaFilled(false);
		btn_addrow.setBackground(SystemColor.control);
		btn_addrow.setFont(new Font("굴림", Font.PLAIN, 13));
		btn_addrow.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn_addrow.setFocusPainted(false);
		btn_addrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] newrow = {"", "", "", "", ""};
				tableModel.addRow(newrow);
			}
		});
		panel_2.add(btn_addrow);
		
		JButton btn_instu = new JButton("   학생 추가   ");
		panel_2.add(btn_instu);
		btn_instu.setContentAreaFilled(false);
		btn_instu.setFocusPainted(false);
		btn_instu.setFont(new Font("굴림", Font.PLAIN, 13));
		btn_instu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//테이블 편집중일경우, 편집완료처리
		        if (table.isEditing()) {
		            table.getCellEditor().stopCellEditing();
		        }
				for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
					String snum = tableModel.getValueAt(i, 0).toString().isEmpty() ? null : tableModel.getValueAt(i, 0).toString();
					String name = tableModel.getValueAt(i, 1).toString().isEmpty() ? null : tableModel.getValueAt(i, 1).toString();
					Integer year = tableModel.getValueAt(i, 2).toString().isEmpty() ? null : Integer.parseInt(tableModel.getValueAt(i, 2).toString());
					String grade = tableModel.getValueAt(i, 3).toString().isEmpty() ? null : tableModel.getValueAt(i, 3).toString();
					String dname = tableModel.getValueAt(i, 4).toString().isEmpty() ? null : tableModel.getValueAt(i, 4).toString();
			        new StudentManager().insertStudent(new Student(name, snum, year, grade, dname));
			        tableModel.removeRow(i);
				}
			}
		});
		btn_instu.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn_instu.setBackground(SystemColor.control);
	}

}
