//전자회로설계과 2학년 1반 16번 정현민
//JOptionPane.showMessageDialog - 경고창
//status바 수정하기
//table 안에 내용 넣기
//toolbar 완성하기

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.*;

public class IPTracker extends JFrame{
	
	String getPort;
	
	public IPTracker() {
		super();
		Font myFont = new Font("Consolas", Font.BOLD, 10);
		JPanel p1 = new JPanel();
		String titles[] = new String[] { "IP", "Hostname", "Ping", "TTL", "Ports[0+]" };

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
				JOptionPane.showMessageDialog(null, "Made By 전자회로설계과 20116 정현민", "About",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		OW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "제작자의 공식 웹사이트는 제공되지 않습니다.\n많은양해부탁드립니다.",
						"Official WebSite - 404 Not Found", JOptionPane.WARNING_MESSAGE);
			}
		});

		FAQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "프로그램에 관련된 문의는 아래 E-mail 주소로 해주시길 바랍니다.\n :   gusals0928@naver.com",
						"FAQ", JOptionPane.QUESTION_MESSAGE);
			}
		});

		RAI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"프로그램 사용중 발생한 버그는 아래 E-mail 주소로 리포트 해주시길 바랍니다.\n버그 리포트를 보내실 때에는 발생일자 / 발생한 버그에 대한 설명 / 관련된 스크린샷과 기타 참고사항 등을 작성해서 보내주십시오.\n :   gusals0928@naver.com",
						"Bug-Report", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		CFNV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"새로운 버전은 제작자의 Github에서 확인하십시오.\n :   https://github.com/Jeong-HyunMin/IPTracker.git",
						"Newer Version", JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JToolBar tb1 = new JToolBar();
		tb1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JToolBar tb2 = new JToolBar();
		tb2.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel p2 = new JPanel(new BorderLayout());
		p2.add(tb1, BorderLayout.NORTH);
		p2.add(tb2, BorderLayout.SOUTH);

		add(p2, BorderLayout.NORTH);

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
		tfRangend.setText(fixedIP + ".254");
		tfhostname.setText(myHostname);
		System.out.println("IP : " + myIP + "  /  Hostname : " + myHostname);
		
		//수정 필요 - Thread로 구현해야할듯
		btStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "잠시만 기다리십시오...", "IP스캔중",
						JOptionPane.INFORMATION_MESSAGE);
				for (int i = 1; i < 255; i++) {
						stats [i-1][0] = fixedIP + "." + i;
				}
				t1.updateUI();
				thread[] pi = new thread[255];
				for(int i=1; i<=254; i++) {
					pi[i-1] = new thread(fixedIP + (i));
					pi[i-1].start();
				}
				for(int i=1; i<=254; i++) {
					Object[] msg = pi[i-1].getMsg();
					if (msg[1] != null) {
						//port SCanner
						
						ExecutorService es = Executors.newFixedThreadPool(20);
						String ip = "127.0.0.1";
						int timeout = 200;
						ArrayList<Future<portScanner>> futures = new ArrayList<>();
						for (int port = 1; port <= 1024; port++) {
							futures.add(portlsOpen(es, ip, port, timeout));
						}
						try {
							es.awaitTermination(200L, TimeUnit.MILLISECONDS);
							int openPorts = 0;
							for (final Future<portScanner> f: futures) {
								if (f.get().isOpen()) {
									openPorts++;
									getPort = Integer.toString(f.get().getPort());
									break;
								}
							}
						} catch(Exception ee) {
							ee.printStackTrace();
							JOptionPane.showMessageDialog(null, "예기치 못한 에러가 발생하였습니다.\n프로그램을 재실행하시길 바랍니다.)",
									"Unexpected Error",JOptionPane.WARNING_MESSAGE);
						}

						stats[i-1][4] = getPort;
					}
		
					if (msg[1] == null) {
						msg[3] = "[n/a]";
						msg[1] = "[n/s]";
						msg[2] = "[n/s]";
						stats[i-1][4] = "[n/s]";
						//t1.getColumnModel().getColumn(i).setCellRenderer();
					} else if (msg[1] == null) {
						msg[1] = "[n/a]";
					}
					//stats[i][0] = msg[0];
					stats[i-1][1] = msg[3];
					stats[i-1][2] = msg[1];
					stats[i-1][3] = msg[2];
				}
				t1.repaint();
				JOptionPane.showMessageDialog(null, "검색이 끝났습니다!", "IP찾기 성공",
						JOptionPane.INFORMATION_MESSAGE);
				
				/*for (int i = 1; i < 256; i++) {
					try{
						stats [i-1][0] = fixedIP + "." + i;
						BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
						String inputLine;
						while ((inputLine = in.readLine()) != null) {
							Pattern pattern = Pattern.compile("Ping\\s+(.+)\\s\\[", Pattern.CASE_INSENSITIVE);
							Pattern pattern2 = Pattern.compile("(\\d+ms)(\\\\s+)TTL=(\\d+)");
							Matcher matcher2 = pattern2.matcher(inputLine);
							Matcher matcher = pattern.matcher(inputLine);

							if (matcher.find()) {
								stats[i-1][3] = matcher.group(1);
							}
							if (matcher2.find()) {
								stats[i-1][1] = matcher2.group(1);
								stats[i-1][2] = matcher2.group(2);
							}
							System.out.println("실행중임, " + i +"번째");
							t1.updateUI();
						//}
						
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "예기치 못한 에러가 발생하였습니다.\n프로그램을 재실행하시길 바랍니다.)",
								"Unexpected Error",JOptionPane.WARNING_MESSAGE);
					}
			}*/
				t1.updateUI();
		}
		});
		
	}
	
	public static Future<portScanner> portlsOpen(final ExecutorService es, final String ip, final int port, final int timeout){
		return es.submit(new Callable<portScanner>() {
			public portScanner call() {
				try {
					Socket socket = new Socket();
					socket.connect(new InetSocketAddress(ip, port), timeout);
					socket.close();
					return new portScanner(port, true);
				}catch (Exception ex) {
					return new portScanner(port, false);
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

class thread extends Thread {
	private Object[] msg;
	private String ip;

	public thread (String ip) {
		this.ip = ip;
		msg = new Object[4];
	}

	public void run() {
		InputStream is = null;
		BufferedReader br = null;
		try {
			Runtime run = Runtime.getRuntime();
			Process p = run.exec("ping -a " + ip);
			msg[0] = ip;
			is = p.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				// System.out.println(line); // line을 임시 출력해본다.
				if (line.indexOf("[") >= 0) {
					msg[3] = line.substring(5, line.indexOf("["));
				}
				if (line.indexOf("ms") >= 0) {
					// Pattern pattern =
					// Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)",Pattern.CASE_INSENSTIVE);
					// Matcher matcher = pattern.matcher(line);
					// System.out.println(matcher.group(1));
					msg[1] = line.substring(line.indexOf("ms") - 1, line.indexOf("ms") + 2);
					msg[2] = line.substring(line.indexOf("TTL=") + 4, line.length());
					break;
				}
				if (line != null)

				{
					
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// if
	
	public Object[] getMsg() {
		try {
			join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return msg;
	}
	
	public static void main(String[] args) {
		
		
		
	}
}


class portScanner extends Thread {
	private int port;
	
	private boolean isOpen;
	
	public portScanner(int port, boolean isOpen) {
		super();
		this.port = port;
		this.isOpen = isOpen;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public boolean isOpen() {
		return isOpen;
	}
	
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
}


