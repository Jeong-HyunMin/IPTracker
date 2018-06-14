
//����ȸ�μ���� 2�г� 1�� 16�� ������
//JOptionPane.showMessageDialog - ���â
//status�� �����ϱ�
//table �ȿ� ���� �ֱ�
//toolbar �ϼ��ϱ�

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;

public class IPTracker extends JFrame {

	public IPTracker() {
		super();
		Font myFont = new Font("Consolas", Font.BOLD, 10);
		JPanel p1 = new JPanel();
		String titles[] = new String[] { "IP", "Ping", "Hostname", "TTL", "Ports[0+]" };

		setTitle("IP Tracker");

		setLayout(new BorderLayout());
		setSize(600, 700);

		Object[][] stats = intializeTableData();
		JTable t1 = new JTable(stats, titles);
		JScrollPane sp = new JScrollPane(t1);
		add(sp, BorderLayout.CENTER);

		p1.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(p1, BorderLayout.SOUTH);
		p1.setPreferredSize(new Dimension(getWidth(), 20));
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		JLabel l1 = new JLabel("Ready");
		JLabel l2 = new JLabel("Display : All");
		JLabel l3 = new JLabel("Threads : 0");
		l1.setHorizontalAlignment(SwingConstants.LEFT);
		l2.setHorizontalAlignment(SwingConstants.LEFT);
		l3.setHorizontalAlignment(SwingConstants.LEFT);
		p1.add(l1);
		p1.add(l2);
		p1.add(l3);
		l1.setBorder(new BevelBorder(BevelBorder.RAISED));
		l2.setBorder(new BevelBorder(BevelBorder.RAISED));
		l3.setBorder(new BevelBorder(BevelBorder.RAISED));
		l1.setPreferredSize(new Dimension(300, 20));
		l2.setPreferredSize(new Dimension(150, 20));
		l3.setPreferredSize(new Dimension(150, 20));
		l1.setFont(myFont);
		l2.setFont(myFont);
		l3.setFont(myFont);

		JMenuBar menuBar = new JMenuBar();

		setJMenuBar(menuBar);

		JMenu scan = new JMenu("Scan");
		JMenu goTo = new JMenu("Go to");
		JMenu commands = new JMenu("Commands");
		JMenu favorites = new JMenu("Favorites");
		JMenu tools = new JMenu("Tools");
		JMenu help = new JMenu("Help");
		menuBar.add(scan);
		menuBar.add(goTo);
		menuBar.add(commands);
		menuBar.add(favorites);
		menuBar.add(tools);
		menuBar.add(help);

		// in the scan
		JMenuItem LFF = new JMenuItem("Load From Files");
		JMenuItem EA = new JMenuItem("Export all...");
		JMenuItem ES = new JMenuItem("Export selection...");
		JMenuItem exit = new JMenuItem("Quit");

		// in the goTo
		JMenuItem NAH = new JMenuItem("Next alive host");
		JMenuItem NOP = new JMenuItem("Next open port");
		JMenuItem NDH = new JMenuItem("Next dead host");
		JMenuItem PAH = new JMenuItem("Previous alive host");
		JMenuItem POP = new JMenuItem("Previous open port");
		JMenuItem PDH = new JMenuItem("Previous dead host");
		JMenuItem Find = new JMenuItem("Find");

		// in the commands
		JMenuItem SD = new JMenuItem("Show details");
		JMenuItem ResIP = new JMenuItem("Rescan IP");
		JMenuItem DelIP = new JMenuItem("Delete IP");
		JMenuItem CopyIP = new JMenuItem("Copy IP");
		JMenuItem Copydetails = new JMenuItem("Copy details");
		JMenuItem Open = new JMenuItem("Open");

		// in the favorites
		JMenuItem AC = new JMenuItem("Add current");
		JMenuItem MF = new JMenuItem("Manage favorites...");

		// in the tools
		JMenuItem Preferences = new JMenuItem("Preferences...");
		JMenuItem Fetchers = new JMenuItem("Fetchers...");
		JMenuItem Sel = new JMenuItem("Selection");
		JMenuItem SS = new JMenuItem("Scan statistics");

		// in the help
		JMenuItem GS = new JMenuItem("Getting Started");
		JMenuItem OW = new JMenuItem("Official Website");
		JMenuItem FAQ = new JMenuItem("FAQ");
		JMenuItem RAI = new JMenuItem("Report an issue");
		JMenuItem Plugins = new JMenuItem("Plugins");
		JMenuItem CLU = new JMenuItem("Command-line usage");
		JMenuItem CFNV = new JMenuItem("Check for newer version...");
		JMenuItem about = new JMenuItem("About");

		scan.add(LFF);
		scan.add(EA);
		scan.add(ES);
		scan.addSeparator();
		scan.add(exit);
		goTo.add(NAH);
		goTo.add(NOP);
		goTo.add(NDH);
		goTo.addSeparator();
		goTo.add(PAH);
		goTo.add(POP);
		goTo.add(PDH);
		goTo.addSeparator();
		goTo.add(Find);
		commands.add(SD);
		commands.addSeparator();
		commands.add(ResIP);
		commands.add(DelIP);
		commands.addSeparator();
		commands.add(CopyIP);
		commands.add(Copydetails);
		commands.addSeparator();
		commands.add(Open);
		favorites.add(AC);
		favorites.add(MF);
		favorites.addSeparator();
		tools.add(Preferences);
		tools.add(Fetchers);
		tools.addSeparator();
		tools.add(Sel);
		tools.add(SS);
		help.add(GS);
		help.addSeparator();
		help.add(OW);
		help.add(FAQ);
		help.add(RAI);
		help.add(Plugins);
		help.addSeparator();
		help.add(CLU);
		help.addSeparator();
		help.add(CFNV);
		help.addSeparator();
		help.add(about);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		about.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Made By ����ȸ�μ���� 20116 ������", "About",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		OW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "�������� ���� ������Ʈ�� �������� �ʽ��ϴ�.\n�������غ�Ź�帳�ϴ�.",
						"Official WebSite - 404 Not Found", JOptionPane.WARNING_MESSAGE);
			}
		});

		FAQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "���α׷��� ���õ� ���Ǵ� �Ʒ� E-mail �ּҷ� ���ֽñ� �ٶ��ϴ�.\n :   gusals0928@naver.com",
						"FAQ", JOptionPane.QUESTION_MESSAGE);
			}
		});

		RAI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"���α׷� ����� �߻��� ���״� �Ʒ� E-mail �ּҷ� ����Ʈ ���ֽñ� �ٶ��ϴ�.\n���� ����Ʈ�� ������ ������ �߻����� / �߻��� ���׿� ���� ���� / ���õ� ��ũ������ ��Ÿ ������� ���� �ۼ��ؼ� �����ֽʽÿ�.\n :   gusals0928@naver.com",
						"Bug-Report", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		CFNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"���ο� ������ �������� Github���� Ȯ���Ͻʽÿ�.\n :   https://github.com/Jeong-HyunMin/IPTracker.git",
						"Newer Version", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JToolBar tb1 = new JToolBar();
		tb1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JToolBar tb2 = new JToolBar();
		tb2.setLayout(new FlowLayout(FlowLayout.LEFT));

		JLabel lbRangStart = new JLabel("IP Range : ");
		JTextField tfRangStart = new JTextField(10);
		JLabel lbRangend = new JLabel("to ");
		JTextField tfRangend = new JTextField(10);

		tfRangStart.setPreferredSize(new Dimension(90, 20));
		tfRangend.setPreferredSize(new Dimension(90, 20));

		JLabel lbhostname = new JLabel("Hostname : ");
		JTextField tfhostname = new JTextField(10);

		JButton btup = new JButton("IP");
		JComboBox cbOption = new JComboBox();
		cbOption.addItem("/24");
		cbOption.addItem("/26");
		JButton btStart = new JButton("Start ");

		tfhostname.setPreferredSize(new Dimension(90, 20));
		btup.setPreferredSize(new Dimension(90, 20));
		cbOption.setPreferredSize(new Dimension(90, 20));
		btStart.setPreferredSize(new Dimension(90, 20));

		tb1.add(lbRangStart);
		tb1.add(tfRangStart);
		tb1.add(lbRangend);
		tb1.add(tfRangend);

		tb2.add(lbhostname);
		tb2.add(tfhostname);
		tb2.add(btup);
		tb2.add(cbOption);
		tb2.add(btStart);

		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(tb1, BorderLayout.NORTH);
		p2.add(tb2, BorderLayout.SOUTH);

		add(p2, BorderLayout.NORTH);

		String myIP = null;
		String myHostname = null;
		try {
			InetAddress ia = InetAddress.getLocalHost();

			myIP = ia.getHostAddress();
			myHostname = ia.getHostName();
		} catch (Exception e) {
			
		}
		String fixedIP = myIP.substring(0, myIP.lastIndexOf("."));
		tfRangStart.setText(fixedIP + ".1");
		tfRangend.setText(fixedIP + ".255");
		tfhostname.setText(myHostname);
		System.out.println(myIP + "          " + myHostname);
		
		btStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 1; i < 256; i++) {

					try {
						String pingCmd = "ping -a " + fixedIP + i;
						Runtime r = Runtime.getRuntime();
						Process p = r.exec(pingCmd);

						BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
						String inputLine;
						while ((inputLine = in.readLine()) != null) {
							Pattern pattern = Pattern.compile("Ping\\s+(.+)\\s\\[", Pattern.CASE_INSENSITIVE);
							Pattern pattern2 = Pattern.compile("(\\d+ms)(\\\\s+)TTL=(\\d+)");
							Matcher matcher2 = pattern2.matcher(inputLine);
							Matcher matcher = pattern.matcher(inputLine);

							if (matcher.find()) {
								stats[i][3] = matcher.group(1);
							}
							if (matcher2.find()) {
								stats[i][1] = matcher2.group(1);
								stats[i][2] = matcher2.group(2);
							}
							System.out.println("��������, " + i +"��°");
						}

						stats[i][0] = fixedIP + i;
						
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
		});
		
	}

	public Object[][] intializeTableData() {
		Object[][] results = new Object[254][5];
		return results;
	}

	public static void main(String[] args) {
		new IPTracker();
	}


}

