INSERT INTO country (name, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Singapore', 'system', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP());
INSERT INTO country (name, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Australia', 'system', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP());

INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Singapore', (select id from country where name='Singapore'), 'system', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP());
INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Victoria', (select id from country where name='Australia'), 'system', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP());
INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('NSW', (select id from country where name='Australia'), 'system', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP())
INSERT INTO state (name, country_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('NT', (select id from country where name='Australia'), 'system', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP())

INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Singapore', (select id from state where name='Singapore'), 'system', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP());
INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Melbourne', (select id from state where name='Victoria'), 'system', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP());
INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Sydney', (select id from state where name='NSW'), 'system', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP());
INSERT INTO city (name, state_id, created_by, created_date, last_modified_by, last_modified_date) VALUES ('Darwin', (select id from state where name='NT'), 'system', CURRENT_TIMESTAMP(), 'system', CURRENT_TIMESTAMP());
