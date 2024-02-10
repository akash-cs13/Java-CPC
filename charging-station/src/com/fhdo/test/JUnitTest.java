package com.fhdo.test;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName; 


@Suite
@SuiteDisplayName("JUnit - Unit Tests")
@SelectPackages({"com.fhdo.test.cases"})
public class JUnitTest {

}
