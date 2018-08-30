/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.leunardo.sistemaanalise.model;

/**
 *
 * @author leonardo
 */
public class SalesmanModel implements IData {
    private String cpf;
    private String name;
    private double salary;

    public SalesmanModel(String cpf, String name, double salary) {
        this.cpf = cpf;
        this.name = name;
        this.salary = salary;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
    
    
    @Override
    public String toString() {
        return getName() + " - CPF: " + getCpf() + " - Salary: " + getSalary();
    }
}
