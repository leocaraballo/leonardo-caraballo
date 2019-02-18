USE high_school;

/*
 * A student can only pass a given course if the average grade in they three partial notes are
 * greater than or equal to 6.0, AND if the final note is also greater than or equal to 6.0.
 *
 * Returns percentage of students that passed the given course.
 */
DELIMITER $$
CREATE FUNCTION percentage_passed (in_course_name VARCHAR(50),
                                   in_year YEAR)
RETURNS DECIMAL(5,2)
BEGIN
    SET @total  := (SELECT COUNT(*) FROM Assist WHERE course_name = in_course_name AND year = in_year);
    SET @passed := (SELECT COUNT(*)
                    FROM Assist
                    WHERE course_name = in_course_name AND year = in_year AND 
                         (grade_1 + grade_2 + grade_3) / 3 > 6 AND
                          grade_final > 6);
    RETURN @passed / @total * 100;
END$$

DELIMITER ;

/*
 * Returns percentage of students that failed the given course.
 */
DELIMITER $$
CREATE FUNCTION percentage_failed (in_course_name VARCHAR(50),
                                   in_year YEAR)
RETURNS DECIMAL(5,2)
BEGIN
    /* Passed */
    RETURN 100.0 - percentage_passed(in_course_name, in_year);
END$$

DELIMITER ;

SELECT percentage_passed('Introduction to Computer Science', 2018);
SELECT percentage_passed('Discrete Mathematics', 2018);
SELECT percentage_passed('Fundamentals of Programming', 2018);

SELECT percentage_failed('Introduction to Computer Science', 2018);
SELECT percentage_failed('Discrete Mathematics', 2018);
SELECT percentage_failed('Fundamentals of Programming', 2018);