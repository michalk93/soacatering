CREATE TABLE companies (
  company_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL UNIQUE ,
  auth_code VARCHAR(128) NOT NULL,

  PRIMARY KEY (company_id)
);