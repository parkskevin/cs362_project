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


import java.io.*;
import java.util.ArrayList;

import junit.framework.TestCase;


/**
 * Performs Validation Test for url validations.
 *
 * @version $Revision: 1128446 $ $Date: 2011-05-27 13:29:27 -0700 (Fri, 27 May 2011) $
 */
public class UrlValidatorTest extends TestCase {

   private boolean printStatus = false;
   private boolean printIndex = false;//print index that indicates current scheme,host,port,path, query test were using.

   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   /**
    * Performs a manual test of URLs by reading from a text file
    */
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   //sanity check
	   ResultPair sane = new ResultPair("http://www.amazon.com", true);
	   this.testResultPair(sane, urlVal);
	   //test from files by creating ResultPairs, then looping on them
	   ArrayList<ResultPair> fileUrls = this.createPairsFromFile("src/UrlsToVerify.txt");
	   for(ResultPair res : fileUrls)
	   {
		   this.testResultPair(res, urlVal);
	   }
   }
   
   /**
    *  Creates a set of URI schemes to test with
    *  list from: http://www.iana.org/assignments/uri-schemes/uri-schemes.xhtml
    */
   public void testYourFirstPartition()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   ArrayList<String> schemas = this.CreateStringList("src/Schemas.txt");
	   String resource = "://www.example.com";
	   ArrayList<ResultPair> schemaUrls = new ArrayList<ResultPair>();
	   for(String schema : schemas)
	   {
		   ResultPair pair = new ResultPair(schema + resource, true);
		   schemaUrls.add(pair);
	   }
	   for(ResultPair pair : schemaUrls)
	   {
		   this.testResultPair(pair, urlVal);
	   }
   }
   
   public void testYourSecondPartition(){
	   
   }
   
   
   public void testIsValid()
   {
	   for(int i = 0;i<10000;i++)
	   {
		   
	   }
   }
   
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
    * Creates a list of ResultPairs from a well-formed text file
    * @param filepath
    * @return
    */
   private ArrayList<ResultPair> createPairsFromFile(String filepath)
   {
	   ArrayList<ResultPair> results = new ArrayList<ResultPair>();
	   try {
			File file = new File(filepath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split("\t");
				if(parts.length < 2)
				{
					String[] tmp = {"", parts[0]};
					parts = tmp;
				}
				boolean expect;
				if(parts[1].contentEquals("invalid"))
					expect = false;
				else if(parts[1].contentEquals("valid"))
					expect = true;
				else
					continue;
				ResultPair result = new ResultPair(parts[0], expect);
				result.item = parts[0];
				results.add(result);
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	return results;
   }
   
   /**
    * Tests a ResultPair against a UrlValidator
    * @param result
    * @param urlVal
    */
   private void testResultPair(ResultPair result, UrlValidator urlVal)
   {
	   assertEquals(result.item, urlVal.isValid(result.item), result.valid);
	   System.out.println(result.item + " PASS");
   }
   
   /**
    * Grabs a line separated list of data from a file
    * @param filepath
    * @return
    */
   private ArrayList<String> CreateStringList(String filepath)
   {
	   ArrayList<String> results = new ArrayList<String>();
	   try {
			File file = new File(filepath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				results.add(line);
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   return results;
   }
}
