package org.wahlzeit.model;


import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;
import org.wahlzeit.testEnvironmentProvider.SysConfigProvider;
import org.wahlzeit.testEnvironmentProvider.UserServiceProvider;
import org.wahlzeit.testEnvironmentProvider.UserSessionProvider;
import org.wahlzeit.utils.Defaults;

/**
 * Component test for {@link TrainPhoto}.
 */
public class TrainPhotoTest {

	@ClassRule
	public static RuleChain ruleChain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider()).
			around(new SysConfigProvider()).
			around(new UserServiceProvider()).
			around(new UserSessionProvider());

	@Test(expected = IllegalArgumentException.class)
	public void testNullMyID() {
		PhotoId id = null;
		new TrainPhoto(id);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullTrain() {
		Train train = null;
		new TrainPhoto(train);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBothNull() {
		Train train = null;
		PhotoId id = null;
		new TrainPhoto(id, train);
	}

}