create table Folders (
       id integer not null auto_increment,
        is_folder bit not null,
        name varchar(255),
        parent_id integer not null,
        primary key (id)
    ) engine=InnoDB;

    alter table Folders 
       add constraint FKmirvgqnksrfdqxmwtdcq6sugx 
       foreign key (Parent_id) 
       references Folders (id)