class n6615576 {
	public static boolean copyFile(File src, File target) throws IOException {
		if (src == null || target == null || !src.exists())
			return false;
		InputStream ins = new BufferedInputStream(new FileInputStream(src));
		if (!target.exists())
			if (!createNewFile(target))
				return false;
		int b;
		OutputStream ops = new BufferedOutputStream(new FileOutputStream(target));
		while (-1 != (b = ins.read()))
			ops.write(b);
		Streams.safeClose(ins);
		Streams.safeFlush(ops);
		Streams.safeClose(ops);
		return target.setLastModified(src.lastModified());
	}

}