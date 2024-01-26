DROP DATABASE IF EXISTS HotelInfoDb;

CREATE DATABASE HotelInfoDb ;

USE HotelInfoDb ; 

-- for create we should check if the city name alread exits and also we should check if its empty or null . 

DROP TABLE IF EXISTS City;
CREATE TABLE City (
    City_id INT AUTO_INCREMENT PRIMARY KEY,
    City_name VARCHAR(255) NOT NULL,
    Description VARCHAR(500),
    Photo_url VARCHAR(255)
);


-- for create we should check if the hotel name already exists and also if its empty or null 
-- and also we should check if the star rating is valid or not and if its null milhem 
-- we should check if the attached city already exist 


DROP TABLE IF EXISTS Hotel;
CREATE TABLE Hotel (
    Hotel_id INT AUTO_INCREMENT PRIMARY KEY,
    Hotel_name VARCHAR(255) NOT NULL,
    Description VARCHAR(500),
    Star_rating INT NOT NULL ,
    Latitude DECIMAL(18,15) NOT NULL ,
    Longitude DECIMAL(18,15) NOT NULL ,
    City_id INT NOT NULL ,
    Photo_url VARCHAR(255),
    FOREIGN KEY (City_id) REFERENCES City(City_id) ON DELETE CASCADE 
);



DROP TABLE IF EXISTS Amenity;
CREATE TABLE Amenity (
    Amenity_id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
);

-- for create we should if the combination if room number and hotel id does not exist 
-- and we should check if the hotel exist 
-- 

DROP TABLE IF EXISTS Room;
CREATE TABLE Room (
    Room_id INT AUTO_INCREMENT PRIMARY KEY,
    Room_number VARCHAR(50) NOT NULL,
    Adult_count INT,
    Children_count INT,
    Cost_night DECIMAL(10,2) NOT NULL ,
    Hotel_id INT NOT NULL ,
    Description VARCHAR(500),
    Room_photoPath VARCHAR(500),
    FOREIGN KEY (Hotel_id) REFERENCES Hotel(Hotel_id) ON DELETE CASCADE 
);


DROP TABLE IF EXISTS Room_Amenity;
CREATE TABLE Room_Amenity (
    Room_id INT,
    Amenity_id INT,
    PRIMARY KEY (Room_id, Amenity_id),
    FOREIGN KEY (Room_id) REFERENCES Room(Room_id) ON DELETE CASCADE ,
    FOREIGN KEY (Amenity_id) REFERENCES Amenity(Amenity_id)
);

DROP TABLE IF EXISTS Customer;
CREATE TABLE Customer (
	Customer_id INT AUTO_INCREMENT PRIMARY KEY,
	Customer_name VARCHAR(255) NOT NULL,
	Email VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS Reservation;
CREATE TABLE Reservation (
	Reservation_id INT AUTO_INCREMENT PRIMARY KEY,
	Start_date DATE NOT NULL,
	End_date DATE NOT NULL,
	Total_cost DECIMAL(10,2) NOT NULL ,
	Payment_method VARCHAR(50) NOT NULL ,
	Room_id INT NOT NULL ,
	Customer_id INT NOT NULL , 
	FOREIGN KEY (Room_id) REFERENCES Room(Room_id) ON DELETE CASCADE ,
	FOREIGN KEY (Customer_id) REFERENCES Customer(Customer_id)
);

DROP TABLE IF EXISTS Customer_phone;
CREATE TABLE Customer_phone(
	Phone VARCHAR(20),
	Customer_id INT NOT NULL , 
	PRIMARY KEY (Customer_id , Phone ),
    FOREIGN KEY (Customer_id) REFERENCES Customer(Customer_id)  ON DELETE CASCADE 
);

DROP TABLE IF EXISTS UserAccount ; 
CREATE TABLE UserAccount (
	User_id INT AUTO_INCREMENT PRIMARY KEY,
    Username VARCHAR(30) ,
    Account_password VARCHAR(30) ,
    Customer_id INT NOT NULL ,
    User_type INT NOT NULL DEFAULT 0,
    FOREIGN KEY (CuStomer_id) REFERENCES Customer(Customer_id) ON DELETE CASCADE 
);

-- we have to check if the user name already exists and also we have to check if the password meet the requirements 
-- we have to check if the customer already exists in the data base 

-- Inserting cities in Palestine
INSERT INTO City (City_name, Description, Photo_url) VALUES
('Jerusalem', 'Historical city with religious significance.', '/img/Jerusalem.JPG'),
('Bethlehem', 'Famed for its biblical connections.', '/img/Bethlehem.JPG'),
('Ramallah', 'A vibrant and modern Palestinian city.', '/img/Ramallah.jpg');

-- Inserting hotels in Palestinian cities
INSERT INTO Hotel (Hotel_name, Description, Star_rating, Latitude, Longitude, City_id, Photo_url ) VALUES
('The American Colony Hotel', 'A luxury hotel with a long history in Jerusalem.', 5, 31.790443457142377, 35.229122215343395, 1 , "/img/AmericanColonyHotel.jpg"),
('Jacir Palace Hotel', 'A landmark hotel in Bethlehem.', 5, 31.718178816758563, 35.20140953068679, 2 , "/img/JahirPalaceHotel.JPG"),
('Grand Park Hotel', 'A top-rated hotel in Ramallah.', 5, 31.889680924513083, 35.19916402328305, 3 , "/img/GrandParkHotel.jpg");

-- Inserting amenities commonly found in hotels
INSERT INTO Amenity (Name) VALUES
('Free Wifi'),
('Balcony'),
('Coffee maker');

-- Inserting rooms for these hotels
INSERT INTO Room (Room_number, Adult_count, Children_count, Cost_night, Hotel_id, Description, Room_photoPath) VALUES
('101', 2, 0, 200.00, 1, 'Suite with a view of the Old City', '/img/Room1.jpg'),
('102', 2, 2, 150.00, 2, 'Deluxe room with comfortable accommodations', '/img/Room2.jpg'),
('201', 2, 0, 100.00, 3, 'Standard room with modern amenities', '/img/Room3.jpg');

-- Linking rooms to amenities
INSERT INTO Room_Amenity (Room_id, Amenity_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(3, 2),
(3, 3);

-- Inserting customers
INSERT INTO Customer (Customer_name, Email) VALUES
('Ali Kazem', 'ali.kazem@example.com'),
('Sara Abed', 'sara.abed@example.com');

-- Inserting reservations
INSERT INTO Reservation (Start_date, End_date, Total_cost, Payment_method, Room_id, Customer_id) VALUES
('2024-01-20', '2024-01-25', 2000.00, 'Visa', 1, 1),
('2024-04-05', '2024-04-10', 1600.00, 'MasterCard', 2, 2);

-- Inserting customer phones
INSERT INTO Customer_phone (Phone, Customer_id) VALUES
('+970599123456', 1),
('+970599654321', 2);

-- Inserting UserAccounts

INSERT INTO UserAccount (Username, Account_password, Customer_id, User_type) VALUES
('Admin', 'Admin', 1, 1),
('Sara_123' , 'Sara_123', 2, 0);