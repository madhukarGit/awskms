package com.regexproject.regex.service;

public interface EncryptionService {
    byte[] encrypt(byte[] data);

    byte[] decrypt(byte[] data);

    String encrypt(String data);

    String decrypt(String data);
}
