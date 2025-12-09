    -- liquibase formatted sql

    CREATE TABLE users (
        id SERIAL PRIMARY KEY,
        email TEXT UNIQUE,
        first_name TEXT,
        last_name TEXT,
        phone TEXT
    );

    CREATE TABLE ad(
        id SERIAL PRIMARY KEY,
        ad_description TEXT,
        price INT,
        title TEXT,
        author BIGINT,
        image_id TEXT
        );

    CREATE TABLE comment(
        id SERIAL PRIMARY KEY,
        created_at INT,
        comment_text TEXT,
        author BIGINT,
        author_image TEXT,
        author_first_name TEXT,
        comment_ad BIGINT
    );

    CREATE TABLE avatar (
        id SERIAL PRIMARY KEY,
        file_path TEXT,
        user_id BIGINT
    );

    CREATE TABLE image (
        id SERIAL PRIMARY KEY,
        file_path TEXT
    );