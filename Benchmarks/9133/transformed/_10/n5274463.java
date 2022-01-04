class n5274463 {
	protected void doRequest(HttpServletRequest request, HttpServletResponse response, boolean inGet)
			throws ServletException, IOException {
		response.setHeader("Server", WebConsoleServlet.SERVER_STRING);
		try {
			String requestedFilename = request.getRequestURI().substring(1);
			URL url = new URL(getJarFileName() + "/");
			JarURLConnection jarConnection = (JarURLConnection) (url.openConnection());
			String negotiatedFilename = null;
			JarFile jarFile = jarConnection.getJarFile();
			ZipEntry zipEntry = null;
			zipEntry = negotiateImageFile(jarFile, requestedFilename, isIE6OrEarlier(request.getHeader("User-Agent")));
			if (zipEntry == null) {
				zipEntry = jarFile.getEntry(requestedFilename);
			} else {
				negotiatedFilename = zipEntry.getName();
			}
			if (zipEntry == null || zipEntry.isDirectory()) {
				handleFileNotFound(inGet, request, response);
				return;
			}
			int fileSize = (int) zipEntry.getSize();
			response.setContentLength(fileSize);
			if (negotiatedFilename != null) {
				response.setContentType(getContentType(negotiatedFilename));
			} else {
				response.setContentType(getContentType(request.getRequestURI()));
			}
			InputStream in = jarFile.getInputStream(zipEntry);
			byte[] file = new byte[fileSize];
			BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
			int bytesRead = bufferedInputStream.read(file);
			bufferedInputStream.close();
			if (bytesRead == fileSize && cachingEnabled) {
				java.util.Date today = new java.util.Date();
				SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
				String date = formatter.format(GenericUtils.addOrSubstractDaysFromDate(today, 365));
				response.setHeader("Expires", date);
			}
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(file);
		} catch (FileNotFoundException e) {
			handleFileNotFound(inGet, request, response);
		} catch (IOException e) {
		} catch (Throwable t) {
			Application.getApplication().logExceptionEvent(EventLogMessage.EventType.WEB_ERROR, t);
		}
	}

}