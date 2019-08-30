package com.tangu.tanguStudy.annotionImport;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class TanguImport2Selector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
    	System.out.println("TanguImport2Selector");
        return new String[]{"com.tangu.tanguStudy.annotionImport.TanguImport2"};
    }
}