package br.univali.tortelli.employee;

import br.univali.tortelli.validator.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Subclasse de Funcionários
 */
public class Director extends Employees {
    private final BigDecimal bonus = new BigDecimal(0.05);
    private int yearsDirector;
    private final BigDecimal workingHours = new BigDecimal(160);
    private final BigDecimal overtime = new BigDecimal(0.5);

    /**
     * Construtor da classe
     * @param name nome
     * @param department departamento
     * @param baseSalary salário base
     * @param hoursWorked horas trabalhadas
     * @param yearsDirector anos de trabalho como diretor
     * @param month mês
     * @param type tipo de funcionário
     */
    public Director(String name, CompanyDepartaments department, double baseSalary, int hoursWorked, int yearsDirector, Month month, TypeEmployee type){
        super(name, department, baseSalary, hoursWorked, month, type);
        Validator validator = new Validator();
        validator.validateYearsDirector(yearsDirector);
        this.yearsDirector = yearsDirector;
    }

    /**
     * @return anos de trabalho como diretor
     */
    public int getYearsDirector() {
        return yearsDirector;
    }

    /**
     * Calcula salário intermediário do Diretor sem impostos e inss
     * @param hourWorked horas trabalhadas no mês
     * @param baseSalary salário base
     * @return salário intermediário
     */
    @Override
    public BigDecimal setSalary(int hourWorked, double baseSalary){
        BigDecimal base = new BigDecimal(baseSalary);
        BigDecimal hour = new BigDecimal(hourWorked);
        BigDecimal years = new BigDecimal(getYearsDirector());
        BigDecimal maxYears = new BigDecimal(8);
        BigDecimal sal = new BigDecimal(0.00);
        if(hour.compareTo(new BigDecimal(144))<=0){
            sal = hour.multiply(getSalaryHour()).setScale(2, RoundingMode.HALF_EVEN);
        }
        if(hour.compareTo(new BigDecimal(144))>0 && hour.compareTo(new BigDecimal(160))<=0){
            sal = base.setScale(2, RoundingMode.HALF_EVEN);
        }
        if(hour.compareTo(new BigDecimal(160))>0) {
            BigDecimal moreHours;
            moreHours = hour.subtract(workingHours);
            sal = base.add(overtime.multiply(getSalaryHour().multiply(moreHours))).setScale(2, RoundingMode.HALF_EVEN);
        }
        if(getYearsDirector()<=8){
            return sal.add(sal.multiply(years.multiply(bonus)).setScale(2, RoundingMode.HALF_EVEN));
        } else{
            return sal.add(sal.multiply(maxYears.multiply(bonus)).setScale(2, RoundingMode.HALF_EVEN));
        }

    }

    /**
     * Calcula o imposto de renda sobre o saláro intermediário do Diretor
     * @return imposto de renda
     */
    @Override
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
        if (salary.compareTo(new BigDecimal(2995.71)) >= 0 && salary.compareTo(new BigDecimal(3743.19)) <= 0) {
            return salary.multiply(new BigDecimal(0.225)).setScale(2, RoundingMode.HALF_EVEN);
        }
        if (salary.compareTo(new BigDecimal(3743.19)) > 0) {
            return salary.multiply(new BigDecimal(0.275)).setScale(2, RoundingMode.HALF_EVEN);
        }
        return null;
    }

    /**
     * Calcula inss do salário do diretor
     * @return inss
     */
    @Override
    public BigDecimal setSocialInsurance(){
        BigDecimal salary = setSalary(getHoursWorked(),getBaseSalary());
        if(salary.multiply(new BigDecimal(0.11)).compareTo(new BigDecimal(482.93))<=0){
            return salary.multiply(new BigDecimal(0.11)).setScale(2, RoundingMode.HALF_EVEN);
        } else{
            return new BigDecimal(482.93).setScale(2, RoundingMode.HALF_EVEN);
        }
    }

    /**
     * Calcula salário líquido do diretor
     * @return salário líquido
     */
    @Override
    public BigDecimal setNetSalary(){
        BigDecimal salary = setSalary(getHoursWorked(),getBaseSalary());
        BigDecimal incomeTax = setIncomeTax();
        BigDecimal socialInsurance = setSocialInsurance();
        return salary.subtract(incomeTax).subtract(socialInsurance).setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Guarda o contracheque do diretor com todas as informações
     */
    @Override
    public void paycheck(){
        System.out.println("Paycheck ");
        System.out.println("Name: " + getName() + " - " + getType() + " - " + getDepartment() + " - " + getMonth());
        System.out.println("Hour Worked : " + getHoursWorked());
        System.out.println("Salary Hour:" + getSalaryHour());
        System.out.println("Years Director: " + getYearsDirector());
        System.out.println("Base Salary: " + getBaseSalary());
        System.out.println("Salary: " + setSalary(getHoursWorked(),getBaseSalary()));
        System.out.println("Income Tax: " + setIncomeTax());
        System.out.println("Social Insurance: " + setSocialInsurance());
        System.out.println("Net Salary: " + setNetSalary());
        System.out.println("*************************");
    }


}
