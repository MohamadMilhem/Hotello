package Entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RetrivalQueryHandler {

    public static Connection connection;
    public static PreparedStatement pStatement ;

    public static ResultSet ExecuteRetrievalQuery (  String Query ){

        RetrivalQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            RetrivalQueryHandler.pStatement = connection.prepareStatement(Query);
            ResultSet resultSet = pStatement.executeQuery() ;


            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }

    public static ResultSet ExecuteRetrievalQuery (  String Query , int param1 ){

        RetrivalQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            RetrivalQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setInt(1,param1);
            ResultSet resultSet = pStatement.executeQuery() ;


            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }


    public static ResultSet ExecuteRetrievalQuery (  String Query , String param1 ){

        RetrivalQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            RetrivalQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1 , param1 );
            ResultSet resultSet = pStatement.executeQuery() ;


            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }

    public static ResultSet ExecuteRetrievalQuery (  String Query , String param1 , String param2 ){

        RetrivalQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            RetrivalQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1 , param1 );
            pStatement.setString(2 , param2 );
            ResultSet resultSet = pStatement.executeQuery() ;


            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }

    public static ResultSet ExecuteRetrievalQuery (  String Query , String param1 , String param2 , String param3 ){

        RetrivalQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            RetrivalQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1 , param1 );
            pStatement.setString(2 , param2 );
            pStatement.setString(3 , param3 );
            ResultSet resultSet = pStatement.executeQuery() ;


            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }



    public static ResultSet ExecuteRetrievalQuery (  String Query , int param1  , int param2 ){

        connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            PreparedStatement pStatement = connection.prepareStatement(Query);
            pStatement.setInt(1 , param1 ) ;
            pStatement.setInt(2 , param2 ) ;
            ResultSet resultSet = pStatement.executeQuery() ;


            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }

    public static ResultSet ExecuteRetrievalQuery (  String Query , int param1 , String param2 , String param3 ){

        connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            PreparedStatement pStatement = connection.prepareStatement(Query);
            pStatement.setInt(1 , param1 ) ;
            pStatement.setString(2 , param2 );
            pStatement.setString(3 , param3 );
            ResultSet resultSet = pStatement.executeQuery() ;

            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }

    public static ResultSet ExecuteRetrievalQuery (  String Query , String param1 , String param2 , int param3 ){

        connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            PreparedStatement pStatement = connection.prepareStatement(Query);
            pStatement.setString(1 , param1 ) ;
            pStatement.setString(2 , param2 ) ;
            pStatement.setInt(3 , param3 ) ;
            ResultSet resultSet = pStatement.executeQuery() ;
            pStatement.close() ;
            connection.close();

            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }


    public static ResultSet ExecuteRetrievalQuery (  String Query , String param1 , String param2 , String param3 , String param4 ){

        RetrivalQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            RetrivalQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString( 1, param1 ) ;
            pStatement.setString( 2 , param2 );
            pStatement.setString( 3 , param3 );
            pStatement.setString( 4 , param4);
            ResultSet resultSet = pStatement.executeQuery() ;


            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }


    public static ResultSet ExecuteRetrievalQuery (  String Query , int param1 , int param2 , int param3  ){

        connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            PreparedStatement pStatement = connection.prepareStatement(Query);
            pStatement.setInt(1 , param1 ) ;
            pStatement.setInt(2 , param2 ) ;
            pStatement.setInt(3 , param3 ) ;
            ResultSet resultSet = pStatement.executeQuery() ;
            pStatement.close() ;
            connection.close();

            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }
    public static ResultSet ExecuteRetrievalQuery (  String Query , String param1  , int param2 ){

        connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            PreparedStatement pStatement = connection.prepareStatement(Query);
            pStatement.setString(1 , param1 ); ;
            pStatement.setInt(2 , param2 ) ;
            ResultSet resultSet = pStatement.executeQuery() ;


            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }

    public static ResultSet ExecuteRetrievalQuery (  String Query , String param1  , int param2 , int param3 ){

        connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            PreparedStatement pStatement = connection.prepareStatement(Query);
            pStatement.setString(1 , param1 ); ;
            pStatement.setInt(2 , param2 ) ;
            pStatement.setInt(3, param3);
            ResultSet resultSet = pStatement.executeQuery() ;


            return  resultSet ;

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null ;

    }

    public static void cleanRetrievalQueryHandler (){

        try {
            RetrivalQueryHandler.pStatement.close();
            ConnectionHandler.closeConnection(RetrivalQueryHandler.connection) ;
        }
        catch ( Exception e ){
            e.printStackTrace();
        }

    }


}
