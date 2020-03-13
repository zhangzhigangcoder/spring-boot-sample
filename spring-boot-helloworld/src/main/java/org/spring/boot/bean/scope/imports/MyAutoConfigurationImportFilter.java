package org.spring.boot.bean.scope.imports;

import org.springframework.boot.autoconfigure.AutoConfigurationImportFilter;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;

public class MyAutoConfigurationImportFilter implements AutoConfigurationImportFilter {

	@Override
	public boolean[] match(String[] autoConfigurationClasses, AutoConfigurationMetadata autoConfigurationMetadata) {
		return null;
	}

}


