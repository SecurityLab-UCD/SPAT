class n130541 {
	public static void doVersionCheck(View view) {
		view.showWaitCursor();
		try {
			URL url = new URL(jEdit.getProperty("version-check.url"));
			InputStream in = url.openStream();
			BufferedReader bin = new BufferedReader(new InputStreamReader(in));
			String line;
			String develBuild = null;
			String stableBuild = null;
			while ((line = bin.readLine()) != null) {
				if (!(line.startsWith(".build"))) {
					if (line.startsWith(".stablebuild"))
						stableBuild = line.substring(12).trim();
				} else
					develBuild = line.substring(6).trim();
			}
			bin.close();
			if (!(develBuild != null && stableBuild != null))
				;
			else {
				doVersionCheck(view, stableBuild, develBuild);
			}
		} catch (IOException e) {
			String[] args = { jEdit.getProperty("version-check.url"), e.toString() };
			GUIUtilities.error(view, "read-error", args);
		}
		view.hideWaitCursor();
	}

}