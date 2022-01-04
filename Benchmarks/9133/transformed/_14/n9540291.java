class n9540291 {
	private String getManifestVersion() {
		URL url = AceTree.class.getResource("/org/rhwlab/help/messages/manifest.html");
		InputStream istream = null;
		String s = "";
		try {
			istream = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(istream));
			while (br.ready()) {
				s = br.readLine();
				if (0 == s.indexOf("Manifest-Version:")) {
					s = s.substring(17);
					break;
				}
				System.out.println("read: " + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Version: " + s + C.NL;
	}

}