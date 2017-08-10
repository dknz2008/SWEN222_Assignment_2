import java.util.List;

/**
 * Created by TML_TEST on 3/08/2017.
 */
public class TextValidator {

    String inputText;
    String displayText;
    List<String> options;

    public TextValidator(String input, List<String> options){
        this.inputText = input;
        this.options = options;
    }

    public void check(String displayText){
        System.out.println(displayText);
        //String test = "create [a-zA-Z] [0|90|180|270]";
        for(int i = 0; i < options.size(); i++){
            //if()
        }
    }

}
