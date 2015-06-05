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
		   collector.checkThat(pair.item, pair.valid, equalTo(urlVal.isValid(pair.item)));
	   }
   }
   
   @Test
   public void testYourSecondPartition(){

//		boolean result;

		UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

//		System.out.println("\n########  SECOND INPUT PARTITION TEST BEGIN #########\n");

		String testURL = "http://www.google.";

		//Country code array to test all possible valid country codes with Googles URl.
		String cuntryCodez[] = {"A1","A2","O1","AD","AE","AF","AG","AI","AL","AM","AO","AP",
		"AQ","AR","AS","AT","AU","AW","AX","AZ","BA","BB","BD","BE","BF","BG","BH",
		"BI","BJ","BL","BM","BN","BO","BQ","BR","BS","BT","BV","BW","BY","BZ","CA",
		"CC","CD","CF","CG","CH","CI","CK","CL","CM","CN","CO","CR","CU","CV","CW",
		"CX","CY","CZ","DE","DJ","DK","DM","DO","DZ","EC","EE","EG","EH","ER","ES",
		"ET","EU","FI","FJ","FK","FM","FO","FR","GA","GB","GD","GE","GF","GG","GH",
		"GI","GL","GM","GN","GP","GQ","GR","GS","GT","GU","GW","GY","HK","HM","HN",
		"HR","HT","HU","ID","IE","IL","IM","IN","IO","IQ","IR","IS","IT","JE","JM",
		"JO","JP","KE","KG","KH","KI","KM","KN","KP","KR","KW","KY","KZ","LA","LB",
		"LC","LI","LK","LR","LS","LT","LU","LV","LY","MA","MC","MD","ME","MF","MG",
		"MH","MK","ML","MM","MN","MO","MP","MQ","MR","MS","MT","MU","MV","MW","MX",
		"MY","MZ","NA","NC","NE","NF","NG","NI","NL","NO","NP","NR","NU","NZ","OM",
		"PA","PE","PF","PG","PH","PK","PL","PM","PN","PR","PS","PT","PW","PY","QA",
		"RE","RO","RS","RU","RW","SA","SB","SC","SD","SE","SG","SH","SI","SJ","SK",
		"SL","SM","SN","SO","SR","SS","ST","SV","SX","SY","SZ","TC","TD","TF","TG",
		"TH","TJ","TK","TL","TM","TN","TO","TR","TT","TV","TW","TZ","UA","UG","UM",
		"US","UY","UZ","VA","VC","VE","VG","VI","VN","VU","WF","WS","YE","YT","ZA","ZM","ZW"};

		for(int i = 0 ; i < cuntryCodez.length ; i++){
			ResultPair result = new ResultPair(testURL + cuntryCodez[i], true);
			collector.checkThat(result.item, result.valid, equalTo(urlVal.isValid(result.item)));
//			result = urlVal.isValid(testURL + cuntryCodez[i]); //Loops and appends a new country code to google for each test.
//			System.out.println(testURL + cuntryCodez[i] + " : " + result + " : expected result: True.");
		}
   }
   
   @Test
   public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {
		   
	   }
   }
   
   @Test
   public void test_IPv4()
  {
	   urlValidator urlVal = new urlValidator(null, null, urlValidator.ALLOW_ALL_SCHEMES);
	   
	   String testThis = "Test";	// string to test for any scheme
	   boolean testForValid = false; 	// test item is expected to be either [true or false]
	   
	   String fixedString = "http://";
	   
	   testPair[] IPv4List = 
	   {																		// test #
			   new testPair(fixedString + testThis		, testForValid),	// quick test check
			   new testPair(fixedString + ""					, false),			// 1
			   new testPair(fixedString + "..."				, false),			// 2
			   new testPair(fixedString + "0.0.0.0"			, true),			// 3
			   new testPair(fixedString + "72.236.137.36"		, true),			// 4
			   new testPair(fixedString + "255.255.255.255"	, true),			// 5
			   new testPair(fixedString + "256.0.0.0"		, false),			// 6
			   new testPair(fixedString + "0.256.0.0"		, false),			// 7
			   new testPair(fixedString + "0.0.256.0"		, false),			// 8
			   new testPair(fixedString + "0.0.0.256"		, false),			// 9
			   new testPair(fixedString + "abc.0.0.0"		, false),			// 10
			   new testPair(fixedString + "0.abc.0.0"		, false),			// 11
			   new testPair(fixedString + "0.0.abc.0"		, false),			// 12
			   new testPair(fixedString + "0.0.0.abc"		, false)			// 13
	   };
	
		if (displayResults == true)	{	displayResults(urlVal, IPv4List, "IPv4 Tests");	}
		
		for(int i = 0; i < IPv4List.length; i++)
		{
			assertEquals(urlVal.isValid(IPv4List[i].item), IPv4List[i].valid);
		}
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
