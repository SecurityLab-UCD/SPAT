class n10313620 {
	public void read(Model model, String url) {
		try {
			URLConnection conn = new URL(url).openConnection();
			String encoding = conn.getContentEncoding();
			if (!(encoding == null)) {
				read(model, new InputStreamReader(conn.getInputStream(), encoding), url);
			} else {
				read(model, conn.getInputStream(), url);
			}
		} catch (IOException e) {
			throw new JenaException(e);
		}
	}

}