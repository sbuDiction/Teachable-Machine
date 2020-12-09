    create table if not exists players(
    id int primary key not null,
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255) not null
);


create table if not exists poses(
    id int primary key not null,
    name varchar(255) not null
);

create table if not exists routines(
    id int primary key not null,
    name varchar(255) not null,
    list_pose_ids integer[]
);

create table if not exists player_pose_scores(
    id int primary key not null,
    player_id int not null,
    pose_id int not null,
    score int not null,
    FOREIGN KEY (player_id) REFERENCES players(id),
    FOREIGN KEY (pose_id) REFERENCES poses(id)
);

create table if not exists sessions(
    id int primary key not null,
    player_pose_scores_id int,
    FOREIGN KEY (player_pose_scores_id) REFERENCES player_pose_scores(id)
);