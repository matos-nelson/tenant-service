-- tenant info 1
insert into tenant(id, manager_id, property_id, full_name, email, phone) values (100, 'auth_user', 1, 'First Tenant', 'firsttenant@email.com', '1234445555');
insert into tenant_vehicle(id, tenant_id, make, model, year_made, color, license_num) values (200, 100, 'Nissan', 'Rogue', 2000, 'Blue', 'AAA-123');
insert into tenant_pet(id, tenant_id, name, breed, weight, age) values (300, 100, 'Dog', 'Boxer', 15, 2);
insert into tenant_occupant(id, tenant_id, first_name, last_name, date_of_birth) values (400, 100, 'First', 'Occupant', '2010-10-10');

-- tenant info 2
insert into tenant(id, manager_id, property_id, full_name, email, phone) values (300, 'update_user', 1909, 'Update Tenant', 'updatetenant@email.com', '1231234444');