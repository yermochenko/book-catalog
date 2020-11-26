CREATE TABLE "author" (
	"id" SERIAL PRIMARY KEY,
	"first_name" TEXT NOT NULL,
	"last_name" TEXT NOT NULL 
);

CREATE TABLE "book" (
	"id" SERIAL PRIMARY KEY,
	"author_id" INTEGER REFERENCES "author",
	"title" TEXT NOT NULL,
	"page" INTEGER NOT NULL,
	"date" DATE
);
