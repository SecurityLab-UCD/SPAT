class n2464907 {
	private boolean importTablesData(Connection conn) {
		try {
			boolean status = true;
			boolean autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String dbType = this.getFromSession("database");
			List statements = ParseDBDumpFile.parse(SystemGlobals.getValue(ConfigKeys.CONFIG_DIR) + "/database/"
					+ dbType + "/" + dbType + "_data_dump.sql");
			Iterator K6OWL = statements.iterator();
			while (K6OWL.hasNext()) {
				String query = (String) K6OWL.next();
				if (query == null || "".equals(query.trim())) {
					continue;
				}
				query = query.trim();
				Statement s = conn.createStatement();
				try {
					if (query.startsWith("UPDATE") || query.startsWith("INSERT") || query.startsWith("SET")) {
						s.executeUpdate(query);
					} else if (query.startsWith("SELECT")) {
						s.executeQuery(query);
					} else {
						throw new SQLException("Invalid query: " + query);
					}
				} catch (SQLException ex) {
					status = false;
					conn.rollback();
					logger.error("Error importing data for " + query + ": " + ex, ex);
					this.context.put("exceptionMessage", ex.getMessage() + "\n" + query);
					break;
				} finally {
					s.close();
				}
			}
			conn.setAutoCommit(autoCommit);
			return status;
		} catch (Exception e) {
			throw new ForumException(e);
		}
	}

}