Spooky org.terracotta.quartz.skipUpdateCheck behaviour.

1. Compile with "ant" to produce build/schedule-bug.war.
2. Copy system.properties to tomee/conf.
3. Start TomEE.
4. Deploy schedule-bug.war and watch catalina.out.
5. Redeploy schedule-bug.war by writing a new copy to webapps.
6. Watch catalina.out again, bug stops scheduled methods.
