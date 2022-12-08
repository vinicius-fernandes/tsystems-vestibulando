package com.vestibulando.utils;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;

public class CustomMetadataBuilderContributor implements MetadataBuilderContributor {
    @Override
    public void contribute(MetadataBuilder metadataBuilder) {
        metadataBuilder.applySqlFunction(GroupConcatFunction.INSTANCE.getName(), GroupConcatFunction.INSTANCE);
    }
}
