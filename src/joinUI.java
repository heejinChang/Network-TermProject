import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.event.*;

public class joinUI extends JFrame{

    public boolean confirm = false;
    public JTextField birthText;
    public JTextField pwText;
    public JTextField NameText;
    public JTextField NickNameText;
    public JTextField EmailText;
    public JButton signUpBtn, cancelBtn;
    private JTextField textField;

    public joinUI() {
        initialize();
    }
    private void initialize() {
        setBounds(100, 100, 340, 322);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setBounds(12, 10, 300, 245);
        getContentPane().add(panel);
        panel.setLayout(null);

        //회원가입UI "이름"
        JLabel lblNewLabel = new JLabel("이름");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(60, 38, 57, 15);
        panel.add(lblNewLabel);
        //회원가입UI "닉네임"
        JLabel lblNewLabel_1 = new JLabel("닉네임");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(60, 63, 57, 15);
        panel.add(lblNewLabel_1);
        //이름 입력칸
        NameText = new JTextField();
        NameText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                }
            }
        });
        NameText.setBounds(129, 35, 116, 21);
        panel.add(NameText);
        NameText.setColumns(10);
        //닉네임 입력칸
        NickNameText = new JTextField();
        NickNameText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    msgSummit();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                }
            }
        });
        NickNameText.setBounds(129, 60, 116, 21);
        panel.add(NickNameText);
        NickNameText.setColumns(10);
        //회원가입UI "가입" 버튼
        signUpBtn = new JButton("\uAC00\uC785");   //가입
        signUpBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        msgSummit();
                    }
                }).start();
                dispose();
            }
        });
        signUpBtn.setBounds(44, 179, 97, 23);
        panel.add(signUpBtn);
        //회원가입UI "취소" 버튼
        cancelBtn = new JButton("\uCDE8\uC18C");   //취소
        cancelBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
        });
        cancelBtn.setBounds(148, 179, 97, 23);
        cancelBtn.addActionListener(this::actionPerformed);
        panel.add(cancelBtn);
        //회원가입UI "생년월일"
        JLabel lblNewLabel_2 = new JLabel("생년월일");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(60, 87, 57, 15);
        panel.add(lblNewLabel_2);
        //생년월일 입력칸
        birthText = new JTextField();
        birthText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    msgSummit();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                }
            }
        });
        birthText.setColumns(10);
        birthText.setBounds(129, 84, 116, 21);
        panel.add(birthText);
        //회원가입UI "아이디(이메일)"
        JLabel lblNewLabel_2_2 = new JLabel("아이디(이메일)");
        lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2.setBounds(60, 137, 57, 15);
        panel.add(lblNewLabel_2_2);
        //이메일 입력칸
        EmailText = new JTextField();
        EmailText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    msgSummit();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                }
            }
        });
        EmailText.setColumns(10);
        EmailText.setBounds(129, 134, 116, 21);
        panel.add(EmailText);
        //회원가입UI "비밀번호"
        JLabel lblNewLabel_2_1 = new JLabel("비밀번호");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setBounds(60, 112, 57, 15);
        panel.add(lblNewLabel_2_1);
        //비밀번호 입력칸
        pwText = new JTextField();
        pwText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    msgSummit();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setVisible(false);
                }
            }
        });
        pwText.setColumns(10);
        pwText.setBounds(129, 109, 116, 21);
        panel.add(pwText);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == cancelBtn) {
        //    new Main();
            // 서버 연결 & 창 메인 창 가져오기

            setVisible(false);
        }

    }


    private void msgSummit() {// 소켓생성
      //  new server(NameText.getText(),  NickNameText.getText() ,  pwText.getText() , EmailText.getText(), birthText.getText());
    }

}