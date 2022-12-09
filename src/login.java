import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame{
    private JPanel loginPanel = new JPanel(new GridLayout(3, 2));
    private JLabel idLabel = new JLabel("아이디(이메일) ");
    private JLabel pwLabel = new JLabel("비밀번호 ");
    private JTextField idText = new JTextField();
    private TextField pwText = new TextField();
    private JButton loginBtn = new JButton("로그인");
    private JButton joinBtn = new JButton("회원가입");
    //private JButton joinBtn = new JButton("회원가입");

    Font f = new Font("나눔스퀘어_ac", Font.BOLD, 14);
    public login(){
        super("Login");
        //#FAEB70
        this.setContentPane(loginPanel);
        idLabel.setFont(f);
        pwLabel.setFont(f);
        joinBtn.setFont(f);
        loginBtn.setFont(f);
        loginPanel.setBackground(Color.yellow);
        pwText.setEchoChar('*');
        loginPanel.add(idLabel);
        loginPanel.add(pwLabel);
        loginPanel.add(idText);
        loginPanel.add(pwText);
        loginPanel.add(joinBtn);
        loginPanel.add(loginBtn);

        idLabel.setHorizontalAlignment(NORMAL);
        pwLabel.setHorizontalAlignment(NORMAL);

        setSize(350,150);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //로그인 UI->로그인 or 회원가입
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //로그인 버튼 액션 -> 메인 페이지
                String id = idText.getText().trim();
                String pw = pwText.getText().trim();

                System.out.println(id + " " + pw);


                if(id.length()==0 || pw.length() == 0) {
                    JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력 하셔야 됩니다.", "아이디나 비번을 입력!", JOptionPane.DEFAULT_OPTION);
                    return;
                }
            //    new server(id, pw);
                setVisible(false);
            }
        });

        joinBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //회원가입 버튼 액션 -> 회원가입 -> 로그인
                //new server(0);
                new joinUI();
                setVisible(false);
            }
        });

        //joinBtn.addActionListener(this);
    }

    public static void main(String[] args){
        new Main();

    }
}
