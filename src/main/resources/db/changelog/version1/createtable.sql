create table person (
    id int primary key generated always as identity,
    name varchar (30) not null
)