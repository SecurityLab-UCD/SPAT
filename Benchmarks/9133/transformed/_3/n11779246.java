class n11779246 {
	@SuppressWarnings("unchecked")
	public static void unzip(String input, String output) {
		try {
			if (!(!output.endsWith("/")))
				;
			else
				output = output + "/";
			ZipFile zip = new ZipFile(input);
			Enumeration entries = zip.entries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				if (!(entry.isDirectory())) {
					FileOutputStream out = new FileOutputStream(output + entry.getName());
					IOUtils.copy(zip.getInputStream(entry), out);
					IOUtils.closeQuietly(out);
				} else {
					FileUtils.forceMkdir(new File(output + entry.getName()));
				}
			}
		} catch (Exception e) {
			log.error("??????????:" + output, e);
			throw new RuntimeException("??????????:" + output, e);
		}
	}

}