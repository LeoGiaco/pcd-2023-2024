package pcd.lab03.liveness.accounts_exercise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountManager {
	
	private final Account[] accounts;
	private final Lock[] accountLocks;

	public AccountManager(int nAccounts, int amount){
		accounts = new Account[nAccounts];
		accountLocks = new Lock[nAccounts];
		for (int i = 0; i < accounts.length; i++){
			accounts[i] = new Account(amount);
			accountLocks[i] = new ReentrantLock();
		}
	}
	
	public synchronized void transferMoney(int from,	int to, int amount) throws InsufficientBalanceException {
		var fromAcc = accounts[from];
		var fromLock = accountLocks[from];
		var toAcc = accounts[to];
		var toLock = accountLocks[to];
		while (true) {
			if (fromLock.tryLock()) {
				try {
					if (toLock.tryLock()) {
						try {
							if (fromAcc.getBalance() < amount) {
								throw new InsufficientBalanceException();
							} else {
								fromAcc.debit(amount);
								toAcc.credit(amount);
								return;
							}
						} finally {
							toLock.unlock();
						}
					}
				} finally {
					fromLock.unlock();
				}
			}
		}
	}
	
	public int getNumAccounts() {
		return accounts.length;
	}
}

