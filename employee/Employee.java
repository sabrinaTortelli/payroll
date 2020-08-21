package br.univali.tortelli.employee;


/**
 * Subclasse de Funcionários
 */
public class Employee extends Employees {
    /**
     * Construtor da classe
     * @param name nome
     * @param department departamento
     * @param baseSalary salário base
     * @param hoursWorked horas trabalhadas por mês
     * @param month mês
     * @param type tipo de funcionário
     */
    public Employee(String name, CompanyDepartaments department, double baseSalary, int hoursWorked, Month month, TypeEmployee type){
        super(name, department, baseSalary, hoursWorked, month, type);
    }
}
