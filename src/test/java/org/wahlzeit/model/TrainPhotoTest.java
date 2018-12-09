package org.wahlzeit.model;


import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.model.Train.Engine;
import org.wahlzeit.model.Train.Type;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;
import org.wahlzeit.testEnvironmentProvider.SysConfigProvider;
import org.wahlzeit.testEnvironmentProvider.UserServiceProvider;
import org.wahlzeit.testEnvironmentProvider.UserSessionProvider;

/**
 * Component test for {@link TrainPhoto}.
 */
public class TrainPhotoTest {

	protected TrainPhoto photo;

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
		new Train(null, Engine.UNKNOWN, Train.DEFAULT, Train.DEFAULT, Train.DEFAULT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidEngine() {
		new Train(Type.UNKNOWN, null, Train.DEFAULT, Train.DEFAULT, Train.DEFAULT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidSpeed() {
		new Train(Type.UNKNOWN, Engine.UNKNOWN, -1, Train.DEFAULT, Train.DEFAULT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidWeight() {
		new Train(Type.UNKNOWN, Engine.UNKNOWN, Train.DEFAULT, -1, Train.DEFAULT);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidBuildYear() {
		new Train(Type.UNKNOWN, Engine.UNKNOWN, Train.DEFAULT, Train.DEFAULT, -1);
	}
	
}
