class n14416228 {
	private static String sendRPC(String xml) throws MalformedURLException, IOException {
		String str = "";
		String strona = OSdbServer;
		URL url = new URL(strona);
		String logowanie = xml;
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("Connection", "Close");
		connection.setRequestProperty("Content-Type", "text/xml");
		connection.setDoOutput(true);
		connection.getOutputStream().write(logowanie.getBytes("UTF-8"));
		Scanner in;
		in = new Scanner(connection.getInputStream());
		;
		while (in.hasNextLine()) {
			str += in.nextLine();
		}
		return str;
	}

}