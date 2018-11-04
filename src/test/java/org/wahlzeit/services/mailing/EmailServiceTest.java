/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package org.wahlzeit.services.mailing;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.services.EmailAddress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EmailServiceTest {

	private final String VALID_ADDRESS_STRING_A = "test@test.de";
	private final String VALID_ADDRESS_STRING_B = "test2@test2.com";
	private final String INVALID_ADDRESS_STRING_A = "invalid.org";
	private final String INVALID_ADDRESS_STRING_B = "onlyinvalid";
	
	private EmailService emailService;
	private EmailAddress validAddressA;
	private EmailAddress validAddressB;
	private EmailAddress invalidAddressA;
	private EmailAddress invalidAddressB;

	@Before
	public void setup() throws Exception {
		emailService = EmailServiceManager.getDefaultService();
		validAddressA = EmailAddress.getFromString(VALID_ADDRESS_STRING_A);
		validAddressB = EmailAddress.getFromString(VALID_ADDRESS_STRING_B);
		invalidAddressA = EmailAddress.getFromString(INVALID_ADDRESS_STRING_A);
		invalidAddressB = EmailAddress.getFromString(INVALID_ADDRESS_STRING_B);
	}

	@Test
	public void testSendInvalidEmail() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(validAddressA, null, "lol", "hi"));
			assertFalse(emailService.sendEmailIgnoreException(null, validAddressA, null, "body"));
			assertFalse(emailService.sendEmailIgnoreException(validAddressA, null, "hi", "       "));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}

	@Test
	public void testSendValidEmail() {
		try {
			assertTrue(emailService.sendEmailIgnoreException(validAddressA, validAddressA, "hi", "test"));
		} catch (Exception ex) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}
	
	@Test
	public void testSendValidEmail2() {
		try {
			assertTrue(emailService.sendEmailIgnoreException(validAddressA, validAddressB, "header", "body"));
		} catch (Exception e) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}
	
	@Test
	public void testSendInvalidEmail2() {
		try {
			assertFalse(emailService.sendEmailIgnoreException(invalidAddressA, invalidAddressB, null, "body"));
		} catch (Exception e) {
			Assert.fail("Silent mode does not allow exceptions");
		}
	}
	
	
	
}