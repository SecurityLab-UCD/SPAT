class n11717079 {
	void copyFile(File inputFile, File outputFile) {
		try {
			FileReader in;
			in = new FileReader(inputFile);
			FileWriter out = new FileWriter(outputFile);
			int c;
			for (; (c = in.read()) != -1;)
				out.write(c);
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}