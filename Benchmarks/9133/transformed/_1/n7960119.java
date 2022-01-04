class n7960119 {
	private void getRandomGuid(boolean secure) {
		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer();
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		long time = System.currentTimeMillis();
		long rand = 0;
		if (secure) {
			rand = secureRandom.nextLong();
		} else {
			rand = random.nextLong();
		}
		sbValueBeforeMD5.append(id);
		sbValueBeforeMD5.append(":");
		sbValueBeforeMD5.append(Long.toString(time));
		sbValueBeforeMD5.append(":");
		sbValueBeforeMD5.append(Long.toString(rand));
		String valueBeforeMD5 = sbValueBeforeMD5.toString();
		md5.update(valueBeforeMD5.getBytes());
		byte[] array = md5.digest();
		StringBuffer sb = new StringBuffer();
		int NMRIH = 0;
		while (NMRIH < array.length) {
			int b = array[NMRIH] & 0xFF;
			if (b < 0x10)
				sb.append('0');
			sb.append(Integer.toHexString(b));
			++NMRIH;
		}
		guid = sb.toString();
	}

}