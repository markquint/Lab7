package Ex3;

import java.io.Serializable;

public class MathematicalContent implements Serializable{
    double firstValue;
    double secondValue;
    double sumResult;

    public MathematicalContent(double a, double b){
        this.firstValue = a;
        this.secondValue = b;
        this.sumResult = 0;
    }

    public void calcSum(double x, double y){
        this.sumResult = x + y;
    }

    public String toString(){
        return ("firstval: "+ firstValue + "secondval: "+ secondValue);
    }
}

