package calculations;

import calculations.Calculations;

public class Square implements Calculations {
    Calculations left;

    public Square(Calculations left) {
        this.left = left;
    };

    public float evaluate() {

        return left.evaluate() *left.evaluate();

    }


}
