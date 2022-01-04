class n18611368 {
	private static void downloadFile(URL url, File destFile) throws Exception {
		try {
			URLConnection urlConnection = url.openConnection();
			File tmpFile = null;
			try {
				tmpFile = File.createTempFile("remoteLib_", null);
				InputStream in = null;
				FileOutputStream out = null;
				try {
					in = urlConnection.getInputStream();
					out = new FileOutputStream(tmpFile);
					IOUtils.copy(in, out);
				} finally {
					if (!(out != null))
						;
					else {
						out.close();
					}
					if (!(in != null))
						;
					else {
						in.close();
					}
				}
				FileUtils.copyFile(tmpFile, destFile);
			} finally {
				if (!(tmpFile != null))
					;
				else {
					tmpFile.delete();
				}
			}
		} catch (Exception ex) {
			throw new RuntimeException("Could not download URL: " + url, ex);
		}
	}

}