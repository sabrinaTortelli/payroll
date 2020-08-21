package br.univali.tortelli.employee;

import br.univali.tortelli.compare.CompareSalary;
import br.univali.tortelli.validator.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Super classe de funcionários
 */
public abstract class Employees {
    private CompanyDepartaments department;
    private final String name;
    private TypeEmployee type;
    private final int hoursWorked;
    private final double baseSalary;
    private final BigDecimal workingHours = new BigDecimal(160);
    private final BigDecimal overtime = new BigDecimal(0.5);
    private Month month;
    private BigDecimal salaryHour;

    /**
     * Construtor da classe
     * @param name nome
     * @param department departamento do funcionário
     * @param baseSalary salário base
     * @param hoursWorked horas trabalhadas por mês
     * @param month meses do ano
     * @param type tipo de funcionário
     */
    public Employees(String name, CompanyDepartaments department, double baseSalary, int hoursWorked, Month month, TypeEmployee type){
        Validator validator = new Validator();
        validator.validateName(name);
        validator.validateBaseSalary(baseSalary);
        validator.validateHoursWorked(hoursWorked);
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
        this.hoursWorked = hoursWorked;
        this.month = month;
        this.type = type;
    }

    /**
     * @return departamento
     */
    public CompanyDepartaments getDepartment() {
        return department;
    }

    /**
     * Classe não utilizada no programa, mas poderá ser se funcionário tiver seu departamento alterado
     * @param department departamento
     */
    public void setDepartment(CompanyDepartaments department) {
        this.department = department;
    }

    /**
     * @return nome
     */
    public String getName() {
        return name;
    }

    /**
     * @return tipo do funcionário
     */
    public TypeEmployee getType() {
        return type;
    }

    /**
     * Classe não utilizada no programa, mas poderá ser se funcionário tiver seu cargo alterado
     * @param type tipo de funcionário
     */
    public void setType(TypeEmployee type) {
        this.type = type;
    }

    /**
     * @return mês do ano
     */
    public Month getMonth() {
        return month;
    }

    /**
     * @return horas trabalhadas no mês
     */
    public int getHoursWorked() {
        return hoursWorked;
    }

    /**
     * @return salário base
     */
    public double getBaseSalary() {
        return baseSalary;
    }

    /**
     * Calcula salário por hora
     * @param baseSalary salário base
     */
    public void setSalaryHour(double baseSalary) {
        BigDecimal base = new BigDecimal(baseSalary);
        this.salaryHour = (base.divide(workingHours)).setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * @return salário por hora
     */
    public BigDecimal getSalaryHour() {
        return salaryHour;
    }

    /**
     * Calcula salário itermediário, sem impostos e inss
     * @param hourWorked horas trabalhadas no mês
     * @param baseSalary salário base
     * @return salário intermediário
     */
    public BigDecimal setSalary(int hourWorked, double baseSalary){
        BigDecimal base = new BigDecimal(baseSalary);
        BigDecimal hour = new BigDecimal(hourWorked);
        if(hour.compareTo(new BigDecimal(144))<=0){
            return hour.multiply(getSalaryHour()).setScale(2, RoundingMode.HALF_EVEN);
        }
        if(hour.compareTo(new BigDecimal(144))>0 && hour.compareTo(new BigDecimal(160))<=0){
            return base.setScale(2, RoundingMode.HALF_EVEN);
        }
        if(hour.compareTo(new BigDecimal(160))>0){
            BigDecimal moreHours;
            moreHours = hour.subtract(workingHours);
            return base.add(overtime.multiply(getSalaryHour().multiply(moreHours))).setScale(2, RoundingMode.HALF_EVEN);
        }
        return new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Calcula Salário Líquido = salário intermediário - (imposto de renda + inss)
     * @return salário líquido
     */
    public BigDecimal setNetSalary(){
        BigDecimal salary = setSalary(getHoursWorked(),getBaseSalary());
        BigDecimal incomeTax = setIncomeTax();
        BigDecimal socialInsurance = setSocialInsurance();
        return salary.subtract(incomeTax).subtract(socialInsurance).setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Calcula imposto de renda sobre o salário intermediário
     * @return imposto de renda
     */
    public BigDecimal setIncomeTax(){
        BigDecimal salary = setSalary(getHoursWorked(),getBaseSalary());
        if (salary.compareTo(new BigDecimal(1499.15)) <= 0) {
            return new BigDecimal(0).setScale(2, RoundingMode.HALF_EVEN);
        }
        if (salary.compareTo(new BigDecimal(1499.16)) >= 0 && salary.compareTo(new BigDecimal(2246.75)) <= 0) {
            return salary.multiply(new BigDecimal(0.075)).setScale(2, RoundingMode.HALF_EVEN);
        }
        if (salary.compareTo(new BigDecimal(2246.76)) >= 0 && salary.compareTo(new BigDecimal(2995.70)) <= 0) {
            return salary.multiply(new BigDecimal(0.15)).setScale(2, RoundingMode.HALF_EVEN);
        }
        if (salary.compareTo(new BigDecimal(2995.71)) >= 0 && salary.compareTo(new BigDecimal(23743.19)) <= 0) {
            return salary.multiply(new BigDecimal(0.225)).setScale(2, RoundingMode.HALF_EVEN);
        }
        if (salary.compareTo(new BigDecimal(3743.19)) > 0) {
            return salary.multiply(new BigDecimal(0.275)).setScale(2, RoundingMode.HALF_EVEN);
        }
        return null;
    }

    /**
     * Calcula o inss sobre o salário intermediário
     * @return inss
     */
    public BigDecimal setSocialInsurance(){
        BigDecimal salary = setSalary(getHoursWorked(),getBaseSalary());
        if(salary.multiply(new BigDecimal(0.11)).compareTo(new BigDecimal(482.93))<=0){
            return salary.multiply(new BigDecimal(0.11)).setScale(2, RoundingMode.HALF_EVEN);
        } else{
            return new BigDecimal(482.93).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    /**
     * Remove Funcionários da lista pelo nome
     * @param name nome
     * @param employees lista de funcionários
     */
    public void removeEmployee(String name, ArrayList<Employees> employees){
        for(int i=0; i<employees.size(); i++){
            if(name.equals(employees.get(i).getName())){
                employees.remove(i);
            }
        }
    }

    /**
     * Encontra o funcionário pelo nome e mês
     * @param name nome
     * @param month mês
     */
    public void findEmployeeName(String name, Month month) {
        if (name.equals(getName()) && month == getMonth()) {
            System.out.println("Name: " + getName() + " - " + getType() + " - " +
                    setNetSalary() + " - " + getMonth());
        }
    }

    /**
     * Encontra o empregado pelo tipo e mês
     * @param type tipo
     * @param month mês
     */
    public void findEmployee(TypeEmployee type, Month month){
        if(type == getType() && month == getMonth()){
            System.out.println("Name: " + getName() + " - " + getType() + " - " + getMonth() + " - " + setNetSalary());
        }
    }

    /**
     * Mostra os funcionários dos departamentos
     * @param department departamento
     * @param month mês
     */
    public void showEmployeeDepartment(CompanyDepartaments department, Month month){
        if(department == getDepartment() && month == getMonth()){
            System.out.println("Name: " + getName() + " - " + getType() + " - " + getDepartment() + " - " + getMonth() + " - "  + setNetSalary());
        }
    }

    /**
     * Guarda todos os dados dos funcionários (contracheque)
     */
    public void paycheck(){
        System.out.println("Paycheck ");
        System.out.println("Name: " + getName() + " - " + getType() + " - " + getDepartment() + " - " + getMonth());
        System.out.println("Hour Worked : " + getHoursWorked());
        System.out.println("Salary Hour:" + getSalaryHour());
        System.out.println("Base Salary: " + getBaseSalary());
        System.out.println("Salary: " + setSalary(getHoursWorked(), getBaseSalary()));
        System.out.println("Income Tax: " + setIncomeTax());
        System.out.println("Social Insurance: " + setSocialInsurance());
        System.out.println("Net Salary: " + setNetSalary());
        System.out.println("*************************");
    }

    /**
     * Mostra o salário líquido do empregado
     */
    public void showEmployeeSalary(){
        System.out.println("Salario do mes: " + getMonth());
        System.out.println(getName() + ": " + setNetSalary());
        System.out.println("*************************");
    }

    /**
     * Mostra o funcionário com o maior salário ordenando a lista de funcionários
     * @param employees lista de funcionários
     */
    public void showEmployeeMostSalary(ArrayList<Employees> employees){
        employees.sort(new CompareSalary());
        System.out.println("Funcionario com mais alto salario: " + employees.get(employees.size()-1).getName() +
                " - " + employees.get(employees.size()-1).setNetSalary() + " - " + employees.get(employees.size()-1).getType() +
                " - " + employees.get(employees.size()-1).getDepartment());
    }

}
