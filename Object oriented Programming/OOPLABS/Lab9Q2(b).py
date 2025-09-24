class BankAccount:
    def __init__(self, IBAN, account_number, available_funds):
        """Initialize the bank account with IBAN, account number, and available funds."""
        self.IBAN = IBAN
        self.account_number = account_number
        self.available_funds = available_funds
        self.transactions = []  # List to store the last 5 transactions

    def deposit(self, amount):
        """Deposit money into the account and record the transaction."""
        if amount <= 0:
            print("Deposit amount must be positive.")
            return
        self.available_funds += amount
        self._record_transaction(f"Deposited €{amount}")

    def withdraw(self, amount):
        """Withdraw money from the account if sufficient funds are available."""
        if amount <= 0:
            print("Withdrawal amount must be positive.")
            return
        if amount > self.available_funds:
            print("Insufficient funds!")
            return
        self.available_funds -= amount
        self._record_transaction(f"Withdrew €{amount}")

    def _record_transaction(self, transaction):
        """Record a transaction and ensure that only the last 5 transactions are kept."""
        self.transactions.append(transaction)
        if len(self.transactions) > 5:
            self.transactions.pop(0)  # Remove the oldest transaction if there are more than 5

    def get_balance(self):
        """Return the current balance of the account."""
        return self.available_funds

    def get_transactions(self):
        """Return the list of the last 5 transactions."""
        return self.transactions

    def get_IBAN(self):
        """Return the IBAN of the account."""
        return self.IBAN

    def get_account_number(self):
        """Return the account number."""
        return self.account_number


# Example usage
account = BankAccount("DE89370400440532013000", 12345678, 5000)

# Deposit some money
account.deposit(1000)
account.deposit(200)

# Withdraw some money
account.withdraw(300)
account.withdraw(1500)

# Check balance and transactions
print(account.get_IBAN())  # Correct object 'account'
print(account.get_account_number())  # Correct object 'account'
print(account.get_balance())  # Correct method name is get_balance(), not get_available_funds()
print("Balance:", account.get_balance())  # 4200
print("Last Transactions:", account.get_transactions())  # List of last 5 transactions

# Try depositing and withdrawing with invalid amounts
account.deposit(-500)  # Invalid deposit
account.withdraw(10000)  # Insufficient funds
