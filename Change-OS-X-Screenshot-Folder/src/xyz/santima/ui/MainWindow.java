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

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField txtRuta;
	private JButton btnAbrir;
	private JButton btnEstablecer;
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
		contentPane.add(getTxtRuta());
		contentPane.add(getBtnAbrir());
		contentPane.add(getBtnEstablecer());
	}
	private JTextField getTxtRuta() {
		if (txtRuta == null) {
			txtRuta = new JTextField();
			txtRuta.setEditable(false);
			txtRuta.setBounds(53, 20, 259, 26);
			txtRuta.setColumns(10);
		}
		return txtRuta;
	}
	private JButton getBtnAbrir() {
		if (btnAbrir == null) {
			btnAbrir = new JButton("open");
			btnAbrir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JFileChooser file = new JFileChooser();
					file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					file.showOpenDialog(mainWindow);
					
					File choose = file.getSelectedFile();
					if(choose != null){
						try {
							mainWindow.txtRuta.setText(choose.getCanonicalPath().toString());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
			});
			btnAbrir.setBounds(324, 20, 117, 29);
		}
		return btnAbrir;
	}
	private JButton getBtnEstablecer() {
		if (btnEstablecer == null) {
			btnEstablecer = new JButton("set folder");
			btnEstablecer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Runtime.getRuntime().exec("defaults write com.apple.screencapture location " + txtRuta.getText());
						Runtime.getRuntime().exec("killall SystemUIServer");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnEstablecer.setBounds(441, 20, 117, 29);
		}
		return btnEstablecer;
	}
}
