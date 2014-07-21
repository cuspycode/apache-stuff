1. Compile with "ant".
2. Put org.terracotta.quartz.skipUpdateCheck=true in system.properties.
3. Start TomEE.
4. Deploy schedule-bug.war and tail catalina.out.
5. Redeploy schedule-bug.war by writing a new copy to webapps.
6. Watch catalina.out again.
