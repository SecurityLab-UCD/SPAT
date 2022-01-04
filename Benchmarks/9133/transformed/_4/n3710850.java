class n3710850 {
	@Override
	public boolean connect(String host, String userName, String password) throws IOException, UnknownHostException {
		try {
			if (ftpClient != null)
				if (ftpClient.isConnected())
					ftpClient.disconnect();
			ftpClient = new FTPSClient("SSL", false);
			boolean success = false;
			ftpClient.connect(host);
			int reply = ftpClient.getReplyCode();
			success = (FTPReply.isPositiveCompletion(reply)) ? ftpClient.login(userName, password) : success;
			if (!success)
				ftpClient.disconnect();
			return success;
		} catch (Exception ex) {
			throw new IOException(ex.getMessage());
		}
	}

}