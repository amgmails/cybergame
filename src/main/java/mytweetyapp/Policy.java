package mytweetyapp;
import java.util.Set;

import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

public class Policy {
	Modality modality;
	Action actionName;
	PropositionalFormula activationCondition, deactivationCondition;
	int reward;
	float punishment;
	public Policy(Modality modality, Action actionName, PropositionalFormula activationCondition, PropositionalFormula deactivationCondition, int reward, float punishment) {
		super();
		this.modality = modality;
		this.actionName = actionName;
		this.activationCondition = activationCondition;
		this.deactivationCondition = deactivationCondition;
		this.reward = reward;
		this.punishment = punishment;
	}
	
	public boolean isActive(Set<PropositionalFormula> stateOfGame) {
		return stateOfGame.contains(this.activationCondition);
	}
}