package Entities;

import java.util.List;

public class UserAuthentication {


    // we want to check if the entered username and password exist
    // check if the username exist and bring its record
    // check if the password is correct .
    // return the result of the table .

    public static int checkIfUserExist ( String Username ){

        int exist = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS ( SELECT Username FROM UserAccount WHERE UserName = ? ) ; " , Username).get(0).get(0) ) ;

        if ( exist == 0 ){
            return -1 ;
        }

        return 1 ;

    }


    // checks if the password for the entered username is correct returns the customer id if its correct , -1 if not .
    public static int isCorrectPassword( String Username , String Account_Password ){

        List<String> UserAccount = QueryData.readQueryData("SELECT * FROM UserAccount WHERE Username = ? ; " , Username).get(0) ;

        String actualPassword = UserAccount.get(2) ;

        if ( actualPassword.equals(Account_Password) ){
            return Integer.parseInt(UserAccount.get(3)) ;
        }

        return -1 ;

    }

    public static int isAdmin ( String Username ){

        int isAdmin = Integer.parseInt(QueryData.readQueryData("SELECT User_type FROM UserAccount WHERE Username = ? " , Username ).get(0).get(0)) ;

        return isAdmin ;

    }

    // get the customer id and returns the customer object with the given id record attributes .
    public static Customer getCustomerById ( int customerId ){

        List<String> customerRecord = QueryData.readQueryData("SELECT * FROM Customer WHERE Customer_id = ? " , customerId).get(0) ;

        Customer customer = new Customer(Integer.parseInt(customerRecord.get(0)) , customerRecord.get(1) , customerRecord.get(2)) ;

        return customer ;

    }

    public static Customer SignInRegularUser ( String Username , String Account_password ){

        if ( checkIfUserExist(Username) == -1 ){
            System.out.println("Incorrect user name") ;
            return null ;
        }

        int customerId = isCorrectPassword(Username , Account_password) ;

        if ( customerId == -1 ){
            System.out.println("incorrect password") ;
            return null ;
        }

        Customer customer = getCustomerById(customerId) ;
        return customer ;

    }

}
