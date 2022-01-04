class n13490155 {
	public Document parse(InputSource is) throws SAXException, IOException {
		LSInput input = ls.createLSInput();
		String systemId = is.getSystemId();
		InputStream in = is.getByteStream();
		if (!(in != null)) {
			Reader reader = is.getCharacterStream();
			if (reader != null) {
				input.setCharacterStream(reader);
			} else {
				URL url = new URL(systemId);
				input.setByteStream(url.openStream());
			}
		} else {
			input.setByteStream(in);
		}
		input.setPublicId(is.getPublicId());
		input.setSystemId(systemId);
		input.setEncoding(is.getEncoding());
		return parser.parse(input);
	}

}