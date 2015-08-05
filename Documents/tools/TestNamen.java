public class TestNamen {

	private static String naam = "Zyad 12 Samuela 13";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// methodOne(naam);
		// System.out.println("-------------------------------------------");
		methodTwo(naam);
	}

	public static void methodOne(String name) {
		String subNaam = name.replaceAll("\\d*$", "");
		System.out.println("Subnaam: " + subNaam.trim());
		String subSubNaam = subNaam.trim().replaceAll("\\d*", "");
		System.out.println("SubSubnaam: " + subSubNaam.trim());
	}

	public static void methodTwo(String name) {
		String[] names = name.split(" ");
		int i = 0;
		for (String name1 : names) {
			// System.out.println(name1);
			// do db stuff
			switch (i) {
			case 0:
				System.out.println("INSERT INTO DATABASE JONGENSNAMEN " + name1);
				break;
			case 1:
				System.out.println("UPDATE AANTAL INTO DATABASE JONGENSNAMEN " + name1);
				break;
			case 2:
				System.out.println("INSERT INTO DATABASE MEISJESNAMEN " + name1);
				break;
			case 3:
				System.out.println("UPDATE AANTAL INTO DATABASE MEISJESNAMEN " + name1);
				break;
			default:
				System.out.println("Zou niet voor moeten komen");
				throw new RuntimeException("Foute invoer " + i);
			}
			i++;
		}
	}

}
