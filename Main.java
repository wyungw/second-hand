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
             System.out.println("加载驱动成功！");
        }catch(Exception e){
             e.printStackTrace();
             System.out.println("加载驱动失败！");
        }
        try{
             Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
             System.out.println("连接数据库成功！");
        }catch(Exception e)
        {
             e.printStackTrace();
             System.out.print("SQL Server连接失败！");
        }        
    }
   
}