package br.univali.tortelli.validator;

/**
 * Classe de validação dos atributos
 */
public class Validator {

    /**
     * Verifica se atributo nome é nulo ou vazio
     * @param name nome
     */
    public void validateName(String name) {
        if(name == null || name.equals(" ")){
            throw new IllegalArgumentException("Name must not be empty");
        }
    }

    /**
     * Validação de salário base - Não poderá ser menor que salário mínimo fixado no país
     * @param baseSalary salário base
     */
    public void validateBaseSalary(double baseSalary) {
        if(baseSalary <= 1045){
            throw new IllegalArgumentException("Base Salary must be greater than or equal to the Minimum Wage ");
        }
    }

    /**
     * Valida horas trabalhadas - não poderá se menor que 0
     * @param hoursWorked horas trabalhadas
     */
    public void validateHoursWorked(int hoursWorked) {
        if(hoursWorked < 0){
            throw new IllegalArgumentException("Hours must be positive");
        }
    }

    /**
     * Valida anos de trabalho como diretor - não poderá ser menor que 0
     * @param yearsDirector anos de trabalho de diretor
     */
    public void validateYearsDirector(int yearsDirector) {
        if(yearsDirector < 0){
            throw new IllegalArgumentException("Years must be positive");
        }
    }

}
