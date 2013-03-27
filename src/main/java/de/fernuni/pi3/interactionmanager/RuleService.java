package de.fernuni.pi3.interactionmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RuleService {

	private static Logger logger = Logger.getLogger(Event.class.getName());
	
	static final String DEFAULT_APPTYPE = "default";

	private Map<String, List<Rule>> appTypeRules = new HashMap<String, List<Rule>>();
	private Map<String, RuleSet> ruleSets = new HashMap<String, RuleSet>();

	@Autowired
	RuleService(List<Rule> rules) {
		for (Rule rule : rules) {
			if (appTypeRules.containsKey(rule.getAppType())) {
				appTypeRules.get(rule.getAppType()).add(rule);
			} else {
				List<Rule> ruleList = new ArrayList<Rule>();
				ruleList.add(rule);
				appTypeRules.put(rule.getAppType(), ruleList);
			}
		}
	}

	RuleSet getRuleSet(String appType) {

		if (!ruleSets.containsKey(appType)) {

			if (appTypeRules.containsKey(appType)) {
				ruleSets.put(appType, new RuleSet(appTypeRules.get(appType)));
			} else {
				logger.warning("There are no rules available for app type '" + appType + "'. Will fallback to default ruleset containing a simple echo rule." );
				ruleSets.put(appType,
						new RuleSet(appTypeRules.get(DEFAULT_APPTYPE)));
			}
		}
		return ruleSets.get(appType);
	}
}
