/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

/**
 *
 * @author Leandro
 */
public class Retangulo implements AreaCalculavel {
    int base;
    int altura;

    @Override
    public double calulaArea() {
       return (base*altura);
    }
}
