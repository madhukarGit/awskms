package com.regexproject.regex.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;

public class LocalstackSupplier implements KmsMasterKeyProvider.RegionalClientSupplier {
    @Override
    public AWSKMS getClient(String s) {
        return AWSKMSClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(DefaultAWSCredentialsProviderChain.getInstance().getCredentials()))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://127.0.0.1:4566","us-east-1"))
                .build();
    }
}
