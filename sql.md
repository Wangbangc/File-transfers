create table departments
(
    DepartmentID   int auto_increment
        primary key,
    DepartmentName varchar(255) not null
);

create table school_user
(
    UserID       int auto_increment
        primary key,
    Username     varchar(255)                                                                                    not null,
    Password     varchar(255)                                                                                    not null,
    Role         enum ('SuperAdmin', 'DepartmentAdmin', 'HighLevelLeader', 'MiddleLevelLeader', 'OrdinaryStaff') not null,
    DepartmentID int                                                                                             null,
    Name         varchar(255)                                                                                    null,
    constraint Username
        unique (Username),
    constraint school_user_ibfk_1
        foreign key (DepartmentID) references departments (departmentid)
            on update cascade on delete cascade
);

create table files
(
    FileID       int auto_increment
        primary key,
    FileName     varchar(255)                        not null,
    FileType     varchar(255)                        not null,
    FileContent  blob                                null,
    PublisherID  int                                 null,
    PublishTime  timestamp default CURRENT_TIMESTAMP null,
    SerialNumber varchar(255)                        not null,
    constraint files_pk
        unique (SerialNumber),
    constraint files_ibfk_1
        foreign key (PublisherID) references school_user (userid)
);

create table filepermissions
(
    FileID         varchar(255)                                                                                    not null,
    DepartmentID   int                                                                                             not null,
    PermissionType enum ('SuperAdmin', 'DepartmentAdmin', 'HighLevelLeader', 'MiddleLevelLeader', 'OrdinaryStaff') not null,
    primary key (FileID, DepartmentID),
    constraint filepermissions_files_SerialNumber_fk
        foreign key (FileID) references files (serialnumber)
            on update cascade on delete cascade,
    constraint filepermissions_ibfk_2
        foreign key (DepartmentID) references departments (departmentid)
);

create index DepartmentID
    on filepermissions (DepartmentID);

create table filereceptions
(
    FileID        int                                 not null,
    UserID        int                                 not null,
    ReceptionTime timestamp default CURRENT_TIMESTAMP null,
    primary key (FileID, UserID),
    constraint filereceptions_ibfk_1
        foreign key (FileID) references files (fileid),
    constraint filereceptions_ibfk_2
        foreign key (UserID) references school_user (userid)
);

