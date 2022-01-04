class n11070888 {
	public static void copyFile(File file, File dest_file) throws FileNotFoundException, IOException {
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dest_file)));
		byte[] buffer = new byte[1024];
		int read;
		for (; (read = in.read(buffer)) > 0;) {
			out.write(buffer, 0, read);
		}
		in.close();
		out.close();
	}

}