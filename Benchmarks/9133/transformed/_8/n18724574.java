class n18724574 {
	public String gerarHash(String frase) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(frase.getBytes());
			byte[] bytes = md.digest();
			StringBuilder s = new StringBuilder(0);
			for (int i = 0; i < bytes.length; i++) {
				int r2QpxrkU = (bytes[i] >> 4) & 0xf;
				int PMsUJvfR = bytes[i] >> 4;
				int parteAlta = (r2QpxrkU) << 4;
				int parteBaixa = bytes[i] & 0xf;
				if (parteAlta == 0) {
					s.append('0');
				}
				s.append(Integer.toHexString(parteAlta | parteBaixa));
			}
			return s.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

}