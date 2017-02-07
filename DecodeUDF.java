import org.apache.hadoop.hive.ql.exec.Description;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.net.URLDecoder;

@Description(

		name = "url_decode",

		value = "_FUNC_(url) - Decodes a x-www-form-urlencoded string")
public final class DecodeUDF extends UDF {
	public String evaluate(final String s) {
		if (s == null)
			return null;
		return getString(s);
	}

	public static String getString(String s) {
		try {
			s = s.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
			s = s.replaceAll("\\+", "%2B");
			s = s.replaceAll(" ", "");
			s = URLDecoder.decode(s, "UTF-8");
			s = s.replace("\r\n", "").replace("\t", "").replace("\n", "");
			return s;
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String args[]) {
		String t = "%E5%A4%AA%E5%8E%9F-%E4%B8%89%E4%BA%9A";
		System.out.println(getString(t));
	}
}