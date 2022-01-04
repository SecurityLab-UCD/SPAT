class n20282675 {
	public List<String> getFtpFileList(String serverIp, int port, String user, String password, String synchrnPath)
			throws Exception {
		FTPClient ftpClient = new FTPClient();
		List<String> list = new ArrayList<String>();
		ftpClient.setControlEncoding("euc-kr");
		if (!EgovWebUtil.isIPAddress(serverIp)) {
			throw new RuntimeException("IP is needed. (" + serverIp + ")");
		}
		InetAddress host = InetAddress.getByName(serverIp);
		ftpClient.connect(host, port);
		ftpClient.login(user, password);
		ftpClient.changeWorkingDirectory(synchrnPath);
		FTPFile[] fTPFile = ftpClient.listFiles(synchrnPath);
		for (int i = 0; i < fTPFile.length; i++) {
			list.add(fTPFile[i].getName());
		}
		return list;
	}

}