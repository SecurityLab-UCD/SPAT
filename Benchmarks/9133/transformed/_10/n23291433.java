class n23291433 {
	public static String sendRequest(String urlstring) {
		String line;
		URL url;
		Log.i("DVBMonitor", "Please wait while receiving data from dvb...");
		try {
			url = new URL(urlstring);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			if ((line = in.readLine()) != null) {
				return line;
			} else {
				return null;
			}
		} catch (Exception ex) {
			Log.e("DVBMonitor", ex.toString() + " while sending request to dvb");
			return null;
		}
	}

}