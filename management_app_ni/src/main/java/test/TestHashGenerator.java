package test;

import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;

import model.HashGenerator;

class TestHashGenerator {

	@Test
	//パスワードが空文字の場合
	void testPassIsEmpty() throws NoSuchAlgorithmException {
		String result = HashGenerator.generateHash("");
		assertEquals(null, result);
	}

}
