package rtx.hsan.sabonchi;

public class ProfitCalculator {

    public String calculate(Double mrp, Double sp){
        String result = "NOTHING";
        Double diff = sp-mrp;
        if(diff > 0){
            result = "PROFIT";
        }
        if(diff < 0){
            result = "LOSS";
        }

        return result;
    }
}