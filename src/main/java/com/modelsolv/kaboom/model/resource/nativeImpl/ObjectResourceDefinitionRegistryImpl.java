package com.modelsolv.kaboom.model.resource.nativeImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.modelsolv.kaboom.model.canonical.CanonicalDataType;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinitionRegistry;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;

public class ObjectResourceDefinitionRegistryImpl implements
		ObjectResourceDefinitionRegistry {

	private Map<String, ObjectResourceDefinition> nameMap = new HashMap<String, ObjectResourceDefinition>();
	private Map<CanonicalDataType, ObjectResourceDefinition> cdtMap = new HashMap<CanonicalDataType, ObjectResourceDefinition>();
	private Map<ResourceDataModel, ObjectResourceDefinition> rdmMap = new HashMap<ResourceDataModel, ObjectResourceDefinition>();

	@Override
	public void registerDefinition(ObjectResourceDefinition definition) {

		class Remover {
			/**
			 * Remove any entry already keyed to this definition. The algo is
			 * aggressive: Any definition having any existing map key will be
			 * removed from all three maps.
			 * 
			 * @param definition
			 */
			public void removeDuplicates(ObjectResourceDefinition definition) {
				removeName(definition.getName());
				removeRDM(definition.getResourceDataModel());
				removeCDT(definition.getResourceDataModel()
						.getCanonicalDataType());
			}

			void removeName(String name) {
				ObjectResourceDefinition def = getResourceDefinition(name);
				if (def != null) {
					remove(def);
				}
			}

			void removeRDM(ResourceDataModel rdm) {
				ObjectResourceDefinition def = getResourceDefinition(rdm);
				if (def != null) {
					remove(def);
				}
			}

			void removeCDT(CanonicalDataType cdt) {
				ObjectResourceDefinition def = getResourceDefinition(cdt);
				if (def != null) {
					remove(def);
				}
			}

			void remove(ObjectResourceDefinition definition) {
				removeFromMap(definition, nameMap);
				removeFromMap(definition, rdmMap);
				removeFromMap(definition, cdtMap);
			}

			void removeFromMap(ObjectResourceDefinition definition,
					Map<?, ?> map) {
				boolean foundAndRemoved;
				do {
					foundAndRemoved = false;
					for (Entry<?, ?> entry : map.entrySet()) {
						if (entry.getValue() == definition) {
							map.remove(entry.getKey());
							foundAndRemoved = true;
							break;
						}
					}
				} while (foundAndRemoved); // removed one, look for another
			}
		}

		new Remover().removeDuplicates(definition);
		nameMap.put(definition.getName(), definition);
		rdmMap.put(definition.getResourceDataModel(), definition);
		cdtMap.put(definition.getResourceDataModel().getCanonicalDataType(),
				definition);
	}

	@Override
	public ObjectResourceDefinition getResourceDefinition(String name) {
		return nameMap.containsKey(name) ? nameMap.get(name) : null;
	}

	@Override
	public ObjectResourceDefinition getResourceDefinition(ResourceDataModel rdm) {
		return rdmMap.containsKey(rdm) ? rdmMap.get(rdm) : null;
	}

	@Override
	public ObjectResourceDefinition getResourceDefinition(CanonicalDataType cdt) {
		return cdtMap.containsKey(cdt) ? cdtMap.get(cdt) : null;
	}

}
