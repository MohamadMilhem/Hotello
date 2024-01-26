package Entities;

import Models.HotelSearchResult;

import java.util.ArrayList;
import java.util.List;


public class SearchHotelsByName {

    public static List<HotelSearchResult> hotelSearchResults ;


    public static String getCityNameById( int cityId ){

        String cityName = QueryData.readQueryData("SELECT City_name FROM City WHERE City_id = ? " , cityId).get(0).get(0) ;
        return cityName ;

    }

    public static List<HotelSearchResult> SearchHotelsByName(String HotelName){

        List <List<String>> result = QueryData.readQueryData("SELECT Hotel_id, Hotel_name, Star_rating, City_id, Photo_url, Description \n" +
                "FROM Hotel \n" +
                "WHERE Hotel_name = ? \n" +
                "OR Hotel_name LIKE CONCAT( ?, '%') \n" +
                "OR Hotel_name LIKE CONCAT('%', ? , '%') \n" +
                "OR Hotel_name LIKE CONCAT('%', ? );", HotelName , HotelName ,HotelName , HotelName ) ;

        if ( hotelSearchResults == null )
            hotelSearchResults = new ArrayList<>() ;

        hotelSearchResults.clear();

        for (int i = 0 ; i < result.size() ; i ++ ){
            HotelSearchResult hotelSearchResult = new HotelSearchResult();
            hotelSearchResult.setHotelId(Integer.parseInt(result.get(i).get(0)));
            hotelSearchResult.setHotelName(result.get(i).get(1));
            hotelSearchResult.setStarRating(Integer.parseInt(result.get(i).get(2)));
            hotelSearchResult.setCityName(getCityNameById(Integer.parseInt(result.get(i).get(3))));
            hotelSearchResult.setImgPath(result.get(i).get(4));
            hotelSearchResult.setDescription(result.get(i).get(5));
            hotelSearchResults.add(hotelSearchResult);
        }

        return hotelSearchResults;

    }

}

