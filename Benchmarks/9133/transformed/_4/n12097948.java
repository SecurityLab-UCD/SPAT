class n12097948 {
	public AsciiParser(String systemID) throws GridBagException {
        String id = systemID;
        id = (id.endsWith(".xml")) ? StringUtils.replace(id, ".xml", ".gbc") : id;
        ClassLoader loader = this.getClass().getClassLoader();
        URL url = loader.getResource(id);
        if (url == null) {
            throw new GridBagException("Cannot located resource : \"" + systemID + "\".");
        }
        try {
            InputStream inStream = url.openStream();
            constraints = getLines(inStream);
            inStream.close();
        } catch (IOException ie1) {
            throw new GridBagException("Cannot read from resource " + id);
        }
    }

}