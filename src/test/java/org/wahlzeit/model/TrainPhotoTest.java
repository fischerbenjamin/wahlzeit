package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;
import org.wahlzeit.testEnvironmentProvider.SysConfigProvider;
import org.wahlzeit.testEnvironmentProvider.UserServiceProvider;
import org.wahlzeit.testEnvironmentProvider.UserSessionProvider;

/**
 * Component test for {@link TrainPhoto}.
 */
public class TrainPhotoTest {

	private TrainPhoto photo;

	@ClassRule
	public static RuleChain ruleChain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider()).
			around(new SysConfigProvider()).
			around(new UserServiceProvider()).
			around(new UserSessionProvider());
	
	@Before
	public void setUp() {
		photo = new TrainPhoto();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidType() {
		photo.setType(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidEngine() {
		photo.setEngine(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidSpeed() {
		photo.setSpeed(-1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidWeight() {
		photo.setWeight(-100);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidBuildYear() {
		photo.setBuildYear(-10000);
	}
	
	@Test
	public void testDefaultInit() {
		assertEquals(photo.getEngine(), TrainPhoto.Engine.UNKNOWN);
		assertEquals(photo.getType(), TrainPhoto.Type.UNKNOWN);
	}
	
}
