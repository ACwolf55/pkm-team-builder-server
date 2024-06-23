CREATE TABLE
  pkm_users (
    pkm_user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(25) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL
  );

CREATE TABLE
  pokemon_team (
    pokemon_team_id SERIAL PRIMARY KEY,
    pkm_user_id INT NOT NULL REFERENCES pkm_users (pkm_user_id),
    team_name VARCHAR(50) NOT NULL,
    pokemon_1 VARCHAR(50) NOT NULL,
    pokemon_2 VARCHAR(50) NOT NULL,
    pokemon_3 VARCHAR(50) NOT NULL,
    pokemon_4 VARCHAR(50) NOT NULL,
    pokemon_5 VARCHAR(50) NOT NULL,
    pokemon_6 VARCHAR(50) NOT NULL
  );


INSERT INTO pkm_users (user_name, password) VALUES ('Ash Ketchum', 'pokemonmaster');
INSERT INTO pkm_users (user_name, password) VALUES ('Leon', 'galar');
INSERT INTO pkm_users (user_name, password) VALUES ('Cynthia', 'sinnoh');
INSERT INTO pkm_users (user_name, password) VALUES ('Erika', 'celadon');



-- Insert sample data for PokemonMembers
INSERT INTO pokemon_team (pokemon_1, pokemon_2, pokemon_3, pokemon_4, pokemon_5, pokemon_6,pkm_user_id,team_name)
VALUES
    ('Pikachu', 'Charizard', 'Bulbasaur', 'Greninja', 'infernape', 'Lucario',1,'Main'),
    ('Vileplume', 'Victreebel', 'Leafeon', 'Tangela', 'Comfey', 'Bellosom',4,'Main'),
    ('Charizard', 'Cinderace', 'Rillaboom', 'Inteleon', 'Aegislash', 'Dragapult',2,'Main'),
     ('Garchomp', 'Spiritomb', 'Togekiss', 'Milotic', 'Lucario', 'Kommo-o',3,'Main');


-- Starting test values with ids of 9999 to avoid test issues
-- insert into account values (9999, 'testuser1', 'password');
-- insert into account values (9998, 'testuser2', 'password');
-- insert into account values (9997, 'testuser3', 'password');
-- insert into account values (9996, 'testuser4', 'password');

-- insert into message values (9999, 9999,'test message 1',1669947792);
-- insert into message values (9997, 9997,'test message 2',1669947792);
-- insert into message values (9996, 9996,'test message 3',1669947792);

