create table customer
(
    id          varchar(255) not null
        primary key,
    firstname   varchar(255) not null,
    lastname    varchar(255) not null,
    email       varchar(255) not null,
    phone       varchar(255) not null,
    password    varchar(255) not null,
    balance     decimal      null,
    create_time datetime     not null,
    constraint customer_ukey1
        unique (email),
    constraint customer_ukey2
        unique (phone)
);

create table manager
(
    id          varchar(255) not null
        primary key,
    firstname   varchar(255) not null,
    lastname    varchar(255) not null,
    email       varchar(255) not null,
    phone       varchar(255) not null,
    create_time datetime     not null,
    password    varchar(255) not null,
    constraint manager_uk1
        unique (email),
    constraint manager_uk2
        unique (phone)
);

create table operator
(
    id          varchar(255) not null
        primary key,
    firstname   varchar(255) not null,
    lastname    varchar(255) not null,
    email       varchar(255) not null,
    phone       varchar(255) not null,
    password    varchar(255) not null,
    create_time datetime     not null,
    constraint staff_uk1
        unique (email),
    constraint staff_uk2
        unique (phone)
);

create table report
(
    id          varchar(255) not null
        primary key,
    create_time datetime     not null,
    create_by   varchar(255) not null,
    title       varchar(255) not null,
    location    varchar(255) not null,
    constraint report_staff_null_fk
        foreign key (create_by) references operator (id)
);

create table station
(
    id        varchar(255) not null
        primary key,
    longitude double       not null,
    latitude  double       not null,
    name      varchar(255) not null,
    constraint station_uk
        unique (name)
);

create table topup
(
    id               varchar(255) not null
        primary key,
    user_id          varchar(255) not null,
    create_time      datetime     not null,
    original_balance decimal      not null,
    current_balance  decimal      not null,
    amount           decimal      not null,
    constraint topup_customer_null_fk
        foreign key (user_id) references customer (id)
);

create table vehicle_type
(
    id          int auto_increment
        primary key,
    vtype       varchar(255) not null,
    create_time varchar(255) not null,
    create_by   varchar(255) not null,
    price       decimal      not null,
    constraint vehicle_type_pk
        unique (vtype),
    constraint vehicle_type_staff_null_fk
        foreign key (create_by) references operator (id)
);

create table vehicle
(
    id                varchar(255)    not null
        primary key,
    vehicle_type      int             not null,
    status            int             not null,
    remaining_battery int default 100 not null,
    longitude         double          not null,
    latitude          double          not null,
    stationId         varchar(255)    null,
    constraint station_id
        foreign key (stationId) references station (id),
    constraint vehicle_vehicle_type_null_fk
        foreign key (vehicle_type) references vehicle_type (id)
);

create table charge_order
(
    id               varchar(255) not null
        primary key,
    vehicle_id       varchar(255) not null,
    current_location varchar(255) not null,
    create_time      datetime     not null,
    end_time         datetime     null,
    status           int          not null,
    staff_id         varchar(255) null,
    constraint charge_order_staff_null_fk
        foreign key (staff_id) references operator (id),
    constraint charge_order_vehicle_null_fk
        foreign key (vehicle_id) references vehicle (id)
);

create table move_order
(
    id               varchar(255) not null
        primary key,
    vehicle_id       varchar(255) not null,
    current_location varchar(255) not null,
    end_location     varchar(255) null,
    create_time      datetime     not null,
    end_time         datetime     null,
    status           int          not null,
    staff_id         varchar(255) null,
    constraint charge_order1_staff_null_fk
        foreign key (staff_id) references operator (id),
    constraint charge_order1_vehicle_null_fk
        foreign key (vehicle_id) references vehicle (id)
);

create table report_damage_order
(
    id               varchar(255) not null
        primary key,
    vehicle_id       varchar(255) not null,
    customer_id      varchar(255) not null,
    damage_info      varchar(255) not null,
    current_location varchar(255) not null,
    create_time      datetime     not null,
    end_time         datetime     not null,
    status           int          not null,
    staff_id         varchar(255) null,
    constraint report_damage_order_customer_null_fk
        foreign key (customer_id) references customer (id),
    constraint report_damage_order_staff_null_fk
        foreign key (staff_id) references operator (id),
    constraint report_damage_order_vehicle_null_fk
        foreign key (vehicle_id) references vehicle (id)
);

create table order_record
(
    id               varchar(255) not null
        primary key,
    vehicle_id       varchar(255) not null,
    user_id          varchar(255) not null,
    payment_id       varchar(255) null,
    order_duration   float        null,
    total_price      decimal      null,
    start_location   varchar(255) not null,
    end_location     varchar(255) null,
    start_time       datetime     not null,
    end_time         datetime     null,
    status           int          not null,
    report_damage_id varchar(255) null,
    constraint order_record_customer_null_fk
        foreign key (user_id) references customer (id),
    constraint order_record_report_damage_order_null_fk
        foreign key (report_damage_id) references report_damage_order (id),
    constraint order_record_vehicle_null_fk
        foreign key (vehicle_id) references vehicle (id)
);

create table payment
(
    id          varchar(255) not null
        primary key,
    order_id    varchar(255) not null,
    user_id     varchar(255) not null,
    create_time datetime     not null,
    pay_time    datetime     null,
    total_price decimal      not null,
    status      int          not null,
    constraint foreign_key_name
        foreign key (user_id) references customer (id),
    constraint payment_order_null_fk
        foreign key (order_id) references order_record (id)
);

alter table order_record
    add constraint order_record_payment_null_fk
        foreign key (payment_id) references payment (id);

