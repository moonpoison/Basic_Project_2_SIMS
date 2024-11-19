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

public class DeleteStudentUI {

	private JFrame frame;
	private JTable table;

	public DeleteStudentUI() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(650, 300, 600, 300);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		Object[][] students = null; 
		String columnNames[] = {" ", "학과", "학년", "이름", "구분", "학번"};
		//첫 열에 체크박스를 넣기위해 재정의 -> ChatGPT 4o 참고
		DefaultTableModel tableModel = new DefaultTableModel(students, columnNames) {
		    @Override
		    public Class<?> getColumnClass(int columnIndex) {
		        // 첫 번째 열은 Boolean (체크박스), 나머지는 String
		        return columnIndex == 0 ? Boolean.class : String.class;
		    }

		    @Override
		    public boolean isCellEditable(int row, int column) {
		        // 첫 번째 열(체크박스)만 편집 가능
		        return column == 0;
		    }
		};
		ArrayList<Student> studentList = new StudentManager().selectAllStudent();
		for(Student i : studentList) {
			Object[] row = {false, i.getName(), i.getNum(), i.getYear(), i.getGrade(), i.getDepartment()};
			tableModel.addRow(row);
		}
		tableModel.fireTableDataChanged();
		JTable table = new JTable(tableModel);
		table.setEnabled(true);
		table.setBackground(Color.white);
		table.setOpaque(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(Color.white);
		table.getColumnModel().getColumn(0).setPreferredWidth(5);;
		
		JScrollPane sp = new JScrollPane(table);
		sp.getViewport().setBackground(Color.white);
		sp.setPreferredSize(new Dimension(720, 230));
		panel.add(sp, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lb_title = new JLabel("학생 삭제");
		lb_title.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		panel_1.add(lb_title, BorderLayout.WEST);
		
		JButton btn_instu = new JButton("   학생 삭제   ");
		btn_instu.setContentAreaFilled(false);
		btn_instu.setFocusPainted(false);
		btn_instu.setFont(new Font("굴림", Font.PLAIN, 13));
		btn_instu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
				    Boolean isSelected = (Boolean) tableModel.getValueAt(i, 0);
				    if (Boolean.TRUE.equals(isSelected)) {
				        String snum =tableModel.getValueAt(i, 2).toString();
				        new StudentManager().deleteStudent(snum);
				        tableModel.removeRow(i);
				    }
				}
			}
		});
		btn_instu.setBorder(new LineBorder(new Color(0, 0, 0)));
		btn_instu.setBackground(SystemColor.control);
		panel_1.add(btn_instu, BorderLayout.EAST);
		frame.setVisible(true);
	}

}
