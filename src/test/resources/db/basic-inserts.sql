----CREATE TABLE users
--(
--    id           UUID PRIMARY KEY,
--    name         VARCHAR(255) NOT NULL,
--    email        VARCHAR(255) NOT NULL,
--    login        VARCHAR(255) NOT NULL,
--    password     VARCHAR(255) NOT NULL,
--    role         VARCHAR(50)  NOT NULL,
--    updated_date TIMESTAMP,
--    address      VARCHAR(255)
--);
--
--CREATE TABLE IF NOT EXISTS restaurants (
--    id UUID PRIMARY KEY,
--    name VARCHAR(255) NOT NULL,
--    address VARCHAR(255) NOT NULL,
--    kitchen_type VARCHAR(100) NOT NULL,
--    opening_hours VARCHAR(255) NOT NULL,
--    owner_id UUID NOT NULL,
--    creator_id UUID NOT NULL,
--    created_at timestamp,
--    updated_by UUID NOT NULL,
--    updated_at timestamp,
--
--    CONSTRAINT fk_restaurant_owner FOREIGN KEY (owner_id) REFERENCES users (id),
--    CONSTRAINT fk_restaurant_creator FOREIGN KEY (creator_id) REFERENCES users (id)
--);
--
--CREATE TABLE IF NOT EXISTS menu_items (
--    id UUID PRIMARY KEY,
--    restaurant_id UUID NOT NULL,
--    name VARCHAR(255) NOT NULL,
--    description TEXT,
--    price DECIMAL(10,2) NOT NULL,
--    available_in_restaurant_only BOOLEAN NOT NULL DEFAULT FALSE,
--    photo_path VARCHAR(500),
--    creator_id UUID NOT NULL,
--    created_at timestamp,
--    updated_by UUID NOT NULL,
--    updated_at timestamp,
--
--    CONSTRAINT fk_menu_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
--    CONSTRAINT fk_menu_creator FOREIGN KEY (creator_id) REFERENCES users (id)
--
--);


--rollback drop table if exists menu_items;
--rollback drop table if exists restaurants;


-- Inserindo usuários (ADMIN e CUSTOMER)
INSERT INTO users (id, name, email, login, password, role, updated_date, address)
VALUES
    ('1555d5ff-837c-4d6e-a824-114ce54921ad'::uuid, 'Master', 'test_master@master.com', 'master',
     '$2a$10$YfS7A4vfHnX3.AN53AqT8uQaKEAbZEsZOsb.mo/Srr7v5L1rBb/Ty', 'ROLE_ADMINISTRATOR', '2024-11-30 00:47:26.850', 'No address'),

    ('3e2a59f3-b31d-4b55-8a2e-8c5d3031e3a1'::uuid, 'Carlos Silva', 'carlos@email.com', 'carlos',
     '$2a$10$abcd1234abcd1234abcd1234abcd1234abcd1234abcd1234abcd12', 'ROLE_CUSTOMER', '2024-12-01 10:00:00', 'Rua A, 123'),

    ('7e41b2d5-9f5e-4d2b-8e2b-5d4f6c7a8b9c'::uuid, 'Ana Souza', 'ana@email.com', 'ana_souza',
     '$2a$10$xyz5678xyz5678xyz5678xyz5678xyz5678xyz5678xyz5678xyz56', 'ROLE_CUSTOMER', '2024-12-02 14:30:00', 'Avenida B, 456');

-- Inserindo restaurantes
INSERT INTO restaurants (id, name, address, kitchen_type, opening_hours, owner_id, creator_id, created_at, updated_by, updated_at)
VALUES
    ('b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e3a'::uuid, 'Restaurante Sabor Caseiro', 'Rua da Comida, 123', 'TRADITIONAL', '08:00 - 22:00',
     '3e2a59f3-b31d-4b55-8a2e-8c5d3031e3a1'::uuid, '1555d5ff-837c-4d6e-a824-114ce54921ad'::uuid, '2024-12-03 09:00:00',
     '1555d5ff-837c-4d6e-a824-114ce54921ad'::uuid, '2024-12-03 10:15:00'),

    ('c5a8d2e4-5b9c-4f3a-88b5-6e7d4f5a9c8d'::uuid, 'Pizzaria Delícia', 'Avenida das Massas, 456', 'INDUSTRIAL', '18:00 - 02:00',
     '3e2a59f3-b31d-4b55-8a2e-8c5d3031e3a1'::uuid, '1555d5ff-837c-4d6e-a824-114ce54921ad'::uuid, '2024-12-04 15:30:00',
     '1555d5ff-837c-4d6e-a824-114ce54921ad'::uuid, '2024-12-04 16:00:00');

-- Inserindo itens no menu
INSERT INTO menu_items (id, restaurant_id, name, description, price, available_in_restaurant_only, photo_path, creator_id, created_at, updated_by, updated_at)
VALUES
    ('b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e5a'::uuid, 'b28a1c34-4a6b-4e3d-a70f-5d2f1a5c6e3a'::uuid, 'Feijoada Completa', 'Feijão preto com carne seca, linguiça, arroz, couve e laranja', 39.90, TRUE, '/photo',
     '1555d5ff-837c-4d6e-a824-114ce54921ad'::uuid, '2024-12-03 10:30:00', '1555d5ff-837c-4d6e-a824-114ce54921ad'::uuid, '2024-12-03 11:00:00'),

    ('d8c38289-5ecd-43f3-bb4e-12186dc5511c'::uuid, 'c5a8d2e4-5b9c-4f3a-88b5-6e7d4f5a9c8d'::uuid, 'Pizza Margherita', 'Molho de tomate, muçarela, manjericão fresco e azeite', 49.90, FALSE, '/photo',
     '1555d5ff-837c-4d6e-a824-114ce54921ad'::uuid, '2024-12-04 16:15:00', '1555d5ff-837c-4d6e-a824-114ce54921ad'::uuid, '2024-12-04 16:45:00');
