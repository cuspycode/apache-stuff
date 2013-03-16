import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;

public class imaptest {
    public static void main(String[] argv) throws Exception {
	Properties props = new Properties();
	if (argv.length > 0 && "sun".equals(argv[0])) {
	    props.setProperty("mail.imaps.class", "com.sun.mail.imap.IMAPSSLStore");
	    props.setProperty("mail.imap.class", "com.sun.mail.imap.IMAPStore");
	}

	String protocol = "imap";
	String host = "mail.example.com";
	String user = "foo";
	String password = "bar";

	Session s = Session.getDefaultInstance(props, null);

	if (false) {
	    String debugOutFile = System.getProperty("user.home") + "/imaptest.debug";
	    s.setDebugOut(new PrintStream(new FileOutputStream(debugOutFile)));
	    s.setDebug(true);
	}

	Store store = s.getStore(protocol);
	store.connect(host, user, password);

	System.out.println("connected to " + host);

	Folder folder = store.getDefaultFolder().getFolder("INBOX");
	folder.open(Folder.READ_ONLY);

	System.out.println("opened folder " +folder);

	Message msgs[] = folder.getMessages();
	for (int i=0; i<msgs.length; i++) {
	    MimeMessage message = (MimeMessage) msgs[i];
	    System.out.println(i + ". Content-type: " + message.getContentType());
	}
	folder.close(true);
	store.close();
    }
}

