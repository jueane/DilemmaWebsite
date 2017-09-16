package testweb.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {

	public boolean debug;

	// URL根路径（不从配置文件读取，运行时自动获取）
	public String rootPath;

	private static Config config;

	public static Config getIntance() {
		if (config == null) {
			config = new Config();

			try {
				Properties p = new Properties();
				p.load(config.getClass().getClassLoader().getResourceAsStream("config.properties"));

				config.debug = Boolean.parseBoolean(p.getProperty("debug"));

				config.rootPath = p.getProperty("rootPath");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return config;
	}

}
