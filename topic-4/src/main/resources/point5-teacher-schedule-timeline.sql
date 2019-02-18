USE high_school;

DELIMITER $$
CREATE PROCEDURE teacher_schedule (IN in_teacher_id INT UNSIGNED)
BEGIN
    /* Teacher */
    SELECT last_name, first_name
    FROM Teacher
    WHERE id = in_teacher_id;

    /* Schedule */
    SELECT CASE S.day   
		    WHEN 0 THEN "Monday"
        WHEN 1 THEN "Tuesday"
        WHEN 2 THEN "Wednesday"
        WHEN 3 THEN "Thursday"
        WHEN 4 THEN "Friday"
        WHEN 5 THEN "Saturday"
        WHEN 6 THEN "Sunday"
    END AS day, S.time_start, S.time_end, C.name                
    FROM Dictated D JOIN Course C
            ON D.course_name = C.name
        JOIN Schedule S
            ON D.schedule_id = S.id
    WHERE C.teacher_id = in_teacher_id
    ORDER BY S.day ASC, S.time_start ASC;
END$$

DELIMITER ;

CALL teacher_schedule(1);