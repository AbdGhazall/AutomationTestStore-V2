package utils;

import java.util.Random;

// TestDataGenerator generates random test data like names, emails, usernames

public class TestDataGenerator {
	static Random rand = new Random();

	public static String getRandomFirstName() {
		String[] names = { "amira", "rogina", "dana", "mais", "dareen" };
		return names[rand.nextInt(names.length)];
	}

	public static String getRandomLastName() {
		String[] names = { "alaa", "saif", "abduallah", "hamzeh", "marwan", "abedalrahman", "omar", "yazan" };
		return names[rand.nextInt(names.length)];
	}

	// I will send to the argument the random first,last names
	public static String getEmail(String first, String last) {
		return first + last + rand.nextInt(7000) + "@gmail.com";
	}

	// needs arguments
	public static String getRandomUsername(String first, String last) {
		return first + last + rand.nextInt(7000);
	}
}
