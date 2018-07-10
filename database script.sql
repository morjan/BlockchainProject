CREATE TABLE UserRole(
	id_role INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	role_name VARCHAR(30)
);

CREATE TABLE UserLogin(
	id_login INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	id_role INTEGER NOT NULL,
	email VARCHAR(30) UNIQUE NOT NULL,
	password VARCHAR(100) NOT NULL
);
ALTER TABLE UserLogin ADD FOREIGN KEY (id_role) REFERENCES UserRole(id_role);

CREATE TABLE Currency(
	id_currency INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	currency_name VARCHAR(30),
	currency_code VARCHAR(30)
);

CREATE TABLE Rate(
	id_rate INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	currency_from INTEGER NOT NULL,
	currency_to INTEGER NOT NULL,
	rate DOUBLE
);
ALTER TABLE Rate ADD FOREIGN KEY (currency_from) REFERENCES Currency(id_currency);
ALTER TABLE Rate ADD FOREIGN KEY (currency_to) REFERENCES Currency(id_currency);

CREATE TABLE UserApplication(
	id_user INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	id_login INTEGER UNIQUE NOT NULL,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	birth_date DATE,
	phone_number VARCHAR(30),
	currency INTEGER NOT NULL,
	credit DOUBLE
);
ALTER TABLE UserApplication ADD FOREIGN KEY (id_login) REFERENCES UserLogin(id_login);
ALTER TABLE UserApplication ADD FOREIGN KEY (currency) REFERENCES Currency(id_currency);

CREATE TABLE UserAdmin(
	id_admin INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	id_login INTEGER UNIQUE NOT NULL,
	first_name VARCHAR(30),
	last_name VARCHAR(30),
	phone_number VARCHAR(30)
);
ALTER TABLE UserAdmin ADD FOREIGN KEY (id_login) REFERENCES UserLogin(id_login);

CREATE TABLE DebitCard(
	id_debitCard INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	id_user INTEGER NOT NULL,
	name_onCard VARCHAR(30),
	card_number VARCHAR(30),
	date_expire VARCHAR(30),
	cvc VARCHAR(30)
);
ALTER TABLE DebitCard ADD FOREIGN KEY (id_user) REFERENCES UserApplication(id_user);

CREATE TABLE BankDetails(
	id_bankDetails INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	id_user INTEGER NOT NULL,
	bank_name VARCHAR(30),
	name_account VARCHAR(30),
	sort_code VARCHAR(30),
	account_number VARCHAR(30)
);
ALTER TABLE BankDetails ADD FOREIGN KEY (id_user) REFERENCES UserApplication(id_user);

CREATE TABLE BalanceOperations(
	id_balanceOperation INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	id_user INTEGER NOT NULL,
	id_typeOperation INTEGER NOT NULL,
	id_currencyOperation INTEGER NOT NULL,
	date_balanceOperation TIMESTAMP,
	amount_balanceOperation DOUBLE
);
ALTER TABLE BalanceOperations ADD FOREIGN KEY (id_user) REFERENCES UserApplication(id_user);
ALTER TABLE BalanceOperations ADD FOREIGN KEY (id_typeOperation) REFERENCES TypeOperation(id_typeOperation);
ALTER TABLE BalanceOperations ADD FOREIGN KEY (id_currencyOperation) REFERENCES Currency(id_currency);

CREATE TABLE TypeOperation(
	id_typeOperation INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	name_typeOperation VARCHAR(30)
);

CREATE TABLE Transfer(
	id_transfer INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	user_sender INTEGER NOT NULL,
	user_recipient INTEGER NOT NULL,
	currency_sender INTEGER NOT NULL,
	currency_recipient INTEGER NOT NULL,
	rate DOUBLE,
	amount_sender DOUBLE,
	amount_recipient DOUBLE,
	date_transfer TIMESTAMP
);
ALTER TABLE Transfer ADD FOREIGN KEY (user_sender) REFERENCES UserApplication(id_user);
ALTER TABLE Transfer ADD FOREIGN KEY (user_recipient) REFERENCES UserApplication(id_user);
ALTER TABLE Transfer ADD FOREIGN KEY (currency_sender) REFERENCES Currency (id_currency);
ALTER TABLE Transfer ADD FOREIGN KEY (currency_recipient) REFERENCES Currency (id_currency);

CREATE TABLE CurrencyChange(
	id_currencyChange INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	id_user INTEGER NOT NULL,
	currency_from INTEGER NOT NULL,
	currency_to INTEGER NOT NULL,
	credit_from DOUBLE,
	credit_to DOUBLE,
	date_change TIMESTAMP
);
ALTER TABLE CurrencyChange ADD FOREIGN KEY (id_user) REFERENCES UserApplication(id_user);
ALTER TABLE CurrencyChange ADD FOREIGN KEY (currency_from) REFERENCES Currency (id_currency);
ALTER TABLE CurrencyChange ADD FOREIGN KEY (currency_to) REFERENCES Currency (id_currency);

CREATE TABLE ContactList(
	id_contact INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
	id_userList INTEGER NOT NULL,
	id_recipient INTEGER NOT NULL
);
ALTER TABLE ContactList ADD FOREIGN KEY (id_userList) REFERENCES UserApplication(id_user);
ALTER TABLE ContactList ADD FOREIGN KEY (id_recipient) REFERENCES UserApplication(id_user);