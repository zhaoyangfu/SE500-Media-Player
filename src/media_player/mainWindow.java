package media_player;
  

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import VideoPlayer.Main.PlayerMain;  
//import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;  
//import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;  

public class mainWindow extends JFrame {

	private JPanel contentPane; // ������������������ҳ�������
	private JMenuBar menuBar; // �˵���
	private JMenu mnFile, mnSetting, mnHelp; // �ļ��˵�
	private JMenuItem mnOpenVideo, mnExit; // �ļ��˵���Ŀ¼�����������˳�
	private JPanel panel; // ������������
	private JProgressBar progress; // ������
	private JPanel progressPanel; // ����������
	private JPanel controlPanel; // ���ư�ť����
	private JButton btnStop, btnPlay, btnPause; // ���ư�ť��ֹͣ�����š���ͣ
	private JSlider slider; // �������ƿ�

	public static void main(String[] args) {

	}

	// MainWindow���췽���������������ŵ�������
	public mainWindow() {
		setTitle("   VideoPlayer   Copyright@2015  by �Ͽ�һ��");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/* ��Ƶ���Ŵ����еĲ˵��� */
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("�ļ�"); // ���ò˵���
		menuBar.add(mnFile);
		mnSetting = new JMenu("����");
		menuBar.add(mnSetting);
		mnHelp = new JMenu("����");
		menuBar.add(mnHelp);

		mnOpenVideo = new JMenuItem("���ļ�"); // �����ļ��˵���Ŀ¼���ļ�
		mnFile.add(mnOpenVideo);

		mnExit = new JMenuItem("�˳�"); // �����ļ��˵���Ŀ¼�˳�
		mnFile.add(mnExit);

		// ���ļ�
		mnOpenVideo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});

		// �˳�
		mnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});

		/* ���������в��Ž��沿�� */
		JPanel videoPane = new JPanel();
		contentPane.add(videoPane, BorderLayout.CENTER);
		videoPane.setLayout(new BorderLayout(0, 0));
		;

		/* ���������п��Ʋ��� */

		panel = new JPanel(); // ʵ����������������
		videoPane.add(panel, BorderLayout.SOUTH);

		progressPanel = new JPanel(); // ʵ��������������
		panel.add(progressPanel, BorderLayout.NORTH);

		// ��ӽ�����
		progress = new JProgressBar();
		progressPanel.add(progress);
		// panel.add(progress,BorderLayout.NORTH);
		progress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // ��������������������Ž���
				int x = e.getX();
			}
		});
		progress.setStringPainted(true);

		controlPanel = new JPanel(); // ʵ�������ư�ť����
		panel.add(controlPanel, BorderLayout.SOUTH);

		// ���ֹͣ��ť
		btnStop = new JButton("ֹͣ");
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		controlPanel.add(btnStop);

		// ��Ӳ��Ű�ť
		btnPlay = new JButton("����");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		controlPanel.add(btnPlay);

		// �����ͣ��ť
		btnPause = new JButton("��ͣ");
		btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		controlPanel.add(btnPause);

		// ����������ƿ�
		slider = new JSlider();
		slider.setValue(80);
		slider.setMaximum(100);
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub

			}
		});
		controlPanel.add(slider);
	}

	// ��ȡ����ý��ʵ����ĳ����Ƶ��
	// public EmbeddedMediaPlayer getMediaPlayer() {
	// return playerComponent.getMediaPlayer();
	// }

	// ��ȡ������ʵ��
	public JProgressBar getProgressBar() {
		return progress;
	}

}  
