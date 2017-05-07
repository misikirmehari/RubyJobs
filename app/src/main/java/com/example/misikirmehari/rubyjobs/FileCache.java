package com.example.misikirmehari.rubyjobs;

import android.Manifest;
import android.content.Context;

import java.io.File;

public class FileCache {

	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	private static String[] PERMISSIONS_STORAGE = {
			Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE
	};

	private File cacheDir;

	public FileCache(Context context) {
		// Find the dir to save cached images

		//String extStore = System.getenv("EXTERNAL_STORAGE");

		File root = android.os.Environment.getExternalStorageDirectory();
		cacheDir = new File(root.getAbsolutePath() + "/ImageRepos");
		//noinspection ResultOfMethodCallIgnored
		cacheDir.mkdirs();

		// Get Contents of the cache
		cacheDir = context.getCacheDir();

	}

	public File getFile(String url) {
		String filename = String.valueOf(url.hashCode());
		// String filename = URLEncoder.encode(url);
		File f = new File(cacheDir, filename);
		return f;

	}

	public void clear() {
		File[] files = cacheDir.listFiles();
		if (files == null)
			return;
		for (File f : files)
			f.delete();
	}

}