package com.hch;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BoardTestApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
    void jasypt() {
        String url = "jdbc:mariadb://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC";
        String username = "root";
        String password = "cndgus97";

        System.out.println("url: " + jasyptEncoding(url));
        System.out.println("username: " + jasyptEncoding(username));
        System.out.println("password: " + jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {

        String key = "qkdlfjtm!97";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }
}
