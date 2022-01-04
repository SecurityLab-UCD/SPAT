class n14284540 {
	public static void copyAFile(final String entree, final String sortie) {
		FileChannel out = null;
		FileChannel in = null;
		try {
			in = new FileInputStream(entree).getChannel();
			out = new FileOutputStream(sortie).getChannel();
			in.transferTo(0, in.size(), out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}