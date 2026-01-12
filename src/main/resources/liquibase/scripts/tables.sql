    -- liquibase formatted sql

    CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        email TEXT UNIQUE,
        first_name TEXT,
        last_name TEXT,
        phone TEXT,
        image_id BIGINT
    );

    CREATE TABLE ad(
        id SERIAL PRIMARY KEY,
        ad_description TEXT,
        price INT,
        title TEXT,
        author_id BIGINT
        );

    CREATE TABLE comment(
        id SERIAL PRIMARY KEY,
        created_at INT,
        comment_text TEXT,
        author_id BIGINT
    );

    CREATE TABLE image (
        id SERIAL PRIMARY KEY,
        file_path TEXT,
        media_type TEXT,
        data BYTEA
    );