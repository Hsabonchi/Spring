package rtx.hsan.sabonchi;

public class SimpleInterest {

    public  Double calculate(Double principal, Double roi, Double noOfYears){
        return (principal*roi*noOfYears)/100;
    }
}