import static org.junit.Assert.*;

import org.junit.Test;
import org.smark.tool.NamingSearch;

public class CorbaTest {

	@Test
	public void namingTest() throws Exception {
		NamingSearch.names("2050"); 
		NamingSearch.loadIOR("2050", "EMSService_logger");
	}
}
