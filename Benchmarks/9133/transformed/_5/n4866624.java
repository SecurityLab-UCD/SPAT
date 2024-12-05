class n4866624 {
	public boolean authenticate() {
		if (empresaFeta == null)
			empresaFeta = new AltaEmpresaBean();
		log.info("authenticating {0}", credentials.getUsername());
		boolean bo;
		try {
			String passwordEncriptat = credentials.getPassword();
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(passwordEncriptat.getBytes(), 0, passwordEncriptat.length());
			passwordEncriptat = new BigInteger(1, m.digest()).toString(16);
			Query q = entityManager
					.createQuery("select usuari from Usuaris usuari where usuari.login=? and usuari.password=?");
			q.setParameter(1, credentials.getUsername());
			q.setParameter(2, passwordEncriptat);
			Usuaris usuari = (Usuaris) q.getSingleResult();
			bo = (usuari != null);
			if (bo) {
				if (usuari.isEsAdministrador()) {
					identity.addRole("admin");
				} else {
					carregaDadesEmpresa();
					log.info("nom de l'empresa: " + empresaFeta.getInstance().getNom());
				}
			}
		} catch (Throwable t) {
			log.error(t);
			bo = false;
		}
		if (bo)
			log.info("L'usuari {0} s'ha identificat b��? : {1} ", credentials.getUsername(), "si");
		else
			log.info("L'usuari {0} s'ha identificat b��? : {1} ", credentials.getUsername(), "no");
		return bo;
	}

}