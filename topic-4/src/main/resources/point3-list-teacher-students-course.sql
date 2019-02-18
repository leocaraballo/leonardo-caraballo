USE high_school;

DELIMITER $$
CREATE PROCEDURE teachers_and_students_in_course (IN in_course_name VARCHAR(50),
                                                  IN in_year YEAR)
BEGIN
	/* Teacher */
	SELECT T.last_name AS teacher_last_name, T.first_name AS teacher_first_name
    FROM Teacher T JOIN Course C
    	ON T.id = C.teacher_id
    WHERE C.name = in_course_name;
    
    /* Students */
    SELECT S.last_name AS student_last_name, S.first_name AS student_first_name
    FROM Student S JOIN Assist A
    	ON S.registration_number = A.student_registration_number
    WHERE A.course_name = in_course_name AND A.year = in_year;
END$$

DELIMITER ;

CALL teachers_and_students_in_course('Introduction to Computer Science', 2018);