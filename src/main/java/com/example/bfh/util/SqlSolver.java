package com.example.bfh.util;

public class SqlSolver {

    public static String solveForRegNo(String regNo) {
        return """SELECT e1.employee_id AS EMP_ID,e1.first_name AS FIRST_NAME,e1.last_name AS LAST_NAME,d.department_name AS DEPARTMENT_NAME,COUNT(e2.employee_id) AS YOUNGER_EMPLOYEES_COUNT FROM employees e1 JOIN departments d ON e1.department_id = d.department_id LEFT JOIN employees e2 ON e1.department_id = e2.department_id AND e2.age < e1.age GROUP BY e1.employee_id, e1.first_name, e1.last_name, d.department_name ORDER BY e1.employee_id DESC;""";
        
    }
}
