package mytweetyapp;
import net.sf.tweety.logics.pl.syntax.PropositionalFormula;

public class Policy {
	Modality modality;
	Action actionName;
	PropositionalFormula activationCondition, deactivationCondition;
	public Policy(Modality modality, Action actionName, PropositionalFormula activationCondition, PropositionalFormula deactivationCondition) {
		super();
		this.modality = modality;
		this.actionName = actionName;
		this.activationCondition = activationCondition;
		this.deactivationCondition = deactivationCondition;
	}
}