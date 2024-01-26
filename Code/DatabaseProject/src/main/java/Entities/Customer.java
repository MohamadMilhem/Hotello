package Entities;

public class Customer {
    private int CustomerId;
    private String customerName ;
    private String email ;

    public Customer ( int customerId,  String customerName , String email ){
        this.CustomerId = customerId;
        this.customerName = customerName ;
        this.email = email ;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", email='" + email + + '\'' +
                '}';
    }

}
