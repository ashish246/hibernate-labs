drop table if exists test.student;

create table test.student (
        STUDENT_ID integer not null auto_increment,
        STUDENT_NAME varchar(10) not null,
        STUDENT_Age varchar(20) not null,
        primary key (STUDENT_ID)
    );
    
alter table test.student 
        add constraint UK_29imo5uwna3jq2mqnfkjumw8n  unique (STUDENT_NAME);
    
    