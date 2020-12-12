package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Code.LoginInterface;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
public class LoginUI{

	private LoginInterface LoginService;
	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	String mySessionCookie = "not set";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginUI window = new LoginUI();
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
	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.WHITE);
		frame.setBounds(250, 100, 850, 500);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 467, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLogoImage = new JLabel("");
		lblLogoImage.setBounds(-50, 0, 1087,460);
		panel.add(lblLogoImage);	
		Image img = new ImageIcon(OrderUI.class.getResource("/Images/Logo.png")).getImage();
		Image newimg = img.getScaledInstance(500, 500,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon newIcon = new ImageIcon(newimg);
		
		lblLogoImage.setIcon(newIcon);
		
		
		JButton btnLogin = new JButton("Sign in");
		btnLogin.setIcon(new ImageIcon(LoginUI.class.getResource("/Images/placeOrderIcon.png")));
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 13));
		btnLogin.setForeground(Color.BLACK);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					LoginService=(LoginInterface) Naming.lookup("rmi://localhost/Login");
					String username=txtUsername.getText();
					String password= new String(txtPassword.getPassword());
					
					String result = LoginService.login(username,password);					
					if( result.equals("incorrect")) { 
						JOptionPane.showMessageDialog(null,"Username or Password Incorrect",
								"Login System",JOptionPane.ERROR_MESSAGE);
						txtPassword.setText(null);
		                txtPassword.grabFocus();
					} else { 
						mySessionCookie = result; 
						System.out.println("Your login was successful.");
						QuestionnaireUI window = new QuestionnaireUI();
						window.frame.setVisible(true);
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"A problem occured:"+ex.toString(),
							"Login System",JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				} 
				
				
			}
		});
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(506, 333, 290, 50);
		frame.getContentPane().add(btnLogin);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("");
		txtUsername.setBounds(506, 158, 290, 30);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(506, 224, 290, 30);
		frame.getContentPane().add(txtPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 13));
		lblUsername.setBounds(506, 133, 80, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPassword.setBounds(506, 199, 80, 14);
		frame.getContentPane().add(lblPassword);
		
		JButton btnAdmin = new JButton("");
		btnAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				AddQuestionsUI UI = new AddQuestionsUI();
				UI.frame.setVisible(true);
			}
			
		});
		Image img1 = new ImageIcon(LoginUI.class.getResource("/Images/admin.png")).getImage();
		Image newImage = img1.getScaledInstance(20, 20,java.awt.Image.SCALE_DEFAULT);
		btnAdmin.setIcon(new ImageIcon(newImage));
		btnAdmin.setBackground(Color.WHITE);
		btnAdmin.setBounds(765, 427, 31, 23);
		frame.getContentPane().add(btnAdmin);
	}
}
