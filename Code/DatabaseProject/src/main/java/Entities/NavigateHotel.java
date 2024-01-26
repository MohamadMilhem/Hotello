package Entities;

import Models.Hotel;
import Models.Room;
import Models.RoomSearchResult;

import java.util.ArrayList;
import java.util.List;


public class NavigateHotel {
    public static Hotel navigateHotelByid( int hotel_id ){

        List<String> result = QueryData.readQueryData("SELECT * FROM Hotel WHERE Hotel_id = ? " , hotel_id ).get(0) ;
        Hotel navigatedHotel = new Hotel() ;

        navigatedHotel.setHotelId(Integer.parseInt(result.get(0)));
        navigatedHotel.setHotelName(result.get(1));
        navigatedHotel.setDescription(result.get(2));
        navigatedHotel.setStarRating(Integer.parseInt(result.get(3)));
        navigatedHotel.setLatitude(Float.parseFloat(result.get(4)));
        navigatedHotel.setLongitude(Float.parseFloat(result.get(5)));
        navigatedHotel.setCityId(Integer.parseInt(result.get(6)));
        navigatedHotel.setImagePath(result.get(7));

        return navigatedHotel;
    }


    public static List<RoomSearchResult> getNavigatedHotelRooms(int hotel_id){
        List<RoomSearchResult> navigatedHotelRooms = new ArrayList<>();

        if ( navigatedHotelRooms == null ){
            navigatedHotelRooms = new ArrayList<>() ;
        }

        navigatedHotelRooms.clear();

        List<List<String>> QueryResult = QueryData.readQueryData("SELECT * FROM Room where Hotel_id = ? ; " , hotel_id ) ;


        for (int i = 0 ; i < QueryResult.size() ; i ++ ){

            int AdultCount ;
            if ( QueryResult.get(i).get(2) == null ){
                AdultCount = 0 ;
            }
            else{
                AdultCount = Integer.parseInt(QueryResult.get(i).get(2)) ;
            }

            int ChildrenCount ;
            if ( QueryResult.get(i).get(3) == null  ){
                ChildrenCount = 0 ;
            }
            else{
                ChildrenCount = Integer.parseInt(QueryResult.get(i).get(3)) ;
            }

            String RoomDescription ;
            if ( QueryResult.get(i).get(6) == null  ){
                RoomDescription = null ;
            }
            else{
                RoomDescription = QueryResult.get(i).get(6) ;
            }

            String photoUrl ;
            if ( QueryResult.get(i).get(7) == null  ){
                photoUrl = null ;
            }
            else{
                photoUrl = QueryResult.get(i).get(7) ;
            }

            RoomSearchResult room = new RoomSearchResult();
            room.setRoomId(Integer.parseInt(QueryResult.get(i).get(0)));
            room.setRoomNumber(QueryResult.get(i).get(1));
            room.setAdultsCount(AdultCount);
            room.setChildrenCount(ChildrenCount);
            room.setCostPerNight(Float.parseFloat(QueryResult.get(i).get(4)));
            room.setHotelId(Integer.parseInt(QueryResult.get(i).get(5)));
            room.setDescription(RoomDescription);
            room.setImagePath(photoUrl);

            navigatedHotelRooms.add(room) ;
        }

        return navigatedHotelRooms;
    }



}
