import java.sql.*;
import javax.swing.JOptionPane;

public class Database {
    Connection con = null;
    Statement stmt = null;
    String url = "jdbc:mysql://127.0.0.1/dragonbrain?serverTimezone=UTC&&useSSL=false&user=root&password= 1234";

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(this.url);
            this.stmt = this.con.createStatement();
            System.out.println("[Server] MySQL 서버 연동 성공");


        } catch (SQLException e) {
            System.out.println("[Server] 1MySQL 서버 연동 실패> ");
        } catch (ClassNotFoundException e) {
            System.out.println("[Server] 2MySQL 서버 연동 실패> ");
        }
    }

    public int get_id(String email) {
        try {
            //SELECT COUNT(name) as cnt FROM hero_collection;
            //System.out.println(email);
            String commandStr = "SELECT * from user where user_email= '" + email + "'";
            //  String checkingStr = "SELECT (*)count ), user_nickname FROM user WHERE user_email='" + id + "'";
            ResultSet result = this.stmt.executeQuery(commandStr);

            int user_id = 0;

            while (result.next()) {
                user_id = result.getInt("user_id");
                //System.out.println(user_id);

            }
            return user_id;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String loginCheck(String _i, String _p) {
        String nickname = "null";
        String id = _i;
        String pw = _p;

        try {
            String checkingStr = "SELECT user_password, user_nickname FROM user WHERE user_email='" + id + "'";
            ResultSet result = this.stmt.executeQuery(checkingStr);

            for (int var8 = 0; result.next(); ++var8) {
                if (pw.equals(result.getString("user_password"))) {
                    nickname = result.getString("user_nickname");
                    System.out.println("[Server] 로그인 성공");
                } else {
                    nickname = "null";
                    System.out.println("[Server] 로그인 실패");
                }
            }
        } catch (Exception var9) {
            nickname = "null";
            System.out.println("[Server] 로그인 실패 > " + var9.toString());
        }

        return nickname;
    }

    public boolean joinCheck(String _n, String _nn, String _p, String _e, String birth) {
        boolean flag = false;
        String na = _n;
        String nn = _nn;
        int id = -1;
        String pw = _p;
        String em = _e;
        String bd = birth;
        boolean check = this.overCheck(_nn, _e);
        System.out.println(check);
        if (check == true) {
            try {
                //id 형성 부분 바꿔야함
                id = (int) (Math.random() * 10000);
                String insertStr = "INSERT INTO user VALUES(" + id + ",'" + na + "'," + "'" + em + "'," + "'" + pw + "'," + "sysdate()," + "'" + nn + "'," + "'" + bd + "')";
                this.stmt.executeUpdate(insertStr);
                flag = true;
                System.out.println("[Server] 회원가입 성공");
            } catch (Exception var14) {
                flag = false;
                System.out.println("[Server] 회원가입 실패 > " + var14.toString());
            }
        } else {
            System.out.println("닉네임 또는 이메일이 중복되었습니다");
        }

        return flag;
    }

    public boolean modiCheck(int user_id, String _nn, String _p, String _e) {
        boolean flag = false;
        String nn = _nn;
        int id = -1;
        int ui = user_id;
        String pw = _p;
        String em = _e;
        boolean check = this.overCheck(_nn, _e);
        System.out.println(check);
        if (check == true) {
            try {
                //id 형성 부분 바꿔야함
                id = (int) (Math.random() * 10000);
                String insertStr = "update user set user_nickname = '" + nn + "', " + " user_password = '" + pw + "'," + "user_email = '" + em + "' where user_id = " + ui + ";";
                this.stmt.executeUpdate(insertStr);
                flag = true;
                System.out.println("[Server] 정보변경 성공");
            } catch (Exception var14) {
                flag = false;
                System.out.println("[Server] 정보변경 실패 > " + var14.toString());
            }
        } else {
            System.out.println("닉네임 또는 이메일이 중복되어 정보변경에 실패하였습니다");
        }

        return flag;
    }




    boolean overCheck(String _a, String _v) { //닉네임과 이메일로 중복체크
        boolean flag = true;
        String att = _a; //닉네임
        String val = _v; //이메일

        try {
            String selcectStr = "SELECT * FROM user WHERE user_nickname= '" + att + "' or user_email= '" + val + "'";

            ResultSet result = this.stmt.executeQuery(selcectStr);

            //System.out.println(selcectStr);
            for (int var8 = 0; result.next(); ++var8) {
                if (att.equals(result.getString("user_nickname")) == true || val.equals(result.getString("user_email")) == true) {
                    flag = false;
                } else {
                    flag = true;
                }
                //System.out.println(flag);
            }

            System.out.println("[Server] 중복 확인 성공");
        } catch (Exception var9) {
            System.out.println("[Server] 중복 확인 실패 > " + var9.toString());
        }

        return flag;
    }

    public void follow(String email, int id) //email =  팔로우하려고 하는 사람의 이메일,  id =  팔로우 주체
    {
        try {
            int abcd = id;
            String str = "SELECT user_id FROM user WHERE user_email= '" + email + "' ";
            ResultSet result = this.stmt.executeQuery(str);
            int ab = 0 ;
            while(result.next())
            {
                ab = result.getInt(1);
            }

            String insertStr = "insert into follow values (" + ab + "," + abcd + ")";
            this.stmt.executeUpdate(insertStr);

            System.out.println("팔로우 완료");

        }catch (Exception var14) {;
            System.out.println("[Server] 팔로우하기 실패 "); //+ var14.toString()
            JOptionPane.showMessageDialog(null, "이미 팔로우 하셨습니다.", "알림", 1);
        }
    }


    public void follow_cancel(String email, int id) //email =  팔로우하려고 하는 사람의 이메일,  id =  팔로우 주체
    {
        try {
            String str = "SELECT * FROM user WHERE user_email= '" + email + "' ";
            ResultSet result = this.stmt.executeQuery(str);
            int ab = 0 ;
            while(result.next())
            {
                ab = result.getInt("user_id");
            }
            System.out.println(ab);
            System.out.println(id);
            String insertStr = "delete from follow where follow_user_id = " + ab + " and follow_following_id = " + id;
            this.stmt.executeUpdate(insertStr);

            System.out.println("팔로우 취소 완료");

        }catch (Exception var14) {;
            System.out.println(var14); //+ var14.toString()
            JOptionPane.showMessageDialog(null, "이미 팔로우 취소 하셨습니다.", "알림", 1);
        }
    }



    public int get_following_num(int user_id) {
        try {
            //SELECT COUNT(name) as cnt FROM hero_collection;
            //유저 아이디가 user_id인 사람의 팔로잉 수
            String commandStr = "SELECT COUNT(follow_user_id) from follow WHERE follow_user_id=" + user_id;
            //  String checkingStr = "SELECT (*)count ), user_nickname FROM user WHERE user_email='" + id + "'";
            ResultSet result = this.stmt.executeQuery(commandStr);

            int count = 0;
            while (result.next()) {
                count = result.getInt("COUNT(follow_user_id)");
            }
            return count;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int get_follower_num(int user_id) {
        int cnt;
        try {
            //SELECT COUNT(name) as cnt FROM hero_collection;
            //유저 아이디가 user_id인 사람의 팔로워 수
            String commandStr = "SELECT COUNT(follow_following_id) from follow WHERE follow_user_id=" + user_id;
            //  String checkingStr = "SELECT (*)count ), user_nickname FROM user WHERE user_email='" + id + "'";
            ResultSet result = this.stmt.executeQuery(commandStr);

            int count = 0;
            while (result.next()) {
                count = result.getInt("COUNT(follow_following_id)");
            }
            return count;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void do_follow(int user_id) {
        try {
            //SELECT COUNT(name) as cnt FROM hero_collection;
            //유저 아이디가 user_id인 사람의 팔로워 수
            String commandStr = "SELECT COUNT(follow_following_id) from follow ";
            //String commandStr = "insert into follow values ()" +user_id + 팔로잉 할 사람의 아이디 ;
            //  String checkingStr = "SELECT (*)count ), user_nickname FROM user WHERE user_email='" + id + "'";
            ResultSet result = this.stmt.executeQuery(commandStr);

            int count = 0;
            while (result.next()) {
                count = result.getInt("COUNT(follow_following_id)");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] get_List() {
        try {
            //SELECT COUNT(name) as cnt FROM hero_collection;
            //유저 아이디가 user_id인 사람의 팔로워 수
            String commandStr = "SELECT user_email from user ";
            //String commandStr = "insert into follow values ()" +user_id + 팔로잉 할 사람의 아이디 ;
            //  String checkingStr = "SELECT (*)count ), user_nickname FROM user WHERE user_email='" + id + "'";
            ResultSet result = this.stmt.executeQuery(commandStr);
            String[] abc = new String[100];
            int i =0;

            while (result.next()) {
                abc[i] = result.getString("user_email");
                i++;
            }

            return abc;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getNumber(int id)
    {
        int count =0;
        try {
            String commandStr = "SELECT * from follow where follow_following_id = " + id;
            ResultSet result = this.stmt.executeQuery(commandStr);
            while(result.next())
            {
                count++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }


    public int getNumbers(int id)
    {
        int count =0;
        try {
            String commandStr = "SELECT * from follow where follow_user_id = " + id;
            ResultSet result = this.stmt.executeQuery(commandStr);
            while(result.next())
            {
                count++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public void go_to_page(String email, int id){
        try {
            int abcd = id;
            String str = "SELECT user_id FROM user WHERE user_email= '" + email + "' ";
            ResultSet result = this.stmt.executeQuery(str);
            int ab = 0 ;
            while(result.next())
            {
                ab = result.getInt(1);
            }

            //해당 Id의 페이지로 가기
            //new OtherPage(email, id);

        }catch (Exception var14) {}
    }


    public void uploadText(int user_id, String upload) {
        try {
            int abcd = user_id;
/*
*             String str = "SELECT user_id FROM user WHERE user_email= '" + email + "' ";
            ResultSet result = this.stmt.executeQuery(str);
            int ab = 0;
            while (result.next()) {
                ab = result.getInt(1);
            }
* */
            int post_id = (int) (Math.random() * 10000);
            // foreign key 수정필요 post_user_id -> user_id
            String insertStr = "insert into post values (" + post_id + "," + user_id +","+ "'"+upload +"'"+ ")";
            this.stmt.executeUpdate(insertStr);

            System.out.println("업로드 완료");

        } catch (Exception var14) {
            ;
            System.out.println(var14);
            System.out.println("[Server] 게시물 업로드 실패 "); //+ var14.toString()
            JOptionPane.showMessageDialog(null, "게시물 업로드 완료!", "알림", 1);
        }
    }



    //내가 팔로우한 사람들의 id를 가져와준다. ->server
    public ResultSet get_follower(int id)
    {
        ResultSet res = null;
        try {
            String commandStr = "SELECT * from follow where follow_following_id = " + id;
            res = this.stmt.executeQuery(commandStr);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

    //나를 팔로우한 사람들의 id가져오기 ->server
    public ResultSet get_follow(int id)
    {
        ResultSet res = null;
        try {
            String commandStr = "SELECT * from follow where follow_user_id = " + id;
            res = this.stmt.executeQuery(commandStr);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }



    //get_follower 함수를 통해 얻어온 id를 이용해서 nickName을 가져온다.
    public String[] get_NickName(ResultSet rs)
    {

        ResultSet res = null;
        String[] str = new String[100];
        int cc = 0;
        try {
            int[]abc = new int[100] ;
            int count = 0;
            while(rs.next())
            {
                abc[count] = rs.getInt("follow_user_id");
                System.out.println(abc[count]);
                count++;
            }
            for(int k =0; k<count; k++) {

                String commandStr = "SELECT * from user where user_id = " + abc[k];
                res = this.stmt.executeQuery(commandStr);
                while (res.next()) {
                    str[k] = res.getString("user_nickname");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return str;
    }


    //get_follow함수를 통해 얻은 id로 nickname가져오기.
    public String[] get_NickNames(ResultSet rs)
    {

        ResultSet res = null;
        String[] str = new String[100];
        int cc = 0;
        try {
            int[]abc = new int[100] ;
            int count = 0;
            while(rs.next())
            {
                abc[count] = rs.getInt("follow_following_id");
                System.out.println(abc[count]);
                count++;
            }
            for(int k =0; k<count; k++) {

                String commandStr = "SELECT * from user where user_id = " + abc[k];
                res = this.stmt.executeQuery(commandStr);
                while (res.next()) {
                    str[k] = res.getString("user_nickname");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    public String[] get_Post(int user_id) {
        try {
            String commandStr = "SELECT post_content from post WHERE post_user_id=" + user_id;
            //  String checkingStr = "SELECT (*)count ), user_nickname FROM user WHERE user_email='" + id + "'";
            ResultSet result = this.stmt.executeQuery(commandStr);
            String[] abc = new String[100];
            int i = 0;

            while (result.next()) {
                abc[i] = result.getString("post_content");
                System.out.println(abc[i]);
                i++;
            }
            return abc;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String[] get_Post(String email) {
        try {
            String commandStr = "SELECT post_content from post WHERE post_user_id=" + email;
            //  String checkingStr = "SELECT (*)count ), user_nickname FROM user WHERE user_email='" + id + "'";
            ResultSet result = this.stmt.executeQuery(commandStr);
            String[] abc = new String[100];
            int i = 0;

            while (result.next()) {
                abc[i] = result.getString("post_content");
                System.out.println(abc[i]);
                i++;
            }
            return abc;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] get_main_Post(int user_id) {
        try {
            //  String commandStr = "SELECT post_content from post WHERE post_user_id!=" + user_id;
            //String commandStr = "select post_content from post where post_user_id not in("+user_id+")";
            String commandStr = "SELECT post_content, post_user_id FROM post WHERE post_user_id NOT IN ("+user_id +")";
            //  String checkingStr = "SELECT (*)count ), user_nickname FROM user WHERE user_email='" + id + "'";
            ResultSet result = this.stmt.executeQuery(commandStr);
            String[] abc = new String[1000];
            int i = 0;

            while (result.next()) {
                abc[i] = result.getString("post_content");
                //abc[i][1] = result.getString("post_user_id");
                // System.out.println(abc[i]);
                i++;
            }
            return abc;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}