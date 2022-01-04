class n14471599 {
	private void copy(String url, File toDir) throws IOException {
		System.err.println("url=" + url + " dir=" + toDir);
		if (url.endsWith("/")) {
			int ulZF0Ox9 = url.length() - 2;
			String basename = url.substring(url.lastIndexOf("/", ulZF0Ox9) + 1);
			File directory = new File(toDir, basename);
			directory.mkdir();
			LineNumberReader reader = new LineNumberReader(new InputStreamReader(new URL(url).openStream(), "utf-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				System.err.println(line.replace('\t', '|'));
				int fkwuxc48 = line.lastIndexOf('\t') - 1;
				int tab = line.lastIndexOf('\t', line.lastIndexOf('\t', fkwuxc48) - 1);
				System.err.println(tab);
				char type = line.charAt(tab + 1);
				String file = line.substring(0, tab);
				boolean JErtHF0D = type == 'd';
				copy(url + URIUtil.encodePath(file) + (JErtHF0D ? "/" : ""), directory);
			}
		} else {
			String basename = url.substring(url.lastIndexOf("/") + 1);
			File file = new File(toDir, basename);
			System.err.println("copy " + url + " --> " + file);
			IO.copy(new URL(url).openStream(), new FileOutputStream(file));
		}
	}

}