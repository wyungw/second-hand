package weizhenyang;

import java.sql.*;

public class Main {
    public Main()
    {
        //Loginframe l=new Loginframe();
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh";
        String userName="sa";
        String userPwd="123456";
        try
        {
             Class.forName(driverName);
             System.out.println("���������ɹ���");
        }catch(Exception e){
             e.printStackTrace();
             System.out.println("��������ʧ�ܣ�");
        }
        try{
             Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
             System.out.println("�������ݿ�ɹ���");
        }catch(Exception e)
        {
             e.printStackTrace();
             System.out.print("SQL Server����ʧ�ܣ�");
        }        
    }
   
}