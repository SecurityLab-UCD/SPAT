class n22998998 {
	public static void copyFile(File sourceFile, File destFile) throws IOException {
		FileChannel inputFileChannel = new FileInputStream(sourceFile).getChannel();
		long offset = 0L;
		FileChannel outputFileChannel = new FileOutputStream(destFile).getChannel();
		final long MAXTRANSFERBUFFERLENGTH = 1024 * 1024;
		long length = inputFileChannel.size();
		try {
			for (; offset < length;) {
				offset += inputFileChannel.transferTo(offset, MAXTRANSFERBUFFERLENGTH, outputFileChannel);
				inputFileChannel.position(offset);
			}
		} finally {
			try {
				outputFileChannel.close();
			} catch (Exception ignore) {
			}
			try {
				inputFileChannel.close();
			} catch (IOException ignore) {
			}
		}
	}

}