CREATE DATABASE IF NOT EXISTS high_school;

USE high_school;

CREATE TABLE Teacher(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE Student(
    /*
     * It is assumed that each student will have a different registration number
     */
    registration_number INT UNSIGNED NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,

    PRIMARY KEY(registration_number)
);

CREATE TABLE Course(
    /*
     * It is assumed that each course will have a different name.
     */
    name VARCHAR(50) NOT NULL,
    /*
     * The hours per week of a course could be derived from its Schedule
     * relationships, so it is not necessary to store it. But here I choose
     * to do it, optimizing the performance.
     */
    week_hours TINYINT UNSIGNED NOT NULL,
    teacher_id INT UNSIGNED NOT NULL,

    PRIMARY KEY(name),
    FOREIGN KEY(teacher_id) REFERENCES Teacher(id)
);

CREATE TABLE Assist(
    student_registration_number INT UNSIGNED NOT NULL,
    course_name VARCHAR(50) NOT NULL,
    year YEAR NOT NULL,
    grade_1 TINYINT UNSIGNED,
    grade_2 TINYINT UNSIGNED,
    grade_3 TINYINT UNSIGNED,
    grade_final TINYINT UNSIGNED,

    PRIMARY KEY(student_registration_number, course_name, year),
    FOREIGN KEY(student_registration_number) REFERENCES Student(registration_number),
    FOREIGN KEY(course_name) REFERENCES Course(name)
);

CREATE TABLE Schedule(
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    /*
     * 0 -> Monday
     * ...
     * 6 -> Sunday
     */
    day TINYINT UNSIGNED NOT NULL,
    time_start TIME NOT NULL,
    time_end TIME NOT NULL,

    PRIMARY KEY(id),
    UNIQUE(day, time_start, time_end)
);

CREATE TABLE Dictated(
    course_name VARCHAR(50) NOT NULL,
    schedule_id INT UNSIGNED NOT NULL,

    PRIMARY KEY(course_name, schedule_id),
    FOREIGN KEY(course_name) REFERENCES Course(name),
    FOREIGN KEY(schedule_id) REFERENCES Schedule(id)
);


