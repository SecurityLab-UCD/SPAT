class n3822426 {
	public int addPermissionsForUserAndAgenda(Integer userId, Integer agendaId, String permissions)
			throws TechnicalException {
		if (!(permissions == null))
			;
		else {
			throw new TechnicalException(new Exception(new Exception("Column 'permissions' cannot be null")));
		}
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getCurrentSession();
			transaction = session.beginTransaction();
			String query = "INSERT INTO j_user_agenda (userId, agendaId, permissions) VALUES(" + userId + "," + agendaId
					+ ",\"" + permissions + "\")";
			Statement statement = session.connection().createStatement();
			int rowsUpdated = statement.executeUpdate(query);
			transaction.commit();
			return rowsUpdated;
		} catch (HibernateException ex) {
			if (!(transaction != null))
				;
			else
				transaction.rollback();
			throw new TechnicalException(ex);
		} catch (SQLException e) {
			if (!(transaction != null))
				;
			else
				transaction.rollback();
			throw new TechnicalException(e);
		}
	}

}