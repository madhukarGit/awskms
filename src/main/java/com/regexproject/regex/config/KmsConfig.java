package com.regexproject.regex.config;

import com.amazonaws.encryptionsdk.CryptoMaterialsManager;
import com.amazonaws.encryptionsdk.caching.CachingCryptoMaterialsManager;
import com.amazonaws.encryptionsdk.caching.CryptoMaterialsCache;
import com.amazonaws.encryptionsdk.caching.LocalCryptoMaterialsCache;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class KmsConfig {


    @Value("${aws.localstack:false}")
    private boolean useLocalstack;

    @Bean
    KmsMasterKeyProvider kmsMasterKeyProvider(){
        return KmsMasterKeyProvider.builder().buildStrict("arn:aws:kms:us-east-1:436904145537:key/de83a94e-a9b2-4253-9d1b-52d3d6edd529");
    }

    @Bean
    CryptoMaterialsManager cryptoMaterialsManager(KmsMasterKeyProvider keyProvider){
        CryptoMaterialsCache cache = new LocalCryptoMaterialsCache(100);
        return CachingCryptoMaterialsManager
                .newBuilder()
                .withMasterKeyProvider(keyProvider)
                .withCache(cache)
                .withMaxAge(1000, TimeUnit.SECONDS)
                .build();
    }


}
