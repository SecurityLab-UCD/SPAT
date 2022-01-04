class n1188100 {
	public void test() throws Exception {
		StorageStringWriter s = new StorageStringWriter(2048, "UTF-8");
		s.addText("Test");
		try {
			s.getOutputStream();
			fail("Should throw IOException as method not supported.");
		} catch (IOException e) {
		}
		s.getWriter().write("ing is important");
		s.close(ResponseStateOk.getInstance());
		assertEquals("Testing is important", s.getText());
		StringWriter writer = new StringWriter();
		InputStream input = s.getInputStream();
		IOUtils.copy(input, writer, "UTF-8");
		assertEquals("Testing is important", writer.toString());
		try {
			s.getWriter();
			fail("Should throw IOException as storage is closed.");
		} catch (IOException e) {
		}
	}

}