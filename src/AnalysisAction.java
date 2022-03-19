import java.util.HashMap;
import java.util.Map;

public class AnalysisAction extends Action{
  private static Map<String, Action> analysisActionDictionary = new HashMap<>();  
  @Override
  public void execute() {
  System.out.println("Choose desired Analysis! Type 'overdue', 'responsible' or 'status'");
  String userInput = scanner.nextLineToLowerCase();
            try {
                analysisActionDictionary.get(userInput).execute(); 
            } catch (NullPointerException e) {
                System.out.println("Not a valid input");
            }
  }

  protected AnalysisAction() {
    analysisActionDictionary.put("overdue", new OverdueAnalysisAction());
    analysisActionDictionary.put("responsible", new ResponsibleAnalysisAction());
    analysisActionDictionary.put("status", new StatusAnalysisAction());
}
}
