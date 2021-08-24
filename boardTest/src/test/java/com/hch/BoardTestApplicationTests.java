package com.hch;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootTest
@EnableEncryptableProperties
class BoardTestApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
    void jasypt() {
        String url = "jdbc:mariadb://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC";
        String username = "root";
        String password = "cndgus97";
        String secret = "spring-boot-spring-security-boardtest-hch930-jwt-secret-key";

        System.out.println("url: " + jasyptEncoding(url));
        System.out.println("username: " + jasyptEncoding(username));
        System.out.println("password: " + jasyptEncoding(password));
        System.out.println("secret: " + jasyptEncoding(secret));
    }

    public String jasyptEncoding(String value) {

        String key = "qkdlfjtm!97";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        pbeEnc.setSaltGenerator(new StringFixedSaltGenerator("someFixedSalt"));
        return pbeEnc.encrypt(value);
    }
}
