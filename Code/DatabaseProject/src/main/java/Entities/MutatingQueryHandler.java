package Entities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MutatingQueryHandler {

    public static Connection connection;
    public static PreparedStatement pStatement ;

    public static void ExecuteMutatingQuery (String Query  ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query , String param1 ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1, param1 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }


    public static void ExecuteMutatingQuery (String Query , String param1 , String param2 ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1, param1 );
            pStatement.setString(2, param2 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }


    public static void ExecuteMutatingQuery (String Query , String param1 , String param2 , String param3  ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1, param1 );
            pStatement.setString(2, param2 );
            pStatement.setString(3, param3 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query , int param1 , int param2 ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setInt(1, param1 );
            pStatement.setInt(2, param2 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query , String param1 , int param2  ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1, param1 );
            pStatement.setInt(2, param2 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query ,  int param1  ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query) ;
            pStatement.setInt(1 , param1 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }


    public static void ExecuteMutatingQuery (String Query ,  float param1 , int param2  ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query) ;
            pStatement.setFloat(1 , param1 );
            pStatement.setInt(2 , param2 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query ,  String param1 , int param2 , int param3 , int param4 ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query) ;
            pStatement.setString(1 , param1 ) ;
            pStatement.setInt(2 , param2 ) ;
            pStatement.setInt(3 , param3 ) ;
            pStatement.setInt(4 , param4 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query ,  String param1 , String param2 , int param3  ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query) ;
            pStatement.setString(1 , param1 ) ;
            pStatement.setString(2 , param2 ); ;
            pStatement.setInt(3 , param3 ) ;
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }


    public static void ExecuteMutatingQuery (String Query ,  String param1 , String param2 ,   int param3 , float param4 , float param5 , int param6 ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query) ;
            pStatement.setString(1 , param1 ) ;
            pStatement.setString(2 , param2 ) ;
            pStatement.setInt(3 , param3 ) ;
            pStatement.setFloat(4 , param4 ) ;
            pStatement.setFloat(5 , param5 ) ;
            pStatement.setInt(6 , param6 ); ;

            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query ,  String param1 , String param2 ,   float param3 , float param4 , int param5  ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query) ;
            pStatement.setString(1 , param1 ) ;
            pStatement.setString(2 , param2 ) ;
            pStatement.setFloat(3 , param3 ) ;
            pStatement.setFloat(4 , param4 ) ;
            pStatement.setInt(5,param5) ;


            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query ,  int param1 , int param2 ,   int param3 , float param4 , int param5 , String param6  ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query) ;
            pStatement.setInt(1 , param1 ); ;
            pStatement.setInt(2 , param2 ); ;
            pStatement.setInt(3 , param3 ); ;
            pStatement.setFloat(4 , param4 ); ;
            pStatement.setInt(5,param5) ;
            pStatement.setString(6 , param6 );


            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query , Date param1 , Date param2 , float param3 , String param4 , int param5 , int param6){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setDate(1, param1 ) ;
            pStatement.setDate(2 , param2) ;
            pStatement.setFloat(3 , param3 ) ;
            pStatement.setString(4 , param4 ) ;
            pStatement.setInt( 5 , param5) ;
            pStatement.setInt(6 , param6 ) ;
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query , String param1, int param2, String param3, String param4, int param5, double param6, double param7){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1, param1 );
            pStatement.setInt(2 , param2);
            pStatement.setString(3 , param3 );
            pStatement.setString(4 , param4 );
            pStatement.setInt( 5 , param5) ;
            pStatement.setDouble(6 , param6 );
            pStatement.setDouble(7 , param7 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query , String param1, int param2, int param3 , double param4, int param5, String param6, String param7 ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1, param1 );
            pStatement.setInt(2 , param2);
            pStatement.setInt(3 , param3 );
            pStatement.setDouble(4 , param4 );
            pStatement.setInt( 5 , param5) ;
            pStatement.setString(6 , param6 );
            pStatement.setString(7 , param7 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query , String param1 , int param2, int param3, double param4, int param5, String param6, String param7, int  param8){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1, param1 );
            pStatement.setInt(2, param2 );
            pStatement.setInt(3,param3);
            pStatement.setDouble(4,param4);
            pStatement.setInt(5, param5 );
            pStatement.setString(6,param6);
            pStatement.setString(7,param7);
            pStatement.setInt(8,param8);
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query , String param1, String param2, String param3, int param4){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1, param1 );
            pStatement.setString(2 , param2);
            pStatement.setString(3 , param3 );
            pStatement.setInt(4 , param4 );
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void ExecuteMutatingQuery (String Query , String param1, String param2, int param3, double param4, double param5, int param6, String param7, int param8 ){

        MutatingQueryHandler.connection = ConnectionHandler.iniitiateConnection("HotelInfoDb" , "root" , "root@123") ;

        try {

            MutatingQueryHandler.pStatement = connection.prepareStatement(Query);
            pStatement.setString(1, param1 );
            pStatement.setString(2 , param2);
            pStatement.setInt(3 , param3 );
            pStatement.setDouble(4 , param4 );
            pStatement.setDouble(5 , param5);
            pStatement.setInt(6 , param6);
            pStatement.setString(7,param7);
            pStatement.setInt(8,param8);
            pStatement.executeUpdate() ;


        }
        catch (Exception e){
            e.printStackTrace();
        }

        cleanMutatingQueryHandler() ;

    }

    public static void cleanMutatingQueryHandler (){

        try {
            MutatingQueryHandler.pStatement.close();
            ConnectionHandler.closeConnection(MutatingQueryHandler.connection) ;
        }
        catch ( Exception e ){
            e.printStackTrace();
        }

    }


}