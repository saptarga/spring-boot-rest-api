-- Seeding Data Role
INSERT INTO sec_roles(name) VALUES('ROLE_USER');
INSERT INTO sec_roles(name) VALUES('ROLE_ADMIN');

-- Seeding Data User
insert into "user" (username, email, "password") values ('dwi_asih', 'dwi_asih@gmail.com', '$2a$10$eM3YLt9AgTs99NtgbbrQX.5lHqTIvYYfLPMozp6WiOotJzD9IWqau');
insert into "user" (username, email, "password") values ('sapta_arga', 'sapta_arga@gmail.com', '$2a$10$eM3YLt9AgTs99NtgbbrQX.5lHqTIvYYfLPMozp6WiOotJzD9IWqau');

-- Seeding Data User Role
insert into sec_user_roles (user_id, role_id) values (1, 1);
insert into sec_user_roles (user_id, role_id) values (2, 1);

-- Seeding Example Data User 1
insert into "user_balance" (user_id , balance, balance_achieve) values (1, 50000, 50000);
insert into "user_balance_history" (user_balance_id, balance_before, balance_after, activity, "type", author) values (1, 0, 50000, 'OPEN_AN_ACCOUNT', 'DEBIT', 'dwi_asih');
insert into "bank_balance" (balance, balance_achieve, code, "enable") values (50000, 50000, '3322030300020223', true);
insert into "bank_balance_history" (bank_balance_id , balance_before, balance_after, activity, "type", author) values (1, 0, 50000, 'OPEN_AN_ACCOUNT', 'DEBIT', 'dwi_asih');

-- Seeding Example Data User 2
insert into "user_balance" (user_id , balance, balance_achieve) values (2, 50000, 50000);
insert into "user_balance_history" (user_balance_id, balance_before, balance_after, activity, "type", author) values (2, 0, 50000, 'OPEN_AN_ACCOUNT', 'DEBIT', 'sapta_arga');
insert into "bank_balance" (balance, balance_achieve, code, "enable") values (50000, 50000, '3322030300020244', true);
insert into "bank_balance_history" (bank_balance_id , balance_before, balance_after, activity, "type", author) values (2, 0, 50000, 'OPEN_AN_ACCOUNT', 'DEBIT', 'sapta_arga');
