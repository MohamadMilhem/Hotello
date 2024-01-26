package Entities;

public class SignUpHandler {


    public static int isValidUserName( String username ){

        StringBuilder temp = new StringBuilder(username) ;

        if (username.isBlank()){
            return -1;
        }

        if (!Character.isDigit(temp.charAt(0))  && !Character.isAlphabetic(temp.charAt(0))){
            System.out.println("Username should start with a character or number");
            return -2 ;
        }

        int exist = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT * FROM UserAccount WHERE Username = ? ) ;" , username).get(0).get(0)) ;

        if ( exist == 1 ){
            System.out.println("username already exists write different username ") ;
            return -3 ;
        }

        return 1 ;

    }

    public static int isValidPassword( String Account_password ){

        StringBuilder temp = new StringBuilder (Account_password) ;

        if ( Account_password.length() < 8 ){
            System.out.println("the length of the password should be 8 characters at least ") ;
            return -1 ;
        }

        for (int i = 0 ; i < Account_password.length() ; i ++ ){

            if ( !Character.isDigit(temp.charAt(i)) && !Character.isAlphabetic(temp.charAt(i)) ){
                System.out.println("invalid char in the input password the password should contain only Alphabetic chars and Digits") ;
                return -2 ;
            }

        }

        boolean containCapital = false , containSmall = false , containDigit = false ;

        for (int i = 0 ; i < Account_password.length() ; i ++ ){

            if ( Character.isUpperCase(temp.charAt(i))){
                containCapital = true ;
            }
            if ( Character.isLowerCase(temp.charAt(i))){
                containSmall = true ;
            }
            if ( Character.isDigit(temp.charAt(i))){
                containDigit = true ;
            }
        }

        if ( !containCapital || !containDigit || !containSmall ){
            System.out.println("the password should contain upper and lower case letters and digits ") ;
            return -3 ;
        }

        return 1 ;

    }

    public static int isValidEmail(String email) {

        int exists = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT * FROM Customer WHERE Email =  ? )" , email).get(0).get(0)) ;

        if ( exists == 1 ){
            System.out.println("Email Already Exists") ;
            return -3 ;
        }

        if (email == null || email.isEmpty()) {
            System.out.println("Error: Email cannot be empty.");
            return -1;
        }

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            System.out.println("Error: Invalid email format.");
            return -2;
        }

        // Email is valid
        return 1;
    }

    public static int isValidCustomerName( String Customer_name ){

        int exist = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT * FROM Customer WHERE Customer_name =  ? ) ; " , Customer_name).get(0).get(0)) ;

        if ( exist == 1 ){
            System.out.println("customer already exist ");
            return -2 ;
        }

        if ( Customer_name.isEmpty() || Customer_name == null ){
            System.out.println("user should fill the customer name ") ;
            return -1 ;
        }
        return  1 ;

    }


    public static int isValidCustomer( Customer customer){

        if ( isValidEmail(customer.getEmail()) == 1 && isValidCustomerName(customer.getCustomerName()) == 1 ){
            return 1 ;
        }
        return -1 ;

    }

    public static int getCustomerIdByName( String customerName ){
        int exist = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT * FROM Customer WHERE Customer_name =  ? ) ; " , customerName ).get(0).get(0)) ;

        if ( exist == 0 ){
            return -1 ;
        }

        return Integer.parseInt(QueryData.readQueryData("SELECT Customer_id FROM Customer WHERE Customer_name =  ? ;" , customerName ).get(0).get(0)) ;


    }

    public static int CreateAccount( Customer customer , String username , String Account_password ){

        if ( isValidCustomer(customer) == 1 && isValidUserName(username) == 1 && isValidPassword(Account_password) == 1 ){

            MutatingQueryHandler.ExecuteMutatingQuery("INSERT INTO Customer (Customer_name, Email) VALUES" +
                    " ( ? , ? ) ; " , customer.getCustomerName() , customer.getEmail());



            MutatingQueryHandler.ExecuteMutatingQuery("INSERT INTO UserAccount (Username, Account_password, Customer_id) VALUES" +
                    "( ? , ? , ?) ; " , username , Account_password , getCustomerIdByName(customer.getCustomerName())) ;

            System.out.println("Account created successfully") ;
            return 1 ;
        }
        else {
            System.out.println("invalid information for account creation check the information ") ;
            return -1 ;
        }

    }

}