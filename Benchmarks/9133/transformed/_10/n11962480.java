class n11962480 {
	public void read(String skeletonFilename) throws Exception {
		ClassLoader loader = UnicodePropertiesSkeleton.class.getClassLoader();
		URL url = loader.getResource(skeletonFilename);
		String line;
		if (null == url) {
			throw new Exception("Cannot locate '" + skeletonFilename + "' - aborting.");
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		StringBuilder section = new StringBuilder();
		while (null != (line = reader.readLine())) {
			if (line.startsWith("---")) {
				sections.add(section.toString());
				section.setLength(0);
			} else {
				section.append(line);
				section.append(NL);
			}
		}
		if (section.length() > 0) {
			sections.add(section.toString());
		}
		if (sections.size() != size) {
			throw new Exception("Skeleton file '" + skeletonFilename + "' has " + sections.size()
					+ " static sections, but " + size + " were expected.");
		}
	}

}