package br.univali.tortelli.compare;

import br.univali.tortelli.employee.Employees;

import java.util.Comparator;

/**
 * Classe de comparação de salário para fazer a ordenação do ArrayList
 */
public class CompareSalary implements Comparator<Employees> {

    /**
     * Compara dois funcionários pelo salário líquido
     * @param employees1 funcinário 1
     * @param employees2 funcionário 2
     * @return retorna um inteiro negativo, zero ou positivo se o primeiro argumento é menor, igual ou maior o segundo respectivamente.
     */
    @Override
    public int compare(Employees employees1, Employees employees2) {
        return employees1.setNetSalary().compareTo(employees2.setNetSalary());
    }
}
