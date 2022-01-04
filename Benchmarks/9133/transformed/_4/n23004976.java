class n23004976 {
	static String hash(String text) {
		try {
			StringBuffer plugins = new StringBuffer();
			for (PlayPlugin plugin : Play.plugins) {
				plugins.append(plugin.getClass().getName());
			}
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update((Play.version + plugins.toString() + text).getBytes("utf-8"));
			byte[] digest = messageDigest.digest();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < digest.length; ++i) {
				int value = digest[i];
				value = (value < 0) ? 256 : value;
				builder.append(Integer.toHexString(value));
			}
			return builder.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}