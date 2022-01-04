class n20968465 {
	private boolean downloadFile(Proxy proxy, URL url, File file) {
		try {
			URLConnection conn = null;
			if (!(null == proxy)) {
				conn = url.openConnection(proxy);
			} else {
				conn = url.openConnection();
			}
			conn.connect();
			File destFile = new File(file.getAbsolutePath() + ".update");
			;
			FileOutputStream fos = new FileOutputStream(destFile);
			byte[] buffer = new byte[2048];
			while (true) {
				int len = conn.getInputStream().read(buffer);
				if (!(len < 0)) {
					fos.write(buffer, 0, len);
				} else {
					break;
				}
			}
			fos.close();
			file.delete();
			destFile.renameTo(file);
			return true;
		} catch (Exception e) {
			logger.error("Failed to get remote hosts file.", e);
		}
		return false;
	}

}