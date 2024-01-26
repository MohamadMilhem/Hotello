package Models;

public class HotelCardInHotelPage {

    private int HotelId;
    private String hotelName;
    private String hotelDescription;
    private int hotelStarRating;
    private String imagePath;
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setHotelId(int hotelId) {
        HotelId = hotelId;
    }
    public int getHotelId() {
        return HotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelDescription() {
        return hotelDescription;
    }

    public void setHotelDescription(String hotelDescription) {
        this.hotelDescription = hotelDescription;
    }

    public int getHotelStarRating() {
        return hotelStarRating;
    }

    public void setHotelStarRating(int hotelStarRating) {
        this.hotelStarRating = hotelStarRating;
    }

}
