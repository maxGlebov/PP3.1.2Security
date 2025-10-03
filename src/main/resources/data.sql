-- роли
INSERT INTO roles (name) VALUES
                             ('ROLE_USER'),
                             ('ROLE_ADMIN');

-- пользователи
INSERT INTO users (name, last_name, email, password) VALUES
                                                         ('User', 'Userov', 'user@example.com', '$2a$10$VfthHQiZwUgqYcHy8JqfWuhfA2hzZ5wWTaZrjHDeOxXH7Iop/I7wq'), -- пароль: user
                                                         ('Admin', 'Adminov', 'admin@example.com', '$2a$10$VuzyfOS4ZIz8akzJMF74NeIVMSNKC83I0RvOobyG0GJvLv8xfJUEO'); -- пароль: admin

-- связи users ↔ roles
INSERT INTO users_roles (user_id, role_id) VALUES
                                               (1, 1), -- user → ROLE_USER
                                               (2, 1), -- admin → ROLE_USER
                                               (2, 2); -- admin → ROLE_ADMIN
