package com.project.challenge;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootTest
class CodingChallengeApplicationTests {

	// Mariadb Test
	@Test
	void test() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/coding-challenge", "root", "j269852");
		System.out.println(con);
	}

}
