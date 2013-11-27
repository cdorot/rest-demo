package com.digiplug.rest;

import java.util.List;

import org.glassfish.jersey.server.wadl.config.WadlGeneratorConfig;
import org.glassfish.jersey.server.wadl.internal.generators.WadlGeneratorApplicationDoc;
import org.glassfish.jersey.server.wadl.internal.generators.resourcedoc.WadlGeneratorResourceDocSupport;

public class SampleWadlGeneratorConfig extends WadlGeneratorConfig {

	@Override
	public List<?> configure() {
		return generator(WadlGeneratorApplicationDoc.class).prop("applicationDocsStream", "application-doc.xml")
				.generator(WadlGeneratorResourceDocSupport.class).prop("resourceDocStream", "resourcedoc.xml")
				.descriptions();
	}

}
