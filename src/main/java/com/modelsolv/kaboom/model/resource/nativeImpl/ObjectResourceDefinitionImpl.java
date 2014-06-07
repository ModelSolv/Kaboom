package com.modelsolv.kaboom.model.resource.nativeImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;

import com.modelsolv.kaboom.model.canonical.CDMProperty;
import com.modelsolv.kaboom.model.resource.ObjectResource;
import com.modelsolv.kaboom.model.resource.ObjectResourceDefinition;
import com.modelsolv.kaboom.model.resource.RDMFactory;
import com.modelsolv.kaboom.model.resource.ResourceDataModel;
import com.modelsolv.kaboom.object.CanonicalObjectReader;

public class ObjectResourceDefinitionImpl implements ObjectResourceDefinition {

	private ResourceDataModel resourceDataModel;
	private String name;
	private String uriTemplate;
	private Map<String, CDMProperty> parameterBindings = new HashMap<String, CDMProperty>();

	public ObjectResourceDefinitionImpl(String uriTemplate,
			ResourceDataModel rdm) {
		this.resourceDataModel = rdm;
		this.uriTemplate = uriTemplate;
	}

	@Override
	public ResourceDataModel getResourceDataModel() {
		return resourceDataModel;
	}

	@Override
	public String getURITemplate() {
		return uriTemplate;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public ObjectResourceDefinition withName(String name) {
		setName(name);
		return this;
	}

	@Override
	public void bindTemplateParameter(String parameter, CDMProperty property) {
		if (!resourceDataModel.getCanonicalDataType().hasProperty(property)) {
			throw new IllegalArgumentException(
					"Parameter must be bound to a property of the canonical data type.");
		}
		parameterBindings.put(parameter, property);
	}

	@Override
	public ObjectResourceDefinition withTemplateParameter(String parameter,
			CDMProperty property) {
		bindTemplateParameter(parameter, property);
		return this;
	}

	@Override
	public ObjectResource getResource(Object canonicalObject,
			CanonicalObjectReader reader) {
		URI uri = bindParameters(canonicalObject, reader);
		return RDMFactory.INSTANCE.createObjectResource(canonicalObject, uri,
				this);
	}

	private URI bindParameters(Object canonicalObject,
			CanonicalObjectReader reader) {

		Pattern pattern = Pattern.compile("\\{(.+?)\\}");
		Matcher matcher = pattern.matcher(getURITemplate());
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			String propName = matcher.group(1);
			String replacement = (String) reader.getPropertyValue(
					canonicalObject, propName);
			matcher.appendReplacement(buffer, "");
			buffer.append(replacement);
		}
		matcher.appendTail(buffer);
		try {
			return new URI(buffer.toString());
		} catch (Exception e) {
			throw new RuntimeException(
					"Could not create resource URI using the provided URI template, "
							+ "canonical object and reader.", e);
		}
	}
}
