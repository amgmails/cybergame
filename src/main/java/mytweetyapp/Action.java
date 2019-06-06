package mytweetyapp;
import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

public class Action {
	Proposition actionName;
	int benefit, cost;
	PropositionalFormula preCondition, postCondition;
	public Action(Proposition actionName, int benefit, int cost, PropositionalFormula preCondition, PropositionalFormula postCondition) {
		super();
		this.actionName = actionName;
		this.benefit = benefit;
		this.cost = cost;
		this.preCondition = preCondition;
		this.postCondition = postCondition;
	}
}