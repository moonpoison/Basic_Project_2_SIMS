package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

import com.mysql.cj.x.protobuf.MysqlxCrud.Delete;

import model.Student;
import util.DepartmentManager;
import util.StudentManager;
import util.ConvertObject;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.JTable;
import javax.swing.DropMode;
import javax.swing.ListSelectionModel;

public class MainUI {

	private JFrame frame;
	private JTable table;
	public static boolean isUpdating = false;

	public MainUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.WHITE);
		frame.setTitle("학생정보관리 프로그램");
		frame.setBounds(100, 100, 900, 300);
		frame.setLocation(600, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu Menu = new JMenu("메뉴");
		menuBar.add(Menu);
		
		JMenuItem mi_InsertStudent = new JMenuItem("학생추가");
		Menu.add(mi_InsertStudent);
		JMenuItem mi_DeleteStudent = new JMenuItem("학생삭제");
		Menu.add(mi_DeleteStudent);
		JMenuItem mi_Exit = new JMenuItem("종료");
		Menu.add(mi_Exit);
		
		JPanel westPanel = new JPanel();
		westPanel.setBackground(Color.WHITE);
		westPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		westPanel.setPreferredSize(new Dimension(160, frame.getHeight()));
		frame.getContentPane().add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_westTitle = new JLabel("Select Student Type");
		westPanel.add(lb_westTitle);
		
		JCheckBox ckUS = new JCheckBox("학부생");
		ckUS.setBackground(Color.WHITE);
		westPanel.add(ckUS);
		
		JCheckBox ckGS = new JCheckBox("대학원생");
		ckGS.setBackground(Color.WHITE);
		westPanel.add(ckGS);
		
		//체크박스 하나만 선택되게
		ckUS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isUpdating) return;
				isUpdating = true;
				if(ckUS.isSelected()&&ckGS.isSelected()) {
					ckGS.setSelected(false);
				}
				isUpdating = false;
			}
		});
		ckGS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isUpdating) return;
				isUpdating = true;
				if(ckUS.isSelected()&&ckGS.isSelected()) {
					ckUS.setSelected(false);
				}
				isUpdating = false;
				
			}
		});
		
		JComboBox<String> cb_depart = new JComboBox<>();
		DepartmentManager depm = new DepartmentManager();
		ArrayList<String> dname = depm.getDepartments();
		for(String i : dname) {
			cb_depart.addItem(i);
		}
		cb_depart.setBackground(Color.WHITE);
		cb_depart.setPreferredSize(new Dimension(150, 20));
		westPanel.add(cb_depart);
		
		//트리생성
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(cb_depart.getSelectedItem());
		for(int  i=1;i<5;++i) {
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(i);
			root.add(node);
		}
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		JTree tr_year = new JTree(treeModel);
		tr_year.setPreferredSize(new Dimension(150, 140));
		westPanel.add(tr_year);
		
		//콤보박스 변경시 루트변경
		cb_depart.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				root.setUserObject(cb_depart.getSelectedItem());
				treeModel.nodeChanged(root);
			}
		});
		
		JPanel centerPanel = new JPanel();
		centerPanel.setForeground(Color.WHITE);
		centerPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		centerPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);

		Object[][] students = null;
		String columnNames[] = {"이름", "학번", "학년", "구분", "학과"};
	    DefaultTableModel tableModel = new DefaultTableModel(students, columnNames);
		JTable table = new JTable(tableModel);
		table.setEnabled(false);
		table.setBackground(Color.white);
		table.setOpaque(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setBackground(Color.white);
		
		JScrollPane sp = new JScrollPane(table);
		sp.getViewport().setBackground(Color.white);
		sp.setPreferredSize(new Dimension(720, 230));
		centerPanel.add(sp);
		
		tr_year.addTreeSelectionListener(new TreeSelectionListener() {	
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selected = (DefaultMutableTreeNode) tr_year.getLastSelectedPathComponent();
				// 선택된 노드가 null이 아닌 경우 처리
                if (selected != null) {
                	//루트 노드
                	if(selected.isRoot()) {
                		String grade=null;
                		if(ckGS.isSelected()) grade=ckGS.getText();
                		if(ckUS.isSelected()) grade=ckUS.getText();
                		String dname = (String) cb_depart.getSelectedItem();
                		Student stu = new Student(null, null, null, grade, dname);
                		StudentManager sm = new StudentManager();
                		ArrayList<Student> studentList = sm.selectStudent(stu);
                		//테이블갱신
                		tableModel.setRowCount(0);
                		for(Student i : studentList) {
                			Object[] row = {i.getName(), i.getNum(), i.getYear(), i.getGrade(), i.getDepartment()};
                			tableModel.addRow(row);
                		}
                		tableModel.fireTableDataChanged();  
                	}
                	//하위 노드
                	else if(selected.isLeaf()) {
                		int year = (int)selected.getUserObject().toString().charAt(0)-(int)'0'; //char to int로 인해 아스키코드 0을 뺌
                		String grade=null;
                		if(ckGS.isSelected()) grade=ckGS.getText();
                		if(ckUS.isSelected()) grade=ckUS.getText();
                		String dname = (String) cb_depart.getSelectedItem();
                		Student stu = new Student(null, null, year, grade, dname);
                		StudentManager sm = new StudentManager();
                		ArrayList<Student> studentList = sm.selectStudent(stu);
                		//테이블갱신
                		tableModel.setRowCount(0);
                		for(Student i : studentList) {
                			Object[] row = {i.getName(), i.getNum(), i.getYear(), i.getGrade(), i.getDepartment()};
                			tableModel.addRow(row);
                		}
                		tableModel.fireTableDataChanged();                		
                	}
                }
			}
		});
		mi_InsertStudent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new InsertStudentUI();	
			}
		});
		mi_DeleteStudent.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteStudentUI();	
			}
		});
		mi_Exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		frame.setVisible(true);
	}

}
