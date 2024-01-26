package Entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryData {

    public static List<List<String>> readQueryData(String query) {

        List<List<String>> tableData = new ArrayList<>() ;

        try {

            ResultSet resultSet = RetrivalQueryHandler.ExecuteRetrievalQuery( query ) ;

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {

                List<String> row = new ArrayList<>() ;

                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }

                tableData.add(row) ;

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RetrivalQueryHandler.cleanRetrievalQueryHandler();

        return tableData;
    }

    public static List<List<String>> readQueryData(String query , int param1 ) {

        List<List<String>> tableData = new ArrayList<>() ;

        try {

            ResultSet resultSet = RetrivalQueryHandler.ExecuteRetrievalQuery( query , param1 ) ;

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {

                List<String> row = new ArrayList<>() ;

                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }

                tableData.add(row) ;

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RetrivalQueryHandler.cleanRetrievalQueryHandler();

        return tableData;
    }

    public static List<List<String>> readQueryData(String query , int param1 , int param2 ) {

        List<List<String>> tableData = new ArrayList<>() ;

        try {

            ResultSet resultSet = RetrivalQueryHandler.ExecuteRetrievalQuery( query , param1 , param2) ;

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {

                List<String> row = new ArrayList<>() ;

                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }

                tableData.add(row) ;

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RetrivalQueryHandler.cleanRetrievalQueryHandler();

        return tableData;
    }


    public static List<List<String>> readQueryData(String query , String param1 , String param2 , String param3 , String param4 ) {

        List<List<String>> tableData = new ArrayList<>() ;

        try {

            ResultSet resultSet = RetrivalQueryHandler.ExecuteRetrievalQuery( query , param1 , param2 , param3 , param4 ) ;

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {

                List<String> row = new ArrayList<>() ;

                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }

                tableData.add(row) ;

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RetrivalQueryHandler.cleanRetrievalQueryHandler();

        return tableData;
    }


    public static List<List<String>> readQueryData(String query , String param1 ) {

        List<List<String>> tableData = new ArrayList<>() ;

        try {

            ResultSet resultSet = RetrivalQueryHandler.ExecuteRetrievalQuery( query , param1) ;

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {

                List<String> row = new ArrayList<>() ;

                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }

                tableData.add(row) ;

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RetrivalQueryHandler.cleanRetrievalQueryHandler();

        return tableData;
    }

    public static List<List<String>> readQueryData(String query , String param1 , String param2 ) {

        List<List<String>> tableData = new ArrayList<>() ;

        try {

            ResultSet resultSet = RetrivalQueryHandler.ExecuteRetrievalQuery( query , param1 , param2) ;

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {

                List<String> row = new ArrayList<>() ;

                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }

                tableData.add(row) ;

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RetrivalQueryHandler.cleanRetrievalQueryHandler();

        return tableData;
    }

    public static List<List<String>> readQueryData(String query , String param1 , String param2  , int param3 ) {

        List<List<String>> tableData = new ArrayList<>() ;

        try {

            ResultSet resultSet = RetrivalQueryHandler.ExecuteRetrievalQuery( query , param1 , param2 , param3) ;

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {

                List<String> row = new ArrayList<>() ;

                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }

                tableData.add(row) ;

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RetrivalQueryHandler.cleanRetrievalQueryHandler();

        return tableData;
    }

    public static List<List<String>> readQueryData(String query , String param1 , int param2 ) {

        List<List<String>> tableData = new ArrayList<>() ;

        try {

            ResultSet resultSet = RetrivalQueryHandler.ExecuteRetrievalQuery( query , param1 ,param2) ;

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {

                List<String> row = new ArrayList<>() ;

                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }

                tableData.add(row) ;

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RetrivalQueryHandler.cleanRetrievalQueryHandler();

        return tableData;
    }

    public static List<List<String>> readQueryData(String query , String param1, int param2, int param3) {

        List<List<String>> tableData = new ArrayList<>() ;

        try {

            ResultSet resultSet = RetrivalQueryHandler.ExecuteRetrievalQuery( query , param1 ,param2 , param3) ;

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {

                List<String> row = new ArrayList<>() ;

                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }

                tableData.add(row) ;

            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        RetrivalQueryHandler.cleanRetrievalQueryHandler();

        return tableData;
    }

}
