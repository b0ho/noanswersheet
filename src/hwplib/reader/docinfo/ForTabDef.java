package hwplib.reader.docinfo;

import java.io.IOException;

import hwplib.object.docinfo.TabDef;
import hwplib.object.docinfo.borderfill.BorderType;
import hwplib.object.docinfo.tabdef.TabInfo;
import hwplib.object.docinfo.tabdef.TabSort;
import hwplib.util.compoundFile.reader.StreamReader;

/**
 * 탭 정의 레코드를 읽는다.
 * 
 * @author neolord
 */
public class ForTabDef {
	/**
	 * 탭 정의 레코드를 읽는다.
	 * 
	 * @param td
	 *            탭 정의 레코드
	 * @param sr
	 *            스트림 리더
	 * @throws IOException
	 */
	public static void read(TabDef td, StreamReader sr) throws IOException {
		td.getProperty().setValue(sr.readUInt4());
		long tabInfoCount = sr.readUInt4();
		if (tabInfoCount > 0) {
			tabInfos(td, sr, tabInfoCount);
		}
	}

	/**
	 * 탭 정보 부분을 읽는다.
	 * 
	 * @param td
	 *            탭 정의 레코드
	 * @param sr
	 *            스트림 리더
	 * @param tabInfoCount
	 *            탭 정의의 개수
	 * @throws IOException
	 */
	private static void tabInfos(TabDef td, StreamReader sr, long tabInfoCount)
			throws IOException {
		for (long i = 0; i < tabInfoCount; i++) {
			TabInfo ti = td.addNewTabInfo();
			ti.setPosition(sr.readUInt4());
			ti.setTabSort(TabSort.valueOf((byte) sr.readUInt1()));
			ti.setFillSort(BorderType.valueOf((byte) sr.readUInt1()));
			sr.skip(2); // reserved
		}
	}
}
