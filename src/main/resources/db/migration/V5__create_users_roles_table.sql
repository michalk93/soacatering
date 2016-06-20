# START TRANSACTION;
# CREATE TABLE users_roles (
#   users_roles_id INT AUTO_INCREMENT NOT NULL,
#   user_id INT NOT NULL,
#   role_id INT NOT NULL,
#   PRIMARY KEY (users_roles_id),
#   FOREIGN KEY (user_id) REFERENCES users(user_id),
#   FOREIGN KEY (role_id) REFERENCES roles(role_id)
# );
#
#
# CREATE VIEW user_role_map_view AS
#   SELECT users_roles.users_roles_id,
#     users.email,
#     roles.name
#     FROM users_roles
#   LEFT JOIN users ON users.user_id = users_roles.user_id
#   LEFT JOIN roles ON roles.role_id = users_roles.role_id;
# COMMIT;