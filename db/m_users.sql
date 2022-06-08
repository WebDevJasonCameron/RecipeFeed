USE feed_me_db;

INSERT INTO users(avatar, bio, email, password, username)
VALUES ('https://icons.iconarchive.com/icons/martin-berube/square-animal/128/Raccoon-icon.png',
        'A users bio information!', 'Jason@email.com', 'password', 'Jason'),
       ('https://icons.iconarchive.com/icons/manda-pie/chocolate/128/chocolate-1-icon.png', 'A users bio information!',
        'Sam@email.com', 'password', 'Sam'),
       ('https://icons.iconarchive.com/icons/alex-t/splash-of-fruit/128/button-apple-icon.png',
        'A users bio information!', 'Luis@email.com', 'password', 'Luis'),
       ('https://icons.iconarchive.com/icons/alex-t/splash-of-fruit/128/button-lemon-icon.png',
        'A users bio information!', 'Jose@email.com', 'password', 'Jose'),
       ('https://icons.iconarchive.com/icons/alex-t/splash-of-fruit/128/button-pomegrante-icon.png',
        'A users bio information!', 'Dennise@email.com', 'password', 'Dennise');

INSERT INTO comments(comment, time_stamp)
VALUES ('needs more salt',030322),
       ('needs some garlic',030322),
       ('amazing',030322),
       ('great',030322),
       ('wow',030322);

INSERT INTO ratings(id, rating)
VALUES
    (11,5),
    (12,5),
    (13,5),
    (14,5),
    (15,5);










