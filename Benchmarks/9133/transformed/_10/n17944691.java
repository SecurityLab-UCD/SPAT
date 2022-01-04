class n17944691 {
	public static void uploadAsync(final ArrayList<RecordedGeoPoint> recordedGeoPoints) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					if (!Util.isSufficienDataForUpload(recordedGeoPoints))
						return;
					final HttpClient httpClient = new DefaultHttpClient();
					final InputStream gpxInputStream = new ByteArrayInputStream(
							RecordedRouteGPXFormatter.create(recordedGeoPoints).getBytes());
					final MultipartEntity requestEntity = new MultipartEntity();
					final HttpPost request = new HttpPost(UPLOADSCRIPT_URL);
					requestEntity.addPart("gpxfile",
							new InputStreamBody(gpxInputStream, "" + System.currentTimeMillis() + ".gpx"));
					httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
					request.setEntity(requestEntity);
					final HttpResponse response = httpClient.execute(request);
					final int status = response.getStatusLine().getStatusCode();
					if (status != HttpStatus.SC_OK) {
						logger.error("GPXUploader", "status != HttpStatus.SC_OK");
					} else {
						final char[] buf = new char[8 * 1024];
						final Reader r = new InputStreamReader(
								new BufferedInputStream(response.getEntity().getContent()));
						final StringBuilder sb = new StringBuilder();
						int read;
						while ((read = r.read(buf)) != -1)
							sb.append(buf, 0, read);
						logger.debug("GPXUploader", "Response: " + sb.toString());
					}
				} catch (final Exception e) {
				}
			}
		}).start();
	}

}