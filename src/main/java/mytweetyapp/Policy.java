package mytweetyapp;
import java.util.Set;

import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

public class Policy {
	Modality modality;
	Action actionName;
	PropositionalFormula activationCondition, deactivationCondition;
	float reward, punishment;
	public Policy(Modality modality, Action actionName, PropositionalFormula activationCondition, PropositionalFormula deactivationCondition, float reward, float punishment) {
		super();
		this.modality = modality;
		this.actionName = actionName;
		this.activationCondition = activationCondition;
		this.deactivationCondition = deactivationCondition;
		this.reward = reward;
		this.punishment = punishment;
	}
	
	public String getName() {
		return this.modality.modality + "_" + this.actionName.actionName.getName();
	}
	
	public boolean isActive(Set<PropositionalFormula> stateOfGame) {
		return stateOfGame.contains(this.activationCondition);
	}
}