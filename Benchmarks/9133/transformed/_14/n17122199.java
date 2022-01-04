class n17122199 {
	public void test_openStream() throws Exception {
		URL BASE = URLTest.class.getClassLoader()
				.getResource(URLTest.class.getPackage().getName().replace('.', File.separatorChar) + "/lf.jar");
		URL url = new URL("jar:" + BASE + "!/foo.jar!/Bugs/HelloWorld.class");
		try {
			url.openStream();
			fail("should throw FNFE.");
		} catch (java.io.FileNotFoundException e) {
		}
		File resources = Support_Resources.createTempFolder();
		Support_Resources.copyFile(resources, null, "hyts_htmltest.html");
		u = new URL("file", "", resources.getAbsolutePath() + "/hyts_htmltest.html");
		InputStream is1 = u.openStream();
		assertTrue("Unable to read from stream", is1.read() != 0);
		is1.close();
		boolean exception = false;
		try {
			u = new URL("file:///nonexistenttestdir/tstfile");
			u.openStream();
		} catch (IOException e) {
			exception = true;
		}
		assertTrue("openStream succeeded for non existent resource", exception);
		int port = Support_Jetty.startHttpServerWithDocRoot("resources/org/apache/harmony/luni/tests/java/net/");
		URL u = new URL("jar:" + "http://localhost:" + port + "/lf.jar!/plus.bmp");
		InputStream in = u.openStream();
		byte[] buf = new byte[3];
		int result = in.read(buf);
		assertTrue("Incompete read: " + result, 3 == result);
		in.close();
		assertTrue("Returned incorrect data", 0x42 == buf[0] && 0x4d == buf[1] && (byte) 0xbe == buf[2]);
		File test = new File("hytest.$$$");
		FileOutputStream out = new FileOutputStream(test);
		out.write(new byte[] { 0x55, (byte) 0xaa, 0x14 });
		out.close();
		u = new URL("file:" + test.getName());
		in = u.openStream();
		buf = new byte[3];
		result = in.read(buf);
		in.close();
		test.delete();
		assertEquals("Incompete read 3", 3, result);
		assertTrue("Returned incorrect data 3", 0x55 == buf[0] && (byte) 0xaa == buf[1] && 0x14 == buf[2]);
	}

}