package Entities;

import Models.Reservation;
import Models.Room;
import Models.RoomSearchResult;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.awt.dnd.DropTarget;
import java.sql.Date ;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List ;
public class ReservationHandler {


    public static List<Date> getStartDates ( int roomId ){

        List<List<String>> result = QueryData.readQueryData("SELECT Start_date FROM Reservation WHERE Room_id = ? ;" , roomId ) ;
        List<Date> startDates = new ArrayList<>() ;

        for (int i = 0 ; i < result.size() ; i ++ ){
            Date temp = Date.valueOf(result.get(i).get(0) ) ;
            startDates.add(temp) ;
        }
        return startDates ;

    }

    public static List<Date> getEndDates ( int roomId ){

        List<List<String>> result = QueryData.readQueryData("SELECT End_date FROM Reservation WHERE Room_id = ? ;" , roomId ) ;
        List<Date> endDates = new ArrayList<>() ;

        for (int i = 0 ; i < result.size() ; i ++ ){
            Date temp = Date.valueOf(result.get(i).get(0) ) ;
            endDates.add(temp) ;
        }
        return endDates ;

    }

    public static List<Date> getAvailableDates( int roomdId ){

        List<Date> availableDates = new ArrayList<>() ;

        long timeNow = System.currentTimeMillis()  ;
        long dayIncrement = 86400000 ;
        Date TempDate ;

        List<Date> startDates = getStartDates(roomdId) ;
        List<Date> endDates = getEndDates(roomdId) ;

        for ( int i = 0 ; i < 30 ; i ++ ){

            TempDate = new Date(timeNow + dayIncrement * i ) ;
            int Valid = 1 ;

            for (int j = 0 ; j < startDates.size() ; j ++ ){
                if ( TempDate.toString().equals(startDates.get(j).toString()) || TempDate.toString().equals(endDates.get(j).toString()) || ( TempDate.after(startDates.get(j)) && TempDate.before(endDates.get(j)))){
                    Valid = 0 ;
                }


            }

            if ( Valid == 1 ){
                availableDates.add(TempDate) ;
            }

        }

        return availableDates ;

    }

    public static int isValidPaymentMethod( String paymentMethod ){

        if ( paymentMethod == null || paymentMethod.isEmpty() ){
            System.out.println("payment method cannot be null or empty");
            return -1 ;
        }

        if ( !paymentMethod.equals("VISA") || !paymentMethod.equals("MASTER CARD") || !paymentMethod.equals("CASH") || !paymentMethod.equals("PAYPAL")){
            System.out.println("PAYMENT METHOD SHOULD BE VISA MASTER CARD OR CASH OR PAYPAL ");
            return -2 ;
        }

        return 1 ;

    }

    public static int getCustomerId ( Customer customer ){

        int customerId = Integer.parseInt(QueryData.readQueryData("SELECT Customer_id FROM Customer WHERE Customer_name = ? ; " , customer.getCustomerName()).get(0).get(0) );
        return customerId ;
    }

    public static List<Date> getAvailableEndDates( Date start_date, List<Date> availableDates ){

        List<Date> availableEndDates = new ArrayList<>() ;

        for (int i = 0 ; i < availableDates.size()  ; i ++ ){


            if ( ( start_date.before(availableDates.get(i)) || start_date.toString().equals(availableDates.get(i).toString()) )   ){
                if ( i != availableDates.size() - 1  && (int)(( availableDates.get(i+1).getTime() -  availableDates.get(i).getTime() )/86400000) > 1  ){
                    availableEndDates.add(availableDates.get(i)) ;
                    break ;
                }
                availableEndDates.add(availableDates.get(i)) ;
            }

        }
        return availableEndDates ;
    }

    public static Reservation createReservation(RoomSearchResult room, Customer customer , Date startdate , Date endDate , String paymentMethod ){

        Reservation reservation = new Reservation() ;

        reservation.setStartDate(startdate) ;
        reservation.setEndDate(endDate ) ;
        reservation.setPaymentMethod(paymentMethod) ;

        float totalCost  ;
        int numberOfDays = (int)(( endDate.getTime() - startdate.getTime() )/86400000) ;
        totalCost = (float) ((numberOfDays + 1) * room.getCostPerNight());

        reservation.setTotalCost(totalCost);
        reservation.setRoomId(room.getRoomId());
        reservation.setCustomerId(getCustomerId(customer));

        return reservation ;

    }

    public static void confirmReservation ( Reservation reservation ){

        MutatingQueryHandler.ExecuteMutatingQuery("INSERT INTO Reservation (Start_date, End_date, Total_cost, Payment_method, Room_id, Customer_id) VALUES " +
                "( ? , ? , ? , ? , ? , ? );" , reservation.getStartDate() , reservation.getEndDate() , reservation.getTotalCost() , reservation.getPaymentMethod() , reservation.getRoomId() , reservation.getCustomerId() ) ;

    }

    public static List<String> getRoomAmenities ( int room_Id ){

        List<List<String>> queryResult = QueryData.readQueryData("SELECT A.Name FROM Amenity A JOIN (SELECT * FROM Room_Amenity WHERE Room_id = ? ) RA ON RA.Amenity_id = A.Amenity_id " , room_Id ) ;

        List<String> roomAmenities = new ArrayList<>() ;

        for (int i = 0 ; i < queryResult.size() ; i ++ ){
            roomAmenities.add(queryResult.get(i).get(0)) ;
        }

        return roomAmenities ;
    }

}