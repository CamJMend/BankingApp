package com.project;

import org.testng.Assert;
import org.testng.annotations.*;

public class BankAccountTest {

    private BankAccount account;

    @BeforeSuite
    public void beforeSuiteSetup() {
        System.out.println("Initializing test suite resources");
    }

    @AfterSuite
    public void afterSuiteTeardown() {
        System.out.println("Cleaning up suite resources");
    }

    @BeforeClass
    public void beforeClassSetup() {
        System.out.println("Preparing to run tests in BankAccountTest class");
    }

    @AfterClass
    public void afterClassSummary() {
        System.out.println("Completed all tests in BankAccountTest class");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        System.out.println("Setting up a fresh account before each test method");
        account = new BankAccount(500.0);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        System.out.println("Cleaning up after test method");
        account = null;
    }

    @Test(groups = {"positive-tests"}, priority = 1)
    public void testDeposit() {
        account.deposit(200.0);
        Assert.assertEquals(account.getBalance(), 700.0, "Balance should be updated correctly after deposit");
    }

    @Test(groups = {"positive-tests"}, priority = 2)
    public void testWithdraw() {
        account.withdraw(100.0);
        Assert.assertEquals(account.getBalance(), 400.0, "Balance should be updated correctly after withdrawal");
    }

    @Test(groups = {"negative-tests"}, expectedExceptions = IllegalArgumentException.class)
    public void testNegativeDeposit() {
        account.deposit(-50.0);
    }

    @Test(groups = {"negative-tests"}, expectedExceptions = IllegalArgumentException.class)
    public void testNegativeWithdrawal() {
        account.withdraw(-30.0);
    }

    @Test(groups = {"negative-tests"})
    public void testOverdraft() {
        try {
            account.withdraw(1000.0);
            Assert.fail("Should have thrown exception for insufficient funds");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Insufficient funds");
        }
    }

    @Test(groups = {"dependency-tests"})
    public void loginTest() {
        System.out.println("Logging in");
    }

    @Test(groups = {"dependency-tests"}, dependsOnMethods = "loginTest")
    public void transferFundsTest() {
        System.out.println("Transferring funds after login");
    }

    @Parameters({"initialAmount", "depositAmount"})
    @Test(groups = {"parameterized-tests"})
    public void testParameterizedDeposit(
            @Optional("1000.0") double initialAmount,
            @Optional("500.0") double depositAmount) {

        BankAccount paramAccount = new BankAccount(initialAmount);
        paramAccount.deposit(depositAmount);
        Assert.assertEquals(paramAccount.getBalance(), initialAmount + depositAmount,
                "Balance should be updated correctly after deposit");
    }

    @DataProvider(name = "depositData")
    public Object[][] depositData() {
        return new Object[][] {
                {100.0, 50.0, 150.0},
                {500.0, 500.0, 1000.0},
                {0.0, 100.0, 100.0}
        };
    }

    @Test(dataProvider = "depositData", groups = {"data-provider-tests"})
    public void testDepositWithDataProvider(double initial, double deposit, double expected) {
        BankAccount acc = new BankAccount(initial);
        acc.deposit(deposit);
        Assert.assertEquals(acc.getBalance(), expected);
    }

    @Test(priority = 0)
    public void openAppTest() {
        System.out.println("Opening banking app...");
    }

    @Test(priority = 1)
    public void loginUserTest() {
        System.out.println("Logging in as user...");
    }
}
