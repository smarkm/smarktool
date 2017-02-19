package org.smark.tool;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextHelper;
public class NamingSearch {
	public static void names(String initialPort)  {
		String[] args = {"-ORBInitialPort",initialPort};
		ORB orb = ORB.init(args, null );
		
		Object object;
		try {
			object = orb.resolve_initial_references("NameService");
			NamingContextExt ctx = NamingContextExtHelper.narrow(object);
			printContext( ctx, "" );
		} catch (InvalidName e) {
			e.printStackTrace();
		}
		/*String iorString = "IOR:000000000000002f49444c3a636f6d2f6f73736572612f617070732f656d732f69646c2f436f726261454d535365727665723a312e3000000000000100000000000000a2000102000000000a3132372e302e302e3100080400000053afabcb0000000020000003e800000001000000000000000200000008526f6f74504f41000000001a4f535345726153657276657250657273697374656e74504f410000000000000a454d53536572766963651400000000020000000100000020000000000001000100000002050100010001002000010109000000010001010000000026000000020002";
		Object emsNameObject =orb.string_to_object(iorString);*/
		//CorbaEMSServer emsServer = CorbaEMSServerHelper.narrow(emsNameObject);
		//String rs = emsServer.getOidConfig();
		//System.out.println(rs);

		
	}
	
	/**
	 * MySMServiceAppService_logger
		EMSServiceAppService
		SanityChecker
		EMSService_logger
		MySMServiceAppService_notifier
		ServiceLocator
		EMSServiceAppService_logger
		EMSService_Internal
		EMSOM_localhost
		MySMService
		EMSService
		MySMServiceAppService
		EMSServiceAppService_notifier

	 * @throws Exception
	 */
	public static void loadIOR(String initialPort,String name )  {
		String[] args = {"-ORBInitialPort",initialPort};
		ORB orb = ORB.init(args, null );
		
		Object object;
		try {
			object = orb.resolve_initial_references("NameService");
			NamingContextExt ctx = NamingContextExtHelper.narrow(object);
			Object object2 = ctx.resolve_str(name);
			System.out.println(object2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 public static void printContext( NamingContext nc, String parent )
	  { 
	    try {
	      final int batchSize = 1000;
	      BindingListHolder bList = new BindingListHolder( );
	      BindingIteratorHolder bIterator = new BindingIteratorHolder( );

	      nc.list( batchSize, bList, bIterator );

	      for ( int i=0; i < bList.value.length; i++ ) {
	        NameComponent[] name = { bList.value[i].binding_name[0] };
	        if (bList.value[i].binding_type == BindingType.ncontext) {
	            NamingContext context = NamingContextHelper.narrow( 
	                nc.resolve( name ) );
	            printContext( context, parent + name[0].id + "." );
	        } else {
	            System.out.println( parent + name[0].id );
	        }
	      }

	    } catch (Exception e) {
	        System.out.println("ERROR : " + e) ;
	    }
	  }

	public static void execute(String[] args) {
		String cmd = args[0];
		switch (cmd) {
		case "names":
			names(args[1]);
			break;

		case "ior":
			loadIOR(args[1], args[2]);
			break;
		}
	}
}
