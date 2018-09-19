package com.pratise.gmailAPI.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pratise.gmailAPI.GmailUtil;

public class TestMailUtil {

	@Test
	public void testReportUtil()  {
		GmailUtil.initialiseProperties();
		boolean isSend = GmailUtil.sendMail("Test Subject","Test Body");
		Assert.assertTrue(isSend, "The Email was not send as the return value was false.");
	}

}
