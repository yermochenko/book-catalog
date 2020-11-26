INSERT INTO "author"
("id", "first_name", "last_name"  ) VALUES
(1   , 'William'   , 'Shakespeare'),
(2   , 'Fenimor'   , 'Cooper'     ),
(3   , 'Jack'      , 'London'     );
SELECT setval('author_id_seq', 3);

INSERT INTO "book"
("id", "author_id", "title"                 , "page") VALUES
(1   , 1          , 'Hamlet'                , 123   ),
(2   , 1          , 'King Lear'             , 234   ),
(3   , 1          , 'The Merchant of Venice', 345   ),
(4   , 2          , 'Pathfinder'            , 456   ),
(5   , NULL       , 'Holy Bible'            , 987   );
SELECT setval('book_id_seq', 5);
