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

package org.wahlzeit.services;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * Test cases for the EmailAddress class.
 */
public class EmailAddressTest extends TestCase {

	private final String EMAIL_ADDRESS_STRING_A = "userA@hostA.com";
	private final String EMAIL_ADDRESS_STRING_B = "userB@hostB.com";
	private final String EMAIL_ADDRESS_STRING_C = "userC@hostC.com";
	private final String EMAIL_ADDRESS_STRING_X = "userA@hostA.com";
	private final String EMAIL_ADDRESS_STRING_EMPTY = "";
	
	private EmailAddress emailAddressA;
	private EmailAddress emailAddressB;
	private EmailAddress emailAddressC;
	private EmailAddress emailAddressX;
	private EmailAddress emailAddressEmpty;

	public EmailAddressTest(String name) {
		super(name);
	}

	protected boolean createEmailAddressIgnoreException(String ea) {
		try {
			EmailAddress.getFromString(ea);
			return true;
		} catch (IllegalArgumentException ex) {
			// creation failed
			return false;
		}
	}
	
	@Before
	public void setUp() {
		emailAddressA = EmailAddress.getFromString(EMAIL_ADDRESS_STRING_A);
		emailAddressB = EmailAddress.getFromString(EMAIL_ADDRESS_STRING_B);
		emailAddressC = EmailAddress.getFromString(EMAIL_ADDRESS_STRING_C);
		emailAddressX = EmailAddress.getFromString(EMAIL_ADDRESS_STRING_X);
		emailAddressEmpty = EmailAddress.getFromString(EMAIL_ADDRESS_STRING_EMPTY);
	}
	
	@Test
	public void testGetEmailAddressFromString() {
		// invalid email addresses are allowed for local testing and online avoided by Google
		assertTrue(createEmailAddressIgnoreException("bingo@bongo"));
		assertTrue(createEmailAddressIgnoreException("bingo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo.bongo@bongo.com"));
		assertTrue(createEmailAddressIgnoreException("bingo+bongo@bango"));
	}

	@Test
	public void testEmptyEmailAddress() {
		assertFalse(EmailAddress.EMPTY.isValid());
	}
	
	@Test
	public void testIsEmpty() {
		assertTrue(emailAddressEmpty.isEmpty());
		assertFalse(emailAddressA.isEmpty());
	}

	@Test
	public void testIsValid() {
		assertTrue(emailAddressA.isValid());
		assertFalse(emailAddressEmpty.isValid());
	}
	
	@Test
	public void testIsEqual() {
		assertTrue(emailAddressB.isEqual(emailAddressB));
		assertFalse(emailAddressA.isEqual(emailAddressC));
		assertTrue(emailAddressA.isEqual(emailAddressX));
	}
	
	@Test
	public void testAsInternetAddress() {
		assertNull(emailAddressEmpty.asInternetAddress());
		assertNotNull(emailAddressC.asInternetAddress());
	}
	
	@Test
	public void testAsString() {
		assertEquals(EMAIL_ADDRESS_STRING_EMPTY, emailAddressEmpty.asString());
		assertEquals(EMAIL_ADDRESS_STRING_A, emailAddressA.asString());
	}
}
