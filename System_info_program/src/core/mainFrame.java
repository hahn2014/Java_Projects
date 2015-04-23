package core;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class mainFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	public String title = "Test your System Information";
	JFrame mainFrame = new JFrame(title);
	JButton showInfo = new JButton("Show System Info");
	JButton test = new JButton("Quit");
	JTextArea text = new JTextArea();
	JScrollPane scroll = new JScrollPane(text);
	public static String osName = System.getProperty("os.name");
	public static String osVersion = System.getProperty("os.version");
	public static String osBit = System.getProperty("os.arch");
	public static int compProc = Runtime.getRuntime().availableProcessors();
	public static String javaVersion = System.getProperty("java.version");
	public static String javaVendor = System.getProperty("java.vendor");
	public static String javaOrgins = System.getProperty("java.home");
	public static String javaURL = System.getProperty("java.vendor.url");
	public static String userName = System.getProperty("user.name");
	public static String userDir = System.getProperty("user.dir");
	public static String userHome = System.getProperty("user.home");
	public static String osThing = System.getProperty("sun.arch.data.model");
	public static String path = System.getProperty("java.class.path");
	public static void main(String[] args) {
		new mainFrame();
	}
	public void setVars() {
	}
	public mainFrame() {
		setVars();
		mainFrame.setSize(400, 400);
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mainFrame.add(showInfo, BorderLayout.SOUTH);
		mainFrame.add(test, BorderLayout.NORTH);
		mainFrame.add(scroll, BorderLayout.CENTER);
		text.setLineWrap(true);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		test.addActionListener(this);
		showInfo.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource().equals(showInfo)) {
			text.setText("");
			//Show message box...
			try {
				text.setText("Operating System: " + osName + "\n");
				text.setText(text.getText() + "\nOperating System Verion: " + osVersion + "\n");
				text.setText(text.getText() + "\nUser Name: " + userName + "\n");
				text.setText(text.getText() + "\nUser Folder Location: " + userHome + "\n");
				text.setText(text.getText() + "\nCurrent Working Directory: " + userDir + "\n");
				text.setText(text.getText() + "\nNumber of Processors: " + compProc + "\n");
				text.setText(text.getText() + "\nSystem Type: " + osThing + "\n");
				text.setText(text.getText() + "\nJava Version: " + javaVersion + "\n");
				text.setText(text.getText() + "\nJava Original Vendor: " + javaVendor + "\n");
				text.setText(text.getText() + "\nJava Website: " + javaURL + "\n");
				text.setText(text.getText() + "\nJava Directory: " + javaOrgins + "\n");
				text.setText(text.getText() + "\nJava Current Usage Path: " + path + "\n");
			} catch(Exception e) {}
		}
		if (event.getSource().equals(test)) {
			int yesNo = JOptionPane.showConfirmDialog(this, "THANKS FOR USING MY PROGRAM! Do you really want to exit?", "Close?", JOptionPane.ERROR_MESSAGE);
			if (yesNo == 0) {
				System.out.println("Shutting Down. . . .");
				System.exit(0);
			}
		}
	}
}