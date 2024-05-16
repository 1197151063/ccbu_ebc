package net.lab1024.smartadmin;

//import sun.misc.BASE64Decoder;
import java.util.Base64;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class ldap {
    public static void main(String[] args) {
//        File file = new File("E:\\history.txt");
        File file = new File("E:\\history.txt");
        BufferedReader reader = null;
        BufferedWriter out  = null;
        BufferedWriter out1  = null;
        StringBuffer sbf = new StringBuffer();
        int uidNumber = 32592;
        Map<String,String> userMap = new HashMap<String,String>();
//        String sql = "INSERT INTO `jc_sys_user_ext` VALUES ('id', '1', '管理员', '3', null, null, null, null, '', '', '', '', '2019-04-15 14:46:47', '2019-11-11 09:03:20', 'admin', 'system', '0');";
        String info = "dn: uid=zhongjianan,ou=People,dc=jic,dc=com\n" +
                "objectClass: posixAccount\n" +
                "objectClass: top\n" +
                "objectClass: inetOrgPerson\n" +
                "gidNumber: 0\n" +
                "uid: zhongjianan\n" +
                "homeDirectory: jic\n" +
                "mail: zhongjianan@jic.cn\n" +
                "cn: zhongjianan\n" +
//                "uidNumber: 32592\n" +
                "sn: zhongjianan\n" +
                "givenName: 名字\n";
        try {
            out  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\new_xm.txt"),
                    "utf-8"));
//            out1  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\user_ext.txt"),
//                    "utf-8"));
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            int i = 0;
            String newStr = "";
            String uid = null;
            String xm = null;
            String pw = null;
            while ((tempStr = reader.readLine()) != null) {
//                System.out.println(tempStr);
                if(tempStr.indexOf("dn:")>-1){
                    uid = null;
                    xm = null;
                    pw = null;
                    newStr = info;
                    if(i>0){
                        out.write("\n");
                    }
                    i+=1;
                    uidNumber+=1;
                }
                if(uid ==null && tempStr.indexOf("uid:")>-1){
                    uid = tempStr.substring(5,tempStr.length());
                }
                if(xm ==null && tempStr.indexOf("sn:")>-1){
                    xm = tempStr.substring(5,tempStr.length());
//                    System.out.println(xm);
                }
                if(pw ==null && tempStr.indexOf("userPassword:")>-1){
                    pw = tempStr.substring(14,tempStr.length());
                }
//                System.out.println(tempStr);
                if(uid!=null && xm!=null && pw!=null){
                    if(!uid.contains(xm)) {
                        byte[] decoded = Base64.getDecoder().decode(xm);
                        xm = new String(decoded, "utf-8");
//                        System.out.println(xm+"===="+uid);
                    }else{
                        xm = uid;
                    }
                    newStr = newStr.replaceAll("zhongjianan",uid);
                    newStr = newStr.replaceAll("名字",xm);
                    newStr = newStr + "userPassword: "+pw+"\n";
                    newStr = newStr + "uidNumber: "+uidNumber+"\n";
                    out.write(newStr);
                    userMap.put(uid,xm);
                    uid = null;
                    xm = null;
                    pw = null;
                }
            }
//            Connection con = new ldap().getConnection();
//            String ssql = "select id,username from jc_sys_user";
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(ssql);
//            while (rs.next()){
//               String userId = rs.getString("id");
//               String username = rs.getString("username");
//               if(userMap.get(username)!=null){
////                   System.out.println(userMap.get(username));
////                   String sql = "INSERT INTO `jc_sys_user_ext` VALUES ('id', '1', '管理员', '3', null, null, null, null, '', '', '', '', '2019-04-15 14:46:47', '2019-11-11 09:03:20', 'admin', 'system', '0');";
////                   sql = sql.replaceAll("管理员",userMap.get(username));
////                   sql = sql.replaceAll("id",userId);
////                   sql = sql.replaceAll("system",username);
//                   String sql = "INSERT INTO `jc_tr_user_rol` VALUES("+userId+",382);";
//                   System.out.println(sql);
////                   out1.write(sql+"\n");
//               }
//            }
//            rs.close();        // 关闭ResultSet
//            stmt.close();    // 关闭Statement
//            con.close();    // 关闭Connection
            out.flush();
            out.close();
            reader.close();
            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public  Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");  System.out.println("数据库驱动加载成功");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cms_x2?characterEncoding=UTF-8","cms","nama1683");
            System.out.println("数据库连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
