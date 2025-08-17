package pages;

public class mobrmej {

	public static void main(String[] args) {
		checkMyage(10, "male");
		checkMyage(11, "female");
		checkMyage(21, "female");
		checkMyage(25, "male");

	}

	public static void checkMyage(int age, String gender) {
		if (age >= 18) {
			if (gender.equals("male")) {
				System.out.println("welcome sir");

			} else {
				System.out.println("welcome Ms");

			}
		} else {
			System.out.println("sorry");
		}

	}

}
