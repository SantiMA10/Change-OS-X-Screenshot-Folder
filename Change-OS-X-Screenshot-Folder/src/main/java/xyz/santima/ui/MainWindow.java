package xyz.santima.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import xyz.santima.logic.CommandExecutor;
import xyz.santima.util.FileDrop;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtPath;
	private JButton btnOpen;
	private JButton btnSet;
	private MainWindow mainWindow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Change screenshot folder");
		mainWindow = this;
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 95);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTxtPath());
		contentPane.add(getBtnOpen());
		contentPane.add(getBtnSet());
		
		btnSet.setEnabled(false);
		
		new FileDrop( contentPane, new FileDrop.Listener() {
			public void filesDropped( File[] files ) {   

				txtPath.setText(files[0].getAbsolutePath());
				btnSet.setEnabled(true);
				
			}   
		});

	}
	private JTextField getTxtPath() {
		if (txtPath == null) {
			txtPath = new JTextField();
			txtPath.setEditable(false);
			txtPath.setBounds(53, 20, 259, 26);
			txtPath.setColumns(10);
		}
		return txtPath;
	}
	private JButton getBtnOpen() {
		if (btnOpen == null) {
			btnOpen = new JButton("open");
			btnOpen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JFileChooser file = new JFileChooser();
					file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					file.showOpenDialog(mainWindow);
					
					File choose = file.getSelectedFile();
					if(choose != null){
						try {
							mainWindow.txtPath.setText(choose.getCanonicalPath().toString());
							btnSet.setEnabled(true);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			});
			btnOpen.setBounds(324, 20, 117, 29);
		}
		return btnOpen;
	}
	private JButton getBtnSet() {
		if (btnSet == null) {
			btnSet = new JButton("set folder");
			btnSet.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					CommandExecutor.changeScreenShotFolder(txtPath.getText());
					btnSet.setEnabled(false);
				}
				
			});
			btnSet.setBounds(441, 20, 117, 29);
		}
		return btnSet;
	}
}