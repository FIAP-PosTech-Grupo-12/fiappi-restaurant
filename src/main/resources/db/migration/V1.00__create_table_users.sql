CREATE TABLE users
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    login        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    role         VARCHAR(50)  NOT NULL,
    updated_date TIMESTAMP,
    address      VARCHAR(255)
);

INSERT INTO users
    (id, "name", email, login, "password", "role", updated_date, address)
VALUES ('1544d5ff-837c-4d6e-a824-114ce54921ad'::uuid, 'Master', 'usuario@master.com', 'master',
        '$2a$10$YfS7A4vfHnX3.AN53AqT8uQaKEAbZEsZOsb.mo/Srr7v5L1rBb/Ty', 'ROLE_ADMINISTRATOR', '2024-11-30 00:47:26.850',
        'No address');
