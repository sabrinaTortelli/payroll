package br.univali.tortelli.test;

import br.univali.tortelli.employee.*;

import java.util.ArrayList;

/**
 * Teste das classes
 */
public class TestPayroll {
    private ArrayList<Employees> employees;
    private Employee employee;
    private Director director;

    /**
     * Cria os empregados e adiciona numa lista
     */
    private void createEmployees(){
        employees = new ArrayList<>();
        employee = new Employee("Renato", CompanyDepartaments.ADMINISTRATIVE, 2560.00, 157, Month.JANUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        employee = new Employee("Hermes", CompanyDepartaments.ADMINISTRATIVE, 3670.00, 168, Month.JANUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        director = new Director("Fabiana", CompanyDepartaments.ADMINISTRATIVE, 7000.00,
                175, 2, Month.JANUARY, TypeEmployee.DIRECTOR);
        employees.add(director);
        employee = new Employee("Leonardo", CompanyDepartaments.COMMERCIAL, 2000.00, 145, Month.JANUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        employee = new Employee("Teodora", CompanyDepartaments.COMMERCIAL, 2845.00, 160, Month.JANUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        director = new Director("Francisco", CompanyDepartaments.COMMERCIAL, 5500.00,
                168, 5, Month.JANUARY, TypeEmployee.DIRECTOR);
        employees.add(director);
        employee = new Employee("Jeremias", CompanyDepartaments.FINANCIAL, 3510.56, 154, Month.JANUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        employee = new Employee("Jéssica", CompanyDepartaments.FINANCIAL, 2694.00, 126, Month.JANUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        director = new Director("Paula", CompanyDepartaments.FINANCIAL, 7500.00,
                152, 2, Month.JANUARY, TypeEmployee.DIRECTOR);
        employees.add(director);
        employee = new Employee("Marta", CompanyDepartaments.OPERATIONAL, 2594.00, 163, Month.JANUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        employee = new Employee("Graciane", CompanyDepartaments.OPERATIONAL, 3691.00, 160, Month.JANUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        director = new Director("Fernanda", CompanyDepartaments.OPERATIONAL, 5240.00,
                135, 4, Month.JANUARY, TypeEmployee.DIRECTOR);
        employees.add(director);
        employee = new Employee("Renato", CompanyDepartaments.ADMINISTRATIVE, 2560.00, 163,Month.FEBRUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        employee = new Employee("Hermes", CompanyDepartaments.ADMINISTRATIVE, 3670.00, 150, Month.FEBRUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        director = new Director("Fabiana", CompanyDepartaments.ADMINISTRATIVE, 7000.00,
                152, 2, Month.FEBRUARY, TypeEmployee.DIRECTOR);
        employees.add(director);
        employee = new Employee("Leonardo", CompanyDepartaments.COMMERCIAL, 1987.00, 168, Month.FEBRUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        employee = new Employee("Teodora", CompanyDepartaments.COMMERCIAL, 2845.00, 173, Month.FEBRUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        director = new Director("Francisco", CompanyDepartaments.COMMERCIAL, 5500.00,
                178, 05, Month.FEBRUARY, TypeEmployee.DIRECTOR);
        employees.add(director);
        employee = new Employee("Jeremias", CompanyDepartaments.FINANCIAL, 3510.56, 168, Month.FEBRUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        employee = new Employee("Jéssica", CompanyDepartaments.FINANCIAL, 2694.00, 160, Month.FEBRUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        director = new Director("Paula", CompanyDepartaments.FINANCIAL, 7500.00,
                159, 2, Month.FEBRUARY, TypeEmployee.DIRECTOR);
        employees.add(director);
        employee = new Employee("Marta", CompanyDepartaments.OPERATIONAL, 2594.00, 158, Month.FEBRUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        employee = new Employee("Graciane", CompanyDepartaments.OPERATIONAL, 3691.00, 169, Month.FEBRUARY, TypeEmployee.EMPLOYEE);
        employees.add(employee);
        director = new Director("Fernanda", CompanyDepartaments.OPERATIONAL, 5240.00,
                179, 04, Month.FEBRUARY, TypeEmployee.DIRECTOR);
        employees.add(director);
    }

    /**
     * Calcula os salários
     */
    private void testNetSalary(){
        for(int i=0; i<employees.size(); i++) {
            employees.get(i).setSalaryHour(employees.get(i).getBaseSalary());
            employees.get(i).setSocialInsurance();
            employees.get(i).setIncomeTax();
            employees.get(i).setNetSalary();
        }

        for (int i=0; i<employees.size(); i++){
            employees.get(i).showEmployeeSalary();
        }
        System.out.println("******************");
    }

    /**
     * Testa a exclusão de um funcionário
     */
    private void testExcludeEmployees(){
        for (int i=0; i<employees.size(); i++){
            employees.get(i).removeEmployee("Graciane", employees);
        }
        for (int i=0; i<employees.size(); i++){
            employees.get(i).showEmployeeSalary();
        }
        System.out.println("*****************************");
    }

    /**
     * Acha um funcionário
     */
    private void testFindEmployees(){
        for (int i=0; i<employees.size(); i++){
            employees.get(i).findEmployeeName("Graciane", Month.JANUARY);
        }
        System.out.println("*****************************");
        for (int i=0; i<employees.size(); i++){
            employees.get(i).findEmployee(TypeEmployee.EMPLOYEE, Month.FEBRUARY);
        }
        System.out.println("*****************************");
        for (int i=0; i<employees.size(); i++){
            employees.get(i).findEmployee(TypeEmployee.DIRECTOR, Month.JANUARY);
        }
        System.out.println("*****************************");
    }

    /**
     * Lista os empregados do departamento
     */
    private void testShowEmployeeDepartment(){
        for (int i=0; i<employees.size(); i++){
            employees.get(i).showEmployeeDepartment(CompanyDepartaments.ADMINISTRATIVE, Month.JANUARY);
        }
        System.out.println("*****************************");
    }

    /**
     * Mostra na tela os contracheques dos funcionários
     */
    private void testPaycheck(){
        for (int i=0; i<employees.size(); i++){
            employees.get(i).paycheck();
        }
        System.out.println("*****************************");
    }

    /**
     * Acha o funcionário com maior salário
     */
    private void testMostSalary(){
        employee.showEmployeeMostSalary(employees);
    }

    public static void main(String[] args) {
        TestPayroll test = new TestPayroll();
        test.createEmployees();
        test.testNetSalary();
        test.testExcludeEmployees();
        test.testFindEmployees();
        test.testShowEmployeeDepartment();
        test.testPaycheck();
        test.testMostSalary();
    }

}
