package Entities;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHandler {

    public static Connection iniitiateConnection( String DataBaseName , String userName , String Password ){

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + DataBaseName, userName, Password);
            return connection ;
        }
        catch ( Exception e ){
            e.printStackTrace() ;
        }

        return null ;

    }

    public static void closeConnection ( Connection connection){

        try {
            if ( connection.isClosed() ){
                System.out.println("Connection is already Closed");
            }
            else{
                connection.close();
            }
        }
        catch ( Exception e ){
            e.printStackTrace();
        }

    }


}