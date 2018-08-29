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
public class Quadrado implements AreaCalculavel {
    int lado;

    @Override
    public double calulaArea() {
        return (lado*lado);
    }
    
}
