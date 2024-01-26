package Entities;

import Models.CitySearchResult;
import Models.HotelSearchResult;
import Models.RoomSearchResult;

import java.util.ArrayList;
import java.util.List ;
public class AdminQueriesHandler {


    public static List<CitySearchResult> searchCitiesNames (String cityName ){

        List <CitySearchResult> searchResult = new ArrayList<>() ;

        List<List<String>> QueryResult = QueryData.readQueryData("SELECT * FROM City WHERE City_name LIKE CONCAT('%', ? , '%') ;" , cityName) ;

        for (int i = 0 ; i < QueryResult.size() ; i ++ ){

            CitySearchResult temp = new CitySearchResult() ;
            temp.setCityId(Integer.parseInt(QueryResult.get(i).get(0)));
            temp.setCityName(QueryResult.get(i).get(1));
            temp.setDescription(QueryResult.get(i).get(2)) ;
            temp.setImagePath(QueryResult.get(i).get(3));

            searchResult.add(temp) ;

        }

        return searchResult ;

    }

    // returns 1 if the city exists 0 otherwise .
    public static int checkIfCityExists( int cityId ){

        int exist = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT City_id FROM City WHERE City_id = ? ) ; " , cityId ).get(0).get(0) ) ;

        return exist ;
    }

    // returns -1 if the city does not exist , returns -2 if there was an error while deleting the city  , and returns 0 if the city was deleted successfully .
    public static int DeleteCityById(int cityId ){

        int cityExists = checkIfCityExists(cityId) ;

        if ( cityExists == 0 ){
            return -1 ;
        }

        MutatingQueryHandler.ExecuteMutatingQuery("DELETE FROM City WHERE City_id = ? ; " , cityId ) ;
        cityExists = checkIfCityExists(cityId) ;

        if ( cityExists == 0 )
            return 0 ;

        return -2 ;
    }

    // returns 1 if the Hotel exists 0 otherwise .
    public static int checkIfHotelExists( int hotelId ){

        int exist = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT Hotel_id FROM Hotel WHERE Hotel_id = ? ) ; " , hotelId ).get(0).get(0) ) ;

        return exist ;
    }

    // returns -1 if the Hotel does not exist , returns -2 if there was an error while deleting the Hotel  , and returns 0 if the Hotel was deleted successfully .
    public static int DeleteHotelById(int hotelId ){

        int hotelExists = checkIfHotelExists(hotelId) ;

        if ( hotelExists == 0 ){
            return -1 ;
        }

        MutatingQueryHandler.ExecuteMutatingQuery("DELETE FROM Hotel WHERE Hotel_id = ? ; " , hotelId ) ;
        hotelExists = checkIfHotelExists(hotelId) ;

        if ( hotelExists == 0 )
            return 0 ;

        return -2 ;
    }


    public static int checkIfRoomExists( int roomId ){

        int exist = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT Room_id FROM Room WHERE Room_id = ? ) ; " , roomId ).get(0).get(0) ) ;

        return exist ;
    }

    // returns -1 if the city does not exist , returns -2 if there was an error while deleting the city  , and returns 0 if the city was deleted successfully .
    public static int DeleteRoomById(int roomId ){

        int roomExists = checkIfRoomExists(roomId) ;

        if ( roomExists == 0 ){
            return -1 ;
        }

        MutatingQueryHandler.ExecuteMutatingQuery("DELETE FROM Room WHERE Room_id = ? ; " , roomId ) ;
        roomExists = checkIfCityExists(roomExists) ;

        if ( roomExists == 0 )
            return 0 ;

        return -2 ;
    }

    public static int isValidCityName( String cityName ){

        if ( cityName.isEmpty() || cityName == null ){
            return 0 ;
        }
        return 1 ;
    }


    public static CitySearchResult getTrendingDestination (){

        List <List<String>> QueryResult = QueryData.readQueryData("SELECT C.City_id, C.City_name, C.Description, C.photo_url, COUNT(R.Reservation_id) AS NumberOfReservations\n" +
                "FROM City C\n" +
                "JOIN Hotel H ON C.City_id = H.City_id\n" +
                "JOIN Room Ro ON H.Hotel_id = Ro.Hotel_id\n" +
                "JOIN Reservation R ON Ro.Room_id = R.Room_id\n" +
                "GROUP BY C.City_id, C.City_name, C.Description, C.photo_url\n" +
                "ORDER BY NumberOfReservations DESC\n" +
                "LIMIT 1;") ;



        CitySearchResult temp = new CitySearchResult() ;
        temp.setCityId(Integer.parseInt(QueryResult.get(0).get(0)));
        temp.setCityName(QueryResult.get(0).get(1));
        temp.setDescription(QueryResult.get(0).get(2));
        temp.setImagePath(QueryResult.get(0).get(3)) ;

        return temp ;

    }

    public static List<String> getCityOptions(){

        List<String> cityOptions = new ArrayList<>() ;

        List<List<String>> QueryResults = QueryData.readQueryData("SELECT City_name FROM City ;" ) ;

        for (int i = 0 ; i < QueryResults.size() ; i ++ ){
            cityOptions.add(QueryResults.get(i).get(0)) ;
        }

        return cityOptions ;

    }

    public static List<String> getHotelOptions(){

        List<List<String>> queryResult = QueryData.readQueryData("SELECT Hotel_name FROM Hotel ;") ;
        List<String> options = new ArrayList<>() ;

        for (int i = 0 ; i < queryResult.size() ; i ++ ){

            options.add(queryResult.get(i).get(0)) ;

        }

        return options ;

    }

    // returns 1 if the city name already exists in the db 0 otherwise .
    public static int checkIfCityNameExists_create ( String cityName ){

        int exists = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT City_name FROM City WHERE City_name = ? ) ; " , cityName ).get(0).get(0) ) ;
        return exists ;

    }

    // returns 1 if valid 0 otherwise .
    public static int checkCityNameConstrains ( String cityName ){

        if ( cityName.isEmpty() || cityName == null )
            return 0 ;

        return 1 ;

    }

    // return -1 if the passed object is invalid for creation 0 if the object was created successfully .
    public static int createCity( CitySearchResult newCity){

        if ( checkCityNameConstrains(newCity.getCityName()) == 0 || checkIfCityNameExists_create(newCity.getCityName()) == 1 ){
            return -1 ;
        }

        MutatingQueryHandler.ExecuteMutatingQuery("INSERT INTO City (City_name, Description, Photo_url) VALUES\n" +
                "(? , ? , ? )" , newCity.getCityName() , newCity.getDescription() , newCity.getImagePath() );

        return 0 ;
    }


    // returns 1 if the hotel name already exists in the db 0 otherwise .
    public static int checkIfHotelNameExists_create ( String hotelName ){

        int exists = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT Hotel_name FROM Hotel WHERE Hotel_name = ? ) ; " , hotelName ).get(0).get(0) ) ;
        return exists ;

    }

    // returns 1 if valid 0 otherwise .
    public static int checkHotelNameConstrains ( String hotelName ){

        if ( hotelName.isEmpty() || hotelName == null )
            return 0 ;

        return 1 ;

    }

    // return 1 if the star rating is valid 0 otherwise .
    public static int isValidStarRating (int starRating ){

        if ( starRating >= 0 && starRating <= 5 ){
            return 1 ;
        }
        return 0 ;
    }

    public static int checkLatitudeAndLongitude( double temp ){

        if ( temp > 180f || temp < -180f){
            return 0 ;
        }
        return 1 ;
    }

    public static int getCityid_byName ( String cityName ){

        int cityId = Integer.parseInt(QueryData.readQueryData("SELECT City_id FROM City WHERE City_name = ? " , cityName).get(0).get(0)) ;
        return cityId ;
    }
    public static int createHotel( HotelSearchResult hotel ){

        if ( checkHotelNameConstrains(hotel.getHotelName()) == 0 ){
            System.out.println("hotel name should not be null ");
            return -1 ;
        }

        if ( checkIfHotelNameExists_create(hotel.getHotelName()) == 1){
            System.out.println("hotel name already exists");
            return -2 ;
        }

        if ( checkIfCityNameExists_create(hotel.getCityName()) == 0 ){
            System.out.println("the entered city does not exist ");
            return -3 ;
        }

        if ( checkLatitudeAndLongitude(hotel.getLongitude()) == 0 ){
            System.out.println("longitude should be between -180 and 180 ");
            return -4 ;
        }

        if ( checkLatitudeAndLongitude(hotel.getLatitude()) == 0 ){
            System.out.println("Latitude should be between -180 and 180 ");
            return -5 ;
        }

        MutatingQueryHandler.ExecuteMutatingQuery("INSERT INTO Hotel ( Hotel_name , City_id , Photo_url , Description , Star_rating , Latitude , Longitude) VALUES" +
                "( ? , ? , ? , ? , ? , ? , ? ) ;" , hotel.getHotelName() , getCityid_byName(hotel.getCityName()) , hotel.getImgPath() , hotel.getDescription() , hotel.getStarRating() , hotel.getLatitude() , hotel.getLongitude());
        return 0 ;

    }

    public static List<String> getAmenitiesOptions(){

        List<List<String>> queryResult = QueryData.readQueryData("SELECT Name FROM Amenity ;") ;
        List<String> options = new ArrayList<>() ;

        for (int i = 0 ; i < queryResult.size() ; i ++ ){
            options.add(queryResult.get(i).get(0));
        }

        return options ;

    }

    public static int getAmenityId_byName ( String amenityName ){

        int amenity_id = Integer.parseInt(QueryData.readQueryData("SELECT Amenity_id FROM Amenity WHERE Name = ?  ;" , amenityName).get(0).get(0) ) ;
        return amenity_id ;

    }
    public static int getHotelId_byName ( String hotelName ){

        int hotelId = Integer.parseInt(QueryData.readQueryData("SELECT Hotel_id FROM Hotel WHERE Hotel_name = ? ; " , hotelName).get(0).get(0)) ;
        return hotelId ;

    }

    /// returns 1 if exist 0 otherwise .
    public static int checkIf_hotelid_roomNumber_AlreadyExist (int hotelId , String roomNumber){

        int exist = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT * FROM Room WHERE Room_number = ? AND Hotel_id = ? ) ; " , roomNumber , hotelId) .get(0).get(0)) ;
        return exist ;
    }

    public static int isValidCost( double costPerNight ){

        if ( costPerNight < 0 ){
            return 0 ;
        }
        return 1 ;

    }

    public static int getRoomId_by_roomNumber_hotel_id ( String roomNumber , int hotelId ){

        int roomId = Integer.parseInt(QueryData.readQueryData("SELECT Room_id FROM Room WHERE Room_number = ? AND Hotel_id = ? " , roomNumber , hotelId).get(0).get(0) ) ;
        return roomId ;
    }

    public static int createRoom( List<String> roomAmenities , RoomSearchResult newRoom ){

        if ( checkIf_hotelid_roomNumber_AlreadyExist( newRoom.getHotelId() , newRoom.getRoomNumber() ) == 1 ){
            System.out.println("the room number already exists for the passed hotel");
            return -1 ;
        }

        if ( isValidCost(newRoom.getCostPerNight()) == 0 ){
            System.out.println("the cost should be above 0 ");
            return -2 ;
        }

        MutatingQueryHandler.ExecuteMutatingQuery("INSERT INTO Room (room_number , Adult_count , Children_count , Cost_night , Hotel_id , Description , Room_photoPath) VALUES" +
                "( ? , ? , ? , ? , ? , ? , ? ) ;" , newRoom.getRoomNumber() , newRoom.getAdultsCount() , newRoom.getChildrenCount() , newRoom.getCostPerNight() , newRoom.getHotelId() , newRoom.getDescription() , newRoom.getImagePath() ) ;

        int roomId = getRoomId_by_roomNumber_hotel_id( newRoom.getRoomNumber() , newRoom.getHotelId() ) ;

        for (int i = 0 ; i < roomAmenities.size() ; i ++ ){

            MutatingQueryHandler.ExecuteMutatingQuery("INSERT INTO Room_Amenity (Room_id, Amenity_id) VALUES\n" +
                    "( ? , ?) ; " , roomId , getAmenityId_byName(roomAmenities.get(i)));

        }

        return 0 ;

    }

    public static int checkIfCityNameExists_update ( String newCityName , int cityId ){

        int exists = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS ( SELECT City_name FROM City WHERE City_name = ? AND City_id != ? ) ;", newCityName , cityId).get(0).get(0))  ;
        return exists ;

    }

    public static int updateCity ( CitySearchResult updatedCity ){

        if ( checkIfCityNameExists_update(updatedCity.getCityName() , updatedCity.getCityId()) == 1 ){
            System.out.println("the new city name already exists") ;
            return -1 ;
        }
        if ( checkHotelNameConstrains(updatedCity.getCityName()) == 0 ){
            System.out.println("the hotel name cannot be null ");
            return -2 ;
        }

        MutatingQueryHandler.ExecuteMutatingQuery("UPDATE City SET City_name = ? , Description = ? , photo_url = ?  WHERE City_id = ? ;" , updatedCity.getCityName() , updatedCity.getDescription(), updatedCity.getImagePath() , updatedCity.getCityId());
        return 0 ;

    }


    public static int checkIfHotelNameExist_update( String hotelName , int hotelId ){

        int exist = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS ( SELECT Hotel_name FROM Hotel WHERE Hotel_name = ? AND Hotel_id != ?) ; " , hotelName , hotelId).get(0).get(0)) ;
        return exist ;

    }


    public static int updateHotel( HotelSearchResult updatedHotel ){

        if ( checkIfHotelNameExist_update(updatedHotel.getHotelName() , updatedHotel.getHotelId()) == 1 ){
            System.out.println("the new hotel name already exists");
            return -1 ;
        }
        if ( isValidStarRating(updatedHotel.getStarRating()) == 0 ){
            System.out.println("invalid start rating , star rating should be between 0 and 5 inclusive .");
            return -2 ;
        }
        if ( checkLatitudeAndLongitude(updatedHotel.getLatitude()) == 0 ){
            System.out.println("invalid latitude , latitude should be between -180 and 180 ");
            return  -3 ;
        }
        if ( checkLatitudeAndLongitude(updatedHotel.getLongitude()) == 0 ){
            System.out.println("invalid longitude , longitude should be between -180 and 180 ");
            return  -4 ;
        }
        if ( checkIfCityNameExists_create(updatedHotel.getCityName()) == 0 ){
            System.out.println("the new city name does not exist");
            return -5 ;
        }

        MutatingQueryHandler.ExecuteMutatingQuery("UPDATE Hotel SET Hotel_name = ? , Description = ? , Star_rating = ? , Latitude = ? , Longitude = ? , City_id = ? , photo_url = ? WHERE Hotel_id = ? " , updatedHotel.getHotelName() , updatedHotel.getDescription() , updatedHotel.getStarRating() , updatedHotel.getLatitude() , updatedHotel.getLongitude() , getCityid_byName(updatedHotel.getCityName()) , updatedHotel.getImgPath() , updatedHotel.getHotelId() );
        return 0 ;


    }

    public static int checkIf_hotelid_roomNumber_AlreadyExist_update (int hotelId , String roomNumber , int roomId ){

        int exist = Integer.parseInt(QueryData.readQueryData("SELECT EXISTS (SELECT * FROM Room WHERE Room_number = ? AND Hotel_id = ? AND Room_id != ? ) ; " , roomNumber , hotelId , roomId) .get(0).get(0)) ;
        return exist ;

    }


    public static int updateRoom ( RoomSearchResult updatedRoom  , List<String> newAmenties ){

        if ( checkIf_hotelid_roomNumber_AlreadyExist_update(updatedRoom.getHotelId() , updatedRoom.getRoomNumber(),  updatedRoom.getRoomId()) == 1 ){
            System.out.println("the room number already exist in the hotel ");
            return -1 ;
        }
        if ( isValidCost(updatedRoom.getCostPerNight()) == 0 ){
            System.out.println("invalid cost per night should be greater then zero . ");
            return -2 ;
        }

        MutatingQueryHandler.ExecuteMutatingQuery("DELETE FROM Room_Amenity WHERE Room_id = ? " , updatedRoom.getRoomId() );
        MutatingQueryHandler.ExecuteMutatingQuery("UPDATE Room Set Room_number = ? , Adult_count = ? , Children_count = ? , Cost_night = ? , Hotel_id = ? , Description = ? , Room_photoPath = ? WHERE Room_id = ? " , updatedRoom.getRoomNumber() , updatedRoom.getAdultsCount() , updatedRoom.getChildrenCount() , updatedRoom.getCostPerNight() , updatedRoom.getHotelId() , updatedRoom.getDescription() , updatedRoom.getImagePath() , updatedRoom.getRoomId() )  ; ;

        for (int i =0 ; i < newAmenties.size() ; i ++ ){

            MutatingQueryHandler.ExecuteMutatingQuery("INSERT INTO Room_Amenity VALUES (? , ? ) ;" , updatedRoom.getRoomId() , getAmenityId_byName(newAmenties.get(i)) );

        }

        return 0 ;

    }

    // returns the hotel name and the number of reservation as a string on each line only top 10 .
    public static List<List<String>> getTopTenTrendingHotels (){

        return QueryData.readQueryData("SELECT H.Hotel_name, COUNT(R.Reservation_id) AS NumberOfReservations\n" +
                "FROM Hotel H\n" +
                "JOIN Room Ro ON H.Hotel_id = Ro.Hotel_id\n" +
                "JOIN Reservation R ON Ro.Room_id = R.Room_id\n" +
                "GROUP BY H.Hotel_id\n" +
                "ORDER BY NumberOfReservations DESC\n" +
                "LIMIT 10;") ;


    }

    // returns the hotel name and the number of reservation as a string on each line only top 10 .
    public static List<List<String>> getTopFiveTrendingHotelInCity( int cityId ){

        return QueryData.readQueryData("SELECT H.Hotel_name, COUNT(R.Reservation_id) AS NumberOfReservations\n" +
                "FROM Hotel H\n" +
                "JOIN Room Ro ON H.Hotel_id = Ro.Hotel_id\n" +
                "JOIN Reservation R ON Ro.Room_id = R.Room_id\n" +
                "WHERE H.City_id = ? \n" +
                "GROUP BY H.Hotel_id\n" +
                "ORDER BY NumberOfReservations DESC\n" +
                "LIMIT 5;\n" , cityId ) ;


    }


    // returns a list of list of strings with 5 rows and 2 columns the first one for the customer name and the second one for the number of reservations (as string )
    public static List<List<String>> getTopFiveCustomers (){

        return QueryData.readQueryData("SELECT Cu.Customer_name, COUNT(R.Reservation_id) AS NumberOfReservations\n" +
                "FROM Customer Cu\n" +
                "JOIN Reservation R ON Cu.Customer_id = R.Customer_id\n" +
                "GROUP BY Cu.Customer_id\n" +
                "ORDER BY NumberOfReservations DESC\n" +
                "LIMIT 5;\n") ;

    }


    // returns the average total cost of a reservation as a string .
    public static List<List<String>>  getAverageReservationCostForHotel_byHotelId (int hotelId){

        return QueryData.readQueryData("SELECT H.Hotel_name, AVG(R.Total_cost) AS AverageReservationCost\n" +
                "FROM Hotel H\n" +
                "JOIN Room Ro ON H.Hotel_id = Ro.Hotel_id\n" +
                "JOIN Reservation R ON Ro.Room_id = R.Room_id\n" +
                "WHERE H.Hotel_id = ?\n" +
                "GROUP BY H.Hotel_id;\n" , hotelId ) ;

    }

    // returns the average reservation cost for the top ten trending hotels ( hotel name , average cost ) as strings . as a list of list of Strings
    public static List<List<String>> getAverageReservationCostForTrendingHotels( ){

        List<List<String>> topTenHotels = getTopTenTrendingHotels() ;

        List<List<String>> averageReservaionCostForTopFiveHotels = new ArrayList<>() ;

        for (int i = 0 ; i < topTenHotels.size() ; i ++ ){

            int hotelId = getHotelId_byName(topTenHotels.get(i).get(0)) ;
            averageReservaionCostForTopFiveHotels.add(getAverageReservationCostForHotel_byHotelId(hotelId).get(0)) ;

        }

        return averageReservaionCostForTopFiveHotels ;

    }

    public static double getHotelLatitude( int Hotel_id ){

        double hotelLatitude = Double.parseDouble(QueryData.readQueryData("SELECT Latitude FROM Hotel WHERE Hotel_id = ? ; " , Hotel_id).get(0).get(0)) ;
        return hotelLatitude;

    }

    public static double getHotelLongitude( int Hotel_id ){

        double hotelLongitude = Double.parseDouble(QueryData.readQueryData("SELECT Longitude FROM Hotel WHERE Hotel_id = ? ; " , Hotel_id).get(0).get(0)) ;
        return hotelLongitude ;

    }

    public static List<String> getRoomAmenties( int roomId ){

        List<String> roomAmenties = new ArrayList<>() ;
        List<List<String>> queryResult = QueryData.readQueryData("SELECT NAME FROM Amenity A JOIN Room_Amenity RA ON RA.Room_id = ? AND A.Amenity_id = RA.Amenity_id ; " , roomId) ;

        for (int i =0 ; i < queryResult.size() ; i ++ ){

            roomAmenties.add(queryResult.get(i).get(0)) ;

        }

        return roomAmenties ;

    }

    public static String getHotelName(int hotelId ){

        String hotelName = QueryData.readQueryData("SELECT Hotel_name FROM Hotel WHERE Hotel_id = ? ; " , hotelId ).get(0).get(0) ;
        return hotelName ;

    }
}