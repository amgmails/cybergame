package mytweetyapp;

import java.util.*;
import net.sf.tweety.logics.pl.syntax.Proposition;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

public class Action {
	Proposition actionName;
	int cost, utility1, utility2, effect;
	Set<PropositionalFormula> preCondition, postCondition;
	String role1, role2;
	public Action(Proposition actionName, Set<PropositionalFormula> preCondition, Set<PropositionalFormula> postCondition, String role1, String role2, int utility1, int utility2, int cost, int effect) {
		super();
		this.actionName = actionName;
		this.preCondition = preCondition;
		this.postCondition = postCondition;
		this.role1 = role1;
		this.role2 = role2;
		this.utility1 = utility1;
		this.utility2 = utility2;
		this.cost = cost;
		this.effect = effect;

	}
}