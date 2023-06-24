CREATE TABLE IF NOT EXISTS Street (
                                    id INT NOT NULL PRIMARY KEY,
                                    streetName VARCHAR(255) NOT NULL,
                                    postcode INT
);

CREATE TABLE IF NOT EXISTS House (
                                    id INT NOT NULL PRIMARY KEY,
                                    houseName VARCHAR(255) NOT NULL,
                                    dateOfConstruction DATE,
                                    quantityOfFloors INT,
                                    typeOfConstruction ENUM('LivingQuarters', 'CommercialPremises', 'Garage', 'UtilityRoom'),
                                    streetNameId INT,
                                    FOREIGN KEY (streetNameId) REFERENCES Street(id)
);

ALTER TABLE House ADD material VARCHAR(255);

CREATE TABLE IF NOT EXISTS AppUser (
                                    id INT NOT NULL PRIMARY KEY,
                                    username VARCHAR(255) NOT NULL,
                                    password VARCHAR(255) NOT NULL,
                                    userType ENUM('ROLE_USER', 'ROLE_ADMIN')
);

CREATE TABLE IF NOT EXISTS Apartment
(
                                    id INT NOT NULL PRIMARY KEY,
                                    number INT NOT NULL,
                                    area INT,
                                    number_of_rooms INT,
                                    houseId INT,
                                    FOREIGN KEY (houseId) REFERENCES House(id),
                                    user_id INT,
                                    FOREIGN KEY (user_id) REFERENCES AppUser(id)
);