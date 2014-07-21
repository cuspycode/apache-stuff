package foo;

import javax.ejb.*;
import java.util.logging.Logger;

@Singleton
@Lock(LockType.READ)
@Startup
public class BugBean {
    static final Logger logger = Logger.getLogger(BugBean.class.getName());

    @Lock(LockType.WRITE)
    @Schedule(second="*/5", minute="*", hour="*")
    public void spam() {
	logger.info("Spam spam spam");
    }

}
