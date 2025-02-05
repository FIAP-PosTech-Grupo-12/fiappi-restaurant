CREATE TABLE IF NOT EXISTS restaurants (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    kitchen_type VARCHAR(100) NOT NULL,
    opening_hours VARCHAR(255) NOT NULL,
    owner_id UUID NOT NULL,
    creator_id UUID NOT NULL,
    created_at timestamp,
    updated_by UUID NOT NULL,
    updated_at timestamp,

    CONSTRAINT fk_restaurant_owner FOREIGN KEY (owner_id) REFERENCES users (id),
    CONSTRAINT fk_restaurant_creator FOREIGN KEY (creator_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS menu_items (
    id UUID PRIMARY KEY,
    restaurant_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    available_in_restaurant_only BOOLEAN NOT NULL DEFAULT FALSE,
    photo_path VARCHAR(500),
    creator_id UUID NOT NULL,
    created_at timestamp,
    updated_by UUID NOT NULL,
    updated_at timestamp,

    CONSTRAINT fk_menu_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
    CONSTRAINT fk_restaurant_creator FOREIGN KEY (creator_id) REFERENCES users (id)

);


--rollback drop table if exists menu_items;
--rollback drop table if exists restaurants;
