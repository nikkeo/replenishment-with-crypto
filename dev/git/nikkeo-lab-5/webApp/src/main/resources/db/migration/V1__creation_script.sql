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