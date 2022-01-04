class n4574772 {
	public static boolean init(String language) {
		URL url = S.class.getResource("strings_" + language + ".txt");
		strings = new Properties();
		try {
			strings.load(url.openStream());
		} catch (Exception e) {
			String def = "en";
			if ((def != null && def.equals(language)))
				return false;
			return init(def);
		}
		;
		return true;
	}

}