class n19875183 {
	private void copy(File inputFile, File outputFile) {
		BufferedWriter writer = null;
		BufferedReader reader = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"));
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
			while (reader.ready()) {
				writer.write(reader.readLine());
				writer.write(System.getProperty("line.separator"));
			}
		} catch (IOException e) {
		} finally {
			try {
				if (reader != null)
					reader.close();
				if (writer != null)
					writer.close();
			} catch (IOException e1) {
			}
		}
	}

}