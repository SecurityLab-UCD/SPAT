class n17673488 {
	public String get(String question) {
		try {
			System.out.println(url + question);
			URL urlonlineserver = new URL(url + question);
			BufferedReader in = new BufferedReader(new InputStreamReader(urlonlineserver.openStream()));
			String inputLine, returnstring = "";
			while ((inputLine = in.readLine()) != null)
				returnstring += inputLine;
			in.close();
			return returnstring;
		} catch (IOException e) {
			return "";
		}
	}

}