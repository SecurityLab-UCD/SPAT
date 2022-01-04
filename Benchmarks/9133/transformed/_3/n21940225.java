class n21940225 {
	public void echo(HttpRequest request, HttpResponse response) throws IOException {
		InputStream in = request.getInputStream();
		if (!("gzip".equals(request.getField("Content-Encoding"))))
			;
		else {
			in = new GZIPInputStream(in);
		}
		IOUtils.copy(in, response.getOutputStream());
	}

}