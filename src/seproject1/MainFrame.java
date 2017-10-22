package seproject1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;






import javax.swing.*;
import javax.swing.border.Border;

public class MainFrame {

  public static void main(String[] args) {
	  EventQueue.invokeLater(() -> {
		JFrame jf = new JFrame("��������ͼ");
		jf.setLocation(300, 150);
		jf.setLayout(new GridBagLayout());
	
		final GridBagConstraints gbc = new GridBagConstraints();
		jf.getContentPane().setBackground(Color.white);
		jf.setSize(400, 150);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(3);
	
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.ipadx = 1;
		gbc.ipady = 1;
		JTextArea jArea = new JTextArea();
		jArea.setText("Please input file path:");
		jArea.setEditable(false);
		Font font = new Font("Microsoft Ya Hei", 1, 16);
		jArea.setFont(font);
		jf.add(jArea, gbc);
	
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 4;
		gbc.gridheight = 1;
		gbc.weightx = 4;
		gbc.weighty = 2;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.ipadx = 1;
		gbc.ipady = 1;
		JTextField jtf = new JTextField();
		Font jtfFont = new Font("Microsoft Ya Hei", 0, 16);
		jtf.setFont(jtfFont);
		jf.add(jtf, gbc);
	
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 0, 5, 5);
		gbc.ipadx = 1;
		gbc.ipady = 1;
		JButton jButton = new JButton("Confirm");
		jf.add(jButton, gbc);
	
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 0, 5, 5);
		gbc.ipadx = 1;
		gbc.ipady = 1;
		JButton jButton2 = new JButton("Browse");
		jf.add(jButton2, gbc);
	
		jf.setVisible(true);
	
		JFrame jf2 = new JFrame("ShowImage");
		jf2.setDefaultCloseOperation(3);

		jButton.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			//System.out.println(jtf.getText());
			String filePath = jtf.getText();

			// if(filePath.matches(""))
			Graph graph = Project1.createDirectedGraph(filePath);
			Project1.showDirectedGraph(graph);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO �Զ����ɵ� catch ��
			
			}
			
			new MainFrame(graph);
			jf.dispose();
		}
	});

	JFileChooser jFileChooser = new JFileChooser();
	File dir = new File("C:\\Users\\Snxt\\Desktop");
	jFileChooser.setCurrentDirectory(dir);
	jButton2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int res = jFileChooser.showOpenDialog(null);
			if (res == jFileChooser.APPROVE_OPTION) {
				jtf.setText(jFileChooser.getSelectedFile().toString());
			} else {
				System.out.println("No file selected!");
				}
			}
		});
	});
}

	public MainFrame(Graph graphG) {
		EventQueue.invokeLater(() -> {
			JFrame frame = new JFrame();
			frame.setTitle("ʵ��1");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(950, 1000);
			frame.setResizable(false);
			Image image = new ImageIcon("icons\\testIcon2.png").getImage();
			frame.setIconImage(image);

			frame.setLayout(new BorderLayout());
			ShowImageComponent sic = new ShowImageComponent(graphG);
			Border imgBorder = BorderFactory.createEtchedBorder();
			Border imgTitledBorder = BorderFactory.createTitledBorder(imgBorder, "Directed Graph");
			sic.setBorder(imgTitledBorder);

			final JScrollPane jScrollPane = new JScrollPane(sic);
			Border btnTitled = BorderFactory.createTitledBorder(imgBorder, "Options");
			Border resultTitled = BorderFactory.createTitledBorder(imgBorder, "Result");

			ResultPanel res = new ResultPanel();
			res.setBorder(resultTitled);

			ButtonPanel buttonsPart = new ButtonPanel(res, sic, graphG);
			buttonsPart.setBorder(btnTitled);

			frame.add(res, BorderLayout.SOUTH);
			frame.add(sic, BorderLayout.CENTER);
			frame.add(buttonsPart, BorderLayout.EAST);
			frame.setVisible(true);
			sic.repaint();
		});
	}

}

class ButtonPanel extends JPanel {
	private JButton bt1;
	private JButton bt2;
	private JButton bt3;
	private JButton bt4;
	private JButton bt5;
	private ResultPanel res;
	private ShowImageComponent showImg;
	private boolean pause;

	private int flag = -1;

	private StringMessage message;

	private String[] randomPath;
	
	public ButtonPanel(ResultPanel r, ShowImageComponent s, Graph graphG) {
		bt5 = new JButton("ֹͣ����");
		bt4 = new JButton("�������");
		bt3 = new JButton("���·��");
		bt2 = new JButton("�������ı�");
		bt1 = new JButton("�ŽӴ�");
		bt5.setVisible(false);
		res = r;
		showImg = s;
		message = new StringMessage();
		setLayout(new GridLayout(18, 1));

		bt1.addActionListener(event -> bridgeWordsFrame(graphG));
		bt2.addActionListener(event -> newTextFrame(graphG));
		bt3.addActionListener(event -> shortestWayFrame(graphG));
		bt4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				flag++;
				if(flag==0) {
					randomPath = Project1.randomWalk(graphG).split("[^a-z]+");
					res.setResult(randomPath[0]);
				}
				else {
					String path = randomPath[0];
					for(int i=1;i<=flag;i++) {
						path += " " +randomPath[i];
					}
					res.setResult(path);
				}
				if(flag>=randomPath.length-1) {
					bt4.setText("�������");
					flag=-1;
				} else {
					bt4.setText("��������");
				}
			}
		});
		add(bt5, 8, 0);
		add(bt4, 6, 0);
		add(bt3, 4, 0);
		add(bt2, 2, 0);
		add(bt1, 0, 0);

	}

	public void bridgeWordsFrame(Graph graphG) {
		EventQueue.invokeLater(() -> {
			JFrame jFrame = new JFrame("��������������");
			jFrame.setLayout(new GridLayout(3, 2));
			jFrame.setDefaultCloseOperation(2);
			jFrame.setSize(300, 150);
			jFrame.setResizable(false);
			jFrame.setLocation(600, 200);
			Font font = new Font("Microsoft YaHei", 1, 12);
			JLabel label1 = new JLabel("Word1:");
			JLabel label2 = new JLabel("word2:");
			label1.setFont(font);
			label2.setFont(font);

			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();

			JButton bridgeCancel = new JButton("ȡ��");
			JButton bridgeConfirm = new JButton("ȷ��");

			jFrame.add(bridgeCancel, 2, 0);
			jFrame.add(bridgeConfirm, 2, 1);
			jFrame.add(label2, 1, 0);
			jFrame.add(textField2, 1, 1);
			jFrame.add(label1, 0, 0);
			jFrame.add(textField1, 0, 1);

			bridgeCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					res.setResult("Operation canceled!");
					jFrame.dispose();
				}
			});
			bridgeConfirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					message.msg1 = textField1.getText();
					message.msg2 = textField2.getText();
					res.setResult(Project1.queryBridgeWords(graphG, message.msg1, message.msg2));
					jFrame.dispose();
				}
			});
			jFrame.setVisible(true);
		});
	}

	public void newTextFrame(Graph graphG) {
		EventQueue.invokeLater(() -> {
			Font font = new Font("Microsoft YaHei", 1, 14);
			JFrame newTextFrame = new JFrame("�������ı�");
			newTextFrame.setLayout(new BorderLayout());
			newTextFrame.setSize(670, 230);
			newTextFrame.setDefaultCloseOperation(2);
			newTextFrame.setResizable(false);
			newTextFrame.setLocation(600, 200);
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();

			Border b1 = BorderFactory.createEtchedBorder();

			p2.setBorder(b1);
			p3.setBorder(b1);

			p3.setLayout(new GridLayout(5, 1));

			JTextField mesg = new JTextField();
			mesg.setText("������ԭ�ı�:");
			mesg.setFont(font);
			mesg.setEditable(false);

			JTextArea originalText = new JTextArea(8, 40);
			originalText.setFont(font);
			originalText.setLineWrap(true);

			JButton newTextCancel = new JButton("ȡ��");
			JButton newTextConfirm = new JButton("ȷ��");

			p1.add(mesg);
			p2.add(originalText);
			p3.add(newTextCancel, 1, 0);
			p3.add(newTextConfirm, 0, 0);

			newTextCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					res.setResult("Operation canceled!");
					newTextFrame.dispose();
				}
			});
			newTextConfirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					message.msg1 = originalText.getText();
					res.setResult("New text : " + "\n" + Project1.generateNewText(graphG, message.msg1));
					newTextFrame.dispose();
				}
			});

			newTextFrame.add(p1, BorderLayout.NORTH);
			newTextFrame.add(p2, BorderLayout.CENTER);
			newTextFrame.add(p3, BorderLayout.EAST);
			newTextFrame.setVisible(true);

		});
	}

	public void shortestWayFrame(Graph graphG) {
		EventQueue.invokeLater(() -> {
			JFrame jFrame = new JFrame("�������·��");
			jFrame.setSize(300, 150);
			jFrame.setLayout(new GridLayout(3, 2));
			jFrame.setResizable(false);
			jFrame.setDefaultCloseOperation(2);

			JButton shortestWayCancel = new JButton("ȡ��");
			JButton shortestWayConfirm = new JButton("ȷ��");
			JLabel word1 = new JLabel("Word1 : ");
			JLabel word2 = new JLabel("Word2 : ");
			JTextField t1 = new JTextField();
			JTextField t2 = new JTextField();

			shortestWayCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					res.setResult("Operation canceled!");
					jFrame.dispose();
				}
			});
			shortestWayConfirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					message.msg2 = t1.getText().toLowerCase();
					message.msg1 = t2.getText().toLowerCase();
					String r = Project1.calcShortestPath(graphG, message.msg1, message.msg2);
					res.setResult(r);
					if(!r.equals("word1 or word2 not in graph")) showImg.setImgFilePath("dotGif.gif");
					//showImg.repaint();
					jFrame.dispose();
				}
			});

			jFrame.add(shortestWayCancel, 2, 0);
			jFrame.add(shortestWayConfirm, 2, 1);
			jFrame.add(word1, 1, 0);
			jFrame.add(t1, 1, 1);
			jFrame.add(word2, 0, 0);
			jFrame.add(t2, 0, 1);

			jFrame.setVisible(true);
		});
	}

	public void randomWalkFrame(String[] randomPath, int i) {

	}
}

class ResultPanel extends JPanel {
	private final JTextArea resultText;
	private String res;
	private JLabel label;

	public ResultPanel() {
		resultText = new JTextArea("Everything ready!" + '\n' + "Please choose an option.", 4, 57);
		resultText.setLineWrap(true);
		Font textFont = new Font("Microsoft YaHei", 1, 16);
		resultText.setFont(textFont);
		resultText.setEditable(false);
		label = new JLabel("Result : ");
		// add(label);
		add(resultText);
	}

	public void setResult(String res) {
		resultText.setText(res);
		this.res = res;
	}
	
}

class ShowImageComponent extends JComponent {
	public static final int DISPLAY_X = 0;
	public static final int DISPLAY_Y = 50;

	private static final int DEFAULT_WIDTH = 800;
	private static final int DEFAULT_HEIGHT = 800;

	private Image image;
	private String imageFilePath;

	private JLabel picLabel;

	public ShowImageComponent(Graph graphG) {
		//Project1.showDirectedGraph(graphG);
		image = getToolkit().getImage("dotGif.gif");
		imageFilePath = "dotGif.gif";
		Icon pic = new ImageIcon("dotGif.gif");

		setLayout(new BorderLayout());
		picLabel = new JLabel("");
		picLabel.setIcon(pic);
		// add(picLabel,BorderLayout.CENTER);
		// try {
		// picLabel.setIcon(new ImageIcon(ImageIO.read(imgfile)));
		// } catch (IOException e) {
		// // TODO �Զ����ɵ� catch ��
		// e.printStackTrace();
		// }
		repaint();
	}
	public void repaintImg() {
		image = new ImageIcon(imageFilePath).getImage();
		repaint();
	}
	
	public void paintComponent(Graphics graphG) {
		super.paintComponents(graphG);
		System.out.println("Show Image");
		// Image img = getToolkit().getImage(imageFilePath);
		// Image img = new ImageIcon(imageFilePath).getImage();
		// Image img2 = getToolkit().getImage(imageFilePath);
		// graphG.drawImage(img2, (DEFAULT_WIDTH - img2.getWidth(this)) / 2, (DEFAULT_HEIGHT
		// - img2.getHeight(this)) / 2, this);
		// graphG.dispose();
		// img2 = getToolkit().getImage(imageFilePath);
		// graphG.drawImage(image, (DEFAULT_WIDTH - image.getWidth(this)) / 2,
		// (DEFAULT_HEIGHT - image.getHeight(this)) / 2, DEFAULT_WIDTH ,DEFAULT_HEIGHT,
		// null);
		graphG.drawImage(image, 30, 30, DEFAULT_WIDTH - 10, DEFAULT_HEIGHT - 10, null);
	}

	public void setImgFilePath(String filepath) {
		image.flush();
		imageFilePath = filepath;
		image = new ImageIcon(imageFilePath).getImage();
		repaint();
	}
	
	public void paint(Graphics graphG) {
		image = new ImageIcon(imageFilePath).getImage();
		graphG.drawImage(image, 30, 30, DEFAULT_WIDTH - 10, DEFAULT_HEIGHT - 10, null);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}

class StringMessage {
	public String msg1 = "";
	public String msg2 = "";
}
