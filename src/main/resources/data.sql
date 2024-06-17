drop table if exists message;
drop table if exists account;
-- Create table for PokemonMembers
CREATE TABLE pokemon_members (
    id SERIAL PRIMARY KEY,
    pokemon_1 VARCHAR(50) NOT NULL,
    pokemon_2 VARCHAR(50) NOT NULL,
    pokemon_3 VARCHAR(50) NOT NULL,
    pokemon_4 VARCHAR(50) NOT NULL,
    pokemon_5 VARCHAR(50) NOT NULL,
    pokemon_6 VARCHAR(50) NOT NULL
);

-- Create table for PokemonTeam
CREATE TABLE pokemon_team (
    id SERIAL PRIMARY KEY,
    pkm_user_id INT NOT NULL,
    team_name VARCHAR(50) NOT NULL,
    FOREIGN KEY (pkm_user_id) REFERENCES pkm_users(id)
);

-- Insert sample data for PokemonMembers
INSERT INTO pokemon_members (pokemon_1, pokemon_2, pokemon_3, pokemon_4, pokemon_5, pokemon_6)
VALUES
    ('Pikachu', 'Charizard', 'Bulbasaur', 'Squirtle', 'Jigglypuff', 'Snorlax'),
    ('Mewtwo', 'Gengar', 'Dragonite', 'Arcanine', 'Alakazam', 'Gyarados'),
    ('Eevee', 'Vaporeon', 'Jolteon', 'Flareon', 'Espeon', 'Umbreon');

-- Insert sample data for PokemonTeam
INSERT INTO pokemon_team (pkm_user_id, team_name)
VALUES
    (1, 'Team Rocket'),
    (2, 'Team Mystic'),
    (3, 'Team Valor');


-- Starting test values with ids of 9999 to avoid test issues
insert into account values (9999, 'testuser1', 'password');
insert into account values (9998, 'testuser2', 'password');
insert into account values (9997, 'testuser3', 'password');
insert into account values (9996, 'testuser4', 'password');

insert into message values (9999, 9999,'test message 1',1669947792);
insert into message values (9997, 9997,'test message 2',1669947792);
insert into message values (9996, 9996,'test message 3',1669947792);

