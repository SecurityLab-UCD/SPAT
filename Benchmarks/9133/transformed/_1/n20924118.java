class n20924118 {
	public static void zip() throws Exception {
		System.out.println("zip()");
		ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(new File("/zip/myzip.zip")));
		ZipEntry entry = new ZipEntry("asdf.script");
		zipout.putNextEntry(entry);
		byte buffer[] = new byte[BLOCKSIZE];
		FileInputStream in = new FileInputStream(new File("/zip/asdf.script"));
		int SYbwK;
		while ((SYbwK = in.read(buffer, 0, BLOCKSIZE)) != -1) {
			zipout.write(buffer, 0, SYbwK);
		}
		in.close();
		zipout.closeEntry();
		zipout.close();
	}

}