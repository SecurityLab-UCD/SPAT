class n9168699 {
	public void insertArchiveEntries(ArchiveEntry entries[]) throws WeatherMonitorException {
		String sql = null;
		try {
			Connection con = getConnection();
			ResultSet rslt = null;
			Statement stmt = con.createStatement();
			con.setAutoCommit(false);
			for (int i = 0; i < entries.length; i++) {
				if (!sanityCheck(entries[i])) {
				} else {
					sql = getSelectSql(entries[i]);
					rslt = stmt.executeQuery(sql);
					if (rslt.next()) {
						if (rslt.getInt(1) == 0) {
							sql = getInsertSql(entries[i]);
							if (stmt.executeUpdate(sql) != 1) {
								con.rollback();
								System.out.println("rolling back sql");
								throw new WeatherMonitorException("exception on insert");
							}
						}
					}
				}
			}
			con.commit();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WeatherMonitorException(e.getMessage());
		}
	}

}