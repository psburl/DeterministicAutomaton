import java.util.List;
import automaton.DeterministicAutomaton;
import grammar.Rule;
import io.SingletonInput;
import minimization.UnreachableRemotion;
import java.util.ArrayList;
import java.util.Arrays;
import globals.GlobalInfo;


public class Main {

	private static DeterministicAutomaton getAutomaton() throws Exception{

		List<String> input = SingletonInput.getInstance().getContentLines();
		List<String> grammar = new ArrayList<String>();

		for (String entry : input){

			if(entry.charAt(0) == '<')
				grammar.add(entry);

			GlobalInfo.getInstance().addToken(entry);
		}

		List<Rule> rules = Rule.mount(grammar);
		
		DeterministicAutomaton automaton = new DeterministicAutomaton(rules);
		
		UnreachableRemotion remotion = new UnreachableRemotion(automaton);
		
		automaton = remotion.Remove();

		return automaton;
	}

	public static void main(String[] args) throws Exception {
			
		DeterministicAutomaton automaton = getAutomaton();
		
		automaton.printTable();
	}
}
