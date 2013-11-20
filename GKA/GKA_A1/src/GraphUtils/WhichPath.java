package GraphUtils;

public class WhichPath {

	private static String OS = System.getProperty("os.name").toLowerCase();
	private static String swaneetpath = "C:/Users/Swaneet/github/WS1314/GKA/graphs/";
	private static String matzepath = "/Users/sacry/dev/uni/s3/WS1314/GKA/graphs/";

	public static String getPath() {
		return isWindows() ? swaneetpath : matzepath;
	}

	public static boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	public static boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}

	public static boolean isUnix() {
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS
				.indexOf("aix") > 0);
	}
}
