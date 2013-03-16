1. Edit the IMAP server/protocol/account settings in the source code.

2. Compile:

       javac -cp mail.jar imaptest.java

3. Run with geronimo-javamail:

       java -cp .:geronimo-javamail_1.4_mail-1.8.2.jar imaptest

   An NPE is thrown at the response to the AUTHENTICATE PLAIN command.

4. Run with Sun's javamail:

       java -cp .:mail.jar imaptest

   Everything works.

5. Run with both implementations in the classpath, with Sun as provider:

       java -cp .:geronimo-javamail_1.4_mail-1.8.2.jar:mail.jar imaptest sun

   Now authentication works, but MimeMessage.getContentType() breaks and
   throws an NPE.

Note: the NPE at step 3 was manifested while connecting to Dovecot 1.1.7
with mandatory encryption and the following capabilities:

IMAP4rev1 SASL-IR SORT THREAD=REFERENCES MULTIAPPEND UNSELECT LITERAL+ IDLE CHILDREN NAMESPACE LOGIN-REFERRALS UIDPLUS LIST-EXTENDED I18NLEVEL=1 AUTH=PLAIN AUTH=LOGIN

An easy way to demonstrate the problem without using a real IMAP server
is to do the following on the target host:

    perl -pe '$|=1;chomp;$_.="\r\n"' | sudo nc -l 143

Then connect to it with Javamail and then enter the following commands
on the host, one line at a time:

* OK Foobar ready.
* CAPABILITY IMAP4rev1 AUTH=PLAIN AUTH=LOGIN
a0 OK Capability completed.
+ 

The last line should have a space after the "+" according to RFC 4959,
not that it makes any difference here...

