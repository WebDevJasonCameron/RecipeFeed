USE feed_me_db;

DELETE FROM ingredients WHERE id > 381;

DELETE FROM recipes WHERE id = 37;

DELETE FROM comments WHERE id > 0;

UPDATE recipes
SET
    user_id = 0

WHERE
    id = 6;



