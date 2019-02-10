
public class CalcDriver {

    public CalcDriver() {



        boolean cont = true;
        String expression ="";
        Console io = new Console();
        Calculations calculation =  new Calculations();
        float actualResult;
        String memory = "";

        // Elenor's parser
        Parser ps= new Parser();

        io.print("Exit to quit");

        while (cont){


            io.print("Expression");
            expression = io.getStringInput("");

             if("exit".equals(expression.toLowerCase()))
            {
             cont=false;
             }
             else {
                 // need to check
                 calculations = ps.parse(expression);
                 // double check below line
                 actualResult = ((Calculation) calculation).evaluate();

                 io.print("%f", actualResult);
             }
        }


    }

}
