/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.util.ArrayList;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import static org.hamcrest.CoreMatchers.equalTo;


/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest {
   private ArrayList<ResultPair> m_schemas = this.InitializeSchemas();
   private ArrayList<ResultPair> m_fileUrls = this.InitializeManualUrls();
	
   @Rule
   public ErrorCollector collector = new ErrorCollector();
   
   /**
    * Performs a manual test of URLs by reading from a text file
    */
   @Test
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   //sanity check
	   ResultPair sane = new ResultPair("http://www.amazon.com", true);
	   collector.checkThat(sane.item, sane.valid, equalTo(urlVal.isValid(sane.item)));
	   //test manualUrls
	   for(ResultPair res : this.m_fileUrls)
	   {
		   collector.checkThat(res.item, res.valid, equalTo(urlVal.isValid(res.item)));
	   }
   }
   
   /**
    *  Creates a set of URI schemes to test with
    *  
    */
   @Test
   public void testYourFirstPartition()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   String resource = "://www.example.com";
	   ArrayList<ResultPair> schemaUrls = new ArrayList<ResultPair>();
	   //append the static valid resource string to each schema as a new pair
	   for(ResultPair schema : this.m_schemas)
	   {
		   ResultPair pair = new ResultPair(schema.item + resource, schema.valid);
		   schemaUrls.add(pair);
	   }
	   //test each now full URL
	   for(ResultPair pair : schemaUrls)
	   {
		   collector.checkThat(pair.valid, equalTo(urlVal.isValid(pair.item)));
	   }
   }
   
   @Test
   public void testYourSecondPartition(){
	   
   }
   
   @Test
   public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {
		   
	   }
   }
   
   @Test
   public void testAnyOtherUnitTest()
   {
	   
   }
   /**
    * Create set of tests by taking the testUrlXXX arrays and
    * running through all possible permutations of their combinations.
    *
    * @param testObjects Used to create a url.
    */

   
   /**
    * Initializes manual URLs for testing, not expected to be used elsewhere
    * @return
    */
   private ArrayList<ResultPair> InitializeManualUrls()
   {
	   ArrayList<ResultPair> fileUrls = new ArrayList<ResultPair>();
	   fileUrls.add(new ResultPair("https://www.google.com", true));
	   fileUrls.add(new ResultPair("https://///www.google.com", false));
	   fileUrls.add(new ResultPair("", false));
	   fileUrls.add(new ResultPair("http://74.125.224.72/", true));
	   fileUrls.add(new ResultPair("file:///C:/", true));
	   fileUrls.add(new ResultPair("http://WWW.GOOGLE.COM", true));
	   fileUrls.add(new ResultPair("http://www.google.com:80/test1", true));
	   fileUrls.add(new ResultPair("h3t://go.cc:65a/$23?action=edit&mode=up", false));
	   fileUrls.add(new ResultPair("12345", false));
	   fileUrls.add(new ResultPair("http://www.space in here.com", false));
	   fileUrls.add(new ResultPair("http://site.com/#citation", true));
	   fileUrls.add(new ResultPair("ftp://site.com", true));
	   fileUrls.add(new ResultPair("http://site.com/hyphen-here", true));
	   fileUrls.add(new ResultPair("foo://example.com:8042/over/there?name=ferret#nose", true));
	   fileUrls.add(new ResultPair("http://user@example.com", true));
	   fileUrls.add(new ResultPair("http://142.10.5.2:8080/", true));
	   return fileUrls;
   }
   
   /**
    * Initializes a list of schemas into ResultPairs
    * list from: http://www.iana.org/assignments/uri-schemes/uri-schemes.xhtml
    * @return
    */
   private ArrayList<ResultPair> InitializeSchemas()
   {
	   ArrayList<ResultPair> schemas = new ArrayList<ResultPair>();
	   schemas.add(new ResultPair("z39.50s", true));
	   schemas.add(new ResultPair("z39.50r", true));
	   schemas.add(new ResultPair("xmpp", true));
	   schemas.add(new ResultPair("xmlrpc.beeps", true));
	   schemas.add(new ResultPair("xmlrpc.beep", true));
	   schemas.add(new ResultPair("xcon-userid", true));
	   schemas.add(new ResultPair("xcon", true));
	   schemas.add(new ResultPair("wss", true));
	   schemas.add(new ResultPair("ws", true));
	   schemas.add(new ResultPair("vemmi", true));
	   schemas.add(new ResultPair("urn", true));
	   schemas.add(new ResultPair("tv", true));
	   schemas.add(new ResultPair("turns", true));
	   schemas.add(new ResultPair("turn", true));
	   schemas.add(new ResultPair("tn3270", true));
	   schemas.add(new ResultPair("tip", true));
	   schemas.add(new ResultPair("thismessage", true));
	   schemas.add(new ResultPair("tftp", true));
	   schemas.add(new ResultPair("telnet", true));
	   schemas.add(new ResultPair("tel", true));
	   schemas.add(new ResultPair("tag", true));
	   schemas.add(new ResultPair("stuns", true));
	   schemas.add(new ResultPair("stun", true));
	   schemas.add(new ResultPair("soap.beeps", true));
	   schemas.add(new ResultPair("soap.beep", true));
	   schemas.add(new ResultPair("snmp", true));
	   schemas.add(new ResultPair("sms", true));
	   schemas.add(new ResultPair("sips", true));
	   schemas.add(new ResultPair("sip", true));
	   schemas.add(new ResultPair("sieve", true));
	   schemas.add(new ResultPair("shttp", true));
	   schemas.add(new ResultPair("session", true));
	   schemas.add(new ResultPair("service", true));
	   schemas.add(new ResultPair("rtspu", true));
	   schemas.add(new ResultPair("rtsps", true));
	   schemas.add(new ResultPair("rtsp", true));
	   schemas.add(new ResultPair("reload", true));
	   schemas.add(new ResultPair("pres", true));
	   schemas.add(new ResultPair("pop", true));
	   schemas.add(new ResultPair("pkcs11", true));
	   schemas.add(new ResultPair("opaquelocktoken", true));
	   schemas.add(new ResultPair("nntp", true));
	   schemas.add(new ResultPair("nih", true));
	   schemas.add(new ResultPair("ni", true));
	   schemas.add(new ResultPair("nfs", true));
	   schemas.add(new ResultPair("news", true));
	   schemas.add(new ResultPair("mupdate", true));
	   schemas.add(new ResultPair("mtqp", true));
	   schemas.add(new ResultPair("msrps", true));
	   schemas.add(new ResultPair("msrp", true));
	   schemas.add(new ResultPair("mid", true));
	   schemas.add(new ResultPair("mailto", true));
	   schemas.add(new ResultPair("ldap", true));
	   schemas.add(new ResultPair("jabber", true));
	   schemas.add(new ResultPair("iris.xpcs", true));
	   schemas.add(new ResultPair("iris.xpc", true));
	   schemas.add(new ResultPair("iris.lwz", true));
	   schemas.add(new ResultPair("iris.beep", true));
	   schemas.add(new ResultPair("iris", true));
	   schemas.add(new ResultPair("ipps", true));
	   schemas.add(new ResultPair("ipp", true));
	   schemas.add(new ResultPair("info", true));
	   schemas.add(new ResultPair("imap", true));
	   schemas.add(new ResultPair("im", true));
	   schemas.add(new ResultPair("icap", true));
	   schemas.add(new ResultPair("iax", true));
	   schemas.add(new ResultPair("https", true));
	   schemas.add(new ResultPair("http", true));
	   schemas.add(new ResultPair("h323", true));
	   schemas.add(new ResultPair("gopher", true));
	   schemas.add(new ResultPair("go", true));
	   schemas.add(new ResultPair("geo", true));
	   schemas.add(new ResultPair("ftp", true));
	   schemas.add(new ResultPair("file", true));
	   schemas.add(new ResultPair("example", true));
	   schemas.add(new ResultPair("dns", true));
	   schemas.add(new ResultPair("dict", true));
	   schemas.add(new ResultPair("dav", true));
	   schemas.add(new ResultPair("data", true));
	   schemas.add(new ResultPair("crid", true));
	   schemas.add(new ResultPair("coaps", true));
	   schemas.add(new ResultPair("coap", true));
	   schemas.add(new ResultPair("cid", true));
	   schemas.add(new ResultPair("cap", true));
	   schemas.add(new ResultPair("acct", true));
	   schemas.add(new ResultPair("acap", true));
	   schemas.add(new ResultPair("about", true));
	   schemas.add(new ResultPair("aaas", true));
	   schemas.add(new ResultPair("aaa", true));
	   return schemas;
   }
}
