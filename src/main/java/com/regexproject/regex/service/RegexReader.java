package com.regexproject.regex.service;

import com.amazonaws.encryptionsdk.AwsCrypto;
import com.amazonaws.encryptionsdk.CommitmentPolicy;
import com.amazonaws.encryptionsdk.kms.KmsMasterKeyProvider;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Scanner;

@Service
public class RegexReader {

    private static final Faker faker = new Faker();

    String keyArn = "arn:aws:kms:us-east-1:436904145537:key/de83a94e-a9b2-4253-9d1b-52d3d6edd529";

    @Autowired
    private AwsSdkEncryptionService awsSdkEncryptionService;


    public void generateCsvData() throws IOException {
        try(FileWriter locFile = new FileWriter("src/main/resources/static/locations.csv")) {
            for(int i=0; i<4;i++){
                locFile.write(String.valueOf(faker.number().randomNumber())+
                        ","+awsSdkEncryptionService.encrypt(faker.name().fullName())+","+faker.company().name()+","+
                        faker.job().position()+","+faker.address().country()+","+
                        faker.code().isbnRegistrant()+"\n");
            }
            System.out.println("file written is successful");
        }

    }



    public void readGeneratedData() throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new FileReader("src/main/resources/static/locations.csv"))){
            scanner.useDelimiter(",");
            while(scanner.hasNextLine()){
                String id = scanner.next();
                scanner.skip(scanner.delimiter());
                String name = awsSdkEncryptionService.decrypt(scanner.next());
                scanner.skip(scanner.delimiter());
                String company = scanner.next();
                scanner.skip(scanner.delimiter());
                String position = scanner.next();
                scanner.skip(scanner.delimiter());
                String address = scanner.next();
                scanner.skip(scanner.delimiter());
                String code = scanner.nextLine();
                System.out.println("id, "+id+" name, "+name+" company, "+company+" position, "+position+"" +
                        " address, "+address+" code, "+code);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
